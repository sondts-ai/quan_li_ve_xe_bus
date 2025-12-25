
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectdb {
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=xekhach;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
