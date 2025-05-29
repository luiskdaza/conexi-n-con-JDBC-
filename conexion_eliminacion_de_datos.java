import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion_eliminacion_de_datos {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/R_H"; 
        String usuario = "root"; 
        String contrasena = ""; 

        Connection conexion = null;

        // Cambia este correo por el que quieras eliminar
        String correoEliminar = "David.daza@example.com";

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa a la base de datos");

            String sql = "DELETE FROM Usuarios WHERE correo_electronico = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, correoEliminar);

            int filasEliminadas = ps.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("🗑️ Usuario eliminado correctamente.");
            } else {
                System.out.println("ℹ️ No se encontró un usuario con ese correo.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o eliminar: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("⚠️ Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
