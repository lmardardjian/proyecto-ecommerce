package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;

public class Pedido {
    private static final String FORMATO_FILA = "%-25s %6d %12.2f %12.2f%n";
    private static final String FORMATO_ENCABEZADO = "%-25s %6s %12s %12s%n";

    private int id; // 0 = todavía no confirmado. El ID lo asigna GestorPedidos al confirmar.
    private List<LineaPedido> lineas = new ArrayList<>();

    public void agregarLinea(LineaPedido linea) {
        Producto producto = linea.getProducto();
        int yaPedido = cantidadPedidaDe(producto);
        int disponible = producto.getStock() - yaPedido;

        if (linea.getCantidad() > disponible) {
            throw new StockInsuficienteException("Error: stock insuficiente de \"" + producto.getNombre()
                    + "\". Disponible para este pedido: " + disponible + ".");
        }

        lineas.add(linea);
    }

    // Suma lo que ya se pidió de un producto en este pedido (puede estar en varias
    // líneas).
    public int cantidadPedidaDe(Producto producto) {
        int total = 0;

        for (LineaPedido linea : lineas) {
            if (linea.getProducto().getId() == producto.getId()) {
                total += linea.getCantidad();
            }
        }
        return total;
    }

    public boolean estaVacio() {
        return lineas.isEmpty();
    }

    // Visibilidad de paquete: solo GestorPedidos puede asignar el ID, y una sola
    // vez.
    void asignarId(int id) {
        if (this.id != 0) {
            throw new IllegalStateException("El pedido ya tiene un ID asignado.");
        }
        this.id = id;
    }

    // Visibilidad de paquete: solo se descuenta al confirmar, desde GestorPedidos.
    void descontarStock() {
        for (LineaPedido linea : lineas) {
            Producto producto = linea.getProducto();
            producto.setStock(producto.getStock() - linea.getCantidad());
        }
    }

    public int getId() {
        return this.id;
    }

    public double calcularTotal() {

        double total = 0;

        for (LineaPedido linea : lineas) {
            total += linea.calcularSubtotal();
        }
        return total;
    }

    public void mostrarLineas() {
        System.out.printf(FORMATO_ENCABEZADO, "Producto", "Cant.", "Precio", "Subtotal");
        System.out.println("-".repeat(53));

        for (LineaPedido linea : lineas) {
            System.out.printf(FORMATO_FILA, linea.getProducto().getNombre(), linea.getCantidad(),
                    linea.getPrecioUnitario(), linea.calcularSubtotal());
        }
    }
}
