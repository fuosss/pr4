package pr3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class vydachaknigDAO {
    public List<vydachaknig> getAllVydacha() {
        List<vydachaknig> vydachaList = new ArrayList<>();
        String sql = "SELECT * FROM `vydacha knig`";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            	vydachaknig vydacha = new vydachaknig(
                    rs.getInt("id_vydachi"),
                    rs.getInt("id_klienta"),
                    rs.getInt("id_knigi"),
                    rs.getDate("data_vydachi"),
                    rs.getDate("data_vozvrata")
                );
                vydachaList.add(vydacha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vydachaList;
    }
    
    public void vydatKnigu(int idKlienta, int idKnigi) {
        String sql = "INSERT INTO `vydacha knig` (id_klienta, id_knigi, data_vydachi) VALUES (?, ?, CURDATE())";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idKlienta);
            pstmt.setInt(2, idKnigi);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при выдаче книги: " + e.getMessage());
        }
    }
}