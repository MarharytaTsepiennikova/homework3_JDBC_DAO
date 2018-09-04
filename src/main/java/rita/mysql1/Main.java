/**
Спроектировать базу «Квартиры». Каждая запись в базе содержит данные о квартире
(район, адрес, площадь, кол. комнат, цена).
Сделать возможность выборки квартир из списка по параметрам.
 */

package rita.mysql1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );

        Connection conn = factory.getConnection();
        try {
            FlatDAO dao = new FlatDAOimpl(conn);
            dao.init();

            FlatDAO2 dao2 = new FlatDAO2(conn, "flats");
            //...

            while (true) {
                System.out.println("1: add flat");
                System.out.println("2: view flats");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        System.out.print("Enter district of flat: ");
                        String district = sc.nextLine();
                        System.out.print("Enter address of flat: ");
                        String address = sc.nextLine();
                        System.out.print("Enter square of flat: ");
                        String strSquare = sc.nextLine();
                        double square = Double.parseDouble(strSquare);
                        System.out.print("Enter number of rooms: ");
                        String strRooms = sc.nextLine();
                        int rooms = Integer.parseInt(strRooms);
                        System.out.print("Enter price: ");
                        String strPrice = sc.nextLine();
                        double price = Double.parseDouble(strPrice);

                        dao.addFlat(district, address, square, rooms, price);

                        break;
                    case "2":
                        List<Flat> list = dao.getAll();
                        for (Flat flats : list) {
                            System.out.println(flats);
                        }
                        break;
                    default:
                        return;
                }
            }
        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }
}
