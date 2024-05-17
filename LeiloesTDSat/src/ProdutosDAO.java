/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPathFunctionException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public static boolean cadastrarProduto (ProdutosDTO produto) throws SQLException{
        
        try{
            //Puxa conexão com o banco de dados
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            //String do comando sql
            String sql = "insert into produtos (nome, valor, status) VALUES(?,?,?);";

            PreparedStatement query = conexao.getConexao().prepareStatement(sql);

            //Insere os valores no campo destinados aos inserts
            query.setString(1, produto.getNome());
            query.setString(2, produto.getValor().toString());
            query.setString(3, produto.getStatus());

            //executa o comando
            query.execute();

            //desconecta
            conexao.desconectar();
            return true;
        
                }catch(SQLException se){
            System.out.println(se);
            return false;
        }
        
         
    }
    
    public static ArrayList<ProdutosDTO> listarProdutos() throws SQLException{
        
        List<ProdutosDTO> listagem = new ArrayList<ProdutosDTO>();
        
        try{
            
            //Puxa conexão com o banco de dados
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();
            
            String sql = "select * from produtos";
        PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
        
        ResultSet resposta = consulta.executeQuery();
        
        while(resposta.next()){
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resposta.getInt("id"));
            produto.setNome(resposta.getString("nome"));
            produto.setValor(resposta.getInt("valor"));
            produto.setStatus(resposta.getString("status"));
            
            listagem.add(produto);
            
        }
           conexao.desconectar();
    } catch (SQLException se) {
        
    } 
        
        return (ArrayList<ProdutosDTO>) listagem;
    }
    
    public static boolean venderProduto(String id) throws SQLException{
        
        try{
         //Puxa conexão com o banco de dados
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();
            
            String sql = "update produtos set status = ? where id = ?";
            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            
            query.setString(1, "Vendido");
            query.setString(2, String.valueOf(id));
            
              query.execute();

            //desconecta
            conexao.desconectar();
            return true;
        
                }catch(SQLException se){
            System.out.println(se);
            return false;
        }
        
    }
}
    
        


