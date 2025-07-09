
package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
    import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;

public class AtualizarExcluirClienteController {
    
Cliente cliente;
Stage stageAtualizarExcluir;

    @FXML
    private Button btnCadastarClientes;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lblDTNSC;

    @FXML
    private Label lblEndereco;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtDTNSC;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

     public void setStage(Stage stage) {
        this.stageAtualizarExcluir = stage;
    }

    
    @FXML
    void OnClickAtualizar(ActionEvent event) {
      try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dtnsc = LocalDate.parse(txtDTNSC.getText(), formatter);

            Atualizar(txtNome.getText(), txtTelefone.getText(), txtEndereco.getText(), dtnsc);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    void OnClickExcluir(ActionEvent event) throws SQLException {

        ClienteDAO pdao = new ClienteDAO();
        pdao.excluir(cliente);
    }

    
    public void Atualizar(String nome, String telefone, String endereco, LocalDate data_nascimento) throws SQLException{
         System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Endereço: " + endereco);
        System.out.println("Data de Nascimento: " + data_nascimento);
        
        cliente = new Cliente(nome, telefone, endereco,data_nascimento);
      

       
   
        ClienteDAO pdao = new ClienteDAO();
        pdao.atualizarCliente(cliente);
    }
}


