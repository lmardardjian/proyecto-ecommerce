package com.techlab.pedidos;
import com.techlab.productos.Producto;

public class LineaPedido {
    private Producto producto;
    private int cantidad;
    private final double precioUnitario; // Precio al momento de armar el pedido; no cambia si después se actualiza el producto.

    public LineaPedido (Producto producto, int cantidad){
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Error: la cantidad debe ser mayor a 0.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public double calcularSubtotal (){
        return cantidad * precioUnitario;
    }

    //Getters

    public Producto getProducto(){
        return this.producto;
    }

    public int getCantidad(){
        return this.cantidad;
    }

    public double getPrecioUnitario(){
        return this.precioUnitario;
    }

}
