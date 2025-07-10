package controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import model.Cliente;
import model.ClienteDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import util.AlertaUtil;

public class ListagemCliente implements Initializable {

    @FXML
    private Button Atualizarouexcluir;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Cliente> tabelaUsuarios;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private TableColumn<Cliente, String> colNome;

    @FXML
    private TableColumn<Cliente, String> colTelefone;

    @FXML
    private TableColumn<Cliente, String> colEndereco;




    private Stage stage;

    private Cliente clienteSelecionado;
    
    
public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
       colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    

    

    carregarClientes();
}


    public void carregarClientes() {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.listarTodosClientes(); 
        tabelaUsuarios.getItems().setAll(lista);
    }

    @FXML
    void TableViewClick(MouseEvent event) {
        clienteSelecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnAtualizarouexcluirClick(ActionEvent event) {
        if (clienteSelecionado == null) {
          AlertaUtil.mostrarErro("ERRo","Selecione um cliente para atualizar ou excluir.");
            return;
        }
        abrirTelaAtualizarExcluir(clienteSelecionado);
    }

    @FXML
    void btnCadastrarClick(ActionEvent event) throws IOException {
        abrirTelaCadastroCliente();
    }

    @FXML
    void btnFecharClick(ActionEvent event) {
      
            stage.close();
        
    }

    

    public void ajustarElementosJanela() throws SQLException {
        // Configurar as colunas da tabela
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        carregarClientes();

        // Adiciona listener para selecionar o cliente na tabela
        tabelaUsuarios.getSelectionModel().selectedItemProperty().addListener(
            (obs, antigo, novo) -> clienteSelecionado = novo
        );
    }

    public void abrirTelaAtualizarExcluir(Cliente cliente) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AtualizarExcluirCliente.fxml"));
            Parent root = loader.load();

            controller.AtualizarouExcluirController controller = loader.getController();
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

    public void abrirTelaCadastroCliente() throws IOException {
          URL url = new File("src/main/java/view/CadastrarCliente.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();
    
    Stage telaCadastrarCliente = new Stage();
    
    CadastrarClienteController luc = loader.getController();

    luc.setStageCadasCli(telaCadastrarCliente);
    
  

    Scene scene = new Scene(root);
    
    telaCadastrarCliente.setTitle("Cadastro de Clientes");
    telaCadastrarCliente.setScene(scene);
    telaCadastrarCliente.show();
    }

   
}




