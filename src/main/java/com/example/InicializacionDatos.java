package com.example;

import java.io.FileInputStream; // Importamos las clases necesarias de la librería Apache POI
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InicializacionDatos {
    private List<Cliente> clientes; // Lista de objetos Cliente
    private List<Transaccion> transacciones; // Lista de objetos Transaccion

    public InicializacionDatos() { // Constructor
        clientes = new ArrayList<>(); // Inicializamos la lista de clientes
        transacciones = new ArrayList<>(); // Inicializamos la lista de transacciones
    }

    public List<Cliente> getClientes() { // Método para obtener la lista de clientes
        return clientes;
    }

    public List<Transaccion> getTransacciones() { // Método para obtener la lista de transacciones
        return transacciones;
    }

    public void inicializar() { // Método para inicializar las listas de clientes y transacciones
        inicializarClientes(); // Llamamos al método para inicializar la lista de clientes
        inicializarTransacciones(); // Llamamos al método para inicializar la lista de transacciones
    }

    private void inicializarClientes() {
        try (FileInputStream file = new FileInputStream("base/clientesBase.xlsx")) {
            // Abrir archivo Excel de entrada y cargar la hoja de cálculo
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Recorrer todas las filas de la hoja de cálculo
            for (Row row : sheet) {
                // Ignorar la primera fila de encabezados
                if (row.getRowNum() == 0) {
                    continue;
                }
                // Leer valores de las celdas para crear un objeto Cliente
                Cell cedulaCell = row.getCell(0);
                Cell nombreCell = row.getCell(1);
                Cell saldoCell = row.getCell(2);
                if (cedulaCell != null && nombreCell != null && saldoCell != null) {
                    int cedula = (int) cedulaCell.getNumericCellValue();
                    String nombre = nombreCell.getStringCellValue();
                    int saldo = (int) saldoCell.getNumericCellValue();
                    Cliente cliente = new Cliente(cedula, nombre, saldo);
                    clientes.add(cliente);
                }
            }

            // Crear archivo de salida y cargar la hoja de cálculo
            Workbook salidaWorkbook = WorkbookFactory.create(true);
            Sheet salidaSheet = salidaWorkbook.createSheet("Clientes");

            // Escribir encabezados en la primera fila de la hoja de cálculo
            Row headerRow = salidaSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Cedula");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Saldo");

            // Escribir información de clientes en la hoja de cálculo
            int rowNum = 1;
            for (Cliente cliente : clientes) {
                Row row = salidaSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cliente.getCedula());
                row.createCell(1).setCellValue(cliente.getNombre());
                row.createCell(2).setCellValue(cliente.getSaldo());
            }

            // Guardar archivo de salida en la carpeta output
            try (FileOutputStream outputStream = new FileOutputStream("output/clientes.xlsx")) {
                salidaWorkbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarTransacciones() {
        try (FileInputStream file = new FileInputStream("base/transaccionesBase.xlsx")) {
            // Cargar la hoja de cálculo en el archivo
            Workbook workbook = WorkbookFactory.create(file);
            // Obtener la primera hoja de la hoja de cálculo
            Sheet sheet = workbook.getSheetAt(0);
            // Iterar a través de las filas de la hoja de cálculo
            for (Row row : sheet) {
                // Ignorar la primera fila de encabezados
                if (row.getRowNum() == 0) {
                    continue;
                }
                // Obtener las celdas de la fila
                Cell cedulaCell = row.getCell(0);
                Cell tipoCell = row.getCell(1);
                Cell montoCell = row.getCell(2);
                // Verificar que las celdas no estén vacías
                if (cedulaCell != null && tipoCell != null && montoCell != null) {
                    // Convertir los valores de las celdas a los tipos de datos apropiados
                    int cedula = (int) cedulaCell.getNumericCellValue();
                    String tipo = tipoCell.getStringCellValue();
                    int monto = (int) montoCell.getNumericCellValue();
                    // Crear un objeto de Transaccion con los valores convertidos
                    Transaccion transaccion = new Transaccion(cedula, tipo, monto);
                    // Agregar la transacción a la lista de transacciones
                    transacciones.add(transaccion);
                }
            }

            // Crear una nueva hoja de cálculo de salida
            Workbook salidaWorkbook = WorkbookFactory.create(true);
            Sheet salidaSheet = salidaWorkbook.createSheet("Transacciones");
            // Crear la fila de encabezado
            Row headerRow = salidaSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Cedula");
            headerRow.createCell(1).setCellValue("Tipo");
            headerRow.createCell(2).setCellValue("Monto");
            // Iterar a través de la lista de transacciones y agregarlas a la hoja de
            // cálculo de salida
            int rowNum = 1;
            for (Transaccion transaccion : transacciones) {
                Row row = salidaSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(transaccion.getCedula());
                row.createCell(1).setCellValue(transaccion.getTipo());
                row.createCell(2).setCellValue(transaccion.getMonto());
            }
            // Escribir la hoja de cálculo de salida en un archivo
            try (FileOutputStream outputStream = new FileOutputStream("output/transacciones.xlsx")) {
                salidaWorkbook.write(outputStream);
            }
        } catch (IOException e) {
            // Manejar cualquier excepción que se produzca durante la lectura o escritura de
            // archivos
            e.printStackTrace();
        }
    }

}
