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
            String inputTeclado = teclado.nextLine();
            input = Integer.parseInt(inputTeclado);
            manejarMenu(input);

        teclado.close();
        } while (input != 7);

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
        System.out.print("\nElija una opción: ");
    }

    static void manejarMenu(int opcion){
        String formatoMenu = "%3s %-25s%n";

        switch (opcion) {
            case 1:
                System.out.printf(formatoMenu,"1)", "Agregar producto");
                System.out.println("===================================");  
                Producto nuevoP = crearProducto();
                gestor.agregarProducto(nuevoP);
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
                System.out.print("Ingrese el ID del producto que desea eliminar: ");
                gestor.eliminarProducto(Integer.parseInt(teclado.nextLine())); 
                    
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
        
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = teclado.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = Double.parseDouble(teclado.nextLine());

        System.out.print("Ingrese el stock del producto: ");
        int stock = Integer.parseInt(teclado.nextLine());

        return new Producto(nombre, precio, stock);
    }

    static private Producto manejoBusqueda(int fB){
        Producto p;
        switch (fB) {
            case 1:
                System.out.print("Ingrese el ID del producto: ");
                int id = Integer.parseInt(teclado.nextLine());
                p = gestor.busquedaPorId(id);
                return p;
            
            case 2:
                System.out.print("Ingrese el nombre del producto: ");
                String nombre = teclado.nextLine();
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
        System.out.print("\nElija una opción: ");
        return Integer.parseInt(teclado.nextLine());
    }

    static private void gestionarActualizacion(Producto productoBuscado){
        String formatoSubOpciones = "%10s %-25s%n";
        String formatoMenu = "%3s %-25s%n";
        System.out.printf(formatoSubOpciones, "1)", "Actualizar stock");
        System.out.printf(formatoSubOpciones, "2)", "Actualizar precio");
        System.out.printf(formatoSubOpciones, "3)", "Volver al menú principal");;
        int seleccion = Integer.parseInt(teclado.nextLine());

        switch (seleccion) {
            case 1:
                System.out.printf(formatoMenu,"1)", "Actualizar stock");
                System.out.println("Ingrese el nuevo stock");
                int nuevoStock = Integer.parseInt(teclado.nextLine());
                gestor.actualizarStock(productoBuscado.getId(), nuevoStock);
                System.out.println("Stock actualizado a: "+ nuevoStock);
                break;
            
            case 2:
                System.out.printf(formatoMenu,"2)", "Actualizar precio");
                System.out.println("Ingrese el nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(teclado.nextLine());
                gestor.actualizarPrecio(productoBuscado.getId(), nuevoPrecio);
                System.out.println("Precio actualizado a: "+ nuevoPrecio);
                break;    
            
            case 3:
                System.out.println("Volviendo al menú principal...");
                break;  
            default:
                System.out.println("Opción no válida.");
                break;
        }

    }

}
