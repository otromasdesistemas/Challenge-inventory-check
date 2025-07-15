import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Tu código comienza aquí
        System.out.println("Reto: Comparación de inventario entre almacenes");

        // Crear productos de ejemplo
        Producto producto1 = new Producto("001", "Camiseta", "Ropa", 25.0, 50);
        Producto producto2 = new Producto("002", "Pantalón", "Ropa", 45.0, 30);
        Producto producto3 = new Producto("003", "Zapatos", "Calzado", 80.0, 20);
        Producto producto4 = new Producto("004", "Gorra", "Accesorios", 15.0, 100);

        // Crear almacén 1 y agregar productos
        Almacen almacen1 = new Almacen("Almacén Norte");
        almacen1.agregarProducto(producto1);
        almacen1.agregarProducto(producto2);
        almacen1.agregarProducto(producto3);

        // Crear almacén 2 y agregar productos (con algunas diferencias)
        Almacen almacen2 = new Almacen("Almacén Sur");
        almacen2.agregarProducto(producto1);
        almacen2.agregarProducto(new Producto("002", "Pantalón", "Ropa", 45.0, 20)); // Diferente stock
        almacen2.agregarProducto(producto4); // Producto diferente

        // Comparar los almacenes
        System.out.println("\nComparación de inventario:");
        almacen1.compararInventario(almacen2);
    }
}

class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Setters
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Producto{" +
               "codigo='" + codigo + '\'' +
               ", nombre='" + nombre + '\'' +
               ", categoria='" + categoria + '\'' +
               ", precio=" + precio +
               ", stock=" + stock +
               '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return codigo.equals(producto.codigo);
    }
}

class Almacen {
    private String nombre;
    private List<Producto> productos;

    public Almacen(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

//    public void compararInventario(Almacen otroAlmacen) {
//        System.out.println("Comparando " + this.nombre + " con " + otroAlmacen.nombre + ":");
//        // TODO: Implementar la lógica para comparar los inventarios de los dos almacenes
//        // 1. Iterar sobre los productos del primer almacén (this.productos)
//        for (Producto productoAlmacen1 : this.productos) {
//            boolean encontradoEnAlmacen1 = false;
//            boolean encontradoEnAlmacen2 = false;
//            for(Producto productoAlmacen2 : otroAlmacen.productos) {
//                // 2. Para cada producto, buscar si existe en el otro almacén (otroAlmacen.productos)
//                if(productoAlmacen1.getCodigo().equals(productoAlmacen2.getCodigo())) {
//                    encontradoEnAlmacen1 = true;
//                    // 3. Si el producto existe en ambos almacenes, comparar el stock
//                    if(productoAlmacen1.getStock() == productoAlmacen2.getStock()) {
//                        System.out.println(
//                                "El producto " + productoAlmacen1.getCodigo() + " " + productoAlmacen1.getNombre()
//                                + " tiene el mismo stock (" + productoAlmacen1.getStock()
//                                + ") en ambos almacenes."
//                        );
//                    }
//                    // 4. Si el stock es diferente, imprimir un mensaje indicando la diferencia
//                    else {
//                        int diferenciaStock = productoAlmacen1.getStock() - productoAlmacen2.getStock();
//                        System.out.println(
//                                "El producto " + productoAlmacen1.getCodigo() + " " + productoAlmacen1.getNombre()
//                                + " tiene una diferencia de stock de " + diferenciaStock
//                                + " entre " + this.nombre + " y " + otroAlmacen.nombre + ".");
//                    }
//                }
//            }
//            // 5. Si el producto no existe en el otro almacén, imprimir un mensaje indicando que no se encuentra
//            if (!encontradoEnAlmacen1) {
//                System.out.println("El producto " + productoAlmacen1.getCodigo() + " " + productoAlmacen1.getNombre()
//                        + " NO se encuentra en el otro almacen."
//                );
//            }
//        }
//        // 6. Repetir el proceso para los productos que están en el otro almacén pero no en el primero
//        for (Producto productoAlmacen2 : otroAlmacen.productos) {
//            boolean encontrado = false;
//
//            for (Producto productoAlmacen1 : this.productos){
//                if (productoAlmacen1.getCodigo().equals(productoAlmacen2.getCodigo())){
//
//                }
//            }
//        }
//    }

    public void compararInventario(Almacen otroAlmacen) {
        // 1. Iterar sobre los productos del primer almacén (this.productos)
        for (Producto product1 : this.productos) {
            Producto pObtained2 = null;

            for (Producto product2 : otroAlmacen.productos) {
                // 2. Para cada producto, buscar si existe en el otro almacén (otroAlmacen.productos)
                if (product1.getCodigo().equals(product2.getCodigo())){
                    pObtained2 = product2;
                    break;
                }
            }
            // 5. Si el producto no existe en el otro almacén, imprimir un mensaje indicando que no se encuentra
            if (pObtained2 == null) {
                System.out.println("El producto " + product1.getCodigo() + " " + product1.getNombre()
                        + " NO se encuentra en el almacén " + otroAlmacen.nombre + ".");
            } else {
                int p1Stock = product1.getStock();
                int p2Stock = pObtained2.getStock();

                // 3. Si el producto existe en ambos almacenes, comparar el stock
                if (p1Stock == p2Stock){
                    System.out.println("El producto " + product1.getCodigo() + " " + product1.getNombre()
                            + " tiene el mismo stock (" + p1Stock + ") en ambos almacenes.");
                } else {
                    // 4. Si el stock es diferente, imprimir un mensaje indicando la diferencia
                    int diff = p1Stock - p2Stock;
                    System.out.println("El producto " + product1.getCodigo() + " " + product1.getNombre()
                            + " tiene una diferencia de stock de " + diff
                            + " entre " + this.nombre + " y " + otroAlmacen.nombre + ".");
                }
            }
        }

        // 6. Repetir el proceso para los productos que están en el otro almacén pero no en el primero
        for (Producto product2 : otroAlmacen.productos) {
            boolean localized = false;
            for (Producto p2 : this.productos) {
                if (p2.getCodigo().equals(product2.getCodigo())) {
                    localized = true;
                    break;
                }
            }

            if (!localized) {
                System.out.println("El producto " + product2.getCodigo() + " " + product2.getNombre()
                        + " NO se encuentra en el almacén " + this.nombre + ".");
            }
        }
    }

//    Para comparar los inventarios de dos almacenes, hice lo siguiente:
//
//    1) Recorrí todos los productos del primer almacén y busqué si están en el segundo.
//    Si no están, aviso que falta ese producto en el otro almacén.
//    Si están, comparo el stock y digo si es igual o cuánto difiere.
//
//    2)Hice lo mismo pero al revés: revisé los productos del segundo almacén para ver si hay alguno
//    que no esté en el primero, y también aviso esos casos.
//
//    3)Así me aseguro de mostrar todas las diferencias posibles:
//    productos que no están en un almacén y diferencias en las cantidades.
//
//    4) Traté de que los mensajes sean claros para entender rápido qué pasa con cada producto y en qué almacén.
//    Usé listas simples y nada muy complicado, para que sea fácil de seguir y modificar después si hace falta.

    // PD: "Si hasta los inventarios necesitan ser entendidos... imaginate tu equipo. Por eso me tienes que contratar :)".
    // PD: Te dejo mi mail por si se te cae una propuesta: galgodevv@gmail.com
}