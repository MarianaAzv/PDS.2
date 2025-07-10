package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Produtos;
import model.ProdutoDAO;

import java.io.IOException;
import java.util.List;

public class ListagemProdutos {

    @FXML
    private Button btnAtualizarExcluir;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Produtos> tabelaProdutos;

    @FXML
    private TextField txtPesquisar;

    private Produtos produtoSelecionado;
    private Stage stageListagemProdutos;

    @FXML
    public void initialize() {
        carregarProdutos();
    }

    @FXML
    void OnClickAtualizarExcluir(ActionEvent event) {
        produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            abrirTelaAtualizarExcluir(produtoSelecionado);
        } else {
            System.out.println("Selecione um produto para atualizar ou excluir.");
        }
    }

    @FXML
    void TableViewClick(MouseEvent event) {
        produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnCadastrarClick(ActionEvent event) {
        abrirTelaCadastroProduto();
    }

    @FXML
    void btnFecharClick(ActionEvent event) {
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    public void carregarProdutos() {
        ProdutoDAO dao = new ProdutoDAO();
        List<Produtos> produtos = dao.listarProdutos();  
        tabelaProdutos.getItems().setAll(produtos);
    }

    private void abrirTelaAtualizarExcluir(Produtos produto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AtualizarExcluirProduto.fxml"));
            Parent root = loader.load();

            AtualizaExcluirodutos controller = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Atualizar / Excluir Produto");
            stage.setScene(new Scene(root));

            controller.setStage(stage);
            controller.setProduto(produto);

            stage.setOnShown(event -> controller.carregarDadosProduto());

            stage.showAndWait();

            carregarProdutos(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaCadastroProduto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroProduto.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Cadastro de Produto");
            stage.setScene(new Scene(root));

            stage.setOnShown(event -> System.out.println("Tela de cadastro de produto aberta"));

            stage.showAndWait();

            carregarProdutos(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stageListagemProdutos) {
        this.stageListagemProdutos = stageListagemProdutos;
    }

}
