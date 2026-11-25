package com.techlab;

import java.util.Scanner;

import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.*;
import com.techlab.pedidos.*;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final GestorProductos gestorPo = new GestorProductos();
    private static final GestorPedidos gestorPe = new GestorPedidos();

    public static void main(String[] args) {
        int input;
        do {
            mostrarMenu();
            input = leerEntero("\nElija una opción: ");

            try {
                manejarMenu(input);
            } catch (ProductoNoEncontradoException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (input != 7);

        teclado.close();

    }

    static void mostrarMenu() {
        System.out.println("===================================");
        System.out.println("SISTEMA DE GESTIÓN - TECHLAB");
        System.out.println("===================================");

        String formatoMenu = "%3s %-25s%n";
        System.out.printf(formatoMenu, "1)", "Agregar producto");
        System.out.printf(formatoMenu, "2)", "Listar productos");
        System.out.printf(formatoMenu, "3)", "Buscar/Actualizar producto");
        System.out.printf(formatoMenu, "4)", "Eliminar producto");
        System.out.printf(formatoMenu, "5)", "Crear un pedido");
        System.out.printf(formatoMenu, "6)", "Listar pedidos");
        System.out.printf(formatoMenu, "7)", "Salir");

    }

    static void manejarMenu(int opcion) {
        String formatoMenu = "%3s %-25s%n";

        switch (opcion) {
            case 1:
                System.out.printf(formatoMenu, "1)", "Agregar producto");
                System.out.println("--------------------------------------------------");
                Producto nuevoP = crearProducto();
                gestorPo.agregarProducto(nuevoP);
                System.out.println("El producto " + nuevoP + " fue agregado correctamente.");
                break;

            case 2:
                System.out.printf(formatoMenu, "2)", "Listar productos");
                System.out.println("===================================");
                gestorPo.listarProductos();
                break;

            case 3:
                System.out.printf(formatoMenu, "3)", "Buscar/Actualizar producto");
                System.out.println("===================================");
                int seleccion = seleccionTipoBusqueda();
                Producto buscadoP = manejoBusqueda(seleccion);
                if (buscadoP != null) {
                    gestorPo.mostrarProducto(buscadoP);
                    gestionarActualizacion(buscadoP);
                }

                break;

            case 4:
                System.out.printf(formatoMenu, "4)", "Eliminar producto");
                System.out.println("===================================");
                Producto eliminadoP = eliminarProducto();
                if (eliminadoP != null) {
                    System.out.println("El producto " + eliminadoP + " fue eliminado correctamente.");
                }
                break;

            case 5:
                System.out.printf(formatoMenu, "5)", "Crear un pedido");
                System.out.println("===================================");
                crearPedido();
                break;

            case 6:
                System.out.printf(formatoMenu, "6)", "Listar pedidos");
                System.out.println("===================================");
                gestorPe.listarPedidos();
                break;

            case 7:
                System.out.println("Saliendo del sistema...");
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    // Opción 4. Pide un ID hasta encontrar el producto; con 0 vuelve al menú sin
    // eliminar nada.
    private static Producto eliminarProducto() {
        while (true) {
            int id = leerEntero("Ingrese el ID del producto que desea eliminar (0 para volver): ");

            if (id == 0) {
                System.out.println("Volviendo al menú principal...");
                return null;
            }

            try {
                return gestorPo.eliminarProducto(id);
            } catch (ProductoNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static Producto crearProducto() {

        String nombre = leerTexto("Ingrese el nombre del producto: ");
        double precio = leerDouble("Ingrese el precio del producto: ");
        int stock = leerEntero("Ingrese el stock del producto: ");

        return new Producto(nombre, precio, stock);
    }

    private static Producto manejoBusqueda(int tipoBusqueda) {
        Producto p;
        switch (tipoBusqueda) {
            case 1:
                int id = leerEntero("Ingrese el ID del producto: ");
                p = gestorPo.busquedaPorId(id);
                return p;

            case 2:
                String nombre = leerTexto("Ingrese el nombre del producto: ");
                p = gestorPo.busquedaPorNombre(nombre);
                return p;

            default:
                System.out.println("La opción ingresada no es válida.");
                return null;
        }
    }

    private static int seleccionTipoBusqueda() {
        String formatoSubOpciones = "%10s %-25s%n";
        System.out.printf(formatoSubOpciones, "1)", "Buscar por ID");
        System.out.printf(formatoSubOpciones, "2)", "Buscar por nombre");
        int seleccion = leerEntero("\nElija una opción: ");
        return seleccion;
    }

    private static void gestionarActualizacion(Producto productoBuscado) {
        String formatoSubOpciones = "%10s %-25s%n";
        String formatoMenu = "%3s %-25s%n";
        System.out.printf(formatoSubOpciones, "1)", "Actualizar stock");
        System.out.printf(formatoSubOpciones, "2)", "Actualizar precio");
        System.out.printf(formatoSubOpciones, "3)", "Volver al menú principal");
        int seleccion = leerEntero("\nElija una opción: ");

        switch (seleccion) {
            case 1:
                System.out.printf(formatoMenu, "1)", "Actualizar stock");
                int nuevoStock = leerEntero("Ingrese el nuevo stock: ");
                gestorPo.actualizarStock(productoBuscado.getId(), nuevoStock);
                System.out.println("Stock actualizado a: " + nuevoStock);
                break;

            case 2:
                System.out.printf(formatoMenu, "2)", "Actualizar precio");
                double nuevoPrecio = leerDouble("Ingrese el nuevo precio: ");
                gestorPo.actualizarPrecio(productoBuscado.getId(), nuevoPrecio);
                System.out.println("Precio actualizado a: " + nuevoPrecio);
                break;

            case 3:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }

    }

    static String leerTexto(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = teclado.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("Error: el texto no puede estar vacío.");
        }
    }

    static int leerEntero(String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número entero válido.");
            }
        }
    }

    static double leerDouble(String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return Double.parseDouble(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número válido.");
            }
        }
    }

    // Opción 5. Primer submenú: se muestra al entrar y se repite hasta que
    // se agrega el primer producto o el usuario vuelve al menú principal.
    static void crearPedido() {
        String formatoSubOpciones = "%10s %-25s%n";
        Pedido pedido = new Pedido();
        int seleccion;

        do {
            System.out.printf(formatoSubOpciones, "1)", "Agregar producto al pedido");
            System.out.printf(formatoSubOpciones, "2)", "Volver al menú principal");
            seleccion = leerEntero("\nElija una opción: ");

            switch (seleccion) {
                case 1:
                    agregarProductoAPedido(pedido);
                    break;

                case 2:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (seleccion != 2 && pedido.estaVacio());

        if (!pedido.estaVacio()) {
            gestionarPedido(pedido);
        }
    }

    // Segundo submenú: se repite hasta que el pedido se confirma o se cancela.
    private static void gestionarPedido(Pedido pedido) {
        String formatoSubOpciones = "%10s %-25s%n";
        int seleccion;

        do {
            System.out.println();
            System.out.printf(formatoSubOpciones, "1)", "Agregar otro producto");
            System.out.printf(formatoSubOpciones, "2)", "Mostrar pedido");
            System.out.printf(formatoSubOpciones, "3)", "Confirmar pedido");
            System.out.printf(formatoSubOpciones, "4)", "Cancelar pedido");
            seleccion = leerEntero("\nElija una opción: ");

            switch (seleccion) {
                case 1:
                    agregarProductoAPedido(pedido);
                    break;

                case 2:
                    mostrarPedido(pedido);
                    break;

                case 3:
                    mostrarPedido(pedido);
                    gestorPe.agregarPedido(pedido);
                    System.out.println("Pedido #" + pedido.getId() + " confirmado. Se descontó el stock.");
                    break;

                case 4:
                    System.out.println("Pedido cancelado. No se modificó el stock.");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (seleccion != 3 && seleccion != 4);
    }

    // Si algo falla (ID inexistente, cantidad inválida o sin stock) se avisa
    // y el pedido conserva las líneas que ya tenía.
    private static void agregarProductoAPedido(Pedido pedido) {
        try {
            int id = leerEntero("Ingrese el ID del producto: ");
            Producto producto = gestorPo.busquedaPorId(id);
            int cantidad = leerEntero("Ingrese la cantidad de \"" + producto.getNombre() + "\": ");

            pedido.agregarLinea(new LineaPedido(producto, cantidad));
            System.out.println(cantidad + " x " + producto.getNombre() + " agregado al pedido.");

        } catch (ProductoNoEncontradoException | StockInsuficienteException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarPedido(Pedido pedido) {
        pedido.mostrarLineas();
        System.out.println("-".repeat(53));
        System.out.printf("%-25s %32.2f%n", "TOTAL:", pedido.calcularTotal());
    }

}
