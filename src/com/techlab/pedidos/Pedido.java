package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static final String FORMATO_FILA = "%-25s %6d %12.2f %12.2f%n";
    private static final String FORMATO_ENCABEZADO = "%-25s %6s %12s %12s%n";
    
    private int id;
    private List<LineaPedido> lineas = new ArrayList<>();
    private static int contador = 0;


    public Pedido() {
        id = generarId();
    }

    public void agregarLinea(LineaPedido linea){
        lineas.add(linea);
    }

    private int generarId() {
        return ++contador;
    }

    public int getId(){
        return this.id;
    }

    public double calcularTotal(){
        
        double total = 0;
        
        for (LineaPedido linea : lineas){
            total += linea.calcularSubtotal();   
        }
        return total;
    }

    public void mostrarLineas(){
        System.out.printf(FORMATO_ENCABEZADO, "Producto", "Cant.", "Precio", "Subtotal");
        System.out.println("-".repeat(53));

        for (LineaPedido linea : lineas){
            System.out.printf(FORMATO_FILA, linea.getProducto().getNombre(), linea.getCantidad(), linea.getProducto().getPrecio(), linea.calcularSubtotal());
        }
    }
}
