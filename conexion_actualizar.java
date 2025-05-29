import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion_actualizar {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/R_H"; 
        String usuario = "root"; 
        String contrasena = ""; 

        Connection conexion = null;

        // Correo actual del usuario que quieres actualizar
        String correoActual = "silva@example.com";

        // Nuevos valores
        String nuevoNombre = "Carlos Daza";
        String nuevaContrasena = "nuevaClaveSegura123";
        String nuevoTipo = "Administrador";
        String nuevoCorreo = "carlos.daza@example.com";

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa a la base de datos");

            String sql = "UPDATE Usuarios SET nombre = ?, contrasena = ?, tipo_usuario = ?, correo_electronico = ? WHERE correo_electronico = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevaContrasena);
            ps.setString(3, nuevoTipo);
            ps.setString(4, nuevoCorreo);      // nuevo correo
            ps.setString(5, correoActual);     // correo anterior

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("🔄 Usuario actualizado correctamente.");
            } else {
                System.out.println("ℹ️ No se encontró un usuario con ese correo.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o actualizar: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("⚠️ Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}

