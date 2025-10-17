package org.example

import javax.swing.*
import java.awt.*
import javax.swing.border.EmptyBorder

class VerTextoPanel(titulo: String, contenido: String) : JPanel() {
    init {
        layout = BorderLayout()
        background = Color.WHITE

        val tituloLabel = JLabel(titulo, SwingConstants.LEFT)
        tituloLabel.font = Font("Arial", Font.BOLD, 16)
        tituloLabel.border = EmptyBorder(6, 6, 6, 6)

        val areaTexto = JTextArea(contenido)
        areaTexto.isEditable = false
        areaTexto.lineWrap = true
        areaTexto.wrapStyleWord = true
        areaTexto.font = Font("Monospaced", Font.PLAIN, 12)
        areaTexto.border = EmptyBorder(8, 8, 8, 8)

        val scroll = JScrollPane(areaTexto)
        scroll.border = BorderFactory.createLineBorder(Color(230, 230, 230))

        add(tituloLabel, BorderLayout.NORTH)
        add(scroll, BorderLayout.CENTER)
    }
}
