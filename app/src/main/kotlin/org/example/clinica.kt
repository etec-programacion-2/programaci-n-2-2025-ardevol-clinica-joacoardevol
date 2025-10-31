package org.example

import javax.swing.JOptionPane

class Clinica {
    private val pacientes = mutableListOf<Paciente>()
    private val medicos = mutableListOf<Medico>()
    private val turnos = mutableListOf<Turno>()

    fun agregarPaciente(paciente: Paciente) { pacientes.add(paciente) }
    fun agregarMedico(medico: Medico) { medicos.add(medico) }
    fun agregarTurno(turno: Turno) { turnos.add(turno) }

    fun listarPacientes(): String =
        if (pacientes.isEmpty()) "No hay pacientes registrados."
        else pacientes.mapIndexed { i, p -> "${i + 1}. $p" }.joinToString("\n")

    fun listarMedicos(): String =
        if (medicos.isEmpty()) "No hay médicos registrados."
        else medicos.mapIndexed { i, m -> "${i + 1}. $m" }.joinToString("\n")

    fun listarTurnos(): String =
        if (turnos.isEmpty()) "No hay turnos registrados."
        else turnos.mapIndexed { i, t -> "${i + 1}. $t" }.joinToString("\n")

    fun obtenerPaciente(index: Int): Paciente? = pacientes.getOrNull(index)
    fun obtenerMedico(index: Int): Medico? = medicos.getOrNull(index)

    fun registrarPacienteGrafico(panelManager: PanelManager) {
        val dni = Paciente.leerDniGrafico()
        val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
        val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
        agregarPaciente(Paciente(dni, nombre, apellido))
        JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente")
        panelManager.showPacientes()
    }

    fun registrarMedicoGrafico(panelManager: PanelManager) {
        val matricula = Medico.leerMatriculaGrafico()
        val nombre = Paciente.leerNombreOApellidoGrafico("nombre")
        val apellido = Paciente.leerNombreOApellidoGrafico("apellido")
        val especialidad = Medico.seleccionarEspecialidadGrafico()
        agregarMedico(Medico(matricula, nombre, apellido, especialidad))
        JOptionPane.showMessageDialog(null, "Médico registrado exitosamente")
        panelManager.showMedicos()
    }

    fun registrarTurnoGrafico(panelManager: PanelManager, menuPrincipal: MenuPrincipal) {
        TurnoRegistrar.registrar(this, panelManager, menuPrincipal)
    }
}

