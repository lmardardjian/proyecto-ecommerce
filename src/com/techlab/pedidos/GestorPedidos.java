package com.techlab.pedidos;

import java.util.List;
import java.util.ArrayList;

public class GestorPedidos {

    private List<Pedido> listaPedidos = new ArrayList<>();

    public void agregarPedido(Pedido pedido){
        if (pedido != null){
            listaPedidos.add(pedido);
        }
    }

    public void listarPedidos(){

        if (listaPedidos.isEmpty()){
            System.out.println("No hay pedidos registrados.");
        } else {
            for (Pedido p : listaPedidos) {
                System.out.println("Pedido #" + p.getId());
                p.mostrarLineas();
                System.out.println("-".repeat(53));
                System.out.printf("%-25s %32.2f%n", "TOTAL:", p.calcularTotal());
                System.out.println();
            }
        }
    }

}
