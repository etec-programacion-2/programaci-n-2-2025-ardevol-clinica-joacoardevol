package org.example

import javax.swing.*
import java.awt.*
import javax.swing.border.EmptyBorder
import com.github.lgooddatepicker.components.*

object TurnoRegistrar {
    fun registrar(clinica: Clinica, panelManager: PanelManager, menuPrincipal: MenuPrincipal) {
        val pacientesStr = clinica.listarPacientes()
        val pacienteIndexStr = JOptionPane.showInputDialog(menuPrincipal, "Seleccione paciente (número):\n$pacientesStr")
        val pacienteIndex = pacienteIndexStr?.toIntOrNull()?.minus(1) ?: -1
        val paciente = clinica.obtenerPaciente(pacienteIndex) ?: return

        val medicosStr = clinica.listarMedicos()
        val medicoIndexStr = JOptionPane.showInputDialog(menuPrincipal, "Seleccione médico (número):\n$medicosStr")
        val medicoIndex = medicoIndexStr?.toIntOrNull()?.minus(1) ?: -1
        val medico = clinica.obtenerMedico(medicoIndex) ?: return

        val datePicker = DatePicker().apply { date = java.time.LocalDate.now() }
        val timePicker = TimePicker().apply { isFocusable = true }

        val dialog = JDialog(menuPrincipal, "Registrar Turno", true).apply {
            setSize(450, 250)
            setLocationRelativeTo(menuPrincipal)
            layout = BorderLayout()
        }

        val formPanel = JPanel(GridLayout(4, 2, 10, 10)).apply {
            border = EmptyBorder(15, 15, 15, 15)
            background = Color.WHITE
        }

        formPanel.add(JLabel("Paciente:"))
        formPanel.add(JLabel("${paciente.nombre} ${paciente.apellido}"))
        formPanel.add(JLabel("Médico:"))
        formPanel.add(JLabel("${medico.nombre} ${medico.apellido}"))
        formPanel.add(JLabel("Seleccione Fecha:"))
        formPanel.add(datePicker)
        formPanel.add(JLabel("Seleccione Hora:"))
        formPanel.add(timePicker)

        val btnGuardar = JButton("Guardar Turno").apply {
            background = Color(33, 150, 243)
            foreground = Color.WHITE
            addActionListener {
                val fecha = datePicker.date ?: return@addActionListener
                val hora = timePicker.time ?: return@addActionListener
                val fechaHora = java.time.LocalDateTime.of(fecha, hora)
                val turno = Turno("T${System.currentTimeMillis()}", paciente, medico, fechaHora)
                clinica.agregarTurno(turno)
                JOptionPane.showMessageDialog(menuPrincipal, "✅ Turno registrado exitosamente:\n\n$turno")
                dialog.dispose()
                panelManager.showTurnos()
                menuPrincipal.revalidate()
                menuPrincipal.repaint()
            }
        }

        val btnCancelar = JButton("Cancelar").apply { addActionListener { dialog.dispose() } }

        val panelBotones = JPanel(FlowLayout(FlowLayout.RIGHT, 10, 10)).apply {
            background = Color.WHITE
            add(btnCancelar)
            add(btnGuardar)
        }

        dialog.add(formPanel, BorderLayout.CENTER)
        dialog.add(panelBotones, BorderLayout.SOUTH)
        dialog.isVisible = true
    }
}
