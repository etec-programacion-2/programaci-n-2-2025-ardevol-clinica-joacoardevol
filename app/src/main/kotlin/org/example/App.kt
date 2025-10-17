package org.example

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.Box

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
        topBar.layout = BorderLayout()
        topBar.background = Color(33, 150, 243)
        topBar.border = EmptyBorder(8, 12, 8, 12)

        val lblTitle = JLabel("Sistema de Clínica", SwingConstants.LEFT)
        lblTitle.foreground = Color.WHITE
        lblTitle.font = Font("Arial", Font.BOLD, 18)

        val lblRight = JLabel("Buscar  |  Usuario", SwingConstants.RIGHT)
        lblRight.foreground = Color.WHITE
        lblRight.font = Font("Arial", Font.PLAIN, 12)

        topBar.add(lblTitle, BorderLayout.WEST)
        topBar.add(lblRight, BorderLayout.EAST)

        add(topBar, BorderLayout.NORTH)

        // Panel lateral (menu)
        panelMenu.layout = BoxLayout(panelMenu, BoxLayout.Y_AXIS)
        panelMenu.background = Color(245, 247, 250)
        panelMenu.border = EmptyBorder(12, 12, 12, 12)
        panelMenu.preferredSize = Dimension(220, 0)

        // Logo / header en el menu lateral
        val header = JPanel()
        header.layout = BorderLayout()
        header.background = panelMenu.background
        header.border = EmptyBorder(8, 4, 16, 4)
        val lblLogo = JLabel(
            "<html><div style='text-align:center'><b>CLÍNICA</b><br/><span style='font-size:10px'>Gestión</span></div></html>",
            SwingConstants.CENTER
        )
        lblLogo.font = Font("Arial", Font.BOLD, 16)
        header.add(lblLogo, BorderLayout.CENTER)
        panelMenu.add(header)

        // Botones del menú (sin espacio adicional)
        val btnInicio = crearBotonMenu("Inicio")
        val btnPacientes = crearBotonMenu("Pacientes")
        val btnMedicos = crearBotonMenu("Médicos")
        val btnTurnos = crearBotonMenu("Turnos")
        val btnSalir = crearBotonMenu("Salir")

        panelMenu.add(btnInicio)
        panelMenu.add(Box.createRigidArea(Dimension(0, 6)))  // Espacio mínimo entre botones
        panelMenu.add(btnPacientes)
        panelMenu.add(Box.createRigidArea(Dimension(0, 6)))
        panelMenu.add(btnMedicos)
        panelMenu.add(Box.createRigidArea(Dimension(0, 6)))
        panelMenu.add(btnTurnos)
        panelMenu.add(Box.createVerticalGlue())
        panelMenu.add(btnSalir)

        add(panelMenu, BorderLayout.WEST)

        // Panel central de contenido
        panelContenido.layout = BorderLayout()
        panelContenido.background = Color.WHITE
        panelContenido.border = EmptyBorder(12, 12, 12, 12)
        add(panelContenido, BorderLayout.CENTER)

        // Acciones
        btnInicio.addActionListener { mostrarInicio() }
        btnPacientes.addActionListener { mostrarPacientes() }
        btnMedicos.addActionListener { mostrarMedicos() }
        btnTurnos.addActionListener { mostrarTurnos() }
        btnSalir.addActionListener { System.exit(0) }

        // Cargar pantalla inicial
        mostrarInicio()
    }

    private fun crearBotonMenu(texto: String): JButton {
        val b = JButton(texto)
        b.maximumSize = Dimension(Int.MAX_VALUE, 44)
        b.alignmentX = java.awt.Component.CENTER_ALIGNMENT
        b.background = Color(255, 255, 255)
        b.font = Font("Arial", Font.PLAIN, 14)
        b.border = javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(Color(220, 220, 220)),
            EmptyBorder(6, 12, 6, 12)
        )
        return b
    }

    private fun cambiarContenido(nuevo: JPanel) {
        panelContenido.removeAll()
        panelContenido.add(nuevo, BorderLayout.CENTER)
        panelContenido.revalidate()
        panelContenido.repaint()
    }

    private fun mostrarInicio() {
        val p = JPanel(BorderLayout())
        p.background = Color.WHITE
        val titulo = JLabel(
            "<html><div style='text-align:center'><h2>Bienvenido a Sistema de Clínica</h2><div>Gestioná Pacientes, Médicos y Turnos</div></div></html>",
            SwingConstants.CENTER
        )
        titulo.font = Font("Arial", Font.PLAIN, 18)
        p.add(titulo, BorderLayout.CENTER)
        cambiarContenido(p)
    }

    private fun mostrarPacientes() {
        val panel = JPanel(BorderLayout())
        panel.background = Color.WHITE
        val texto = clinica.listarPacientes()
        val verTexto = VerTextoPanel("Pacientes", texto)
        panel.add(verTexto, BorderLayout.CENTER)

        val btnRegistrar = JButton("Registrar Nuevo Paciente")
        btnRegistrar.addActionListener {
            val dni = Paciente.leerDniGrafico()
            val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
            val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
            clinica.agregarPaciente(Paciente(dni, nombre, apellido))
            JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente")
            mostrarPacientes()  // Refresca la lista
        }
        panel.add(btnRegistrar, BorderLayout.SOUTH)
        cambiarContenido(panel)
    }

    private fun mostrarMedicos() {
        val panel = JPanel(BorderLayout())
        panel.background = Color.WHITE
        val texto = clinica.listarMedicos()
        val verTexto = VerTextoPanel("Médicos", texto)
        panel.add(verTexto, BorderLayout.CENTER)

        val btnRegistrar = JButton("Registrar Nuevo Médico")
        btnRegistrar.addActionListener {
            val matricula = Medico.leerMatriculaGrafico()
            val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
            val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
            val especialidad = Medico.seleccionarEspecialidadGrafico()
            clinica.agregarMedico(Medico(matricula, nombre, apellido, especialidad))
            JOptionPane.showMessageDialog(this, "Médico registrado exitosamente")
            mostrarMedicos()  // Refresca la lista
        }
        panel.add(btnRegistrar, BorderLayout.SOUTH)
        cambiarContenido(panel)
    }

    private fun mostrarTurnos() {
        val panel = JPanel(BorderLayout())
        panel.background = Color.WHITE
        val texto = clinica.listarTurnos()
        val verTexto = VerTextoPanel("Turnos", texto)
        panel.add(verTexto, BorderLayout.CENTER)

        val btnRegistrar = JButton("Registrar Nuevo Turno")
        btnRegistrar.addActionListener {
            val pacientesStr = clinica.listarPacientes()
            val pacienteIndexStr = JOptionPane.showInputDialog(this, "Seleccione paciente (número):\n$pacientesStr")
            val pacienteIndex = pacienteIndexStr?.toIntOrNull()?.minus(1) ?: -1
            val paciente = clinica.obtenerPaciente(pacienteIndex)
            if (paciente == null) {
                JOptionPane.showMessageDialog(this, "Paciente inválido")
                return@addActionListener
            }

            val medicosStr = clinica.listarMedicos()
            val medicoIndexStr = JOptionPane.showInputDialog(this, "Seleccione médico (número):\n$medicosStr")
            val medicoIndex = medicoIndexStr?.toIntOrNull()?.minus(1) ?: -1
            val medico = clinica.obtenerMedico(medicoIndex)
            if (medico == null) {
                JOptionPane.showMessageDialog(this, "Médico inválido")
                return@addActionListener
            }

            val fechaHora = Turno.leerFechaHoraGrafico()
            val id = "T${System.currentTimeMillis()}"
            clinica.agregarTurno(Turno(id, paciente, medico, fechaHora))
            JOptionPane.showMessageDialog(this, "Turno registrado exitosamente")
            mostrarTurnos()  // Refresca la lista
        }
        panel.add(btnRegistrar, BorderLayout.SOUTH)
        cambiarContenido(panel)
    }
}

fun main() {
    SwingUtilities.invokeLater { MenuPrincipal().isVisible = true }
}