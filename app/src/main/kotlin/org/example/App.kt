package org.example

fun main() {
    val clinica = Clinica()
    val scanner = java.util.Scanner(System.`in`)

    loop@ while (true) {
        println("\n=== Sistema de Clínica ===")
        println("1. Registrar paciente")
        println("2. Registrar médico")
        println("3. Registrar turno")
        println("4. Mostrar pacientes")
        println("5. Mostrar médicos")
        println("6. Mostrar turnos")
        println("7. Salir")
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

                val fechaHora = Turno.leerFechaHora()
                val id = "T${System.currentTimeMillis()}"
                clinica.asignarTurno(Turno(id, paciente, medico, fechaHora))
            }
            4 -> clinica.listarPacientes()
            5 -> clinica.listarMedicos()
            6 -> clinica.listarTurnos()
            7 -> {
                println("👋 Saliendo del sistema...")
                break@loop
            }
            else -> println("⚠️ Opción inválida")
        }
    }
}
