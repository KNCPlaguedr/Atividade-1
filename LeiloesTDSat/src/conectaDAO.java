
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectaDAO {
   
    private Connection conexao;
   
    public void conectar() throws SQLException{
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Inicia a conexão com o banco de dados
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?user=root&password=24071195&autoReconnect=true&useSSL=false");
       
    }catch(ClassNotFoundException e){
            
            System.out.println(e);
            
        }
    }
            //puxa a conexão
     public Connection getConexao() {
        return conexao;
    }
           //fecha a conexão
     public void desconectar() {
        try {
            if(conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("DESCONECTADO COM SUCESSO!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar");
        }
    }
     
    
}
