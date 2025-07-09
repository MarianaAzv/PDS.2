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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class VendaDAO {






    public int inserirVenda(Venda venda) {
        String sql = "INSERT INTO venda (data_compra, valor_total, cliente_id) VALUES (?, ?, ?)";
        int vendaId = -1;

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, Date.valueOf(venda.getDataCompra()));
            stmt.setDouble(2, venda.getValorTotal());
            stmt.setInt(3, venda.getClienteId());

            stmt.executeUpdate();

            // Pega o ID gerado da venda
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                vendaId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir venda: " + e.getMessage());
        }

        return vendaId;
    }

    public List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setDataCompra(rs.getDate("data_compra").toLocalDate());
                venda.setValorTotal(rs.getDouble("valor_total"));
                venda.setClienteId(rs.getInt("cliente_id"));
                vendas.add(venda);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }

        return vendas;
    }


}
