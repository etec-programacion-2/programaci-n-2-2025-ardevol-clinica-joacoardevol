package org.example

import java.awt.*
import javax.swing.*
import javax.swing.border.EmptyBorder

class PanelManager(private val menuPrincipal: MenuPrincipal, private val clinica: Clinica) {

    private val panelContenido = JPanel(BorderLayout()).apply { background = Color.WHITE }

    init {
        menuPrincipal.contentPane.add(panelContenido, BorderLayout.CENTER)
    }

    private fun limpiarYAgregar(panel: JPanel) {
        panelContenido.removeAll()
        panelContenido.add(panel, BorderLayout.CENTER)
        panelContenido.revalidate()
        panelContenido.repaint()
    }

    fun showInicio() {
        val p = JPanel(BorderLayout()).apply { background = Color.WHITE }
        val titulo = JLabel(
            "<html><div style='text-align:center'><h2>Bienvenido al Sistema de Clínica</h2><div>Gestioná Pacientes, Médicos y Turnos</div></div></html>",
            SwingConstants.CENTER
        ).apply { font = Font("Arial", Font.PLAIN, 18) }
        p.add(titulo, BorderLayout.CENTER)
        limpiarYAgregar(p)
    }

    fun showPacientes() {
        val panel = JPanel(BorderLayout()).apply {
            background = Color.WHITE
            border = EmptyBorder(10, 10, 10, 10)
        }
        val verTexto = VerTextoPanel("Pacientes", clinica.listarPacientes())
        panel.add(verTexto, BorderLayout.CENTER)

        val panelBoton = JPanel(FlowLayout(FlowLayout.RIGHT)).apply { background = Color.WHITE }
        val btnRegistrar = JButton("➕ Registrar Nuevo Paciente").apply {
            background = Color(76, 175, 80)
            foreground = Color.WHITE
            font = Font("Arial", Font.BOLD, 14)
            border = EmptyBorder(12, 24, 12, 24)
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            isFocusPainted = false
            addActionListener { clinica.registrarPacienteGrafico(this@PanelManager) }
        }

        panelBoton.add(btnRegistrar)
        panel.add(panelBoton, BorderLayout.SOUTH)
        limpiarYAgregar(panel)
    }

    fun showMedicos() {
        val panel = JPanel(BorderLayout()).apply {
            background = Color.WHITE
            border = EmptyBorder(10, 10, 10, 10)
        }
        val verTexto = VerTextoPanel("Médicos", clinica.listarMedicos())
        panel.add(verTexto, BorderLayout.CENTER)

        val panelBoton = JPanel(FlowLayout(FlowLayout.RIGHT)).apply { background = Color.WHITE }
        val btnRegistrar = JButton("➕ Registrar Nuevo Médico").apply {
            background = Color(33, 150, 243)
            foreground = Color.WHITE
            font = Font("Arial", Font.BOLD, 14)
            border = EmptyBorder(12, 24, 12, 24)
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            isFocusPainted = false
            addActionListener { clinica.registrarMedicoGrafico(this@PanelManager) }
        }

        panelBoton.add(btnRegistrar)
        panel.add(panelBoton, BorderLayout.SOUTH)
        limpiarYAgregar(panel)
    }

    fun showTurnos() {
        val panel = JPanel(BorderLayout()).apply {
            background = Color.WHITE
            border = EmptyBorder(10, 10, 10, 10)
        }
        val verTexto = VerTextoPanel("Turnos", clinica.listarTurnos())
        panel.add(verTexto, BorderLayout.CENTER)

        val panelBoton = JPanel(FlowLayout(FlowLayout.RIGHT)).apply { background = Color.WHITE }
        val btnRegistrar = JButton("➕ Registrar Nuevo Turno").apply {
            background = Color(255, 152, 0)
            foreground = Color.WHITE
            font = Font("Arial", Font.BOLD, 14)
            border = EmptyBorder(12, 24, 12, 24)
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            isFocusPainted = false
            addActionListener { clinica.registrarTurnoGrafico(this@PanelManager, menuPrincipal) }
        }

        panelBoton.add(btnRegistrar)
        panel.add(panelBoton, BorderLayout.SOUTH)
        limpiarYAgregar(panel)
    }
}
