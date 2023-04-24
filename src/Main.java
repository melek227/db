
import java.sql.*;
import java.util.ArrayList;

public class Main {

    //statement    JDBCde sql cümleciği
    public static void main(String[] args) throws SQLException {

        Connection connection = null; //veritabanıyla bağlantı kurmamızı sağlayan class
        dbHelper helper = new dbHelper();
        PreparedStatement statement = null;//kaydedilecek parametreleri kullanıcıdan alırız

        try {

            connection = helper.getConnection();
            String sql="delete from city where id=?";


            statement = connection.prepareStatement(sql);
            statement.setInt(1,4082);
            statement.executeUpdate(); //etkilenen kayıt sayısını döndürür
            System.out.println("kayıt silindi");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            helper.showErrorMesssage(e);

        } finally {
            statement.close();
            connection.close();


        }



    }

    public void updateData() throws SQLException{
        Connection connection = null; //veritabanıyla bağlantı kurmamızı sağlayan class
        dbHelper helper = new dbHelper();
        PreparedStatement statement = null;//kaydedilecek parametreleri kullanıcıdan alırız
        try {

            connection = helper.getConnection();
            String sql="update city set population=80000 where id=?";


            statement = connection.prepareStatement(sql);
            statement.setInt(1,4082);
            int result=statement.executeUpdate(); //etkilenen kayıt sayısını döndürür
            System.out.println("kayıt güncellendi");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            helper.showErrorMesssage(e);

        } finally {
            statement.close();
            connection.close();


        }

    }

    public static void insertData() throws SQLException{
        Connection connection = null; //veritabanıyla bağlantı kurmamızı sağlayan class
        dbHelper helper = new dbHelper();
        PreparedStatement statement = null;//kaydedilecek parametreleri kullanıcıdan alırız
        try {

            connection = helper.getConnection();
            String sql="insert into city (Name, CountryCode, District, Population) values (?,?,?,?)";
            //  ? parametrelerin kullanıcı tarafından gönderileceğini ifade eder
            statement.setString(1,"Düzce"); //kullanıcıdan aldığımız değerleri  yazmak için setString fonksiyonu kullandık
            statement.setString(2,"TUR"); //setter fonksiyonu
            statement.setString(3,"Turkey");
            statement.setInt(4,5000);


            statement = connection.prepareStatement(sql);
            //veritabanı bağlantısında sql sorgusunun bağımsız bir değişken olarak hazırlanmasını sağlar

            statement.executeUpdate(); //etkilenen kayıt sayısını döndürür
            System.out.println("kayıt eklendi");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            helper.showErrorMesssage(e);

        } finally {
            statement.close();
            connection.close();


        }

    }




    public static void selectDemo() throws SQLException{ //Veritabanından veri almak için selectDemo fonksiyonunu tanımladık
        Connection connection=null; //veritabanıyla bağlantı kurmamızı sağlayan class
        dbHelper helper=new dbHelper();
        //veritabanı bağlantısını sağlayan ve hatalarını döndüren tanımladığımız dbHelper sınıfından nesne türettik
        Statement statement=null; //sql cümleciğini (statement object) null değerini atadık
        ResultSet resultSet;//sorgu sonucunda gelen datayı aktarmak için değişken tanımladık

        //veritabanına bağlı olmayabiliriz.
        //getConnection fonksiyonu SQLException fırlatabilir.try-catch bloğunu tanımladık
        try{

            connection=helper.getConnection();
            //dbHelper classından tanımladığımız helper nesnesiyle getconnection fonksiyonunu kullanarak bağlantı kurduk

            statement=connection.createStatement();//gönderilecek sorgunun bu veritabanı bağlantısına gideceğini bellirttik

            resultSet=statement.executeQuery("select Code, Name, Continent, Region from Country");
            //executeQuery=> resultset nesnesi döndüren verilen sql cümleciğini çalıştırır.


            ArrayList<Country>countries=new ArrayList<Country>(); //resultsetten gelecek datayı atayacağımız nesne
            while(resultSet.next()){  //next fonksiyonu resultsetteki datayı tektek gezer.
                System.out.println(resultSet.getString("Name"));
                countries.add(new Country(     //resultsetteki ülkelerin sütunlarını döndürdük
                        resultSet.getString("code"), //bilgileri okumak için getString fonksiyonu kullandık//getter fonksiyonu
                        resultSet.getString("name"),
                        resultSet.getString("continent"),
                        resultSet.getString("region")
                ));


            }

        }catch(SQLException e){ //veritabanına bağlı olmaması durumunda oluşturacağı hata için tanımlanan blok
            helper.showErrorMesssage(e);//hatayı ve hatanın kodunu yazdırdık

        }finally{ //her koşulda çalışacak bloktur.
            connection.close();//veritabanı bağlantısını kapatır


        }


    }
}


