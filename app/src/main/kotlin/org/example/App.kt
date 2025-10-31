package org.example

import javax.swing.*

class MenuPrincipal : JFrame() {

    private val clinica = Clinica()
    private val panelManager = PanelManager(this, clinica)
    private val uiConfig = UIConfig(this, clinica, panelManager)

    init {
        uiConfig.configureUI()
        panelManager.showInicio()
    }
}

fun main() {
    SwingUtilities.invokeLater {
        val ventana = MenuPrincipal()
        ventana.isVisible = true
    }
}
