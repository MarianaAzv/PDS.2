package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Produto;
import model.ProdutoDAO;

public class AtualizarProdutoController {

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtEstoque;

    @FXML
    private Label lblStatus;

    private Produto produto;
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public void setProduto(Produto produto) {
        this.produto = produto;
        txtDescricao.setText(produto.getDescricao());
        txtValor.setText(String.valueOf(produto.getValor()));
        txtEstoque.setText(String.valueOf(produto.getQuantidade_estoque()));
    }

    @FXML
    void salvarAlteracoes() {
        try {
            produto.setDescricao(txtDescricao.getText());
            produto.setValor(Float.parseFloat(txtValor.getText()));
            produto.setQuantidade_estoque(Integer.parseInt(txtEstoque.getText()));

            produtoDAO.atualizarProduto(produto);
            lblStatus.setText("Produto atualizado com sucesso!");

            // Fecha a janela após salvar
            ((Stage) txtDescricao.getScene().getWindow()).close();

        } catch (NumberFormatException e) {
            lblStatus.setText("Valor ou estoque inválido.");
        } catch (Exception e) {
            lblStatus.setText("Erro ao atualizar: " + e.getMessage());
        }
    }
}
