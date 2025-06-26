import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {

    public static Connection getConnection() {
        try {
            InputStream inputStream = DbUtil.class.getResourceAsStream("/db_example.properties");
            if (inputStream == null) {
                System.err.println("Error: db.properties file not found in the classpath.");
                return null;
            }

            Properties properties = new Properties();
            properties.load(inputStream);

            String dbDriver = properties.getProperty("dbDriver");
            String connectionUrl = properties.getProperty("connectionUrl");
            String userName = properties.getProperty("userName");
            String password = properties.getProperty("password");

            if (dbDriver == null || connectionUrl == null || userName == null || password == null) {
                System.err.println("Error: Missing required properties in db.properties.");
                return null;
            }

            Class.forName(dbDriver);
            return DriverManager.getConnection(connectionUrl, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
