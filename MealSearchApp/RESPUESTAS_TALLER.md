# Respuestas — Taller API REST · TheMealDB

## Sección A — Datos de las recetas

**1. Busca "arrabiata". ¿Categoría?**
`Vegetarian` 

**2. Busca "arrabiata". ¿País de origen?**
`Italian` 
**3. Busca "donuts". ¿Categoría?**
no se encuentran recetas para donuts

**4. Busca "donuts". ¿País?**
no se encuentran recetas para donuts

**5. Busca "lasagne". ¿Tags tal como los muestra la app?**
sin tags
## Sección B — IDs y datos específicos

**6. Busca "carbonara". ¿ID numérico?**
 52982
**7. Busca "pancakes". ¿País?**
United States

**8. Busca "pad thai". ¿Categoría?**
Seafood
## Sección C — Código y comprensión

**9. ¿Por qué `meals` es `List<Meal>?` (con signo de interrogación)?**
Porque cuando la API no encuentra recetas devuelve `"meals": null` (no una lista vacía). El `?` indica que el campo **puede ser `null`** y obliga a revisarlo antes de usarlo. En el proyecto: `consulta().meals.orEmpty()` convierte el `null` en lista vacía y así se muestra el mensaje "No se encontraron recetas…" en lugar de cerrar la app.

**10. ¿Para qué sirve `@SerializedName("strMeal")`?**
Le dice a **Gson** cómo se llama el campo en el **JSON** para asignarlo a la propiedad Kotlin. El JSON usa `strMeal`, pero en Kotlin la propiedad se llama `nombre`: `@SerializedName("strMeal") val nombre: String`. Sin la anotación, Gson buscaría un campo `nombre` que no existe y quedaría en `null`.

**11. ¿Diferencia entre `@Query` y `@Path`? ¿Cuál se usa y por qué?**
`@Path` reemplaza una parte **dentro de la ruta** de la URL (por ejemplo `@GET("meals/{id}")` → `meals/52771`). `@Query` agrega un **parámetro después del `?`** (`search.php?s=arrabiata`). En este taller se usa **`@Query`** porque la ruta es fija (`search.php`) y lo que cambia es el parámetro `s`: `@Query("s") nombre: String`.

**12. (Desafío) Usa 🎲 SORPRESA hasta encontrar una receta de categoría "Seafood". ¿Nombre de la receta?**
Escovitch Fish 
