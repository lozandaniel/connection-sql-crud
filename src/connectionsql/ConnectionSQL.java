package connectionsql;

/**
 *
 * @author Daniel Lozano y Stiven Quiroga
 */
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSQL {

    public static void main(String[] args) throws SQLException {

        String user = "root"; // Usuario de la base de datos
        String password = "-contraseña-"; // Insertar contraseña de base de datos
        String url = "jdbc:mysql://localhost:3306/software_sena"; // Direccion url de la base de datos
        Connection conexion;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        conexion = DriverManager.getConnection(url, user, password);
        statement = conexion.createStatement();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Ingrese la opcion solicitada");
            System.out.println("1. Agregar Producto: ");
            System.out.println("2. Obtener lista de productos: ");
            System.out.println("3. Eliminar un producto por su ID: ");
            System.out.println("4. Actualizar un valor de un producto por su ID: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarProducto(statement);
                    break;
                case 2:
                    obtenerProductos(statement);
                    break;
                case 3:
                    eliminarProducto(statement);
                    break;
                case 4:
                    actualizarProducto(statement);
                    break;
                default:
                    throw new AssertionError();
            }

        } while (opcion != 5);

        statement.close();
        scanner.close();
    }

    // Funcion para agregar un producto
    private static void agregarProducto(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del producto!");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la descripcion del producto!");
        String descripcion = scanner.nextLine();
        System.out.println("Ingrese la categoria del producto!");
        String categoria = scanner.nextLine();
        System.out.println("Ingrese la cantidad del producto!");
        int cantidad = scanner.nextInt();
        System.out.println("Ingrese el precio del producto!");
        double precio = scanner.nextDouble();

        statement.executeUpdate("INSERT INTO productos(id_proovedor, nombre, descripcion, categoria, cantidad, precio) "
                + "VALUES (1, '" + nombre + "','" + descripcion + "','" + categoria + "'," + cantidad + "," + precio + ");");
        System.out.println("Usuario agregado a la base de datos correctamente;");
    }

    // Funcion para obtener todos los productos que se encuentran actualmente en la tabla;
    private static void obtenerProductos(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * from productos");

        while (resultSet.next()) {
            System.out.println("Id:" + resultSet.getInt("id_producto") + " " + resultSet.getString("nombre") + " / " + resultSet.getString("descripcion"));
        }
    }

    // Funcion para eliminar un producto por medio de su ID
    private static void eliminarProducto(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id del producto que desea eliminar..");
        int idProducto = scanner.nextInt();

        statement.executeUpdate("DELETE from productos WHERE id_producto = " + idProducto);
        System.out.println("Producto eliminado con exito de la base de datos!");

    }

    // Funcion para actualizar producto
    private static void actualizarProducto(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del producto que desea actualizar");
        int idProducto = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        System.out.println("Ingrese el nombre de la columna a actualizar");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el nuevo valor:");
        String nuevoValor = scanner.nextLine();

        statement.executeUpdate("UPDATE productos SET " + nombre + " = '" + nuevoValor + "' WHERE id_producto = " + idProducto);
        System.out.println("Usuario actualizado");
    }
}
