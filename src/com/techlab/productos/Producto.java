package com.techlab.productos;

public class Producto {
    private final int id;
    private String nombre;
    private double precio;
    private int stock;
    private static int contador = 0; //Iniciamos con 0 productos.

    public Producto( String n, double p, int s ){
        setNombre(n);
        setPrecio(p);
        setStock(s);
        this.id = generarId();

    }

    //Getters

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public int getStock() {
        return this.stock;
    }

    public int getId() {
        return this.id;
    }

    //Setters

    public void setNombre( String n ) {
        if( n == null || n.isBlank() ){
            throw new IllegalArgumentException("El nombre no puede ser nulo ni estar vacío.");
        }
        
        String nombreFormateado;

        nombreFormateado = formatearNombreProducto(n);
        this.nombre = nombreFormateado;
    }

    public void setPrecio( double p ) {
        if (p <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0."); 
        }
        
        this.precio = p;
    }

    public void setStock( int s ) {
        if (s < 0) {
            throw new IllegalArgumentException("El stock no puede ser menor a 0."); 
        }

        this.stock = s;
    }

    // Métodos

    private static int generarId() {
        return ++contador; //Se incrementa a 1 cuando se crea un producto y devuelve su valor.
    }

    public static String formatearNombreProducto(String nombre) { 
        nombre = nombre.trim().toLowerCase(); 
        // Dividimos en palabras 
        String[] palabras = nombre.split(" "); 
        StringBuilder sb = new StringBuilder(); 

            for (int i = 0; i < palabras.length; i++) { 

                if (!palabras[i].isEmpty()) { 
                    String primeraLetra = palabras[i].substring(0,1).toUpperCase(); 
                    String resto = palabras[i].substring(1); 
                    sb.append(primeraLetra).append(resto); 

                    if (i < palabras.length - 1) { 
                        sb.append(" "); 
                    } 
                } 
            } 
        return sb.toString(); 
    }

}
