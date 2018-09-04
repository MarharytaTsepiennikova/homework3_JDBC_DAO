package rita.mysql1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlatDAOimpl implements FlatDAO {
    private final Connection conn;

    public FlatDAOimpl (Connection conn){
        this.conn = conn;
    }

    @Override
    public void init(){
        try{
            Statement st = conn.createStatement();
            try{
                st.execute("DROP TABLE IF EXISTS Flats");
                st.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCEMENT PRIMARY KEY, " +
                        "district VARCHAR (20), address VARCHAR (200) NOT NULL, square DOUBLE NOT NULL," +
                        "rooms SMALLINT NOT NULL, price DOUBLE)");
            } finally {
                st.close();
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addFlat(String district, String address, double square, int rooms, double price){
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO Flats (district, address, square, " +
                    "rooms, price) VALUES (?, ?, ?, ?, ?)")) {
                st.setString(1, district);
                st.setString(2, address);
                st.setDouble(3, square);
                st.setInt(4, rooms);
                st.setDouble(5, price);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteFlat(int id){
        try {
            try (Statement st = conn.prepareStatement("DELETE FROM Flats WHERE id = ?")) {
                ((PreparedStatement) st).setInt(1, 1);
            }

        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Flat> getAll(){
        List<Flat> res = new ArrayList<>();
        try {
            try (Statement st = conn.createStatement()){
                try (ResultSet rs = st.executeQuery("SELECT * FROM Flats")){
                    while (rs.next()) {
                        Flat flat = new Flat();
                        flat.setId(rs.getInt(1));
                        flat.setDistrict(rs.getString(2));
                        flat.setAddress(rs.getString(3));
                        flat.setSquare(rs.getDouble(4));
                        flat.setRooms(rs.getInt(5));
                        flat.setPrice(rs.getDouble(6));
                        res.add(flat);
                    }
                }
            }
            return res;
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
