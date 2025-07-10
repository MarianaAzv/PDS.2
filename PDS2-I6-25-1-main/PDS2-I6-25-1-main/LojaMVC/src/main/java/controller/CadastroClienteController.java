package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import util.AlertaUtil;

public class CadastroClienteController {
   private Stage stage;
    @FXML
    private Button btnSair;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtData;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtn;

    @FXML
    private TextField txttel;

    @FXML
    void Onsair(ActionEvent event) {
stage.close();
    }

    @FXML
    void SalvarOnClick(ActionEvent event) {
 try {
            if (txtn.getText().isEmpty() || txtEndereco.getText().isEmpty()
                    || txttel.getText().isEmpty() || txtData.getText().isEmpty()) {
                AlertaUtil.mostrarErro("Erro", "Por favor, preencha todos os campos.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(txtData.getText(), formatter);

            cadastrar(txtn.getText(), txttel.getText(), txtEndereco.getText(), dataNascimento);

        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
public void setStage(Stage telaCadastrarCliente) {
        this.stage = telaCadastrarCliente;
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

       
       
    }
}
