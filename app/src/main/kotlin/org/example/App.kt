package org.example

fun main() {
    val clinica = Clinica()
    val scanner = java.util.Scanner(System.`in`)

    // Agregar pacientes por defecto
    val pacientesDefault = listOf(
        Paciente("12345678", "Juan", "Pérez"),
        Paciente("23456789", "María", "Gómez"),
        Paciente("34567890", "Carlos", "López"),
        Paciente("45678901", "Ana", "Martínez"),
        Paciente("56789012", "Lucía", "Fernández")
    )
    pacientesDefault.forEach { clinica.agregarPaciente(it) }

    // Agregar médicos por defecto
    val medicosDefault = listOf(
        Medico("1001", "Dr. Alberto", "Sánchez", Especialidad.PEDIATRIA),
        Medico("1002", "Dra. Beatriz", "Ramírez", Especialidad.CLINICA),
        Medico("1003", "Dr. Carlos", "Torres", Especialidad.TRAUMATOLOGIA),
        Medico("1004", "Dra. Daniela", "Vargas", Especialidad.PEDIATRIA),
        Medico("1005", "Dr. Eduardo", "Molina", Especialidad.CLINICA)
    )
    medicosDefault.forEach { clinica.agregarMedico(it) }

    loop@ while (true) {
        println("\n=== Sistema de Clínica ===")
        println("1. Registrar paciente")
        println("2. Registrar médico")
        println("3. Registrar turno")
        println("4. Mostrar pacientes y médicos")
        println("5. Mostrar turnos")
        println("6. Salir")
        print("Opción: ")

        when (scanner.nextLine().toIntOrNull() ?: -1) {
            1 -> {
                val dni = Paciente.leerDni()
                val nombre = Paciente.leerNombreOApellido("nombre")
                val apellido = Paciente.leerNombreOApellido("apellido")
                clinica.agregarPaciente(Paciente(dni, nombre, apellido))
            }
            2 -> {
                val matricula = Medico.leerMatricula()
                val nombre = Paciente.leerNombreOApellido("nombre")
                val apellido = Paciente.leerNombreOApellido("apellido")
                val especialidad = Medico.seleccionarEspecialidad()
                clinica.agregarMedico(Medico(matricula, nombre, apellido, especialidad))
            }
            3 -> {
                clinica.listarPacientes()
                print("Seleccione paciente (número): ")
                val pacienteIndex = (scanner.nextLine().toIntOrNull() ?: -1) - 1
                val paciente = clinica.obtenerPaciente(pacienteIndex)
                if (paciente == null) {
                    println("⚠️ Paciente inválido")
                    continue@loop
                }

                clinica.listarMedicos()
                print("Seleccione médico (número): ")
                val medicoIndex = (scanner.nextLine().toIntOrNull() ?: -1) - 1
                val medico = clinica.obtenerMedico(medicoIndex)
                if (medico == null) {
                    println("⚠️ Médico inválido")
                    continue@loop
                }

                // Mostrar agenda tipo tabla
                clinica.mostrarTurnosPorMedico(medico)

                val disponibles = clinica.obtenerTurnosDisponibles(medico)
                if (disponibles.isEmpty()) {
                    println("⚠️ No hay turnos disponibles para este médico")
                    continue@loop
                }

                println("\nTurnos disponibles:")
                disponibles.forEachIndexed { index, turno ->
                    val hora = turno.fechaHora.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
                    println("${index + 1}. $hora")
                }

                print("Seleccione turno (número): ")
                val turnoIndex = (scanner.nextLine().toIntOrNull() ?: -1) - 1
                val turnoSeleccionado = disponibles.getOrNull(turnoIndex)

                if (turnoSeleccionado == null) {
                    println("⚠️ Turno inválido")
                    continue@loop
                }

                val nuevoTurno = Turno(
                    id = "T${System.currentTimeMillis()}",
                    paciente = paciente,
                    medico = medico,
                    fechaHora = turnoSeleccionado.fechaHora
                )

                try {
                    clinica.asignarTurno(nuevoTurno)
                } catch (e: Exception) {
                    println("⚠️ No se pudo asignar el turno: ${e.message}")
                }
            }
            4 -> {
                println("\n--- Pacientes ---")
                clinica.listarPacientes()
                println("\n--- Médicos ---")
                clinica.listarMedicos()
            }
            5 -> {
                println("\n--- Turnos asignados ---")
                clinica.listarTurnos()
            }
            6 -> {
                println("👋 Saliendo del sistema...")
                break@loop
            }
            else -> println("⚠️ Opción inválida")
        }
    }
}
