

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    /*
     * Каждый из тасков решается одним SQL запросом
     */

    /*
    Создать таблицу принтеры, Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)
    добавить в нее 3 записи:
    1 1012 col laser 20000 (производитель HP)
    2 1010 bw jet 5000 (производитель Canon)
    3 1010 bw jet 5000 (производитель Canon)
    Каждая вставка в таблицу принтер должна отражаться добавлением записи в таблицу продукт
     */
    private void fillPreparedStatements(PreparedStatement psPrinter, PreparedStatement psProd, int model, String color, String type, int price, String maker) throws SQLException {
        psPrinter.setInt(1, model);
        psPrinter.setString(2, color);
        psPrinter.setString(3, type);
        psPrinter.setInt(4, price);
        psPrinter.addBatch();
        psProd.setString(1, maker);
        psProd.setInt(2, model);
        psProd.setString(3, "Printer");
        psProd.addBatch();
    }

    public void createPrinterTable(Connection con, Statement stmt) {
        try {
            stmt.execute("CREATE TABLE Printer(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, model INTEGER, color TEXT, type TEXT, price INTEGER)");
            PreparedStatement psForPrinter = con.prepareStatement("INSERT INTO printer (model, color, type, price) values (?, ?, ?, ?)");
            PreparedStatement psForProduct = con.prepareStatement("INSERT INTO Product (maker, model, type) VALUES (?, ?, ?)");
            fillPreparedStatements(psForPrinter, psForProduct, 1012, "col", "laser", 20000, "HP");
            fillPreparedStatements(psForPrinter, psForProduct, 1010, "bw", "jet", 5000, "Canon");
            fillPreparedStatements(psForPrinter, psForProduct, 1010, "bw", "jet", 5000, "Canon");
            psForPrinter.executeBatch();
            psForProduct.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt) {
        ArrayList<String> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT DISTINCT model FROM PC WHERE price > 15000 ");
            while(rs.next()){
                result.add(rs.getString("model"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt) {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            ResultSet rs =  stmt.executeQuery("SELECT id FROM Laptop WHERE speed > 2500 ");
            while(rs.next()){
                result.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt) {
        ArrayList<String> result = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT maker FROM Product where Type = 'Laptop' and maker IN (SELECT DISTINCT maker FROM Product where Type = 'PC'); ");
            while(rs.next()){
                result.add(rs.getString("maker"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * Метод должен вернуть максимальную среди всех произодителей
     * суммарную стоимость всех изделий типов ноутбук или компьютер,
     * произведенных одним производителем
     * Необходимо объединить таблицы продуктов ноутбуков и компьютеров
     * и отгрупировать по сумме прайсов после чего выбрать максимум
     * или сделать любым другим способом
     */

    public int makerWithMaxProceeds(Statement stmt) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT  maker, SUM(price) FROM (SELECT  id, maker, type, price FROM laptop INNER JOIN (SELECT DISTINCT * FROM product WHERE type = 'Laptop') AS dist on dist.model = laptop.model\n" +
                    "UNION ALL\n" +
                    "SELECT  id, maker, type, price FROM PC INNER JOIN (SELECT DISTINCT * FROM product WHERE type = 'PC') AS dist on dist.model = PC.model)\n" +
                    "GROUP BY maker\n" +
                    "ORDER BY SUM(price) DESC ");
            rs.next();
            return rs.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
