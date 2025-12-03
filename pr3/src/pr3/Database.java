package pr3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteka";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Подключение к БД успешно!");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }   
    public static void initDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            
            String createKlient = "CREATE TABLE IF NOT EXISTS klient (" +
                "id_klienta INT(11) PRIMARY KEY," +
                "fio VARCHAR(90)," +
                "nomer_telephona VARCHAR(11)," +
                "email VARCHAR(50)," +
                "adres_prozhivaniya VARCHAR(50)" +
            ")";
            stmt.executeUpdate(createKlient);    
            String createKniga = "CREATE TABLE IF NOT EXISTS kniga (" +
                "id_knigi INT(11) PRIMARY KEY," +
                "nazvanie VARCHAR(50)," +
                "avtor VARCHAR(50)," +
                "kolvo_stranic VARCHAR(50)," +
                "god_publikacii DATE" +
            ")";
            stmt.executeUpdate(createKniga);           
            String createVydacha = "CREATE TABLE IF NOT EXISTS `vydacha knig` (" +
                "id_vydachi INT(11) PRIMARY KEY AUTO_INCREMENT," +
                "id_klienta INT(11)," +
                "id_knigi INT(11)," +
                "data_vydachi DATE," +
                "data_vozvrata DATE" +
            ")";
            stmt.executeUpdate(createVydacha);     
            System.out.println("Таблицы библиотеки готовы!");
        } catch (SQLException e) {
            System.out.println("Ошибка инициализации таблиц: " + e.getMessage());
        }
}
}
