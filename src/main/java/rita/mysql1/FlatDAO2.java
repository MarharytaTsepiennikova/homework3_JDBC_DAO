package rita.mysql1;

import java.sql.Connection;

public class FlatDAO2 extends AbstractDAO<Integer, Flat>{
    public FlatDAO2(Connection conn, String table) {
        super(conn, table);
    }
}
