// Declaramos el nombre del paquete para esta clase
package com.example;

// Definimos la clase Transaccion
public class Transaccion {

    // Declaramos los atributos privados de la clase
    private int cedula; // Número de identificación
    private String tipo; // Tipo de transacción
    private int monto; // Monto de la transacción

    // Definimos el constructor de la clase, el cual recibe tres argumentos
    public Transaccion(int cedula, String tipo, int monto) {
        // Inicializamos los atributos de la clase con los valores recibidos como
        // argumentos
        this.cedula = cedula;
        this.tipo = tipo;
        this.monto = monto;
    }

    // Definimos el método de acceso público getCedula(), que permite obtener el
    // valor del atributo "cedula"
    public int getCedula() {
        return cedula;
    }

    // Definimos el método de acceso público getTipo(), que permite obtener el valor
    // del atributo "tipo"
    public String getTipo() {
        return tipo;
    }

    // Definimos el método de acceso público getMonto(), que permite obtener el
    // valor del atributo "monto"
    public int getMonto() {
        return monto;
    }
}
