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

    fun showInicio() { /* igual que antes */
        val p = JPanel(BorderLayout()).apply { background = Color.WHITE }
        val titulo = JLabel(
            "<html><div style='text-align:center'><h2>Bienvenido al Sistema de Clínica</h2><div>Gestioná Pacientes, Médicos y Turnos</div></div></html>",
            SwingConstants.CENTER
        ).apply {
            font = Font("Arial", Font.PLAIN, 18)
            border = EmptyBorder(20, 20, 20, 20)
        }
        p.add(titulo, BorderLayout.CENTER)
        limpiarYAgregar(p)
    }

    fun showPacientes() {
        println("PanelManager -> showPacientes() -> clinica id=${System.identityHashCode(clinica)}")
        val texto = clinica.listarPacientes()
        JOptionPane.showMessageDialog(menuPrincipal, texto, "Pacientes", JOptionPane.INFORMATION_MESSAGE)
    }

    fun showMedicos() {
        println("PanelManager -> showMedicos() -> clinica id=${System.identityHashCode(clinica)}")
        val texto = clinica.listarMedicos()
        JOptionPane.showMessageDialog(menuPrincipal, texto, "Médicos", JOptionPane.INFORMATION_MESSAGE)
    }

    fun showTurnos() {
        println("PanelManager -> showTurnos() -> clinica id=${System.identityHashCode(clinica)}")
        val texto = clinica.listarTurnos()
        JOptionPane.showMessageDialog(menuPrincipal, texto, "Turnos", JOptionPane.INFORMATION_MESSAGE)
    }
}