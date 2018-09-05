package servidor;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Casa
 */


public class Conexao {
    
    public java.sql.Statement stn;
    public ResultSet rs;
    private String driver = "org.mysql.Driver";
    private String caminho = "jdbc:mysql://localhost:3306/imc";
    private String usuario = "root";
    private String senha = "3306";
    public Connection con;
    
    public void conexao() throws SQLException{
        
        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho,usuario,senha);    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar conexão:\n"+e);
        }
        
    }
    
    public void executaSql(String sql) throws SQLException{
        
        try {
            stn = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stn.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na execução do sql!\n"+e);
        }
        
    }
    
    public void desconecta(){
        
        try {
            con.close();
            JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao descconectar!"+e);
        }
        
    }
    
}
