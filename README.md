# Sistema de Gestión de Hospital
## Descripción
Sistema desarrollado en Java para la gestión básica de un hospital mediante consola.
El proyecto permite administrar pacientes, médicos, citas médicas y tratamientos, aplicando principios de Programación Orientada a Objetos (POO) como encapsulamiento, herencia, polimorfismo y abstracción.
Además, incorpora validaciones de datos, control de disponibilidad médica, cálculo de costos de tratamientos y generación de reportes administrativos.

---
## Objetivos del Proyecto
- Gestionar pacientes y médicos.
- Administrar citas médicas.
- Registrar tratamientos médicos.
- Mantener historiales de pacientes.
- Calcular costos de tratamientos.
- Generar reportes hospitalarios.
- Aplicar conceptos de Programación Orientada a Objetos.
---
## Funcionalidades
### 1. Validación General de Datos
Permite validar:
- Cédulas.
- Teléfonos.
- Nombres.
- Edades.
- Identificadores.
- Fechas.
- Horas.
- Costos.
- Duración de tratamientos.
- Número de sesiones.
---
### 2. Gestión de Pacientes
- Registrar pacientes.
- Buscar pacientes por cédula.
- Modificar información de pacientes.
- Listar pacientes registrados.
Datos gestionados:
- Cédula.
- Nombre completo.
- Edad.
- Teléfono.
- Tipo de seguro.
---
### 3. Gestión de Médicos y Disponibilidad
- Registrar médicos.
- Buscar médicos.
- Modificar médicos.
- Consultar disponibilidad.
- Filtrar médicos por especialidad.
- Listar médicos registrados.
Datos gestionados:
- Cédula.
- Nombre.
- Especialidad.
- Horario de atención.
---
### 4. Gestión de Citas Médicas
- Registrar citas.
- Buscar citas.
- Modificar citas.
- Cancelar citas.
- Marcar citas como atendidas.
- Listar citas registradas.
Estados de las citas:
- PROGRAMADA
- ATENDIDA
- CANCELADA
---
### 5. Gestión de Tratamientos
Tipos de tratamientos implementados:
#### Cirugía
Calcula el costo mediante:
Costo Base + Materiales
#### Terapia
Calcula el costo mediante:
Costo por Sesión × Número de Sesiones
#### Medicación
Calcula el costo mediante:
Costo Diario × Duración
Operaciones disponibles:
- Registrar tratamientos.
- Calcular costos.
- Listar tratamientos.
---
### 6. Historial y Costos por Paciente
Permite:
- Consultar tratamientos asociados a un paciente.
- Visualizar historial médico.
- Calcular costo total acumulado.
---
### 7. Reportes del Hospital
Genera reportes de:
- Citas atendidas por especialidad.
- Ingresos totales por tratamientos.
- Ingresos por tipo de tratamiento.
---
## Tecnologías Utilizadas
### Lenguaje
- Java
### Paradigma
- Programación Orientada a Objetos (POO)
### Herramientas
- NetBeans IDE
- Apache Ant
### Librerías Utilizadas
- ArrayList
- LocalDate
- LocalTime
- Scanner
- Excepciones personalizadas
---
## Conceptos de POO Aplicados
### Encapsulamiento
Uso de atributos privados con métodos getter y setter.
### Herencia
La clase abstracta `Tratamiento` es heredada por:
- Cirugia
- Terapia
- Medicacion
### Polimorfismo
Cada tipo de tratamiento implementa su propia versión de:
```java
calcularCosto()
```
### Abstracción
Uso de clases abstractas y separación de responsabilidades mediante gestores y menús.
---
## Estructura General del Proyecto
```text
src
│
├── validaciongeneraldedatos
├── gestiondepacientes
├── gestiondemedicosydisponibilidadbasica
├── gestiondecitasmedicas
├── gestiondetratamientos
├── historialycostosporpaciente
├── reportesdelhospital
└── sistemadegestiondehospitalproyecto
```
---
## Requisitos
- Java JDK 17 o superior
- NetBeans IDE (recomendado)
- Apache Ant
---
## Instrucciones de Ejecución
### Desde NetBeans
1. Abrir NetBeans.
2. Seleccionar:
```text
File → Open Project
```
3. Abrir la carpeta del proyecto.
4. Ejecutar:
```text
Clean and Build Project
```
5. Verificar que aparezca:
```text
BUILD SUCCESSFUL
```
6. Ejecutar:
```text
Run Project
```
7. Se mostrará el menú principal del sistema.
---
### Desde Línea de Comandos
Ubicarse en la carpeta del proyecto:
```bash
cd SistemadeGestiondeHospitalProyecto
```
Compilar:
```bash
ant clean jar
```
Ejecutar:
```bash
java -jar dist/SistemadeGestiondeHospitalProyecto.jar
```
---
## Menú Principal
```text
1. Validacion general de datos
2. Gestion de pacientes
3. Gestion de medicos y disponibilidad
4. Gestion de citas medicas
5. Gestion de tratamientos
6. Historial y costos por paciente
7. Reportes del hospital
8. Salir del sistema
```
---
## Autores
Grupo 7

- María Durango
- Martín Suazo
- Joel Tomalá
  
Proyecto desarrollado con fines académicos y educativos.
