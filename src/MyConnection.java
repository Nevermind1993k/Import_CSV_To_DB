import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
    public static Connection getConnection() throws Exception {
        Connection conn = null;

        try {
            Properties props = new Properties();
            InputStreamReader in = new InputStreamReader(new FileInputStream("appProperties.txt"), "UTF-8");
            props.load(in);
            in.close();
            String connString = props.getProperty("DBConnectionString");
            conn = DriverManager.getConnection(connString);
            return conn;

        } catch (IOException | SQLException exc) {
            throw new Exception(exc);
        }
    }
}
