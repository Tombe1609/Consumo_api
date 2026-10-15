package com.example.mealsearchapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsearchapp.databinding.ItemMealBinding

// Muestra una tarjeta por cada receta encontrada.
class MealAdapter : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private var recetas: List<Meal> = emptyList()

    class MealViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun actualizar(nuevasRecetas: List<Meal>) {
        recetas = nuevasRecetas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun getItemCount(): Int = recetas.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = recetas[position]
        val vista = holder.binding

        // Imagen con Coil
        if (meal.imagenUrl.isNullOrBlank()) {
            vista.ivFoto.setImageDrawable(null)
        } else {
            vista.ivFoto.load(meal.imagenUrl)
        }

        vista.tvNombre.text = meal.nombre
        vista.tvId.text = "ID: ${meal.id}"
        vista.tvCategoria.text = "📂 Categoría: ${meal.categoria ?: "Sin categoría"}"
        vista.tvArea.text = "🌍 País de origen: ${meal.area ?: "Sin dato"}"
        vista.tvTags.text = "🏷️ Tags: ${meal.tags?.takeIf { it.isNotBlank() } ?: "Sin tags"}"
        vista.tvIngredientes.text = "🧂 Ingredientes:\n${meal.ingredientesListados()}"
    }
}
