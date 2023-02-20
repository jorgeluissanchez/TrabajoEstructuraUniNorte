package com.example;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    /***************************************/
    /** RECOMIENDO LEER EL ARCHIVO README **/
    /** ANTES DE COMENZAR A VER EL CÓDIGO **/
    /***************************************/
    /**** Jorge Luis Sanchez Barreneche ****/
    /***************************************/

    public static void main(String[] args) throws IOException {
        System.out.println("**************************************************");
        System.out.println("**     RECOMIENDO LEER EL ARCHIVO README        **");
        System.out.println("**    ANTES DE COMENZAR A VER EL CÓDIGO         **");
        System.out.println("**************************************************");

        // Se crea un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        // Se crea un objeto Banco, que es donde se guardarán los datos de los clientes
        // y las transferencias
        Banco banco = new Banco();
        int n;
        // Se inicializa el banco con algunos datos de ejemplo
        banco.inicializar();
        int opcion = 0;
        do {
            // Se muestra el menú de opciones al usuario
            System.out.println("===== MENU =====");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar transferencia");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Eliminar transferencia");
            System.out.println("5. Ejecutar transacciones");
            System.out.println("6. Mostrar clientes");
            System.out.println("7. Mostrar transacciónes");
            System.out.println("8. Mostrar clientes y transacciónes");
            System.out.println("9. Salir");
            System.out.print("Ingrese la opción deseada: ");
            try {
                // Se lee la opción elegida por el usuario
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                int cedula = 0;
                String tipo = "";
                int monto = 0;
                // Se ejecuta un bloque switch que depende de la opción elegida por el usuario
                switch (opcion) {
                    case 1:
                        System.out.println("Ha elegido la opcion: Agregar cliente\n");
                        n = 1;
                        cedula = 0;
                        do {
                            try {
                                // Se lee la cédula del cliente a agregar
                                System.out.print("Ingrese la cédula del cliente: ");
                                cedula = scanner.nextInt();
                                // Se verifica si ya existe un cliente con esa cédula
                                if (banco.buscarCliente(cedula) == null) {
                                    // Se verifica si la cédula es válida
                                    if (cedula > 1000000 && cedula < 999999999) {
                                        n = 0;
                                    } else {
                                        System.out.println("En este campo debe"
                                                + " haber un numero de 7 a 9 digitos");
                                    }
                                } else {
                                    System.out.println("Ya existe un cliente con esta cedula");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar un número válido.");
                                scanner.nextLine(); // Limpiar buffer
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);
                        // Se lee el nombre del cliente a agregar
                        String nombre = "";
                        System.out.print("Ingrese el nombre del cliente: ");
                        nombre = scanner.next();
                        // Se agrega el cliente al banco
                        banco.agregarCliente(cedula, nombre);
                        break;
                    // Opción 2: Agregar transferencia
                    case 2:
                        System.out.println("Ha elegido la opcion: Agregar transferencia\n");

                        // Solicitar la cédula del cliente
                        n = 1;
                        cedula = 0;
                        do {
                            try {
                                System.out.print("Ingrese la cédula del cliente: ");
                                cedula = scanner.nextInt();
                                // Verificar que el cliente exista y tenga una cédula válida
                                if (banco.buscarCliente(cedula) != null) {
                                    if (cedula > 1000000 && cedula < 999999999) {
                                        n = 0;
                                    } else {
                                        System.out.println("En este campo debe"
                                                + " haber un numero de 7 a 9 digitos");
                                    }
                                } else {
                                    System.out.println("No existe un cliente con esta cedula");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar un número válido.");
                                scanner.nextLine(); // Limpiar buffer
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);

                        // Solicitar el tipo de transacción
                        n = 1;
                        tipo = "";
                        do {
                            try {
                                System.out.print("Ingrese el tipo de transaccion: ");
                                tipo = scanner.next();
                                // Verificar que el tipo de transacción sea válido
                                if (tipo.equals("retiro") || tipo.equals("consignacion")) {
                                    n = 0;
                                } else {
                                    System.out.println("El tipo de transaccion debe ser retiro o consignacion");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);

                        // Solicitar el monto de la transacción
                        n = 1;
                        monto = 0;
                        do {
                            try {
                                System.out.print("Ingrese la valor del monto: ");
                                monto = scanner.nextInt();
                                // Verificar que el monto sea válido
                                if (monto > 0) {
                                    n = 0;
                                } else {
                                    System.out.println("En este campo debe"
                                            + " haber un numero mayor de 0");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar un número válido.");
                                scanner.nextLine(); // Limpiar buffer
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);

                        // Agregar la transacción al registro del cliente correspondiente
                        banco.agregarTransaccion(cedula, tipo, monto);
                        break;

                    // Eliminar cliente
                    case 3:
                        System.out.println("Ha elegido la opcion: Eliminar cliente\n");

                        // Establecer un flag para el bucle do-while
                        n = 1;
                        cedula = 0;

                        // Bucle do-while para asegurarse de que el usuario ingrese una cédula válida
                        do {
                            try {
                                // Solicitar al usuario que ingrese la cédula del cliente a eliminar
                                System.out.print("Ingrese la cédula del cliente: ");
                                cedula = scanner.nextInt();

                                // Verificar si el cliente existe
                                if (banco.buscarCliente(cedula) != null) {
                                    // Verificar que la cédula tenga entre 7 y 9 dígitos
                                    if (cedula > 1000000 && cedula < 999999999) {
                                        // Si la cédula es válida, establecer n a 0 para salir del bucle
                                        n = 0;
                                    } else {
                                        System.out.println("En este campo debe haber un numero de 7 a 9 digitos");
                                    }
                                } else {
                                    System.out.println("No existe un cliente con esta cedula");
                                }
                            } catch (InputMismatchException e) {
                                // Capturar una excepción si el usuario ingresa algo que no es un número
                                System.out.println("Debe ingresar un número válido.");
                                scanner.nextLine(); // Limpiar el buffer
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);

                        // Llamar al método para eliminar el cliente del sistema
                        banco.eliminarCliente(cedula);
                        break;
                    // Eliminar transferencia
                    case 4:
                        // Se muestra al usuario que ha elegido la opción de eliminar una transferencia
                        System.out.println("Ha elegido la opcion: Eliminar transferencia\n");

                        // Se inicializan variables necesarias
                        n = 1;
                        cedula = 0;
                        // Se pide al usuario que ingrese la cedula del cliente asociado a la
                        // transferencia
                        // Se valida que la cedula ingresada corresponda a un cliente existente en el
                        // banco
                        // Se valida que la cedula ingresada tenga entre 7 y 9 digitos
                        // Se utiliza un bloque try-catch para manejar excepciones en caso de que se
                        // ingrese un valor no numerico
                        // o se produzca otro error al buscar al cliente
                        do {
                            try {
                                System.out.print("Ingrese la cédula del cliente: ");
                                cedula = scanner.nextInt();
                                if (banco.buscarCliente(cedula) != null) {
                                    if (cedula > 1000000 && cedula < 999999999) {
                                        n = 0;
                                    } else {
                                        System.out.println("En este campo debe haber un numero de 7 a 9 digitos");
                                    }
                                } else {
                                    System.out.println("No existe un cliente con esta cedula");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar un número válido.");
                                scanner.nextLine(); // Limpiar buffer
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);

                        // Se pide al usuario que ingrese el tipo de transacción
                        // Se valida que el tipo ingresado sea "retiro" o "consignacion"
                        // Se utiliza un bloque try-catch para manejar excepciones en caso de que se
                        // ingrese un valor no valido

                        tipo = "";
                        n = 0;
                        do {
                            try {
                                System.out.print("Ingrese el tipo de transaccion: ");
                                tipo = scanner.next();
                                if (tipo.equals("retiro") || tipo.equals("consignacion")) {
                                    n = 0;
                                } else {
                                    System.out.println("El tipo de transaccion debe ser retiro o consignacion");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);

                        // Se pide al usuario que ingrese el monto de la transacción
                        // Se valida que el monto ingresado sea mayor a 0
                        // Se utiliza un bloque try-catch para manejar excepciones en caso de que se
                        // ingrese un valor no numerico
                        // o se produzca otro error al ingresar el monto

                        monto = 0;
                        n = 0;
                        do {
                            try {
                                System.out.print("Ingrese la valor del monto: ");
                                monto = scanner.nextInt();
                                if (monto > 0) {
                                    n = 0;
                                } else {
                                    System.out.println("En este campo debe haber un numero mayor de 0");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar un número válido.");
                                scanner.nextLine(); // Limpiar buffer
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (n == 1);

                        // Se elimina la transacción al cliente correspondiente en el banco
                        banco.eliminarTransaccion(cedula, tipo, monto);
                        break;
                    case 5:
                        // Seleccionar la opción para ejecutar transacciones
                        // Llama al método ejecutarTransacciones() en el objeto banco
                        banco.ejecutarTransacciones();
                        break;

                    case 6:
                        // Seleccionar la opción para mostrar clientes
                        System.out.println("Clientes:");

                        // Mostrar una tabla con los nombres, cédulas y saldos de los clientes
                        System.out.printf("%-20s %-20s %-20s\n", "Cedula", "Nombre", "Saldo");
                        for (Cliente cliente : banco.getClientes()) {
                            System.out.printf("%-20s %-20s %-20s\n", cliente.getCedula(),
                                    cliente.getNombre(), cliente.getSaldo());
                        }
                        break;

                    case 7:
                        // Seleccionar la opción para mostrar transacciones
                        System.out.println("Transacciones:");

                        // Mostrar una tabla con las cédulas, tipos y montos de las transacciones
                        System.out.printf("%-20s %-20s %-20s\n", "Cedula", "Tipo", "Monto");
                        for (Transaccion transaccion : banco.getTransacciones()) {
                            System.out.printf("%-20s %-20s %-20s\n", transaccion.getCedula(),
                                    transaccion.getTipo(), transaccion.getMonto());
                        }
                        break;

                    case 8:
                        // Seleccionar la opción para mostrar clientes y transacciones
                        System.out.println("Clientes:");

                        // Mostrar una tabla con los nombres, cédulas y saldos de los clientes
                        System.out.printf("%-20s %-20s %-20s\n", "Cedula", "Nombre", "Saldo");
                        for (Cliente cliente : banco.getClientes()) {
                            System.out.printf("%-20s %-20s %-20s\n", cliente.getCedula(),
                                    cliente.getNombre(), cliente.getSaldo());
                        }

                        // Mostrar una línea en blanco seguida de las transacciones
                        System.out.println();
                        System.out.println("Transacciones:");
                        System.out.printf("%-20s %-20s %-20s\n", "Cedula", "Tipo", "Monto");
                        for (Transaccion transaccion : banco.getTransacciones()) {
                            System.out.printf("%-20s %-20s %-20s\n", transaccion.getCedula(),
                                    transaccion.getTipo(), transaccion.getMonto());
                        }
                        break;

                    case 9:
                        // Si el usuario selecciona la opción 9, se muestra un mensaje de despedida y se
                        // sale del bucle.
                        System.out.println("¡Hasta pronto!");
                        break;
                    default:
                        // Si el usuario selecciona una opción inválida, se muestra un mensaje de error.
                        System.out.println("Opción inválida.");
                        break;
                }
            } catch (InputMismatchException e) {
                // Si se produce un error al leer la entrada del usuario, se muestra un mensaje
                // de error y se limpia el buffer.
                System.out.println("Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar buffer
            } catch (Exception e) {
                // Si se produce cualquier otro tipo de error, se muestra un mensaje de error
                // con la descripción del error.
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 9); // El bucle se repite mientras el usuario no seleccione la opción 9.
    }
}