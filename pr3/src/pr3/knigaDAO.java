package pr3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class knigaDAO {
    public List<kniga> getAllKniga() {
        List<kniga> knigaList = new ArrayList<>();
        String sql = "SELECT * FROM kniga";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                kniga kniga = new kniga(
                    rs.getInt("id_knigi"),
                    rs.getString("nazvanie"),
                    rs.getString("avtor"),
                    rs.getString("kolvo_stranic"),
                    rs.getDate("god_publikacii")
                );
                knigaList.add(kniga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knigaList;
    }
}
