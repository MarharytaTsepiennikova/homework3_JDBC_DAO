package rita.mysql1;

import java.util.List;

public interface FlatDAO {
    void init();
    void addFlat(String district, String address, double square, int rooms, double price);
    void deleteFlat(int id);
    List<Flat> getAll();
}
