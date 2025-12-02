# Sistema de Web Banking – Grupo 4

Proyecto académico desarrollado para la cátedra **Lenguaje de Programación II** (3.er semestre) de la **Facultad Politécnica – UNA**.  
El sistema implementa un **Web Banking de escritorio** que permite a los usuarios gestionar sus cuentas bancarias y realizar operaciones básicas mediante una interfaz gráfica.

---

## 1. Descripción general

El sistema permite a un cliente:

- Consultar el **saldo** de sus cuentas.
- Realizar **depósitos**.
- Efectuar **transferencias** entre cuentas.
- Pagar **servicios** (ANDE, ESSAP, JAHA, IPS).
- Pagar la **deuda de una tarjeta de crédito**.
- Ingresar al sistema mediante **inicio de sesión con token**.

Los principales objetivos son:

- Proporcionar una interfaz sencilla y clara para el usuario final.
- Implementar mecanismos básicos de seguridad (token, PIN y validaciones).
- Organizar el código bajo un **modelo orientado a objetos**, permitiendo futuras extensiones.

---

## 2. Funcionalidades

### 2.1 Inicio de sesión

- Ventanas principales:
  - Acceso al sistema (menú inicial).
  - Inicio de sesión.
- Datos requeridos:
  - Número de cuenta.
  - Token de seguridad.
- Validaciones:
  - Ambos campos son obligatorios.
  - Verificación de que la cuenta exista.
  - Verificación de coincidencia del token.
- Flujo:
  - En caso de éxito, se muestra el **menú principal**.
  - En caso de error, se muestra un mensaje explicativo.
  - El usuario puede cancelar y regresar a la pantalla anterior.

---

### 2.2 Consulta de saldo y depósitos

- Permite:
  - Consultar el **saldo actual** de una cuenta mediante su ID.
  - Realizar **depósitos** a la cuenta.
- Comportamiento:
  - El sistema obtiene el saldo desde un archivo o estructura de datos persistida.
  - Tras un depósito, el saldo se actualiza y se guarda el nuevo valor.
- Validaciones:
  - El ID de cuenta debe existir.
  - El monto a depositar debe ser numérico y mayor que cero.
- Interacción:
  - Uso de cuadros de diálogo (`JOptionPane`) para informar saldos, confirmaciones y errores.

---

### 2.3 Transferencias entre cuentas

- Datos requeridos:
  - ID de cuenta de origen.
  - ID de cuenta de destino.
  - Monto a transferir.
  - PIN de transacción.
- Proceso:
  1. El usuario ingresa las cuentas y el monto.
  2. El sistema solicita el PIN de transacción.
  3. Se valida:
     - Existencia de ambas cuentas.
     - Monto positivo.
     - Saldo suficiente en la cuenta de origen.
     - PIN correcto.
  4. Si todo es correcto:
     - Se descuenta el monto en la cuenta de origen.
     - Se acredita en la cuenta de destino.
     - Se actualizan los registros persistidos.
- Acciones disponibles:
  - Confirmar transferencia.
  - Visualizar comprobante.
  - Cancelar (limpieza de campos).
  - Cerrar sesión.

---

### 2.4 Pago de servicios

- Servicios contemplados:
  - ANDE
  - ESSAP
  - JAHA
  - IPS
- Interfaz:
  - Lista desplegable (`JComboBox`) para elegir el servicio.
  - Campos de texto para:
    - Monto a pagar.
    - ID de la cuenta.
- Validaciones:
  - Selección obligatoria de un servicio.
  - Monto numérico y, cuando aplique, cumplimiento de monto mínimo.
  - ID de cuenta no vacío y válido.
- Resultado:
  - Registro del pago en el sistema.
  - Emisión de comprobante (fecha, servicio, monto, cuenta), mostrado mediante un cuadro de diálogo.

---

### 2.5 Pago de tarjeta de crédito

- Permite cancelar total o parcialmente la deuda de una tarjeta de crédito asociada a una cuenta.
- Datos requeridos:
  - ID de la cuenta asociada.
  - Monto a pagar.
- Validaciones:
  - Monto numérico y mayor que cero.
  - El monto no debe superar la deuda pendiente.
  - La cuenta debe poseer saldo suficiente.
- Resultado:
  - Disminución de la deuda de la tarjeta.
  - Descuento del monto en la cuenta.
  - Mensaje de confirmación con datos actualizados.

---

## 3. Arquitectura y tecnologías

- **Lenguaje:** Java (Java SE).
- **Interfaz gráfica:** Swing (`JFrame`, `JPanel`, `JButton`, `JTextField`, `JComboBox`, `JOptionPane`, entre otros).
- **Persistencia:**
  - Manejo de datos mediante **archivos** que simulan una base de datos.
  - Clase encargada de la lectura/escritura centralizada (por ejemplo, `Archivos` o equivalente).
- **Modelo orientado a objetos:**
  - Clases de dominio (nombres referenciales, pueden variar en la implementación real):
    - `Cuenta`
    - `Cliente`
    - `Servicios`
    - `Tarjeta`
    - `TarjetaDeDebito`
    - `TarjetaDeCredito`
    - `Transaccion`
    - `Comprobante`
  - Clases de persistencia:
    - `Archivos` u otra clase responsable del acceso a datos.
  - Clases de interfaz de usuario:
    - Acceso al sistema.
    - Inicio de sesión.
    - Menú principal.
    - Pantalla de pago de servicios.
    - Pantalla de pago de tarjeta.
    - Pantalla de consulta de saldo y depósito.
    - Pantalla de transferencia.

---

## 4. Estructura sugerida del proyecto

> La estructura puede variar según el IDE y la organización del repositorio. A continuación se presenta una estructura referencial.

```text
/Proyecto-WebBanking
├── /src
│   ├── modelo
│   │   ├── Cuenta.java
│   │   ├── Cliente.java
│   │   ├── Servicios.java
│   │   ├── Tarjeta.java
│   │   ├── TarjetaDeDebito.java
│   │   ├── TarjetaDeCredito.java
│   │   ├── Transaccion.java
│   │   └── Comprobante.java
│   ├── persistencia
│   │   └── Archivos.java
│   └── vista
│       ├── VentanaAccederAlSistema.java
│       ├── VentanaInicioSesion.java
│       ├── MenuPrincipal.java
│       ├── PagoServicios.java
│       ├── PagoTarjeta.java
│       ├── SaldoYDeposito.java
│       └── TransaccionUI.java
└── README.md

Equipo de desarrollo

Proyecto elaborado por el Grupo 4:

Leyda Aylen Fleitas Cabrera
Desarrollo de clases de dominio: Comprobante, Cuenta, Servicios, Tarjeta, TarjetaDeDebito, TarjetaDeCredito, Cliente, Archivos.
Implementación de interfaces: pago de servicios, pago de tarjeta, consulta de saldo y depósito.

Andrea Lujan Núñez Rodríguez
Diseño de todas las interfaces.
Codificación de menús principal e inicial.
Generación de documentación Javadoc y archivo ejecutable (.jar).

Elena Gisselle Ramirez Medina
Desarrollo de la clase Transaccion.
Implementación de la interfaz de transacciones.

Baian Juan Pablo Romero Gimenez
Interfaz de acceso al sistema.
Interfaz de inicio de sesión.
Interfaz para creación de nueva cuenta.
