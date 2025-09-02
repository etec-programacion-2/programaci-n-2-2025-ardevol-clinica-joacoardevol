package org.example

import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)
    val pacientes = mutableListOf<Paciente>()
    val medicos = mutableListOf<Medico>()

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

    fun mostrarListas() {
        println("\n--- Lista de Pacientes ---")
        if (pacientes.isEmpty()) println("No hay pacientes registrados.")
        else pacientes.forEach { println("DNI: ${it.dni}, Nombre: ${it.nombre}, Apellido: ${it.apellido}") }

        println("\n--- Lista de Médicos ---")
        if (medicos.isEmpty()) println("No hay médicos registrados.")
        else medicos.forEach { println("Matrícula: ${it.matricula}, Nombre: ${it.nombre}, Apellido: ${it.apellido}, Especialidad: ${it.especialidad}") }
    }

    println("=== Sistema de Clínica ===")

    loop@ while (true) {
        println("\nSeleccione una opción:")
        println("1. Registrar paciente")
        println("2. Registrar médico")
        println("3. Mostrar pacientes y médicos")
        println("4. Salir")

        print("Opción: ")
        val opcion = reader.nextLine().toIntOrNull() ?: -1

        when (opcion) {
            1 -> registrarPaciente()
            2 -> registrarMedico()
            3 -> {
                mostrarListas()
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
            4 -> {
                println("Saliendo del sistema...")
                break@loop
            }
            else -> println("⚠️ Opción inválida, intente nuevamente.")
        }
    }

    reader.close()
}



