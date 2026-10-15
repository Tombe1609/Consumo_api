package com.example.mealsearchapp

import com.google.gson.annotations.SerializedName

// Respuesta raíz de la API: contiene la lista de recetas.
// "meals" es null (no una lista vacía) cuando no hay resultados.
data class MealResponse(
    val meals: List<Meal>?
)

// Una receta tal como la devuelve TheMealDB.
data class Meal(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val nombre: String,
    @SerializedName("strCategory") val categoria: String?,
    @SerializedName("strArea") val area: String?,
    @SerializedName("strInstructions") val instrucciones: String?,
    @SerializedName("strMealThumb") val imagenUrl: String?,
    @SerializedName("strYoutube") val youtube: String?,
    @SerializedName("strTags") val tags: String?,

    // Ingredientes: la API usa campos separados (1..20) en lugar de un arreglo
    @SerializedName("strIngredient1") val ing1: String?,
    @SerializedName("strIngredient2") val ing2: String?,
    @SerializedName("strIngredient3") val ing3: String?,
    @SerializedName("strIngredient4") val ing4: String?,
    @SerializedName("strIngredient5") val ing5: String?,
    @SerializedName("strIngredient6") val ing6: String?,
    @SerializedName("strIngredient7") val ing7: String?,
    @SerializedName("strIngredient8") val ing8: String?,
    @SerializedName("strIngredient9") val ing9: String?,
    @SerializedName("strIngredient10") val ing10: String?,
    @SerializedName("strIngredient11") val ing11: String?,
    @SerializedName("strIngredient12") val ing12: String?,
    @SerializedName("strIngredient13") val ing13: String?,
    @SerializedName("strIngredient14") val ing14: String?,
    @SerializedName("strIngredient15") val ing15: String?,
    @SerializedName("strIngredient16") val ing16: String?,
    @SerializedName("strIngredient17") val ing17: String?,
    @SerializedName("strIngredient18") val ing18: String?,
    @SerializedName("strIngredient19") val ing19: String?,
    @SerializedName("strIngredient20") val ing20: String?,

    // Cantidades: strMeasure1..20 corresponden al ingrediente con el mismo número
    @SerializedName("strMeasure1") val med1: String?,
    @SerializedName("strMeasure2") val med2: String?,
    @SerializedName("strMeasure3") val med3: String?,
    @SerializedName("strMeasure4") val med4: String?,
    @SerializedName("strMeasure5") val med5: String?,
    @SerializedName("strMeasure6") val med6: String?,
    @SerializedName("strMeasure7") val med7: String?,
    @SerializedName("strMeasure8") val med8: String?,
    @SerializedName("strMeasure9") val med9: String?,
    @SerializedName("strMeasure10") val med10: String?,
    @SerializedName("strMeasure11") val med11: String?,
    @SerializedName("strMeasure12") val med12: String?,
    @SerializedName("strMeasure13") val med13: String?,
    @SerializedName("strMeasure14") val med14: String?,
    @SerializedName("strMeasure15") val med15: String?,
    @SerializedName("strMeasure16") val med16: String?,
    @SerializedName("strMeasure17") val med17: String?,
    @SerializedName("strMeasure18") val med18: String?,
    @SerializedName("strMeasure19") val med19: String?,
    @SerializedName("strMeasure20") val med20: String?
) {
    /**
     * Une cada ingrediente no vacío con su cantidad, una línea por ingrediente.
     * Ejemplo: "• penne rigate: 1 pound"
     */
    fun ingredientesListados(): String {
        val ingredientes = listOf(
            ing1, ing2, ing3, ing4, ing5, ing6, ing7, ing8, ing9, ing10,
            ing11, ing12, ing13, ing14, ing15, ing16, ing17, ing18, ing19, ing20
        )
        val medidas = listOf(
            med1, med2, med3, med4, med5, med6, med7, med8, med9, med10,
            med11, med12, med13, med14, med15, med16, med17, med18, med19, med20
        )

        val lineas = ingredientes.indices
            .filter { !ingredientes[it].isNullOrBlank() }
            .map { i ->
                val ingrediente = ingredientes[i].orEmpty().trim()
                val medida = medidas[i].orEmpty().trim()
                if (medida.isEmpty()) "• $ingrediente" else "• $ingrediente: $medida"
            }

        return if (lineas.isEmpty()) "Sin ingredientes disponibles" else lineas.joinToString("\n")
    }
}
