package Primer_Final;

/**
 * Grupo 4
 * @author Leyda Aylen Fleitas Cabrera
 * @version 19/11/2024
 */
public class ClaseTarjeta {
    private String numeroDeTarjeta;
    private String fechaExpiracion;
    private String codigoCVV;
    private String tipoDeTarjeta;

    public ClaseTarjeta(String numeroDeTarjeta, String fechaExpiracion, String codigoCVV, String tipoDeTarjeta) {
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.codigoCVV = codigoCVV;
        this.tipoDeTarjeta = tipoDeTarjeta;
    }

    public String getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public String getCodigoCVV() {
        return codigoCVV;
    }

    public String getTipoDeTarjeta() {
        return tipoDeTarjeta;
    }
    

    public void setNumeroDeTarjeta(String numeroDeTarjeta) {
        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public void setCodigoCVV(String codigoCVV) {
        this.codigoCVV = codigoCVV;
    }

    public void setTipoDeTarjeta(String tipoDeTarjeta) {
        this.tipoDeTarjeta = tipoDeTarjeta;
    }
    
    @Override
    public String toString(){
        return String.format("Número de Tarjeta: %s%nTipo de Tarjeta: %s%n",
                getNumeroDeTarjeta(),
                getTipoDeTarjeta());
    }
}
