package org.example

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.JOptionPane

data class Turno(val id: String, val paciente: Paciente, val medico: Medico, val fechaHora: LocalDateTime) {
    override fun toString(): String {
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        return "Turno #$id → ${paciente.nombre} ${paciente.apellido} con ${medico.nombre} ${medico.apellido} el ${fechaHora.format(formato)}"
    }
}
