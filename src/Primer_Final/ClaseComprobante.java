package Primer_Final;

/**
 * Grupo 4
 * @author Leyda Aylen Fleitas Cabrera
 * @version 19/11/2024
 */
public class ClaseComprobante {
    private double monto;
    private String fecha;
    private String descripcion;
    private String cuenta;

    public ClaseComprobante(double monto, String fecha, String descripcion, String cuenta) {
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cuenta = cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    //Datos para imprimir el comprobante luego de realizar una transacción
    @Override
    public String toString(){
        return String.format("Fecha: %s%nCuenta: %s%nMonto: %s%nDescripción: Pago de %s%n",
                getFecha(),
                getCuenta(),
                getMonto(),
                getDescripcion());        
    }
     
}
