package org.example

import javax.swing.JOptionPane
import javax.swing.JOptionPane.*

data class Medico(
    val matricula: String,
    val nombre: String,
    val apellido: String,
    val especialidad: Especialidad
) {
    override fun toString(): String {
        return "Médico: $nombre $apellido (Matrícula: $matricula, Especialidad: $especialidad)"
    }

    companion object {
        fun leerMatriculaGrafico(): String {
            while (true) {
                val input = JOptionPane.showInputDialog("Ingrese matrícula (solo números):")
                if (input != null && input.matches(Regex("^\\d+$"))) return input
                JOptionPane.showMessageDialog(null, "⚠️ Matrícula inválida. Debe contener solo números.")
            }
        }

        fun seleccionarEspecialidadGrafico(): Especialidad {
            val opciones = Especialidad.values().map { it.toString() }.toTypedArray()
            val seleccion = JOptionPane.showInputDialog(null, "Seleccione especialidad:", "Especialidad", QUESTION_MESSAGE, null, opciones, opciones[0]) as String
            return Especialidad.valueOf(seleccion.uppercase())
        }
    }
}