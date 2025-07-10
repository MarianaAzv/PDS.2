package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<Cliente> listarTodosClientes() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM cliente";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());

            clientes.add(cliente);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar clientes: " + e.getMessage());
    }

    return clientes;
}
}