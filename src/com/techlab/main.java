package com.techlab;

import java.util.Scanner;
import com.techlab.productos.*;

    public class Main {
    
    private static final Scanner teclado = new Scanner(System.in);
    private static final GestorProductos gestor = new GestorProductos();

    public static void main(String[] args){
        int input;
        do{
            mostrarMenu();
            input = leerEntero("\nElija una opción: ");
            manejarMenu(input);

        } while (input != 7);

        teclado.close();

    }

    static void mostrarMenu(){
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

    static void manejarMenu(int opcion){
        String formatoMenu = "%3s %-25s%n";

        switch (opcion) {
            case 1:
                System.out.printf(formatoMenu,"1)", "Agregar producto");
                System.out.println("--------------------------------------------------");  
                Producto nuevoP = crearProducto();
                gestor.agregarProducto(nuevoP);
                System.out.println("El producto " + nuevoP + " fue agregado correctamente.");  
                break;

            case 2:
                System.out.printf(formatoMenu,"2)", "Listar productos");
                System.out.println("===================================");  
                gestor.listarProductos();
                break;

            case 3:
                System.out.printf(formatoMenu,"3)", "Buscar/Actualizar producto");
                System.out.println("===================================");
                int seleccion = seleccionTipoBusqueda();
                Producto buscadoP = manejoBusqueda(seleccion);
                if (buscadoP != null){
                    gestor.mostrarProducto(buscadoP);
                    gestionarActualizacion(buscadoP);
                }

                break;

            case 4:
                System.out.printf(formatoMenu,"4)", "Eliminar producto");
                System.out.println("===================================");
                int idProducto = leerEntero("Ingrese el ID del producto que desea eliminar: ");
                gestor.eliminarProducto(idProducto); 
                    
                break;

            case 5:
                System.out.printf(formatoMenu,"5)", "Crear un pedido");
                System.out.println("===================================");  
                break;

            case 6:
                System.out.printf(formatoMenu,"6)", "Listar pedidos");
                System.out.println("===================================");  
                break;

            case 7:
                System.out.println("Saliendo del sistema...");
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    static Producto crearProducto(){
        
        String nombre = leerTexto("Ingrese el nombre del producto: ");
        double precio = leerDouble("Ingrese el precio del producto: ");
        int stock = leerEntero("Ingrese el stock del producto: ");

        return new Producto(nombre, precio, stock);
    }

    static private Producto manejoBusqueda(int fB){
        Producto p;
        switch (fB) {
            case 1:
                int id = leerEntero("Ingrese el ID del producto: "); 
                p = gestor.busquedaPorId(id);
                return p;
            
            case 2:
                String nombre = leerTexto("Ingrese el nombre del producto: ");
                p = gestor.busquedaPorNombre(nombre);
                return p;

            default:
                System.out.println("La opción ingresada no es válida.");
                return null;
        }
    }

    static private int seleccionTipoBusqueda(){
        String formatoSubOpciones = "%10s %-25s%n";
        System.out.printf(formatoSubOpciones, "1)", "Buscar por ID");
        System.out.printf(formatoSubOpciones, "2)", "Buscar por nombre");
        int seleccion = leerEntero("\nElija una opción: ");
        return seleccion;
    }

    static private void gestionarActualizacion(Producto productoBuscado){
        String formatoSubOpciones = "%10s %-25s%n";
        String formatoMenu = "%3s %-25s%n";
        System.out.printf(formatoSubOpciones, "1)", "Actualizar stock");
        System.out.printf(formatoSubOpciones, "2)", "Actualizar precio");
        System.out.printf(formatoSubOpciones, "3)", "Volver al menú principal");;
        int seleccion = leerEntero("\nElija una opción: ");

        switch (seleccion) {
            case 1:
                System.out.printf(formatoMenu,"1)", "Actualizar stock");
                int nuevoStock = leerEntero("Ingrese el nuevo stock: ");
                gestor.actualizarStock(productoBuscado.getId(), nuevoStock);
                System.out.print("Stock actualizado a: "+ nuevoStock);
                break;
            
            case 2:
                System.out.printf(formatoMenu,"2)", "Actualizar precio");
                double nuevoPrecio = leerDouble("Ingrese el nuevo precio: "); 
                gestor.actualizarPrecio(productoBuscado.getId(), nuevoPrecio);
                System.out.print("Precio actualizado a: "+ nuevoPrecio);
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
            System.out.print(mensaje);
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

}
