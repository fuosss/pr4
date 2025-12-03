package pr3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class klientDAO {
    public List<klient> getAllKlient() {
        List<klient> klientList = new ArrayList<>();
        String sql = "SELECT * FROM klient";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                klient klient = new klient(
                    rs.getInt("id_klienta"),
                    rs.getString("fio"),
                    rs.getString("nomer_telephona"),
                    rs.getString("email"),
                    rs.getString("adres_prozhivaniya")
                );
                klientList.add(klient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return klientList;
    }
}
