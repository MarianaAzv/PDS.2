package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Produto;
import model.ProdutoDAO;
import util.AlertaUtil;

public class CadastroProdutoController {

    Stage telaCadastrarProduto;
    @FXML
    private Button btnsalvar;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtQ;

    @FXML
    private TextField txtvalor;

    @FXML
    void OnClickSalvar(ActionEvent event) {
        try {
            String descricao = txtDesc.getText();
            float valor = Float.parseFloat(txtvalor.getText());
            int quantidade = Integer.parseInt(txtQ.getText());

            Produto produto = new Produto();
            produto.setDescricao(descricao);
            produto.setValor(valor);
            produto.setQuantidade_estoque(quantidade);

            ProdutoDAO dao = new ProdutoDAO();
            dao.inserirProduto(produto);

            AlertaUtil.mostrarErro("Sucesso", "Produto cadastrado com sucesso!");

           

        } catch (NumberFormatException e) {
            AlertaUtil.mostrarErro("Erro", "Valor ou quantidade inválidos!");
        }
    }

    public void setStage(Stage telaCadastrarProduto) {
        this.telaCadastrarProduto = telaCadastrarProduto;
    }
}
