etec@etec-AB350M-DS3H-V2:~/.ssh/programaci-n-2-2025-ardevol-clinica-joacoardevol$ gradle --version

------------------------------------------------------------
Gradle 9.0.0
------------------------------------------------------------

Build time:    2025-07-31 16:35:12 UTC
Revision:      328772c6bae126949610a8beb59cb227ee580241

Kotlin:        2.2.0
Groovy:        4.0.27
Ant:           Apache Ant(TM) version 1.10.15 compiled on August 25 2024
Launcher JVM:  11.0.28 (Ubuntu 11.0.28+6-post-Ubuntu-1ubuntu124.04.1)
Daemon JVM:    /usr/lib/jvm/java-11-openjdk-amd64 (no JDK specified, using current Java home)
OS:            Linux 6.8.0-87-generic amd64



etec@etec-AB350M-DS3H-V2:~/.ssh/programaci-n-2-2025-ardevol-clinica-joacoardevol$ java -version

# openjdk version **"11.0.28" 2025-07-15**

OpenJDK Runtime Environment (build 11.0.28+6-post-Ubuntu-1ubuntu124.04.1)
OpenJDK 64-Bit Server VM (build 11.0.28+6-post-Ubuntu-1ubuntu124.04.1, mixed mode, sharing)

Comandos A seguir para poder abrir el code
Moverse a la carpeta de ssh: cd .shh
Clonar repositorio en tu comutadora: git clone git@github.com:etec-programacion-2/programaci-n-2-2025-ardevol-clinica-joacoardevol.git
Moverse a esa la carpeta: cd programaci-n-2-2025-ardevol-clinica-joacoardevol
Para hacer que el code funcione: ./gradlew run


**Manual de Usuario - Sistema de Gestión de Clínica
INICIO DEL PROGRAMA**
PASO 1: Ejecuta el programa
● Se abrirá una ventana con el título "Sistema de Clínica"
PASO 2: Observa la pantalla principal
● A la izquierda verás un menú con botones

CÓMO VER INFORMACIÓN
VER PACIENTES REGISTRADOS
PASO 1: Haz clic en el botón "Pacientes"
PASO 2: El sistema muestra:
● Si HAY pacientes: Lista completa con nombre, apellido y DNI
● Si NO HAY pacientes: Mensaje "No hay pacientes registrados"
PASO 3: Haz clic en "Aceptar" para cerrar la ventana
VER MÉDICOS REGISTRADOS
PASO 1: Haz clic en el botón "Médicos"
PASO 2: El sistema muestra:
● Si HAY médicos: Lista completa con nombre, apellido, matrícula y especialidad
● Si NO HAY médicos: Mensaje "No hay médicos registrados"
PASO 3: Haz clic en "Aceptar" para cerrar la ventana
VER TURNOS REGISTRADOS
PASO 1: Haz clic en el botón "Turnos"

PASO 2: El sistema muestra:
● Si HAY turnos: Lista completa con paciente, médico, fecha y hora
● Si NO HAY turnos: Mensaje "No hay turnos registrados"
PASO 3: Haz clic en "Aceptar" para cerrar la ventana
CÓMO REGISTRAR UN PACIENTE
PASO 1: Haz clic en el botón azul "Registrar Paciente"
PASO 2: Ingresa el DNI
● Escribe SOLO números (ejemplo: 12345678)
● NO pongas letras ni símbolos
● Haz clic en "Aceptar"
PASO 3: Si el DNI es INCORRECTO:
● Aparece mensaje: "DNI inválido. Debe contener solo números"
● Haz clic en "Aceptar"
● Vuelve al PASO 2
PASO 4: Si el DNI es CORRECTO, ingresa el NOMBRE
● Escribe SOLO letras (ejemplo: Juan)
● NO pongas números
● Haz clic en "Aceptar"
PASO 5: Si el NOMBRE es INCORRECTO:
● Aparece mensaje: "Nombre inválido. No debe contener números"
● Haz clic en "Aceptar"
● Vuelve al PASO 4
PASO 6: Si el NOMBRE es CORRECTO, ingresa el APELLIDO
● Escribe SOLO letras (ejemplo: Pérez)
● NO pongas números
● Haz clic en "Aceptar"
PASO 7: Si el APELLIDO es INCORRECTO:
● Aparece mensaje: "Apellido inválido. No debe contener números"
● Haz clic en "Aceptar"
● Vuelve al PASO 6
PASO 8: Si TODOS los datos son CORRECTOS:
● Aparece mensaje: " Paciente registrado exitosamente"

● Se muestra automáticamente la lista actualizada de pacientes
PASO 9: Haz clic en "Aceptar"
CÓMO REGISTRAR UN MÉDICO
PASO 1: Haz clic en el botón azul "Registrar Médico"
PASO 2: Ingresa la MATRÍCULA
● Escribe SOLO números (ejemplo: 54321)
● NO pongas letras ni símbolos
● Haz clic en "Aceptar"
PASO 3: Si la MATRÍCULA es INCORRECTA:
● Aparece mensaje: " Matrícula inválida. Debe contener solo números"
● Haz clic en "Aceptar"
● Vuelve al PASO 2
PASO 4: Si la MATRÍCULA es CORRECTA, ingresa el NOMBRE
● Escribe SOLO letras (ejemplo: María)
● NO pongas números
● Haz clic en "Aceptar"
PASO 5: Si el NOMBRE es INCORRECTO:
● Aparece mensaje: " Nombre inválido. No debe contener números"
● Haz clic en "Aceptar"
● Vuelve al PASO 4
PASO 6: Si el NOMBRE es CORRECTO, ingresa el APELLIDO
● Escribe SOLO letras (ejemplo: González)
● NO pongas números
● Haz clic en "Aceptar"
PASO 7: Si el APELLIDO es INCORRECTO:
● Aparece mensaje: "Apellido inválido. No debe contener números"
● Haz clic en "Aceptar"
● Vuelve al PASO 6
PASO 8: Si el APELLIDO es CORRECTO, selecciona la ESPECIALIDAD
● Aparece una lista desplegable
● Elige una opción:
○ CLINICA
○ PEDIATRIA

○ CARDIOLOGIA
○ DERMATOLOGIA
○ GINECOLOGIA
● Haz clic en "Aceptar"
PASO 9: Si TODOS los datos son CORRECTOS:
● Aparece mensaje: " Médico registrado exitosamente"
● Se muestra automáticamente la lista actualizada de médicos
PASO 10: Haz clic en "Aceptar"

CÓMO REGISTRAR UN TURNO
PASO 1: Verifica que haya pacientes y médicos registrados
● Si NO hay pacientes: Aparece "No hay pacientes registrados. Primero debe registrar
al menos un paciente"
● Si NO hay médicos: Aparece "No hay médicos registrados. Primero debe registrar al
menos un médico"
● En ambos casos, primero debes registrar pacientes y/o médicos
PASO 2: Haz clic en el botón azul "Registrar Turno"
PASO 3: Selecciona el PACIENTE
● Aparece la lista de pacientes con números
● Escribe el NÚMERO del paciente que deseas (ejemplo: 1)
● Haz clic en "Aceptar"
PASO 4: Selecciona el MÉDICO
● Aparece la lista de médicos con números
● Escribe el NÚMERO del médico que deseas (ejemplo: 2)
● Haz clic en "Aceptar"
PASO 5: Se abre una ventana con un formulario que muestra:
● El nombre del paciente seleccionado
● El nombre del médico seleccionado
● Un calendario para seleccionar la FECHA
● Un reloj para seleccionar la HORA
PASO 6: Selecciona la FECHA
● Haz clic en el calendario
● Elige el día que desees

PASO 7: Selecciona la HORA
● Haz clic en el selector de hora
● Elige la hora del turno
PASO 8: Guarda el turno
● Haz clic en el botón "Guardar Turno"
PASO 9: Si todo es correcto:
● Aparece mensaje: "Turno registrado exitosamente" con los detalles
● Se muestra automáticamente la lista actualizada de turnos
PASO 10: Haz clic en "Aceptar"

CÓMO IR A LA PANTALLA DE INICIO
PASO 1: Haz clic en el botón "Inicio"
PASO 2: Aparece la pantalla de bienvenida con el nombre de la clínica

CÓMO SALIR DEL PROGRAMA
PASO 1: Haz clic en el botón "Salir"
PASO 2: El programa se cierra automáticamente

EJEMPLO COMPLETO: Primer Uso del Sistema
REGISTRAR EL PRIMER MÉDICO
PASO 1: Clic en "Registrar Médico"
PASO 2: Matrícula: escribe 12345 → Clic "Aceptar"
PASO 3: Nombre: escribe Juan → Clic "Aceptar"
PASO 4: Apellido: escribe Cruzado → Clic "Aceptar"
PASO 5: Especialidad: selecciona PEDIATRIA → Clic "Aceptar"

PASO 6: Mensaje: "✓ Médico registrado exitosamente" → Clic "Aceptar"

REGISTRAR EL PRIMER PACIENTE
PASO 1: Clic en "Registrar Paciente"
PASO 2: DNI: escribe 12345678 → Clic "Aceptar"
PASO 3: Nombre: escribe Joaco → Clic "Aceptar"
PASO 4: Apellido: escribe Ardevol → Clic "Aceptar"
PASO 5: Mensaje: "✓ Paciente registrado exitosamente" → Clic "Aceptar"

REGISTRAR EL PRIMER TURNO
PASO 1: Clic en "Registrar Turno"
PASO 2: Seleccionar paciente: escribe 1 → Clic "Aceptar"
PASO 3: Seleccionar médico: escribe 1 → Clic "Aceptar"
PASO 4: Se abre ventana con formulario
PASO 5: Selecciona fecha en el calendario: 15/12/2025
PASO 6: Selecciona hora: 10:30
PASO 7: Clic en "Guardar Turno"
PASO 8: Mensaje: "Turno registrado exitosamente" → Clic "Aceptar"

ERRORES COMUNES
Error con DNI:
● Escribiste: 1234ABC
● Debes escribir: 1234567
Error con Nombre:
● Escribiste: Juan123
● Debes escribir: Juan
Error con Matrícula:

● Escribiste: MAT123
● Debes escribir: 123

CONSEJOS
CONSEJO 1: Registra primero los médicos, luego los pacientes, después los turnos
CONSEJO 2: Si te equivocas al escribir, el sistema te lo dirá y podrás corregirlo
CONSEJO 3: Puedes registrar tantos pacientes, médicos y turnos como necesites
CONSEJO 4: Usa los botones del menú izquierdo para navegar entre secciones
CONSEJO 5: Siempre verifica que los datos estén correctos antes de hacer clic en
"Aceptar"
