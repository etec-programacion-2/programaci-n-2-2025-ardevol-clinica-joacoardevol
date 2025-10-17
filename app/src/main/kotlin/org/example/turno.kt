package org.example

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.JOptionPane

data class Turno(
    val id: String,
    val paciente: Paciente,
    val medico: Medico,
    val fechaHora: LocalDateTime
) {
    override fun toString(): String {
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        return "Turno #$id → ${paciente.nombre} ${paciente.apellido} con ${medico.nombre} ${medico.apellido} el ${fechaHora.format(formato)}"
    }

    companion object {
        fun leerFechaHoraGrafico(): LocalDateTime {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            while (true) {
                val input = JOptionPane.showInputDialog("Ingrese fecha y hora (formato dd/MM/yyyy HH:mm):")
                if (input != null) {
                    try {
                        return LocalDateTime.parse(input, formatter)
                    } catch (e: Exception) {
                        JOptionPane.showMessageDialog(null, "⚠️ Formato inválido. Intente nuevamente.")
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar una fecha y hora.")
                }
            }
        }
    }
}