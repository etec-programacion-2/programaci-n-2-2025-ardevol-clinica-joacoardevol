package org.example

import javax.swing.JOptionPane

object Clinica {
    private val pacientes = mutableListOf<Paciente>()
    private val medicos = mutableListOf<Medico>()
    private val turnos = mutableListOf<Turno>()

    fun agregarPaciente(paciente: Paciente) { pacientes.add(paciente) }
    fun agregarMedico(medico: Medico) { medicos.add(medico) }
    fun agregarTurno(turno: Turno) { turnos.add(turno) }

    fun listarPacientes(): String = if (pacientes.isEmpty())
        "No hay pacientes registrados.\n\nHaga clic en 'Registrar Nuevo Paciente' para agregar uno."
    else pacientes.mapIndexed { i, p -> "${i + 1}. $p" }.joinToString("\n")

    fun listarMedicos(): String = if (medicos.isEmpty())
        "No hay médicos registrados.\n\nHaga clic en 'Registrar Nuevo Médico' para agregar uno."
    else medicos.mapIndexed { i, m -> "${i + 1}. $m" }.joinToString("\n")

    fun listarTurnos(): String = if (turnos.isEmpty())
        "No hay turnos registrados.\n\nHaga clic en 'Registrar Nuevo Turno' para agregar uno."
    else turnos.mapIndexed { i, t -> "${i + 1}. $t" }.joinToString("\n")

    fun obtenerPaciente(index: Int): Paciente? = pacientes.getOrNull(index)
    fun obtenerMedico(index: Int): Medico? = medicos.getOrNull(index)

    fun registrarPacienteGrafico(panelManager: PanelManager) {
        try {
            val dni = Paciente.leerDniGrafico()
            val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
            val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
            agregarPaciente(Paciente(dni, nombre, apellido))
            JOptionPane.showMessageDialog(null, "✓ Paciente registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE)
            panelManager.showPacientes()
        } catch (_: Exception) {}
    }

    fun registrarMedicoGrafico(panelManager: PanelManager) {
        try {
            val matricula = Medico.leerMatriculaGrafico()
            val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
            val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
            val especialidad = Medico.seleccionarEspecialidadGrafico()
            agregarMedico(Medico(matricula, nombre, apellido, especialidad))
            JOptionPane.showMessageDialog(null, "✓ Médico registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE)
            panelManager.showMedicos()
        } catch (_: Exception) {}
    }

    fun registrarTurnoGrafico(panelManager: PanelManager, menuPrincipal: MenuPrincipal) {
        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(menuPrincipal, "No hay pacientes registrados.\nPrimero debe registrar al menos un paciente.", "Advertencia", JOptionPane.WARNING_MESSAGE)
            return
        }
        if (medicos.isEmpty()) {
            JOptionPane.showMessageDialog(menuPrincipal, "No hay médicos registrados.\nPrimero debe registrar al menos un médico.", "Advertencia", JOptionPane.WARNING_MESSAGE)
            return
        }
        TurnoRegistrar.registrar(this, panelManager, menuPrincipal)
    }
}
