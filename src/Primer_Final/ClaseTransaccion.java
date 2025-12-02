package Primer_Final;

/**
 * @author Elena Ramirez
 * @version 19/11/2024
 */

public class ClaseTransaccion {
    private double monto;
    private String fecha;
    private String descripcion; 


    public ClaseTransaccion(double monto, String fecha, String descripcion) {
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;

    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
  

}
