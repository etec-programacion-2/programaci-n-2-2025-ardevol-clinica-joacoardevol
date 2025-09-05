package org.example

import java.util.Scanner

data class Paciente(
    val dni: String,
    val nombre: String,
    val apellido: String
) {
    companion object {
        fun leerDni(): String {
            val reader = Scanner(System.`in`)
            while (true) {
                print("Ingrese DNI (solo números): ")
                val dni = reader.nextLine()
                if (dni.matches(Regex("^\\d+$"))) return dni
                println("⚠️ DNI inválido. Debe contener solo números.")
            }
        }

        fun leerNombreOApellido(campo: String): String {
            val reader = Scanner(System.`in`)
            while (true) {
                print("Ingrese $campo (sin números): ")
                val texto = reader.nextLine()
                if (texto.matches(Regex("^[^\\d]+$"))) return texto
                println("⚠️ $campo inválido. No debe contener números.")
            }
        }
    }
}
