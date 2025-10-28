package org.example

import java.awt.*
import java.time.LocalDateTime
import javax.swing.*
import javax.swing.border.EmptyBorder
import com.github.lgooddatepicker.components.DatePicker
import com.github.lgooddatepicker.components.TimePicker

class MenuPrincipal : JFrame() {

    private val clinica = Clinica()
    private val panelMenu = JPanel()
    private val panelContenido = JPanel()
    private val topBar = JPanel()

    init {
        title = "Sistema de Clínica"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1000, 650)
        setLocationRelativeTo(null)
        layout = BorderLayout()

        // Barra superior
        configurarBarraSuperior()

        // Panel lateral (menu)
        configurarPanelMenu()

        // Panel central de contenido
        configurarPanelContenido()

        // Cargar pantalla inicial
        mostrarInicio()
    }

    private fun configurarBarraSuperior() {
        topBar.layout = BorderLayout()
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

        add(topBar, BorderLayout.NORTH)
    }

    private fun configurarPanelMenu() {
        panelMenu.layout = BoxLayout(panelMenu, BoxLayout.Y_AXIS)
        panelMenu.background = Color(245, 247, 250)
        panelMenu.border = EmptyBorder(12, 12, 12, 12)
        panelMenu.preferredSize = Dimension(220, 0)

        // Logo / header en el menu lateral
        val header = JPanel().apply {
            layout = BorderLayout()
            background = panelMenu.background
            border = EmptyBorder(8, 4, 16, 4)
        }
        val lblLogo = JLabel(
            "<html><div style='text-align:center'><b>CLÍNICA</b><br/><span style='font-size:10px'>Gestión</span></div></html>",
            SwingConstants.CENTER
        ).apply {
            font = Font("Arial", Font.BOLD, 16)
        }
        header.add(lblLogo, BorderLayout.CENTER)
        panelMenu.add(header)

        // Botones del menú
        val botones = listOf("Inicio", "Pacientes", "Médicos", "Turnos", "Salir")
        botones.forEach { texto ->
            val boton = crearBotonMenu(texto)
            panelMenu.add(boton)
            panelMenu.add(Box.createRigidArea(Dimension(0, 6)))
            boton.addActionListener {
                when (texto) {
                    "Inicio" -> mostrarInicio()
                    "Pacientes" -> mostrarPacientes()
                    "Médicos" -> mostrarMedicos()
                    "Turnos" -> mostrarTurnos()
                    "Salir" -> System.exit(0)
                }
            }
        }

        panelMenu.add(Box.createVerticalGlue())
        add(panelMenu, BorderLayout.WEST)
    }

    private fun configurarPanelContenido() {
        panelContenido.layout = BorderLayout()
        panelContenido.background = Color.WHITE
        panelContenido.border = EmptyBorder(12, 12, 12, 12)
        add(panelContenido, BorderLayout.CENTER)
    }

    private fun crearBotonMenu(texto: String): JButton {
        return JButton(texto).apply {
            maximumSize = Dimension(Int.MAX_VALUE, 44)
            alignmentX = Component.CENTER_ALIGNMENT
            background = Color(255, 255, 255)
            font = Font("Arial", Font.PLAIN, 14)
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color(220, 220, 220)),
                EmptyBorder(6, 12, 6, 12)
            )
        }
    }

    private fun cambiarContenido(nuevo: JPanel) {
        panelContenido.removeAll()
        panelContenido.add(nuevo, BorderLayout.CENTER)
        panelContenido.revalidate()
        panelContenido.repaint()
    }

    private fun mostrarInicio() {
        val p = JPanel(BorderLayout()).apply {
            background = Color.WHITE
        }
        val titulo = JLabel(
            "<html><div style='text-align:center'><h2>Bienvenido al Sistema de Clínica</h2><div>Gestioná Pacientes, Médicos y Turnos</div></div></html>",
            SwingConstants.CENTER
        ).apply {
            font = Font("Arial", Font.PLAIN, 18)
        }
        p.add(titulo, BorderLayout.CENTER)
        cambiarContenido(p)
    }

    private fun mostrarPacientes() {
        val panel = JPanel(BorderLayout()).apply {
            background = Color.WHITE
        }
        val texto = clinica.listarPacientes()
        val verTexto = VerTextoPanel("Pacientes", texto)
        panel.add(verTexto, BorderLayout.CENTER)

        val btnRegistrar = JButton("Registrar Nuevo Paciente").apply {
            addActionListener {
                val dni = Paciente.leerDniGrafico()
                val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
                val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
                clinica.agregarPaciente(Paciente(dni, nombre, apellido))
                JOptionPane.showMessageDialog(this@MenuPrincipal, "Paciente registrado exitosamente")
                mostrarPacientes()
            }
        }
        panel.add(btnRegistrar, BorderLayout.SOUTH)
        cambiarContenido(panel)
    }

    private fun mostrarMedicos() {
        val panel = JPanel(BorderLayout()).apply {
            background = Color.WHITE
        }
        val texto = clinica.listarMedicos()
        val verTexto = VerTextoPanel("Médicos", texto)
        panel.add(verTexto, BorderLayout.CENTER)

        val btnRegistrar = JButton("Registrar Nuevo Médico").apply {
            addActionListener {
                val matricula = Medico.leerMatriculaGrafico()
                val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
                val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
                val especialidad = Medico.seleccionarEspecialidadGrafico()
                clinica.agregarMedico(Medico(matricula, nombre, apellido, especialidad))
                JOptionPane.showMessageDialog(this@MenuPrincipal, "Médico registrado exitosamente")
                mostrarMedicos()
            }
        }
        panel.add(btnRegistrar, BorderLayout.SOUTH)
        cambiarContenido(panel)
    }

    private fun mostrarTurnos() {
        val panel = JPanel(BorderLayout()).apply {
            background = Color.WHITE
        }
        val texto = clinica.listarTurnos()
        val verTexto = VerTextoPanel("Turnos", texto)
        panel.add(verTexto, BorderLayout.CENTER)

        val btnRegistrar = JButton("Registrar Nuevo Turno").apply {
            addActionListener {
                val pacientesStr = clinica.listarPacientes()
                val pacienteIndexStr = JOptionPane.showInputDialog(this@MenuPrincipal, "Seleccione paciente (número):\n$pacientesStr")
                val pacienteIndex = pacienteIndexStr?.toIntOrNull()?.minus(1) ?: -1
                val paciente = clinica.obtenerPaciente(pacienteIndex)
                if (paciente == null) {
                    JOptionPane.showMessageDialog(this@MenuPrincipal, "Paciente inválido")
                    return@addActionListener
                }

                val medicosStr = clinica.listarMedicos()
                val medicoIndexStr = JOptionPane.showInputDialog(this@MenuPrincipal, "Seleccione médico (número):\n$medicosStr")
                val medicoIndex = medicoIndexStr?.toIntOrNull()?.minus(1) ?: -1
                val medico = clinica.obtenerMedico(medicoIndex)
                if (medico == null) {
                    JOptionPane.showMessageDialog(this@MenuPrincipal, "Médico inválido")
                    return@addActionListener
                }

                // Selector gráfico de fecha y hora
                val datePicker = DatePicker().apply {
                    font = Font("Arial", Font.PLAIN, 14)
                    background = Color(240, 240, 240)
                    foreground = Color(0, 0, 0)
                }
                val timePicker = TimePicker().apply {
                    font = Font("Arial", Font.PLAIN, 14)
                    background = Color(240, 240, 240)
                    foreground = Color(0, 0, 0)
                }

                val dialog = JDialog(this@MenuPrincipal, "Registrar Turno", true).apply {
                    setSize(400, 300)
                    setLocationRelativeTo(this@MenuPrincipal)
                    layout = BorderLayout()
                }

                val formPanel = JPanel(GridLayout(4, 2, 10, 10)).apply {
                    border = EmptyBorder(10, 10, 10, 10)
                    background = Color(255, 255, 255)
                }

                formPanel.add(JLabel("Seleccione Fecha:"))
                formPanel.add(datePicker)

                formPanel.add(JLabel("Seleccione Hora:"))
                formPanel.add(timePicker)

                val btnGuardar = JButton("Guardar Turno").apply {
                    addActionListener {
                        val selectedDate = datePicker.date
                        val selectedTime = timePicker.time
                        if (selectedDate != null && selectedTime != null) {
                            val fechaHora = LocalDateTime.of(selectedDate, selectedTime)
                            val id = "T${System.currentTimeMillis()}"
                            clinica.agregarTurno(Turno(id, paciente, medico, fechaHora))
                            JOptionPane.showMessageDialog(this@MenuPrincipal, "Turno registrado exitosamente")
                            dialog.dispose()
                            mostrarTurnos()
                        } else {
                            JOptionPane.showMessageDialog(this@MenuPrincipal, "Seleccione fecha y hora válidas")
                        }
                    }
                }

                dialog.add(formPanel, BorderLayout.CENTER)
                dialog.add(btnGuardar, BorderLayout.SOUTH)
                dialog.isVisible = true
            }
        }
        panel.add(btnRegistrar, BorderLayout.SOUTH)
        cambiarContenido(panel)
    }
}

fun main() {
    SwingUtilities.invokeLater { MenuPrincipal().isVisible = true }
}