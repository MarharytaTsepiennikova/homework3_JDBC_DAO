package rita.mysql1;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<K, T> {
    private final Connection conn;
    private final String table;

    public AbstractDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public List<T> getAll() throws Exception{
        List<T> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData md = rs.getMetaData();

                    while (rs.next()) {
                        T flat = new T();
                        Class<?> cls = flat.getClass();

                        for (int i = 1; i < md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);

                            Field field = cls.getField(columnName);
                            field.setAccessible(true);

                            field.set(flat, rs.getObject(columnName));
                        }

                        res.add(flat);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
