import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion_consulta {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/R_H"; 
        String usuario = "root"; 
        String contrasena = ""; 

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("✅ Conexión exitosa a la base de datos");

            String sql = "SELECT * FROM Usuarios";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n📋 Lista de usuarios:");
            while (rs.next()) {
                // Eliminado: int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo_electronico");
                String tipo = rs.getString("tipo_usuario");

                System.out.println("Nombre: " + nombre + ", Correo: " + correo + ", Tipo: " + tipo);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o consultar: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                System.out.println("⚠️ Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
