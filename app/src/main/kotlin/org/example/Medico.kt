package org.example

import java.util.Scanner

data class Medico(
    val matricula: String,
    val nombre: String,
    val apellido: String,
    val especialidad: Especialidad
) {
    companion object {
        fun leerMatricula(): String {
            val reader = Scanner(System.`in`)
            while (true) {
                print("Ingrese matrícula (solo números): ")
                val matricula = reader.nextLine()
                if (matricula.matches(Regex("^\\d+$"))) return matricula
                println("⚠️ Matrícula inválida. Debe contener solo números.")
            }
        }

        fun seleccionarEspecialidad(): Especialidad {
            val reader = Scanner(System.`in`)
            println("Seleccione especialidad:")
            Especialidad.values().forEachIndexed { index, esp ->
                println("${index + 1}. $esp")
            }

            var opcion: Int
            while (true) {
                print("Opción: ")
                opcion = reader.nextLine().toIntOrNull() ?: -1
                if (opcion in 1..Especialidad.values().size) break
                println("⚠️ Opción inválida, intente nuevamente.")
            }

            return Especialidad.values()[opcion - 1]
        }
    }
}
