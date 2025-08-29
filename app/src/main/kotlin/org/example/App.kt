package org.example

// Data class para representar a un paciente
data class Paciente(
    val dni: String,
    val nombre: String,
    val apellido: String
)

// Enum class para representar las especialidades médicas
enum class Especialidad {
    PEDIATRIA,
    CLINICA,
    TRAUMATOLOGIA
}

// Data class para representar a un médico
data class Medico(
    val matricula: String,
    val nombre: String,
    val apellido: String,
    val especialidad: Especialidad
)

fun main() {
    val pacientes = mutableListOf<Paciente>()
    val medicos = mutableListOf<Medico>()

    // === Ingresar un paciente ===
    print("Ingrese DNI del paciente: ")
    val dniPaciente = readLine() ?: ""
    print("Ingrese nombre del paciente: ")
    val nombrePaciente = readLine() ?: ""
    print("Ingrese apellido del paciente: ")
    val apellidoPaciente = readLine() ?: ""

    val nuevoPaciente = Paciente(dniPaciente, nombrePaciente, apellidoPaciente)
    pacientes.add(nuevoPaciente)
    println("✅ Paciente agregado: $nuevoPaciente\n")

    // === Ingresar un médico ===
    print("Ingrese matrícula del médico: ")
    val matricula = readLine() ?: ""
    print("Ingrese nombre del médico: ")
    val nombreMedico = readLine() ?: ""
    print("Ingrese apellido del médico: ")
    val apellidoMedico = readLine() ?: ""

    println("Seleccione especialidad:")
    Especialidad.values().forEachIndexed { index, especialidad ->
        println("${index + 1}. $especialidad")
    }
    print("Opción: ")
    val opcion = readLine()?.toIntOrNull() ?: 0

    if (opcion in 1..Especialidad.values().size) {
        val especialidad = Especialidad.values()[opcion - 1]
        val nuevoMedico = Medico(matricula, nombreMedico, apellidoMedico, especialidad)
        medicos.add(nuevoMedico)
        println("✅ Médico agregado: $nuevoMedico\n")
    } else {
        println("⚠️ Opción inválida, médico no agregado.\n")
    }

    // === Mostrar los datos ingresados ===
    println("📋 Lista de pacientes:")
    pacientes.forEach { println("DNI: ${it.dni}, Nombre: ${it.nombre}, Apellido: ${it.apellido}") }

    println("\n📋 Lista de médicos:")
    medicos.forEach { println("Matrícula: ${it.matricula}, Nombre: ${it.nombre}, Apellido: ${it.apellido}, Especialidad: ${it.especialidad}") }
}
