# Sistema de Gestión - TechLab

Aplicación de consola en Java para registrar, mostrar y gestionar productos, y para crear pedidos con varios productos.
Es la preentrega del curso de Java de TechLab y la base del Trabajo Final Integrador.

## Funcionalidades

Menú principal (se repite hasta elegir **Salir**):

| Opción | Descripción |
|---|---|
| 1) Agregar producto | Pide nombre, precio y stock. El ID se asigna automáticamente. |
| 2) Listar productos | Muestra ID, nombre, precio y stock de todos los productos. |
| 3) Buscar/Actualizar producto | Busca por ID o por nombre, muestra el producto y permite actualizar stock o precio. |
| 4) Eliminar producto | Elimina un producto por su ID. |
| 5) Crear un pedido | Permite agregar productos y cantidades, ver el detalle con el total, y confirmar o cancelar el pedido. |
| 6) Listar pedidos | Muestra cada pedido confirmado con sus productos, cantidades, subtotales y total. |
| 7) Salir | Termina el programa. |

### Validaciones

- El nombre no puede estar vacío y se guarda con formato de título (`café premium` → `Café Premium`).
- El precio debe ser mayor a 0 y el stock no puede ser negativo.
- Si se ingresa texto donde se espera un número, se vuelve a pedir el dato.
- Al armar un pedido, la cantidad debe ser mayor a 0 y no puede superar el stock disponible (se tiene en cuenta lo que ya se agregó de ese producto al mismo pedido).
- El stock se descuenta recién cuando el pedido se confirma; si se cancela, no se modifica.
- El precio de cada línea se fija al agregarla al pedido, así que actualizar después el precio del producto no altera los pedidos ya armados.

## Estructura del proyecto

```
src/com/techlab/
├── Main.java                          Menú y lectura de datos por consola
├── productos/
│   ├── Producto.java                  Atributos, validaciones e ID autogenerado
│   └── GestorProductos.java           Alta, listado, búsqueda, actualización y baja
├── pedidos/
│   ├── Pedido.java                    Líneas del pedido, control de stock y total
│   ├── LineaPedido.java               Producto + cantidad + precio unitario
│   └── GestorPedidos.java             Confirmación y listado de pedidos
└── excepciones/
    ├── ProductoNoEncontradoException.java
    └── StockInsuficienteException.java
```

## Conceptos aplicados

- **Tipos de datos y operadores:** `int` para IDs y cantidades, `double` para precios, `String` para nombres, `boolean` en validaciones.
- **Colecciones:** `ArrayList<Producto>`, `ArrayList<Pedido>` y `ArrayList<LineaPedido>`.
- **POO:** clases con atributos privados, getters y setters con validación, contador estático para los IDs y colaboración entre clases (`Main` → gestores → entidades).
- **Excepciones:** `try/catch` para `NumberFormatException` en la entrada de datos, y excepciones personalizadas para producto inexistente y stock insuficiente.
- **Paquetes:** `com.techlab.productos`, `com.techlab.pedidos` y `com.techlab.excepciones`.

## Requisitos

- JDK 11 o superior.

## Cómo ejecutarlo

### Desde VS Code

Abrir la carpeta del proyecto con la extensión *Extension Pack for Java* instalada y ejecutar `Main.java` con **Run**.

### Desde la terminal

Desde la raíz del proyecto:

```
javac -d bin src/com/techlab/Main.java src/com/techlab/productos/*.java src/com/techlab/pedidos/*.java src/com/techlab/excepciones/*.java
java -cp bin com.techlab.Main
```

## Autor

Lucas Mardardjian
