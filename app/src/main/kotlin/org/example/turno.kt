package org.example

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Turno(
    val id: String,
    var paciente: Paciente?,
    val medico: Medico,
    val fechaHora: LocalDateTime,
    var ocupado: Boolean = false
) {
    fun mostrar(): String {
        val estado = if (ocupado) "❌ Ocupado" else "✅ Disponible"
        val fecha = fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        return "[$estado] $fecha"
    }
}
