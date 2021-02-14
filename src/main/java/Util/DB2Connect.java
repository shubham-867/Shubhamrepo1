package Util;

import Application.Base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.sun.deploy.util.Property;
import org.openqa.selenium.WebDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DB2Connect extends Base {
    public DB2Connect(WebDriver driver, ExtentTest test) {
        super(driver,test);
    }
    public String DBUserName, DBQuery;
    public String DBPassword;
    public Connection connection = null;
    public static Statement statement = null;
    public GenericMethods gm = new GenericMethods();
    public Properties env = gm.envReader();
    public Properties envsql = gm.envReaderSQLQuery();
    public List<String>  list;


    public Statement Db2Connection() throws Exception{

//        String JDBC_Driver = "jdbc:mysql://localhost:3306/seleniumdemo";
//        DBUserName = "root";
//        DBPassword = "@Msd1Lsd";
          String JDBC_Driver= env.getProperty("JDBC_Driver");
          DBUserName = env.getProperty("DBUserName");
          DBPassword = env.getProperty("DBPassword");


        try {
            connection = DriverManager.getConnection(JDBC_Driver,DBUserName,DBPassword);
            if(connection!=null){
                System.out.println("Connection success");
            }else{System.out.println("Connection fail");}

            statement= connection.createStatement();

        }catch (Exception e)
        {e.printStackTrace(); }
        return statement;
    }

    public String getSQLQuery(String query){
    String Query = envsql.getProperty(query);
    // add code to modify the query.
        return Query;
    }
     public List<String> fetchDBData(String querytype, String Col, String Account) throws Exception {

        String query = getSQLQuery(querytype);

        if (query.contains("$Account$")){
            query = query.replace("$Account$",Account);
        }

         String[] columns = Col.split(";");
         int no_col = columns.length;
         ResultSet rst = Db2Connection().executeQuery(query);

         list = new ArrayList<>();
         while (rst.next()){
             for(int i = 0; i <= no_col; i++){
                 list.add(rst.getString(columns[i].trim())); } }
         return list;
         }

     }


