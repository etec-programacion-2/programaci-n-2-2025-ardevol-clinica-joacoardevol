package org.example

import javax.swing.*

fun main() {
    SwingUtilities.invokeLater {
        val ventana = MenuPrincipal()
        ventana.isVisible = true
    }
}
