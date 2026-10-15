package com.example.mealsearchapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsearchapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = MealAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRecetas.layoutManager = LinearLayoutManager(this)
        binding.rvRecetas.adapter = adapter

        binding.btnBuscar.setOnClickListener {
            val texto = binding.etReceta.text.toString().trim()
            if (texto.isEmpty()) {
                // Campo vacío: no se hace ninguna petición
                Toast.makeText(this, "Escribe el nombre de una receta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            cargarRecetas("No se encontraron recetas para \u0027$texto\u0027. Recuerda que la búsqueda es en inglés (ej: chicken).") {
                RetrofitInstance.api.buscarPorNombre(texto)
            }
        }

        binding.btnAleatorio.setOnClickListener {
            cargarRecetas("No se pudo obtener una receta aleatoria. Intenta de nuevo.") {
                RetrofitInstance.api.recetaAleatoria()
            }
        }
    }

    // Ejecuta una consulta a la API en segundo plano y muestra el resultado o el error.
    private fun cargarRecetas(mensajeSinResultados: String, consulta: suspend () -> MealResponse) {
        lifecycleScope.launch {
            limpiar()
            mostrarCargando(true)
            try {
                // meals puede ser null si no hay resultados
                val recetas = consulta().meals.orEmpty()
                if (recetas.isEmpty()) {
                    mostrarMensaje("❌ $mensajeSinResultados")
                } else {
                    adapter.actualizar(recetas)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: HttpException) {
                mostrarMensaje("❌ Error del servidor (código ${e.code()}). Intenta más tarde.")
            } catch (e: IOException) {
                mostrarMensaje("❌ Sin conexión. Revisa tu Internet e intenta de nuevo.")
            } catch (e: Exception) {
                mostrarMensaje("❌ Ocurrió un error inesperado: ${e.message}")
            } finally {
                mostrarCargando(false)
            }
        }
    }

    private fun mostrarCargando(cargando: Boolean) {
        binding.progressBar.visibility = if (cargando) View.VISIBLE else View.GONE
        binding.btnBuscar.isEnabled = !cargando
        binding.btnAleatorio.isEnabled = !cargando
    }

    private fun mostrarMensaje(texto: String) {
        binding.tvError.text = texto
        binding.tvError.visibility = View.VISIBLE
    }

    private fun limpiar() {
        binding.tvError.text = ""
        binding.tvError.visibility = View.GONE
        adapter.actualizar(emptyList())
    }
}
