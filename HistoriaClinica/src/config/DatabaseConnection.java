package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/clinica53";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    
    static {
        try {
            //Carga del driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //Excepción que se lanza si el driver no esté disponible
            throw new RuntimeException("Error: no se encontró el driver JDBC. ", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        //Validación para asegurar que las credenciales no estén vacías
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException("Configuración de la base de datos ó incompleta o inválida.");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
