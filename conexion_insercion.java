import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion_insercion {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/R_H"; 
        String usuario = "root"; 
        String contrasena = ""; 

        Connection conexion = null;

        try {
            
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa a la base de datos");

            
            String sql = "INSERT INTO Usuarios (nombre, correo_electronico, contrasena, tipo_usuario) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, "Cindy");
            ps.setString(2, "fonseca@example.com");
            ps.setString(3, "Segura"); 
            ps.setString(4, "Aspirante");

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("✅ Usuario insertado correctamente");
            }

        } catch (SQLException e) {
            System.out.println("�?� Error al conectar o insertar: " + e.getMessage());
        } finally {
            // Cerrar conexión
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("⚠�? Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
