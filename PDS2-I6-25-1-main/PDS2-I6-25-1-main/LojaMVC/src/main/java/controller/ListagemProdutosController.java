package controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Produto;
import model.ProdutoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import javafx.stage.Modality;

public class ListagemProdutosController {

    @FXML
    private VBox Vbox;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVoltar;

    private Produto produtoSelecionado; // ⬅️ produto clicado

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @FXML
    public void initialize() {
        carregarProdutos();
    }

    private void carregarProdutos() {
        Vbox.getChildren().clear(); // Limpa antes de adicionar
        List<Produto> produtos = produtoDAO.listarProdutos();

        for (Produto p : produtos) {
            Button btnProduto = new Button(p.getDescricao());
            btnProduto.setMaxWidth(Double.MAX_VALUE); // Ocupa largura total do VBox
            btnProduto.setOnAction(e -> {
                produtoSelecionado = p;
                System.out.println("Selecionado: " + p.getDescricao());
            });
            Vbox.getChildren().add(btnProduto);
        }
    }

    @FXML
    void atualizar(ActionEvent event) {
        if (produtoSelecionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/java/view/atualizar.fxml"));
                Parent root = loader.load();

                AtualizarProdutoController controller = loader.getController();
                controller.setProduto(produtoSelecionado);

                Stage stage = new Stage();
                stage.setTitle("Atualizar Produto");
                stage.setScene(new Scene(root));
                stage.showAndWait();

                carregarProdutos(); // Recarrega os produtos após edição

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Selecione um produto primeiro para atualizar.");
        }
    }

   @FXML
void cadastro(ActionEvent event) {
    try {
         URL url = new File("src/main/java/view/CadastrarProduto.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();
    

              CadastroProdutoController controller = loader.getController();
              
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Produto");
        stage.setScene(new Scene(root));
        controller.setStage(stage);
        stage.showAndWait();

        carregarProdutos(); // Recarrega a listagem após o cadastro

    } catch (IOException e) {
        e.printStackTrace();
    }
}


            

            
           
            
            
           
    @FXML
    void Excluir(ActionEvent event) {
        if (produtoSelecionado != null) {
            produtoDAO.excluirProduto(produtoSelecionado.getId());
            carregarProdutos();
            produtoSelecionado = null;
        } else {
            System.out.println("Selecione um produto para excluir.");
        }
    }

    @FXML
    void Voltar(ActionEvent event) {
        // Lógica para voltar à tela anterior
    }
}
