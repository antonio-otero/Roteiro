package com.example.roteiro

/*
 * DATA CLASS:
 * En Kotlin, una 'data class' es una clase diseñada específicamente para contener datos/información.
 * Al anteponer la palabra clave 'data', el compilador genera automáticamente:
 * 1. equals() / hashCode(): Comparación de instancias por valor de sus campos (y no por referencia de memoria).
 * 2. toString(): Representación legible en formato String -> "Lugar(nome=..., concello=..., descricion=...)".
 * 3. componentN(): Funciones de desestructuración (permite hacer 'val (nombre, concello) = lugar').
 * 4. copy(): Permite duplicar un objeto modificando solo algunas propiedades de forma inmutable.
 *
 * ¿CUÁNDO SE USA?
 * Se utiliza para definir modelos de datos (DTOs o entidades del dominio) que representan conceptos del negocio
 * (en este caso, un punto de interés turístico con sus propiedades inmutables nome, concello y descricion).
 */
data class Lugar(
    val nome: String,
    val concello: String,
    val descricion: String
)
