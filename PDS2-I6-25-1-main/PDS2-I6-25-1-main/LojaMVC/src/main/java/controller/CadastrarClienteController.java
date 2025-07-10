package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import util.AlertaUtil;

public class CadastrarClienteController {

    private Stage stage;

     @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtData;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelef;
    @FXML
    void OnClickSalvar(ActionEvent event) {
        try {
            if (txtNome.getText().isEmpty() || txtEndereco.getText().isEmpty()
                    || txtTelef.getText().isEmpty() || txtData.getText().isEmpty()) {
                AlertaUtil.mostrarErro("Erro", "Por favor, preencha todos os campos.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(txtData.getText(), formatter);

            cadastrar(txtNome.getText(), txtTelef.getText(), txtEndereco.getText(), dataNascimento);

        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public void cadastrar(String nome, String telefone, String endereco, LocalDate dataNascimento) throws IOException {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setDataNascimento(dataNascimento);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.inserirCliente(cliente);

        AlertaUtil.mostrarInformacao("Sucesso", "Cliente cadastrado com sucesso!");

       
        VoltarListagem();
    }

    public void setStageCadasCli(Stage telaCadastrarCliente) {
        this.stage = telaCadastrarCliente;
    }
   
    public void  VoltarListagem() throws IOException{
         URL url = new File("src/main/java/view/ListagemCliente.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        
        Stage telaListagemCliente = new Stage();
        
      ListagemCliente  luc = loader.getController();

        luc.setStage(telaListagemCliente);

       telaListagemCliente.setOnShown(evento -> {
            try {
                luc.ajustarElementosJanela();
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Scene scene = new Scene(root);
        
        telaListagemCliente.setTitle("Listagem de Clientes");
        telaListagemCliente.setScene(scene);
        telaListagemCliente.show();
    }

   

   
}
