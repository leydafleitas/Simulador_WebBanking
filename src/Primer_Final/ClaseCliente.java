package Primer_Final;

/**
 * Grupo 4
 * @author Leyda Aylen Fleitas Cabrera
 * @version 19/11/2024
 */
import java.util.ArrayList;
import java.util.List;

public class ClaseCliente {
    private String nombre;
    private String apellido;
    private String numeroDeCedula;
    private String telefono;
    private String pinTransaccion;
    private List<ClaseCuenta> cuentas;  // Lista de cuentas

    public ClaseCliente(String nombre, String apellido, String numeroDeCedula, String telefono, String pinTransaccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDeCedula = numeroDeCedula;
        this.telefono = telefono;
        this.pinTransaccion = pinTransaccion;
        this.cuentas = new ArrayList<>();  // Inicializar la lista de cuentas
    }

    // Métodos getter y setter básicos
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroDeCedula() {
        return numeroDeCedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumeroDeCedula(String numeroDeCedula) {
        this.numeroDeCedula = numeroDeCedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPinTransaccion() {
        return pinTransaccion;
    }

    public void setPinTransaccion(String pinTransaccion) {
        this.pinTransaccion = pinTransaccion;
    }

    // Métodos relacionados con las cuentas
    public List<ClaseCuenta> getCuentas() {
        return new ArrayList<>(cuentas);  // Retornar una copia de la lista de cuentas
    }

    public void agregarCuenta(ClaseCuenta cuenta) {
        if (cuenta != null) {
            cuentas.add(cuenta);
        }
    }

    public void eliminarCuenta(ClaseCuenta cuenta) {
        cuentas.remove(cuenta);
    }

    public ClaseCuenta obtenerCuentaPorID(int id) {
        for (ClaseCuenta cuenta : cuentas) {
            if (cuenta.getID() == id) {
                return cuenta;
            }
        }
        return null;  // Retornar null si no se encuentra la cuenta
    }

}
