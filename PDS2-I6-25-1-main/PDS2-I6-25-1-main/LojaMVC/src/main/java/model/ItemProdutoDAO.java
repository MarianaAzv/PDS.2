/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mariana
 */
import dal.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemProdutoDAO {
   




    public void inserirItem(ItemProduto item) {
        String sql = "INSERT INTO item_produto (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getVendaId());
            stmt.setInt(2, item.getProdutoId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPrecoUnitario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir item de produto: " + e.getMessage());
        }
    }

    public List<ItemProduto> listarItensPorVenda(int vendaId) {
        List<ItemProduto> itens = new ArrayList<>();
        String sql = "SELECT * FROM item_produto WHERE venda_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vendaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemProduto item = new ItemProduto();
                item.setId(rs.getInt("id"));
                item.setVendaId(rs.getInt("venda_id"));
                item.setProdutoId(rs.getInt("produto_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("preco_unitario"));
                itens.add(item);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar itens da venda: " + e.getMessage());
        }

        return itens;
    }


}
