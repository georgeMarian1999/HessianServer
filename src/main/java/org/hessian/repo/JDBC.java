package org.hessian.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JDBC {
    private Properties JDBCProps;
    private final String user = "nbwavzyshzkuiy";
    private final String password = "5ce75fe8818ecb2e397d77d0749ef05d630b38c732e7cbfb6eeaf7eba775a90f";



    public JDBC(){
        Properties JDBCProps = new Properties();
        JDBCProps.setProperty("user","nbwavzyshzkuiy");
        JDBCProps.setProperty("password","5ce75fe8818ecb2e397d77d0749ef05d630b38c732e7cbfb6eeaf7eba775a90f");
        JDBCProps.setProperty("ssl","true");
    }
    private Connection instance=null;
    public Connection getNewConnection(){


        String url = "jdbc:postgresql://ec2-52-200-215-149.compute-1.amazonaws.com:5432/ds75uhlqkbe92";
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");

            con= DriverManager.getConnection(url,user, password);
        }
        catch(SQLException ex){
            System.out.println("Error while connecting " + ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public Connection getConnection(){
        try{
            if(instance==null || instance.isClosed())
                instance=this.getNewConnection();
        }catch(SQLException ex){
            System.out.println(ex);
        }

        return instance;
    }
}
