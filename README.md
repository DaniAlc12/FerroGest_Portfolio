# FerroGest - Sistema de Gestión Ferroviaria

FerroGest es una aplicación backend de consola desarrollada en Java para la gestión integral de una flota de trenes. Este proyecto nace como una prueba de concepto para demostrar la aplicación práctica de Programación Orientada a Objetos (POO) avanzada, arquitecturas limpias y persistencia de datos relacional.

## 🚀 Tecnologías y Herramientas
* **Lenguaje:** Java 8+ (Implementación de API Stream y Lambdas)
* **Gestor de dependencias:** Maven
* **Base de Datos:** PostgreSQL
* **Acceso a Datos:** JDBC nativo
* **Testing:** JUnit 5 (Jupiter)

## 🏗️ Arquitectura y Patrones
El proyecto está diseñado bajo un enfoque modular y escalable:
* **Modelo-Vista-Controlador (MVC):** Separación estricta entre la interfaz de usuario (`Main`), la lógica de negocio (`GestorFerroviario`) y las entidades del dominio (`Tren`, `TrenPasajeros`, `TrenMercancias`).
* **Patrón DAO (Data Access Object):** Aislamiento total de las consultas SQL mediante la clase `TrenDAO`. El controlador de negocio desconoce por completo la existencia de la base de datos.
* **Polimorfismo aplicado:** Reducción del uso de `instanceof` en la capa de negocio, confiando en el contrato de la clase abstracta genérica.
* **Single Table Strategy:** Resolución del *Object-Relational Impedance Mismatch* almacenando la herencia de clases en una única tabla PostgreSQL utilizando una columna discriminadora.

## ⚙️ Características Técnicas Destacadas
1. **Programación Declarativa:** Sustitución de bucles imperativos tradicionales por la **API Stream** para realizar cálculos agregados de la flota de forma funcional y optimizada.
2. **Excepciones con Estado (Checked):** Implementación de excepciones personalizadas como `CapacidadExcedidaException`, diseñadas para encapsular internamente el estado exacto del fallo (ID del tren y cantidad excedida) garantizando un manejo seguro por parte del compilador.
3. **Gestión Segura de Recursos:** Uso intensivo de bloques `try-with-resources` para garantizar el cierre automático de las conexiones a la base de datos (`Connection`, `PreparedStatement`, `ResultSet`), previniendo *memory leaks*.
4. **Testing Automatizado:** Cobertura de las reglas de negocio críticas aplicando el patrón **AAA (Arrange, Act, Assert)**, utilizando aserciones matemáticas y captura de excepciones lambda mediante `assertThrows`.

## 🛠️ Instalación y Despliegue

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/FerroGest_Portfolio.git](https://github.com/tu-usuario/FerroGest_Portfolio.git)
   cd FerroGest_Portfolio
