package Primer_Final;

/**
 * Grupo 4
 * @author Leyda Aylen Fleitas Cabrera
 * @version 19/11/2024
 */

import Primer_Final.ClaseServicios;
import Primer_Final.ClaseTarjeta;
import Primer_Final.ClaseTarjetaDeCredito;
import Primer_Final.ClaseTarjetaDeDebito;
import Primer_Final.ClaseCliente;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

/* Trabajar con los archivos. Guardar en un array las deudas de los servicios, 
en otro array los ID, en el pago de Servicios agarra directo la deuda y 
descuenta de la tarjeta a pagar del usuario
*/

public class ClaseCuenta {
    private int ID;
    private ArrayList<ClaseTarjetaDeCredito> tarjetasDeCredito;
    private ArrayList<ClaseTarjetaDeDebito> tarjetasDeDebito;
    private double saldo; 
    private int tokenActual; 

    public ClaseCuenta(int ID, ArrayList<ClaseTarjetaDeCredito> tarjetasDeCredito, ArrayList<ClaseTarjetaDeDebito> tarjetasDeDebito, double saldo, int tokenActual) {
        this.ID = ID;
        this.tarjetasDeCredito = tarjetasDeCredito;
        this.tarjetasDeDebito = tarjetasDeDebito;
        this.saldo = saldo;
        this.tokenActual = tokenActual;
    }
    
    public void setTarjeta(ClaseTarjeta tarjeta) {
        if (tarjeta instanceof ClaseTarjetaDeCredito) {
            this.tarjetasDeCredito.add((ClaseTarjetaDeCredito) tarjeta);
        } else if (tarjeta instanceof ClaseTarjetaDeDebito) {
            this.tarjetasDeDebito.add((ClaseTarjetaDeDebito) tarjeta);
        }
    }

    public int getID() {
        return ID;
    }
    public ArrayList<ClaseTarjetaDeCredito> getTarjetasDeCredito() {
        return tarjetasDeCredito;
    }

    public ArrayList<ClaseTarjetaDeDebito> getTarjetasDeDebito() {
        return tarjetasDeDebito;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTarjetaDeCredito(ClaseTarjetaDeCredito tarjeta) {
        tarjetasDeCredito.add(tarjeta);
    }

    public void setTarjetaDeDebito(ClaseTarjetaDeDebito tarjeta) {
        tarjetasDeDebito.add(tarjeta);
    }
    
    
    public double getSaldo() {
    return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getTokenActual() {
        return tokenActual;
    }

    public void setTokenActual(int tokenActual) {
        this.tokenActual = tokenActual;
    }
    
}
