package Primer_Final;

/**
 * Grupo 4
 * @author Leyda Aylen Fleitas Cabrera
 * @version 19/11/2024
 */
public class ClaseServicios {
    //Colocar como array las clave de los Servicios
    private int IDDeServicio; //Cada servicio tiene un ID único que le corresponde
    private double deudaServicio;

    public ClaseServicios(int IDDeServicio, double deudaServicio) {
        this.IDDeServicio = IDDeServicio;
        this.deudaServicio = deudaServicio;
    }

    public int getIDDeServicio() {
        return IDDeServicio;
    }

    public double getDeudaServicio() {
        return deudaServicio;
    }

    public void setIDDeServicio(int IDDeServicio) {
        this.IDDeServicio = IDDeServicio;
    }

    public void setDeudaServicio(double deudaServicio) {
        this.deudaServicio = deudaServicio;
    }    
}
