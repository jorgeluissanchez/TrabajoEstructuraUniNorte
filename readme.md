# Documentación de la clase Java Banco

Este es una documentacion completa para el programa Java de un banco.

## Programa Java

El programa Java es una interfaz de línea de comandos (CLI) que permite a los usuarios interactuar con la clase Banco, que representa un banco. El programa proporciona un menú con varias opciones para administrar clientes y sus transacciones. Crea un objeto Banco e inicializa sus datos con información de ejemplo. El programa entra en un bucle do-while que muestra el menú y lee la opción del usuario. Dependiendo de la opción elegida por el usuario, el programa ejecuta un bloque de código diferente, definido por una declaración switch.

## Clase Banco

La clase Banco tiene dos variables miembro, clientes y transacciones, que son listas que contienen objetos Cliente y Transaccion, respectivamente. El constructor Banco() inicializa estas listas. El método inicializar() inicializa los datos del banco, que incluyen clientes y transacciones, utilizando una instancia de InicializacionDatos. El método getClientes() devuelve la lista clientes, y el método getTransacciones() devuelve la lista transacciones.

El método ejecutarTransacciones() ejecuta las transacciones pendientes. Itera sobre la lista de transacciones, encuentra el objeto Cliente correspondiente para cada transacción y ejecuta la transacción. También guarda las transacciones y clientes actualizados en un archivo de Excel.

El método eliminarTransaccion() elimina una transacción de la lista de transacciones. Toma tres parámetros: cedula, tipo y monto, que se utilizan para identificar la transacción que se eliminará. También guarda la lista de transacciones actualizada en un archivo de Excel.

El método eliminarCliente() elimina un cliente de la lista de clientes. Toma un parámetro, cedula, que se utiliza para identificar el cliente que se eliminará. También elimina todas las transacciones asociadas con el cliente y guarda las transacciones y clientes actualizados en archivos de Excel.

La clase depende de otras clases, como Cliente, Transaccion e InicializacionDatos, que no se muestran aquí.

## Clase Cliente

La clase Cliente tiene tres variables miembro privadas, cedula, nombre y saldo, que representan el número de identificación del cliente, el nombre y el saldo. La clase proporciona un constructor que recibe estos tres valores como parámetros, así como métodos getter para recuperar cada uno de ellos. También incluye dos métodos para depositar o retirar dinero de la cuenta del cliente.

## Clase Transaccion

La clase Transaccion tiene tres variables miembro privadas, cedula, tipo y monto, que representan el número de identificación del cliente, el tipo de transacción y el monto de la transacción. La clase proporciona un constructor que recibe estos tres valores como parámetros, así como métodos getter para recuperar cada uno de ellos.

## Clase InicializacionDatos

La clase InicializacionDatos tiene dos variables miembro privadas, clientes y transacciones, que representan listas de objetos Cliente y Transaccion. Proporciona métodos getter para recuperar estas listas, así como un método inicializar() público para inicializarlas. Este método lee datos de un archivo de Excel utilizando Apache POI y crea objetos Cliente y Transaccion en función

## Autor

Jorge Luis Sanchez Barreneche
# TrabajoEstructuraUniNorte
