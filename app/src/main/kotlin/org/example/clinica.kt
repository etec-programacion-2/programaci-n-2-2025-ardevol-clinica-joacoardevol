package org.example

class Clinica {
    private val pacientes = mutableListOf<Paciente>()
    private val medicos = mutableListOf<Medico>()
    private val turnos = mutableListOf<Turno>()

    fun agregarPaciente(paciente: Paciente) {
        pacientes.add(paciente)
        println("✅ Paciente agregado: ${paciente.nombre} ${paciente.apellido}")
    }

    fun agregarMedico(medico: Medico) {
        medicos.add(medico)
        println("✅ Médico agregado: ${medico.nombre} ${medico.apellido} (${medico.especialidad})")
    }

    fun asignarTurno(turno: Turno) {
        turnos.add(turno)
        println("✅ Turno asignado: ${turno.id} - Paciente ${turno.paciente.nombre}, Médico ${turno.medico.nombre}")
    }

    fun listarPacientes() {
        if (pacientes.isEmpty()) println("⚠️ No hay pacientes registrados")
        else pacientes.forEachIndexed { index, p -> println("${index + 1}. DNI: ${p.dni}, Nombre: ${p.nombre} ${p.apellido}") }
    }

    fun listarMedicos() {
        if (medicos.isEmpty()) println("⚠️ No hay médicos registrados")
        else medicos.forEachIndexed { index, m -> println("${index + 1}. Matrícula: ${m.matricula}, Nombre: ${m.nombre} ${m.apellido}, Especialidad: ${m.especialidad}") }
    }

    fun listarTurnos() {
        if (turnos.isEmpty()) println("⚠️ No hay turnos registrados")
        else turnos.forEach { t -> println("ID: ${t.id}, Paciente: ${t.paciente.nombre}, Médico: ${t.medico.nombre}, Fecha: ${t.fechaHora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}") }
    }

    fun obtenerPaciente(index: Int): Paciente? = pacientes.getOrNull(index)
    fun obtenerMedico(index: Int): Medico? = medicos.getOrNull(index)
}
