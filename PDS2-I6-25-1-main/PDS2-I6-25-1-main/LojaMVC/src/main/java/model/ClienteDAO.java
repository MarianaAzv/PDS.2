package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome,telefone,endereco,data_nascimento) VALUES (?,?,?,?)";

        try (Connection conn = ConexaoBD.conectar(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,cliente.getNome());
            stmt.setString(2,cliente.getTelefone());
            stmt.setString(3,cliente.getEndereco());
            stmt.setDate(4,Date.valueOf(cliente.getDataNascimento()));
            
            stmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        } catch (SQLException e) {
         System.out.println("Erro ao inserir cliente: " + e.getMessage() );
        }
    }
    
    public void atualizarCliente(Cliente cliente) throws SQLException{

String sql="UPDATE cliente set nome =?,telefone =?,endereco=?,data_nascimento=? where id =?";
           try (Connection conn = ConexaoBD.conectar(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
stmt.setString(1,cliente.getNome());
stmt.setString(2,cliente.getTelefone());
stmt.setString(3,cliente.getEndereco());
stmt.setDate(4,Date.valueOf(cliente.getDataNascimento()));
stmt.setInt(5,cliente.getId());
 stmt.executeUpdate();
}

    }
    
    
         
            
          
    
 public void excluir(Cliente cliente) throws SQLException {
     
    
     
        String delete = "DELETE FROM USUARIOS WHERE ID = ?";
         try (Connection conn = ConexaoBD.conectar(); 
                PreparedStatement stmt = conn.prepareStatement(delete)) {
        
        stmt.setInt(1,cliente.getId());
         stmt.executeUpdate();
    }         
            
    }
    public void listarClientes(){
        String sql = "SELECT * FROM cliente";
        
        try(Connection conn = ConexaoBD.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                Date nascimento = rs.getDate("data_nascimento");

             System.out.println("ID: " + id + " | Nome:" + nome + "| Tel: " + telefone + " | Endereço: " + endereco + " | Nascimento: " + nascimento);
             
            }
            
        } catch (SQLException e) {
         System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
    }
}