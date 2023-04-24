import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class dbHelper {

    static String userName="root";
    static String password="147369258";
    static String dbUrl="jdbc:mysql://localhost:33066/world"; //world bağlanılan veritabanı adıdır.

/*
    -Veritabanı bağlantısı için dbHelper classı tanımla
        * kullanıcı adı şifre dburli tanımla
        * veritabanına bağlanmak için getconnection fonksiyonunu tanımla
        * veritabanına bağlantı kurmak için drivermanager sınıfının getConnection metodunu  kullan
        * getconnection metodu connection objesi döndürür
        * Bu objeyle sql cümlecikleri (statement object) oluşturup sorgular yürütebiliriz
*/
    public Connection getConnection()throws SQLException{
        return DriverManager.getConnection(dbUrl, userName, password);
        //getConnection SQLException fırlatır

    }

    /*
    Veritabanı hatalarını ve kodlarını döndürmek için showerrormessage sınıfını tanımla
     */

    public void showErrorMesssage(SQLException e){ //veritabanıyla ilgili hataları yönetmesi için kullanılan sınıf
        System.out.println("error: "+e.getMessage());//oluşan hatayı döndürür
        System.out.println("error code: " + e.getErrorCode());//oluşan hata kodunu döndürür


    }


}
