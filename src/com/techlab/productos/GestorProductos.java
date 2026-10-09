package com.techlab.productos;

import java.util.ArrayList;
import com.techlab.excepciones.ProductoNoEncontradoException;

public class GestorProductos {

    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public void listarProductos(){

        String formatoLista = "%5d %-20s %10.2f %5d%n";

        if (listaProductos.isEmpty()){
            System.out.println("No hay productos en el catálogo.");
        } else {
            System.out.printf("%5s %-20s %10s %5s%n", "ID", "NOMBRE", "PRECIO", "STOCK");
            System.out.println("-------------------------------------------------------------");
            for (Producto p : listaProductos) {
                System.out.printf(formatoLista, p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
            }
            System.out.println("-------------------------------------------------------------");
        
        }
    }

    public void agregarProducto(Producto producto){
        if( producto != null){
            listaProductos.add(producto);
        }
    }

    public Producto busquedaPorId(int id){
        for (Producto p : listaProductos) {
            if(p.getId() == id){
                return p;
            } 
        }
        throw new ProductoNoEncontradoException("Error: El producto con ID " + id + " no existe.");
    }

    public Producto busquedaPorNombre(String nombre){

        String nombreFormateado;
        nombreFormateado = Producto.formatearNombreProducto(nombre);

        for (Producto p : listaProductos) {
            if(nombreFormateado.equals(p.getNombre())){
                return p;
            } 
        }
        throw new ProductoNoEncontradoException("Error: El producto \"" + nombre + "\" no existe.");
    }

    public void mostrarProducto(Producto producto){
        String nombre = producto.getNombre();
        int id = producto.getId();
        double precio = producto.getPrecio();
        int stock = producto.getStock();

        System.out.printf("%-20s %40d%n", "ID:", id);
        System.out.printf("%-20s %40s%n", "Nombre:", nombre);
        System.out.printf("%-20s %40.2f%n", "Precio:", precio);
        System.out.printf("%-20s %40d%n", "Stock:", stock);
    }

    public void actualizarPrecio(int id, double nuevoPrecio){
        busquedaPorId(id).setPrecio(nuevoPrecio);
    }

    public void actualizarStock(int id, int nuevoStock){
        busquedaPorId(id).setStock(nuevoStock);
    }

    public void eliminarProducto(int id){
        listaProductos.remove(busquedaPorId(id));
    }

}
