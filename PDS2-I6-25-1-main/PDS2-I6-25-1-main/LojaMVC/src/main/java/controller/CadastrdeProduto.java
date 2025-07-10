package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Produtos;
import model.ProdutoDAO;
import util.AlertaUtil;

public class CadastrdeProduto{

    @FXML
    private Label LblQe;

    @FXML
    private Label LblValor;

    @FXML
    private Button OnClickSalvar;

    @FXML
    private Label lblDescri;

    @FXML
    private TextField txtDescri;

    @FXML
    private TextField txtQe;

    @FXML
    private TextField txtValor;

    @FXML
    void salvarProduto(ActionEvent event) {
        try {
            String descricao = txtDescri.getText();
            float valor = Float.parseFloat(txtValor.getText());
            int quantidadeEstoque = Integer.parseInt(txtQe.getText());

            Produtos produto = new Produtos();
            produto.setDescricao(descricao);
            produto.setValor(valor);
            produto.setQuantidade_estoque(quantidadeEstoque);

            ProdutoDAO dao = new ProdutoDAO();
            dao.inserirProduto(produto);

            AlertaUtil.mostrarInformacao("Sucesso", "Produto cadastrado com sucesso!");

            limparCampos();

        } catch (NumberFormatException e) {
            AlertaUtil.mostrarErro("Erro", "Valor ou Quantidade inválidos.");
        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Falha ao salvar produto: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtDescri.clear();
        txtValor.clear();
        txtQe.clear();
    }
}
