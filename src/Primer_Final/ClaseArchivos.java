package Primer_Final;

import java.io.*;
import java.util.*;
import Primer_Final.ClaseCliente;
import Primer_Final.ClaseCuenta;
import Primer_Final.ClaseTarjetaDeCredito;
import Primer_Final.ClaseTarjetaDeDebito;
import javax.swing.JOptionPane;

public class ClaseArchivos {
    private static final String ARCHIVO_CLIENTES = "registroDeClientes.txt";
    private static List<ClaseCliente> clientes = new ArrayList<>();

    public static void cargarClientesDesdeArchivo() {
    // Utilizar la ruta relativa para el archivo
    String rutaArchivo = "src/Primer_Final/registroDeClientes.txt"; 

    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        ClaseCliente clienteActual = null;
        ClaseCuenta cuentaActual = null;

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            System.out.println("Leyendo línea: [" + linea + "]");

            if (linea.isEmpty()) {
                continue; // Saltar líneas vacías
            }

            if (linea.startsWith("Cliente:")) {
                System.out.println("Iniciando lectura de un nuevo cliente...");
                clienteActual = leerCliente(br);
                if (clienteActual != null) {
                    clientes.add(clienteActual);
                    System.out.println("Cliente cargado: " + clienteActual.getNombre() + " " + clienteActual.getApellido());
                } else {
                    System.out.println("No se pudo leer el cliente. Continuando con el siguiente...");
                }
            } else if (linea.startsWith("Cuenta:")) {
                if (clienteActual != null) {
                    cuentaActual = leerCuenta(linea, br);
                    if (cuentaActual != null) {
                        clienteActual.agregarCuenta(cuentaActual);
                        System.out.println("Cuenta cargada: " + cuentaActual.getID() + " con saldo: " + cuentaActual.getSaldo());
                    } else {
                        System.out.println("Error al leer la cuenta.");
                    }
                } else {
                    System.out.println("Error: Se encontró una cuenta sin un cliente asociado.");
                }
            } else if (linea.startsWith("Tarjeta de credito:") || linea.startsWith("Tarjeta de debito:")) {
                if (cuentaActual != null) {
                    ClaseTarjeta tarjeta = leerTarjeta(linea, br);
                    if (tarjeta != null) {
                        cuentaActual.setTarjeta(tarjeta);
                        System.out.println("Tarjeta cargada: " + tarjeta.getNumeroDeTarjeta() + " para la cuenta ID: " + cuentaActual.getID());
                    } else {
                        System.out.println("Error al leer la tarjeta.");
                    }
                } else {
                    System.out.println("Error: Se encontró una tarjeta sin una cuenta asociada.");
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("No se pudo encontrar el archivo: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }
}



private static ClaseCliente leerCliente(BufferedReader br) {
    try {
        String nombre = leerLineaConFormato(br, "Nombre");
        String apellido = leerLineaConFormato(br, "Apellido");
        String ci = leerLineaConFormato(br, "CI");
        String telefono = leerLineaConFormato(br, "Telefono");
        String pinTransaccion = leerLineaConFormato(br, "Pin Transaccion");
        return new ClaseCliente(nombre, apellido, ci, telefono, pinTransaccion);
    } catch (IOException e) {
        System.out.println("Error al leer el cliente: " + e.getMessage());
    }
    return null;
}

private static ClaseCuenta leerCuenta(String cuentaLinea, BufferedReader br) {
    try {
        String cuentaId = cuentaLinea.split(":")[1].trim();
        double saldo = Double.parseDouble(leerLineaConFormato(br, "Saldo"));
        String pinCuenta = leerLineaConFormato(br, "Pin Transaccion");
        return new ClaseCuenta(Integer.parseInt(cuentaId), new ArrayList<>(), new ArrayList<>(), saldo, Integer.parseInt(pinCuenta));
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error al leer la cuenta: " + e.getMessage());
    }
    return null;
}

private static ClaseTarjeta leerTarjeta(String tipoTarjeta, BufferedReader br) {
    try {
        String nro = leerLineaConFormato(br, "Nro");
        if (tipoTarjeta.startsWith("Tarjeta de credito")) {
            double limiteCredito = Double.parseDouble(leerLineaConFormato(br, "Limite Credito"));
            double deudaActual = Double.parseDouble(leerLineaConFormato(br, "Deuda"));
            String vencimiento = leerLineaConFormato(br, "Vencimiento");
            String cvc = leerLineaConFormato(br, "CVC");

            double saldoDisponible = limiteCredito - deudaActual;

            return new ClaseTarjetaDeCredito(limiteCredito, deudaActual, saldoDisponible, nro, vencimiento, cvc, "Crédito");
        } else if (tipoTarjeta.startsWith("Tarjeta de debito")) {
            double saldoCuenta = Double.parseDouble(leerLineaConFormato(br, "Saldo"));
            String vencimiento = leerLineaConFormato(br, "Vencimiento");
            String cvc = leerLineaConFormato(br, "CVC");

            return new ClaseTarjetaDeDebito(saldoCuenta, nro, vencimiento, cvc, "Débito");
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error al leer la tarjeta: " + e.getMessage());
    }
    return null;
}

private static String leerLineaConFormato(BufferedReader br, String campoEsperado) throws IOException {
    String linea = br.readLine();

    if (linea == null) {
        throw new IOException("Se esperaba el campo: " + campoEsperado + ", pero se llegó al final del archivo.");
    }

    linea = linea.trim();
    System.out.println("Leyendo línea para " + campoEsperado + ": [" + linea + "]");

    if (!linea.contains(":")) {
        throw new IOException("Formato incorrecto en el campo: " + campoEsperado + " (No se encontró el carácter `:`). Línea leída: [" + linea + "]");
    }

    String[] partes = linea.split(":", 2);
    if (partes.length < 2) {
        throw new IOException("Formato incorrecto para el campo: " + campoEsperado + " (No hay valor después de `:`). Línea leída: [" + linea + "]");
    }

    String valor = partes[1].trim();
    if (valor.isEmpty()) {
        throw new IOException("El campo: " + campoEsperado + " no tiene un valor asignado. Línea leída: [" + linea + "]");
    }

    return valor;
}



    
    //Actualizar archivo
    public static void actualizarArchivo() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES))) {
        for (ClaseCliente cliente : clientes) {
            bw.write("Cliente:\n");
            bw.write(cliente.getNombre() + "\n");
            bw.write(cliente.getApellido() + "\n");
            bw.write("CI: " + cliente.getNumeroDeCedula() + "\n");
            bw.write("Telefono: " + cliente.getTelefono() + "\n");
            bw.write("Pin Transaccion: " + cliente.getPinTransaccion() + "\n");

            for (ClaseCuenta cuenta : cliente.getCuentas()) {
                bw.write("Cuenta: " + cuenta.getID() + "\n");

                for (ClaseTarjetaDeCredito tarjetaCredito : cuenta.getTarjetasDeCredito()) {
                    bw.write("Tarjeta de credito:\n");
                    bw.write("Nro: " + tarjetaCredito.getNumeroDeTarjeta() + "\n");
                    bw.write("Saldo: " + tarjetaCredito.getSaldoDisponible() + "\n");
                    bw.write("Deuda: " + tarjetaCredito.getDeudaActual() + "\n");
                    bw.write("Vencimiento: " + tarjetaCredito.getFechaExpiracion() + "\n");
                    bw.write("CVC: " + tarjetaCredito.getCodigoCVV() + "\n");
                    bw.write("Limite Credito: " + tarjetaCredito.getLimiteCredito() + "\n");
                }

                for (ClaseTarjetaDeDebito tarjetaDebito : cuenta.getTarjetasDeDebito()) {
                    bw.write("Tarjeta de debito:\n");
                    bw.write("Nro: " + tarjetaDebito.getNumeroDeTarjeta() + "\n");
                    bw.write("Saldo: " + tarjetaDebito.getSaldoCuenta() + "\n");
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error al actualizar el archivo: " + e.getMessage());
    }
}

    // Método para buscar una cuenta por ID de cuenta
    public static ClaseCuenta buscarCuenta(String cuentaId) {
        // Validar que la lista de clientes no sea null o esté vacía
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No se han cargado clientes.");
            return null;
        }

        try {
            int idCuentaBuscada = Integer.parseInt(cuentaId); // Parsear el ID de la cuenta como entero

            // Recorrer los clientes y sus cuentas
            for (ClaseCliente cliente : clientes) {
                for (ClaseCuenta cuenta : cliente.getCuentas()) {
                    if (cuenta.getID() == idCuentaBuscada) {
                        return cuenta; // Devolver la cuenta si se encuentra el ID
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de cuenta no válido: " + cuentaId);
        }

        // Si no se encuentra la cuenta o ocurre un error
        System.out.println("No se encontró la cuenta con ID: " + cuentaId);
        return null;
    }


    // Método para validar el PIN de transacción para una cuenta específica
    public static boolean validarPinTransaccion(String cuentaId, String pin) {
        ClaseCuenta cuenta = buscarCuenta(cuentaId); // Busca la cuenta por ID
        if (cuenta != null) {
            ClaseCliente cliente = obtenerClientePorCuenta(cuenta);
            if (cliente != null && cliente.getPinTransaccion().equals(pin)) {
                return true;
            }
        }
        return false;
    }

    // Método para obtener el cliente que posee una cuenta específica
    public static ClaseCliente obtenerClientePorCuenta(ClaseCuenta cuenta) {
        for (ClaseCliente cliente : clientes) {
            if (cliente.getCuentas().contains(cuenta)) {
                return cliente;
            }
        }
        return null;
    }

    public static boolean transferirEntreCuentas(String cuentaOrigenId, String cuentaDestinoId, String pinTransaccion, double monto) {
        // Cargar los clientes desde el archivo en un array local
        List<ClaseCliente> clientesLocal = new ArrayList<>();
        cargarClientes(clientesLocal);

        // Buscar las cuentas de origen y destino
        ClaseCuenta cuentaOrigen = buscarCuentaLocal(clientesLocal, cuentaOrigenId);
        ClaseCuenta cuentaDestino = buscarCuentaLocal(clientesLocal, cuentaDestinoId);

        // Validaciones
        if (cuentaOrigen == null || cuentaDestino == null) {
            System.out.println("Una de las cuentas no existe.");
            return false;
        }
        if (!validarPinLocal(clientesLocal, cuentaOrigenId, pinTransaccion)) {
            System.out.println("PIN de transacción inválido.");
            return false;
        }
        if (cuentaOrigen.getSaldo() < monto) {
            System.out.println("Saldo insuficiente en la cuenta de origen.");
            return false;
        }

        // Realizar la transferencia
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        System.out.println("Transferencia exitosa.");

        return true;
    }

    // Método para cargar clientes en el array local
    private static void cargarClientes(List<ClaseCliente> clientesLocal) {
        String rutaArchivo = "src/Primer_Final/registroDeClientes.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            ClaseCliente clienteActual = null;
            ClaseCuenta cuentaActual = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                if (linea.startsWith("Cliente:")) {
                    clienteActual = leerCliente(br);
                    if (clienteActual != null) {
                        clientesLocal.add(clienteActual);
                    }
                } else if (linea.startsWith("Cuenta:")) {
                    if (clienteActual != null) {
                        cuentaActual = leerCuenta(linea, br);
                        if (cuentaActual != null) {
                            clienteActual.agregarCuenta(cuentaActual);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
        }
    }

    // Buscar una cuenta en el array local
    private static ClaseCuenta buscarCuentaLocal(List<ClaseCliente> clientesLocal, String cuentaId) {
        for (ClaseCliente cliente : clientesLocal) {
            for (ClaseCuenta cuenta : cliente.getCuentas()) {
                if (String.valueOf(cuenta.getID()).equals(cuentaId)) {
                    return cuenta;
                }
            }
        }
        return null;
    }

    // Validar el PIN de la cuenta en el array local
    private static boolean validarPinLocal(List<ClaseCliente> clientesLocal, String cuentaId, String pin) {
        ClaseCuenta cuenta = buscarCuentaLocal(clientesLocal, cuentaId);
        if (cuenta != null) {
            for (ClaseCliente cliente : clientesLocal) {
                if (cliente.getCuentas().contains(cuenta)) {
                    return cliente.getPinTransaccion().equals(pin);
                }
            }
        }
        return false;
    }
    
    public static boolean validarCuentaYPin(String cuentaId, String pinTransaccion) {
    // Validar que la lista de clientes no sea nula o esté vacía
    if (clientes == null || clientes.isEmpty()) {
        System.out.println("No se han cargado clientes.");
        return false;
    }

    try {
        // Buscar la cuenta en la lista de clientes
        ClaseCuenta cuenta = buscarCuenta(cuentaId);
        if (cuenta != null) {
            // Verificar si el PIN de la cuenta coincide
            String pinCuenta = String.valueOf(cuenta.getTokenActual());
            return pinCuenta.equals(pinTransaccion); 
        } else {
            System.out.println("La cuenta con ID " + cuentaId + " no fue encontrada.");
        }
    } catch (Exception e) {
        System.out.println("Error al validar la cuenta y el PIN: " + e.getMessage());
    }

    return false; // Si no se encuentra la cuenta o el PIN no coincide
}
    
}


