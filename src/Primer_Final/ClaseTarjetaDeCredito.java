package Primer_Final;

/**
 * Grupo 4
 * @author Leyda Aylen Fleitas Cabrera
 * @version 19/11/2024
 */
public class ClaseTarjetaDeCredito extends ClaseTarjeta{
    
    private double limiteCredito;
    private double deudaActual;
    private double saldoDisponible;

    public ClaseTarjetaDeCredito(double limiteCredito, double deudaActual, double saldoDisponible, String numeroDeTarjeta, String fechaExpiracion, String codigoCVV, String tipoDeTarjeta) {
        super(numeroDeTarjeta, fechaExpiracion, codigoCVV, tipoDeTarjeta);
        this.limiteCredito = limiteCredito;
        this.deudaActual = deudaActual;
        this.saldoDisponible = saldoDisponible;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public double getDeudaActual() {
        return deudaActual;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public void setDeudaActual(double deudaActual) {
        this.deudaActual = deudaActual;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public void descontarPago(double monto){
        if (saldoDisponible >= monto) {
            saldoDisponible -= monto;
        }else {
            System.out.println("Saldo insuficiente en la tarjeta.");
        }
    }
}
