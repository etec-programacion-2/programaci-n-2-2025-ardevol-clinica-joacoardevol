package org.example

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner

data class Turno(
    val id: String,
    val paciente: Paciente,
    val medico: Medico,
    val fechaHora: LocalDateTime
) {
    companion object {
        fun leerFechaHora(): LocalDateTime {
            val reader = Scanner(System.`in`)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            while (true) {
                print("Ingrese fecha y hora (formato dd/MM/yyyy HH:mm): ")
                val input = reader.nextLine()
                try {
                    return LocalDateTime.parse(input, formatter)
                } catch (e: Exception) {
                    println("⚠️ Formato inválido. Intente nuevamente.")
                }
            }
        }
    }
}
