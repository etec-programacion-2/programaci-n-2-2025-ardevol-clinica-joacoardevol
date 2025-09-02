package org.example

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)
    val pacientes = mutableListOf<Paciente>()
    val medicos = mutableListOf<Medico>()
    val turnos = mutableListOf<Turno>()

    // Validaciones
    fun leerDni(): String {
        while (true) {
            print("Ingrese DNI (solo números): ")
            val dni = reader.nextLine()
            if (dni.matches(Regex("^\\d+$"))) return dni
            println("⚠️ DNI inválido. Debe contener solo números.")
        }
    }

    fun leerNombreOApellido(campo: String): String {
        while (true) {
            print("Ingrese $campo (sin números): ")
            val texto = reader.nextLine()
            if (texto.matches(Regex("^[^\\d]+$"))) return texto
            println("⚠️ $campo inválido. No debe contener números.")
        }
    }

    fun leerMatricula(): String {
        while (true) {
            print("Ingrese matrícula (solo números): ")
            val matricula = reader.nextLine()
            if (matricula.matches(Regex("^\\d+$"))) return matricula
            println("⚠️ Matrícula inválida. Debe contener solo números.")
        }
    }

    fun registrarPaciente() {
        println("\n--- Registro de Paciente ---")
        val dni = leerDni()
        val nombre = leerNombreOApellido("nombre")
        val apellido = leerNombreOApellido("apellido")
        pacientes.add(Paciente(dni, nombre, apellido))
        println("✅ Paciente agregado!")
    }

    fun registrarMedico() {
        println("\n--- Registro de Médico ---")
        val matricula = leerMatricula()
        val nombre = leerNombreOApellido("nombre")
        val apellido = leerNombreOApellido("apellido")

        println("Seleccione especialidad:")
        Especialidad.values().forEachIndexed { index, esp ->
            println("${index + 1}. $esp")
        }

        var espOpcion: Int
        while (true) {
            print("Opción: ")
            espOpcion = reader.nextLine().toIntOrNull() ?: -1
            if (espOpcion in 1..Especialidad.values().size) break
            println("⚠️ Opción inválida, intente nuevamente.")
        }

        val especialidad = Especialidad.values()[espOpcion - 1]
        medicos.add(Medico(matricula, nombre, apellido, especialidad))
        println("✅ Médico agregado!")
    }

    fun mostrarListasCompletas() {
        println("\n--- Lista de Pacientes ---")
        if (pacientes.isEmpty()) println("No hay pacientes registrados.")
        else pacientes.forEach { println("DNI: ${it.dni}, Nombre: ${it.nombre}, Apellido: ${it.apellido}") }

        println("\n--- Lista de Médicos ---")
        if (medicos.isEmpty()) println("No hay médicos registrados.")
        else medicos.forEach { println("Matrícula: ${it.matricula}, Nombre: ${it.nombre}, Apellido: ${it.apellido}, Especialidad: ${it.especialidad}") }

        println("\n--- Lista de Turnos ---")
        if (turnos.isEmpty()) {
            println("No hay turnos registrados.")
        } else {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            turnos.forEach { turno ->
                println("ID: ${turno.id}, Paciente: ${turno.paciente.nombre} ${turno.paciente.apellido}, Médico: ${turno.medico.nombre} ${turno.medico.apellido}, Fecha y Hora: ${turno.fechaHora.format(formatter)}")
            }
        }
    }

    fun registrarTurno() {
        if (pacientes.isEmpty()) {
            println("⚠️ No hay pacientes registrados. Registre al menos un paciente primero.")
            return
        }
        if (medicos.isEmpty()) {
            println("⚠️ No hay médicos registrados. Registre al menos un médico primero.")
            return
        }

        println("\n--- Registro de Turno ---")

        // Mostrar pacientes para elegir
        println("Seleccione paciente:")
        pacientes.forEachIndexed { index, paciente ->
            println("${index + 1}. DNI: ${paciente.dni}, Nombre: ${paciente.nombre} ${paciente.apellido}")
        }
        var pacienteOpcion: Int
        while (true) {
            print("Opción: ")
            pacienteOpcion = reader.nextLine().toIntOrNull() ?: -1
            if (pacienteOpcion in 1..pacientes.size) break
            println("⚠️ Opción inválida, intente nuevamente.")
        }
        val pacienteSeleccionado = pacientes[pacienteOpcion - 1]

        // Mostrar médicos para elegir
        println("Seleccione médico:")
        medicos.forEachIndexed { index, medico ->
            println("${index + 1}. Matrícula: ${medico.matricula}, Nombre: ${medico.nombre} ${medico.apellido}, Especialidad: ${medico.especialidad}")
        }
        var medicoOpcion: Int
        while (true) {
            print("Opción: ")
            medicoOpcion = reader.nextLine().toIntOrNull() ?: -1
            if (medicoOpcion in 1..medicos.size) break
            println("⚠️ Opción inválida, intente nuevamente.")
        }
        val medicoSeleccionado = medicos[medicoOpcion - 1]

        // Leer fecha y hora
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        var fechaHora: LocalDateTime
        while (true) {
            print("Ingrese fecha y hora (formato dd/MM/yyyy HH:mm): ")
            val input = reader.nextLine()
            try {
                fechaHora = LocalDateTime.parse(input, formatter)
                break
            } catch (e: Exception) {
                println("⚠️ Formato inválido. Intente nuevamente.")
            }
        }

        // Generar id simple (puedes mejorar con UUID)
        val id = "T${turnos.size + 1}"

        turnos.add(Turno(id, pacienteSeleccionado, medicoSeleccionado, fechaHora))
        println("✅ Turno agregado! ID: $id")
    }

    println("=== Sistema de Clínica ===")

    loop@ while (true) {
        println("\nSeleccione una opción:")
        println("1. Registrar paciente")
        println("2. Registrar médico")
        println("3. Mostrar pacientes, médicos y turnos")
        println("4. Registrar turno")
        println("5. Salir")

        print("Opción: ")
        val opcion = reader.nextLine().toIntOrNull() ?: -1

        when (opcion) {
            1 -> registrarPaciente()
            2 -> registrarMedico()
            3 -> {
                mostrarListasCompletas()
                // Submenú después de mostrar listas
                subloop@ while (true) {
                    println("\n¿Qué desea hacer ahora?")
                    println("1. Agregar paciente")
                    println("2. Agregar médico")
                    println("3. Salir")

                    print("Opción: ")
                    when (reader.nextLine().toIntOrNull() ?: -1) {
                        1 -> {
                            registrarPaciente()
                            break@subloop
                        }
                        2 -> {
                            registrarMedico()
                            break@subloop
                        }
                        3 -> {
                            println("Saliendo del sistema...")
                            break@loop
                        }
                        else -> println("⚠️ Opción inválida, intente nuevamente.")
                    }
                }
            }
            4 -> registrarTurno()
            5 -> {
                println("Saliendo del sistema...")
                break@loop
            }
            else -> println("⚠️ Opción inválida, intente nuevamente.")
        }
    }

    reader.close()
}