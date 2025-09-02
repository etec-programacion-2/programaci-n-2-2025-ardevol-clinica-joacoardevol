package org.example

import java.time.LocalDateTime

// Data class para representar un turno médico (cita)
data class Turno(
    val id: String,
    val paciente: Paciente,
    val medico: Medico,
    val fechaHora: LocalDateTime
)
