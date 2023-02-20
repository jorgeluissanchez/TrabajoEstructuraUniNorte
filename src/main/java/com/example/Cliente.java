// Indicamos que esta clase pertenece al paquete "com.example"
package com.example;

// Definimos la clase Cliente
public class Cliente {

    // Declaramos tres variables miembro privadas
    private int cedula; // Número de cédula del cliente
    private String nombre; // Nombre del cliente
    private int saldo; // Saldo de la cuenta del cliente

    // Constructor de la clase Cliente, que recibe tres parámetros
    public Cliente(int cedula, String nombre, int saldo) {
        // Asignamos los parámetros a las variables miembro
        this.cedula = cedula;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    // Método para obtener la cédula del cliente
    public int getCedula() {
        return cedula;
    }

    // Método para obtener el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    // Método para obtener el saldo de la cuenta del cliente
    public int getSaldo() {
        return saldo;
    }

    // Método para depositar dinero en la cuenta del cliente
    public void depositar(int monto) {
        saldo += monto; // Añadimos el monto al saldo actual
    }

    // Método para retirar dinero de la cuenta del cliente
    public boolean retirar(int monto) {
        if (saldo >= monto) { // Si hay suficiente saldo en la cuenta
            saldo -= monto; // Restamos el monto al saldo actual
            return true; // Devolvemos true para indicar que se pudo retirar el dinero
        } else {
            return false; // Devolvemos false para indicar que no se pudo retirar el dinero
        }
    }
}
