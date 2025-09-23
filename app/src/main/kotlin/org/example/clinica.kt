package org.example

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

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
        generarAgenda(medico)
    }

    private fun generarAgenda(medico: Medico) {
        val hoy = LocalDateTime.now().toLocalDate()
        val intervalos = listOf(
            LocalTime.of(8, 0) to LocalTime.of(13, 0),
            LocalTime.of(17, 0) to LocalTime.of(20, 0)
        )

        for ((inicio, fin) in intervalos) {
            var hora = inicio
            while (hora.isBefore(fin)) {
                val fechaHora = LocalDateTime.of(hoy, hora)
                val turno = Turno(
                    id = "T${medico.matricula}-${fechaHora.format(DateTimeFormatter.ofPattern("HHmm"))}",
                    paciente = null,
                    medico = medico,
                    fechaHora = fechaHora
                )
                // Algunos turnos ocupados aleatoriamente
                turno.ocupado = Random.nextBoolean() && Random.nextBoolean()
                turnos.add(turno)

                hora = hora.plusMinutes(45)
            }
        }
    }

    fun asignarTurno(turno: Turno) {
        val existente = turnos.find { it.medico == turno.medico && it.fechaHora == turno.fechaHora }
        if (existente != null && existente.ocupado) {
            throw Exception("Ese turno ya está ocupado.")
        }
        existente?.paciente = turno.paciente
        existente?.ocupado = true
        println("✅ Turno confirmado: ${turno.fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))} con ${turno.medico.nombre}")
    }

    fun obtenerTurnosDisponibles(medico: Medico): List<Turno> {
        return turnos.filter { it.medico == medico && !it.ocupado }
    }

    fun mostrarTurnosPorMedico(medico: Medico) {
        val agenda = turnos.filter { it.medico == medico }
        if (agenda.isEmpty()) {
            println("⚠️ No hay turnos para este médico")
            return
        }
        println("\n--- Agenda de ${medico.nombre} ---")
        agenda.forEach { t ->
            val estado = if (t.ocupado) "❌" else "✅"
            val hora = t.fechaHora.format(DateTimeFormatter.ofPattern("HH:mm"))
            println("$hora -> $estado")
        }
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
        val asignados = turnos.filter { it.ocupado }
        if (asignados.isEmpty()) println("⚠️ No hay turnos registrados")
        else asignados.forEach { t ->
            println("ID: ${t.id}, Paciente: ${t.paciente?.nombre}, Médico: ${t.medico.nombre}, Fecha: ${t.fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}")
        }
    }

    fun obtenerPaciente(index: Int): Paciente? = pacientes.getOrNull(index)
    fun obtenerMedico(index: Int): Medico? = medicos.getOrNull(index)
}
