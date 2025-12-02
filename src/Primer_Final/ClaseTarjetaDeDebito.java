package Primer_Final;

/**
 * Grupo 4
 * @author Leyda Aylen Fleitas Cabrera
 * @version 19/11/2024
 */
public class ClaseTarjetaDeDebito extends ClaseTarjeta{
    private double saldoCuenta;

    public ClaseTarjetaDeDebito(double saldoCuenta, String numeroDeTarjeta, String fechaExpiracion, String codigoCVV, String tipoDeTarjeta) {
        super(numeroDeTarjeta, fechaExpiracion, codigoCVV, tipoDeTarjeta);
        this.saldoCuenta = saldoCuenta;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void descontarPago(double monto){
        if (saldoCuenta >= monto) {
            saldoCuenta -= monto;
        }else {
            System.out.println("Saldo insuficiente en la tarjeta.");
        }
    }
}
