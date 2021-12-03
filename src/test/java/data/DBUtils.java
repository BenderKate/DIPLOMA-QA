package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;
import static java.sql.DriverManager.*;


public class DBUtils {
    private static String url= System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    public static void cleanTable() {
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                url, user, password)
        ) {
            runner.update(conn, deletePaymentEntity);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static String getPaymentStatus() throws SQLException {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    private static String getStatus(String query) throws SQLException {
        val runner = new QueryRunner();
        try
                (val conn = DriverManager.getConnection(
                        url, user, password)
                ) {
            String status = runner.query(conn, query, new ScalarHandler<String>());
            return status;
        }
    }
}

