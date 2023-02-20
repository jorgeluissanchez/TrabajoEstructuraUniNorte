package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Banco {

    private List<Cliente> clientes;
    private List<Transaccion> transacciones;

    // Constructor de la clase Banco
    public Banco() {
        clientes = new ArrayList<>();
        transacciones = new ArrayList<>();
    }

    // Método para obtener la lista de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }

    // Método para obtener la lista de transacciones
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    // Método para inicializar los datos del banco
    public void inicializar() {
        InicializacionDatos inicializacionDatos = new InicializacionDatos();
        inicializacionDatos.inicializar();
        clientes = inicializacionDatos.getClientes();
        transacciones = inicializacionDatos.getTransacciones();
    }

    // Método para ejecutar las transacciones pendientes
    public void ejecutarTransacciones() throws IOException {
        // Iterar sobre las transacciones pendientes
        for (Transaccion transaccion : transacciones) {
            // Buscar el cliente correspondiente a la transacción
            Cliente cliente = buscarCliente(transaccion.getCedula());
            // Verificar si el cliente existe
            if (cliente != null) {
                // Ejecutar la transacción de consignación
                if (transaccion.getTipo().equals("consignacion")) {
                    cliente.depositar(transaccion.getMonto());
                    System.out.println("Consignación de " + transaccion.getMonto()
                            + " realizada con éxito para el cliente " + cliente.getNombre());
                    // Ejecutar la transacción de retiro
                } else if (transaccion.getTipo().equals("retiro")) {
                    if (cliente.retirar(transaccion.getMonto())) {
                        System.out.println("Retiro de " + transaccion.getMonto()
                                + " realizado con éxito para el cliente " + cliente.getNombre());
                    } else {
                        System.out.println("No se pudo realizar el retiro de " + transaccion.getMonto()
                                + " para el cliente " + cliente.getNombre() + " por falta de fondos.");
                    }
                }
            }
        }
        // Limpiar la lista de transacciones pendientes
        transacciones.clear();
        // Guardar las transacciones y clientes actualizados en un archivo Excel
        guardarTransacciones();
        guardarClientes();
    }

    // Método para eliminar una transacción
    public void eliminarTransaccion(int cedula, String tipo, int monto) throws IOException {
        // Iterar sobre las transacciones pendientes
        for (int i = 0; i < transacciones.size(); i++) {
            Transaccion transaccion = transacciones.get(i);
            // Verificar si la transacción coincide con los parámetros especificados
            if (transaccion.getCedula() == cedula &&
                    transaccion.getMonto() == monto &&
                    transaccion.getTipo().equals(tipo)) {
                // Eliminar la transacción de la lista
                transacciones.remove(i);
                System.out.println("Transacción eliminada con éxito para el cliente con cédula " + cedula);
                guardarTransacciones();
                return;
            }
        }
        System.out.println("No se encontró la transacción para el cliente con cédula " + cedula + ", tipo " + tipo
                + " y monto " + monto);
        guardarTransacciones();
    }

    // Metodo para eliminar un cliente
    public void eliminarCliente(int cedula) throws IOException {
        // Busca el cliente a eliminar en la lista de clientes
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.getCedula() == cedula) {
                // Elimina el cliente de la lista de clientes
                clientes.remove(i);
                // Busca todas las transacciones asociadas al cliente y las elimina
                for (int j = 0; j < transacciones.size(); j++) {
                    Transaccion transaccion = transacciones.get(j);
                    if (transaccion.getCedula() == cedula) {
                        transacciones.remove(j);
                    }
                }
                // Imprime un mensaje indicando que el cliente ha sido eliminado con éxito
                System.out.println("Cliente eliminado con éxito con cédula " + cedula);
                // Guarda las transacciones y los clientes en archivos Excel
                guardarTransacciones();
                guardarClientes();
                return;
            }
        }
        // Si el cliente no se encuentra en la lista de clientes, imprime un mensaje
        // indicando que no se encontró
        System.out.println("No se encontró al cliente con cédula " + cedula);
    }

    // Método para guardar transacciones en un archivo de salida Excel
    private void guardarTransacciones() throws IOException {
        // Crear un objeto de libro de trabajo y una hoja de cálculo para las
        // transacciones
        Workbook salidaWorkbook = WorkbookFactory.create(true);
        Sheet salidaSheet = salidaWorkbook.createSheet("Transacciones");
        // Crear una fila para el encabezado y agregar columnas para "Cédula", "Tipo" y
        // "Monto"
        Row headerRow = salidaSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Cedula");
        headerRow.createCell(1).setCellValue("Tipo");
        headerRow.createCell(2).setCellValue("Monto");
        // Escribir cada transacción en una fila separada en la hoja de cálculo de
        // salida
        int rowNum = 1;
        for (Transaccion transaccion : transacciones) {
            Row row = salidaSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(transaccion.getCedula());
            row.createCell(1).setCellValue(transaccion.getTipo());
            row.createCell(2).setCellValue(transaccion.getMonto());
        }
        // Guardar el archivo de salida de transacciones en el directorio "output"
        try (FileOutputStream outputStream = new FileOutputStream("output/transacciones.xlsx")) {
            salidaWorkbook.write(outputStream);
        }
    }

    // Método para guardar clientes en un archivo de salida Excel
    private void guardarClientes() throws IOException {
        // Crear un objeto de libro de trabajo y una hoja de cálculo para los clientes
        Workbook salidaWorkbook = WorkbookFactory.create(true);
        Sheet salidaSheet = salidaWorkbook.createSheet("Clientes");
        // Crear una fila para el encabezado y agregar columnas para "Cédula", "Nombre"
        // y "Saldo"
        Row headerRow = salidaSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Cedula");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Saldo");
        // Escribir cada cliente en una fila separada en la hoja de cálculo de salida
        int rowNum = 1;
        for (Cliente cliente : clientes) {
            Row row = salidaSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cliente.getCedula());
            row.createCell(1).setCellValue(cliente.getNombre());
            row.createCell(2).setCellValue(cliente.getSaldo());
        }
        // Guardar el archivo de salida de clientes en el directorio "output"
        try (FileOutputStream outputStream = new FileOutputStream("output/clientes.xlsx")) {
            salidaWorkbook.write(outputStream);
        }
    }

    // Método para agregar un cliente
    public void agregarCliente(int cedula, String nombre) throws IOException {
        // Se crea un nuevo cliente con la cedula, nombre y saldo inicial de 0
        Cliente nuevoCliente = new Cliente(cedula, nombre, 0);
        // Se agrega el nuevo cliente a la lista de clientes
        clientes.add(nuevoCliente);
        // Se guarda la lista de clientes en un archivo
        guardarClientes();

        System.out.println("Se ha agregado un nuevo cliente con exito");
    }

    // Método para agregar una transaccion
    public void agregarTransaccion(int cedula, String tipo, int monto) throws IOException {
        // Si es una consignación y el monto es mayor a $500.000 se le suma un bono de
        // $20.000
        if (tipo.equals("consignacion") && monto > 500000) {
            System.out.println("Ha ganado un bono de $20.000");
            monto += 20000;
        }
        // Se crea una nueva transacción con la cedula, tipo y monto
        Transaccion nuevaTransaccion = new Transaccion(cedula, tipo, monto);
        // Se agrega la nueva transacción a la lista de transacciones
        transacciones.add(nuevaTransaccion);
        // Se guarda la lista de transacciones en un archivo
        guardarTransacciones();

        System.out.println("Se ha agregado una nueva transaccion con exito");

    }

    // Método para buscar un clientes
    public Cliente buscarCliente(int cedula) {
        // Se busca un cliente en la lista de clientes que tenga la cédula dada
        for (Cliente cliente : clientes) {
            if (cliente.getCedula() == cedula) {
                return cliente; // Se retorna el cliente encontrado
            }
        }
        return null; // Si no se encuentra un cliente con esa cédula, se retorna null
    }

}
