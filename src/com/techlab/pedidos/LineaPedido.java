package com.techlab.pedidos;
import com.techlab.productos.Producto;

public class LineaPedido {
    private Producto producto;
    private int cantidad;

    public LineaPedido (Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calculoSubtotal (){
        return cantidad * producto.getPrecio();
    }

    //Getters

    public Producto getProducto(){
        return this.producto;
    }

    public int getCantidad(){
        return this.cantidad;
    }

}
