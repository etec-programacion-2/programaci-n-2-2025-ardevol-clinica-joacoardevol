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
        val paciente = clinica.obtenerPaciente(pacienteIndex)
        if (paciente == null) {
            JOptionPane.showMessageDialog(menuPrincipal, "Paciente inválido")
            return
        }

        val medicosStr = clinica.listarMedicos()
        val medicoIndexStr = JOptionPane.showInputDialog(menuPrincipal, "Seleccione médico (número):\n$medicosStr")
        val medicoIndex = medicoIndexStr?.toIntOrNull()?.minus(1) ?: -1
        val medico = clinica.obtenerMedico(medicoIndex)
        if (medico == null) {
            JOptionPane.showMessageDialog(menuPrincipal, "Médico inválido")
            return
        }

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

        formPanel.add(JLabel("Paciente:").apply { font = Font("Arial", Font.BOLD, 13) })
        formPanel.add(JLabel("${paciente.nombre} ${paciente.apellido}").apply { font = Font("Arial", Font.PLAIN, 13) })
        formPanel.add(JLabel("Médico:").apply { font = Font("Arial", Font.BOLD, 13) })
        formPanel.add(JLabel("${medico.nombre} ${medico.apellido}").apply { font = Font("Arial", Font.PLAIN, 13) })
        formPanel.add(JLabel("Seleccione Fecha:").apply { font = Font("Arial", Font.BOLD, 13) })
        formPanel.add(datePicker)
        formPanel.add(JLabel("Seleccione Hora:").apply { font = Font("Arial", Font.BOLD, 13) })
        formPanel.add(timePicker)

        val btnGuardar = JButton("Guardar Turno").apply {
            background = Color(33, 150, 243)
            foreground = Color.WHITE
            font = Font("Arial", Font.BOLD, 14)
            border = EmptyBorder(10, 20, 10, 20)

            addActionListener {
                val selectedDate = datePicker.date
                val selectedTime = timePicker.time
                if (selectedDate == null || selectedTime == null) {
                    JOptionPane.showMessageDialog(menuPrincipal, "Debe seleccionar fecha y hora")
                    return@addActionListener
                }
                val fechaHora = java.time.LocalDateTime.of(selectedDate, selectedTime)
                val id = "T${System.currentTimeMillis()}"
                clinica.agregarTurno(Turno(id, paciente, medico, fechaHora))
                JOptionPane.showMessageDialog(menuPrincipal, "✓ Turno registrado exitosamente")
                dialog.dispose()
                panelManager.showTurnos()
            }
        }

        val btnCancelar = JButton("Cancelar").apply {
            background = Color(220, 220, 220)
            foreground = Color(60, 60, 60)
            font = Font("Arial", Font.PLAIN, 14)
            border = EmptyBorder(10, 20, 10, 20)
            addActionListener { dialog.dispose() }
        }

        val buttonPanel = JPanel(FlowLayout(FlowLayout.RIGHT, 10, 10)).apply {
            background = Color.WHITE
            add(btnCancelar)
            add(btnGuardar)
        }

        dialog.add(formPanel, BorderLayout.CENTER)
        dialog.add(buttonPanel, BorderLayout.SOUTH)
        dialog.isVisible = true
    }
}
