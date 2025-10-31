package org.example

import javax.swing.*
import java.awt.*
import javax.swing.border.EmptyBorder

class VerTextoPanel(titulo: String, contenido: String) : JPanel() {
    init {
        layout = BorderLayout()
        background = Color.WHITE

        val tituloLabel = JLabel(titulo, SwingConstants.LEFT).apply {
            font = Font("Arial", Font.BOLD, 16)
            border = EmptyBorder(6, 6, 6, 6)
        }

        val areaTexto = JTextArea(contenido).apply {
            isEditable = false
            lineWrap = true
            wrapStyleWord = true
            font = Font("Monospaced", Font.PLAIN, 12)
            border = EmptyBorder(8, 8, 8, 8)
        }

        val scroll = JScrollPane(areaTexto)
        scroll.border = BorderFactory.createLineBorder(Color(230, 230, 230))

        add(tituloLabel, BorderLayout.NORTH)
        add(scroll, BorderLayout.CENTER)
    }
}
