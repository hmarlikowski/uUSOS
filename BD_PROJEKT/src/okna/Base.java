/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package okna;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
/**
 *
 * @author hubert
 */
public class Base {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    int numRows;

    public Base(String url, String usr, String passwd){

       /* String url = "jdbc:postgresql://localhost/test";
        String user = "postgres";
        String password = "24godziny";
        */


        try {
            //con = DriverManager.getConnection(url, user, password);
            con = DriverManager.getConnection(url, usr, passwd);
            st = con.createStatement();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Base.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
    }

    public void close(){
                            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Base.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
    
    public ResultSet executeQuerry(String s){
        try {
            rs = st.executeQuery(s);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Base.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return rs;
    }

    public int executeUpdate(String s){
        try {
            numRows = st.executeUpdate(s);
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Base.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return numRows;
    }
    
}
