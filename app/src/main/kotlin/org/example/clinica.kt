package org.example

import java.time.LocalDateTime

class Clinica {
    private val pacientes = mutableListOf<Paciente>()
    private val medicos = mutableListOf<Medico>()
    private val turnos = mutableListOf<Turno>()

    fun agregarPaciente(paciente: Paciente) {
        pacientes.add(paciente)
    }

    fun agregarMedico(medico: Medico) {
        medicos.add(medico)
    }

    fun agregarTurno(turno: Turno) {
        turnos.add(turno)
    }

    fun listarPacientes(): String {
        return if (pacientes.isEmpty()) "No hay pacientes registrados."
        else pacientes.joinToString("\n")
    }

    fun listarMedicos(): String {
        return if (medicos.isEmpty()) "No hay médicos registrados."
        else medicos.joinToString("\n")
    }

    fun listarTurnos(): String {
        return if (turnos.isEmpty()) "No hay turnos registrados."
        else turnos.joinToString("\n")
    }

    fun obtenerPaciente(index: Int): Paciente? = pacientes.getOrNull(index)
    fun obtenerMedico(index: Int): Medico? = medicos.getOrNull(index)
    // Datos de prueba (opcional)
    fun cargarDatosEjemplo() {
        val paciente = Paciente("12345678", "Juan", "Pérez")
        val medico = Medico("A001", "Ana", "García", Especialidad.CLINICA)
        val turno = Turno("1", paciente, medico, LocalDateTime.now())
        agregarPaciente(paciente)
        agregarMedico(medico)
        agregarTurno(turno)
    }
}