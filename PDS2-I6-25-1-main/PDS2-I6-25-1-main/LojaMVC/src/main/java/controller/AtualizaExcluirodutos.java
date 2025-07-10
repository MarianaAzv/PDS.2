package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Produtos;
import model.ProdutoDAO;
import util.AlertaUtil;

public class AtualizaExcluirodutos {

    @FXML
    private Label LblQe;

    @FXML
    private Label LblValor;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lblDescri;

    @FXML
    private TextField txtDescri;

    @FXML
    private TextField txtQe;

    @FXML
    private TextField txtValor;

    private Produtos produto;
    private Stage stage;

    @FXML
    void OnClickbtnAtualizar(ActionEvent event) {
        try {
            produto.setDescricao(txtDescri.getText());
            produto.setValor(Float.parseFloat(txtValor.getText()));
            produto.setQuantidade_estoque(Integer.parseInt(txtQe.getText()));

            ProdutoDAO dao = new ProdutoDAO();
            dao.atualizarProduto(produto);

            AlertaUtil.mostrarInformacao("Sucesso", "Produto atualizado com sucesso!");
            stage.close();
        } catch (NumberFormatException e) {
            AlertaUtil.mostrarErro("Erro", "Valor ou Quantidade inválidos.");
        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Falha ao atualizar: " + e.getMessage());
        }
    }

    @FXML
    void onclickbtnExcluir(ActionEvent event) {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            dao.excluirProduto(produto.getId());

            AlertaUtil.mostrarInformacao("Sucesso", "Produto excluído com sucesso!");
            stage.close();
        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Falha ao excluir: " + e.getMessage());
        }
    }

    public void carregarDadosProduto() {
        txtDescri.setText(produto.getDescricao());
        txtValor.setText(String.valueOf(produto.getValor()));
        txtQe.setText(String.valueOf(produto.getQuantidade_estoque()));
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
