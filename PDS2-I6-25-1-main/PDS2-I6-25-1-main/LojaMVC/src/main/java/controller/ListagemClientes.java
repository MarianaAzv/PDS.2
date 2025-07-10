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
import model.Cliente;

import java.io.IOException;
import java.util.List;

public class ListagemClientes {
 Stage stage;


    @FXML
    private Button btnAtualizarExcluir;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TextField txtPesquisar;

    private Cliente clienteSelecionado;

    @FXML
    public void initialize() {
        carregarClientes();
    }

    @FXML
    void OnClickAtualizarExcluir(ActionEvent event) {
        clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            abrirTelaAtualizarExcluir(clienteSelecionado);
        } else {
            System.out.println("Selecione um cliente para atualizar ou excluir.");
        }
    }

    @FXML
    void TableViewClick(MouseEvent event) {
        clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnCadastrarClick(ActionEvent event) {
        abrirTelaCadastroCliente();
    }

    @FXML
    void btnFecharClick(ActionEvent event) {
        
        stage.close();
    }

    public void carregarClientes() {
        model.ClienteDAO dao = new model.ClienteDAO();
        List<Cliente> clientes = dao.listarTodosClientes();  
        tabelaClientes.getItems().setAll(clientes);
    }

    private void abrirTelaAtualizarExcluir(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AtualizarExcluirCliente.fxml"));
            Parent root = loader.load();

            controller.AtualizarExcluirClienteController controller = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Atualizar / Excluir Cliente");
            stage.setScene(new Scene(root));

            controller.setStage(stage);
            controller.setCliente(cliente);

            stage.setOnShown(event -> controller.carregarDadosCliente());

            stage.showAndWait();

            carregarClientes(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaCadastroCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroCliente.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Cadastro de Cliente");
            stage.setScene(new Scene(root));

            // Se quiser usar setOnShown aqui para algo, pode fazer
          

            stage.showAndWait();

            carregarClientes(); // Atualiza lista após cadastro
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
    this.stage = stage;
}

}
