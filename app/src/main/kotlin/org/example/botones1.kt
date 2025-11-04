package org.example

import java.awt.*
import javax.swing.*
import javax.swing.border.EmptyBorder

class UIConfig(
    private val menuPrincipal: MenuPrincipal,
    private val clinica: Clinica,
    private val panelManager: PanelManager
) {

    fun configureUI() {
        menuPrincipal.title = "Sistema de Clínica"
        menuPrincipal.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        menuPrincipal.setSize(1000, 650)
        menuPrincipal.setLocationRelativeTo(null)
        menuPrincipal.layout = BorderLayout()

        configurarBarraSuperior()
        configurarPanelMenu()
    }

    private fun configurarBarraSuperior() {
        val topBar = JPanel(BorderLayout())
        topBar.background = Color(33, 150, 243)
        topBar.border = EmptyBorder(8, 12, 8, 12)

        val lblTitle = JLabel("Sistema de Clínica", SwingConstants.LEFT).apply {
            foreground = Color.WHITE
            font = Font("Arial", Font.BOLD, 18)
        }

        val lblRight = JLabel("Buscar  |  Usuario", SwingConstants.RIGHT).apply {
            foreground = Color.WHITE
            font = Font("Arial", Font.PLAIN, 12)
        }

        topBar.add(lblTitle, BorderLayout.WEST)
        topBar.add(lblRight, BorderLayout.EAST)
        menuPrincipal.add(topBar, BorderLayout.NORTH)
    }

    private fun configurarPanelMenu() {
        val panelMenu = JPanel()
        panelMenu.layout = BoxLayout(panelMenu, BoxLayout.Y_AXIS)
        panelMenu.background = Color(245, 247, 250)
        panelMenu.border = EmptyBorder(12, 12, 12, 12)
        panelMenu.preferredSize = Dimension(220, 0)

        val header = JPanel(BorderLayout()).apply {
            background = panelMenu.background
            border = EmptyBorder(8, 4, 16, 4)
        }

        val lblLogo = JLabel(
            "<html><div style='text-align:center'><b>CLÍNICA</b><br/><span style='font-size:10px'>Gestión</span></div></html>",
            SwingConstants.CENTER
        ).apply { font = Font("Arial", Font.BOLD, 16) }

        header.add(lblLogo, BorderLayout.CENTER)
        panelMenu.add(header)

        val botones = listOf("Inicio", "Pacientes", "Médicos", "Turnos", "Salir")
        botones.forEach { texto ->
            val boton = JButton(texto).apply {
                maximumSize = Dimension(Int.MAX_VALUE, 44)
                alignmentX = Component.CENTER_ALIGNMENT
                background = Color.WHITE
                font = Font("Arial", Font.PLAIN, 14)
                border = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color(220, 220, 220)),
                    EmptyBorder(6, 12, 6, 12)
                )
            }
            panelMenu.add(boton)
            panelMenu.add(Box.createRigidArea(Dimension(0, 6)))
            boton.addActionListener {
                when (texto) {
                    "Inicio" -> panelManager.showInicio()
                    "Pacientes" -> panelManager.showPacientes()
                    "Médicos" -> panelManager.showMedicos()
                    "Turnos" -> panelManager.showTurnos()
                    "Salir" -> System.exit(0)
                }
            }
        }

        panelMenu.add(Box.createRigidArea(Dimension(0, 20)))
        panelMenu.add(crearBotonLateral("Registrar Paciente") {
            clinica.registrarPacienteGrafico(panelManager)
        })
        panelMenu.add(Box.createRigidArea(Dimension(0, 6)))
        panelMenu.add(crearBotonLateral("Registrar Médico") {
            clinica.registrarMedicoGrafico(panelManager)
        })
        panelMenu.add(Box.createRigidArea(Dimension(0, 6)))
        panelMenu.add(crearBotonLateral("Registrar Turno") {
            clinica.registrarTurnoGrafico(panelManager, menuPrincipal)
        })

        panelMenu.add(Box.createVerticalGlue())
        menuPrincipal.add(panelMenu, BorderLayout.WEST)
    }

    private fun crearBotonLateral(texto: String, accion: () -> Unit): JButton {
        return JButton(texto).apply {
            maximumSize = Dimension(Int.MAX_VALUE, 36)
            alignmentX = Component.CENTER_ALIGNMENT
            background = Color(33, 150, 243)
            foreground = Color.WHITE
            font = Font("Arial", Font.BOLD, 13)
            border = BorderFactory.createEmptyBorder(6, 12, 6, 12)
            isFocusPainted = false
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            addActionListener { accion() }
        }
    }
}
