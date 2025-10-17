package org.example

import javax.swing.JOptionPane

data class Paciente(
    val dni: String,
    val nombre: String,
    val apellido: String
) {
    override fun toString(): String {
        return "Paciente: $nombre $apellido (DNI: $dni)"
    }

    companion object {
        fun leerDniGrafico(): String {
            while (true) {
                val input = JOptionPane.showInputDialog("Ingrese DNI (solo números):")
                if (input != null && input.matches(Regex("^\\d+$"))) return input
                JOptionPane.showMessageDialog(null, "⚠️ DNI inválido. Debe contener solo números.")
            }
        }

        fun leerNombreOApellidoGrafico(campo: String): String {
            while (true) {
                val input = JOptionPane.showInputDialog("Ingrese $campo (sin números):")
                if (input != null && input.matches(Regex("^[^\\d]+$"))) return input
                JOptionPane.showMessageDialog(null, "⚠️ $campo inválido. No debe contener números.")
            }
        }
    }
}