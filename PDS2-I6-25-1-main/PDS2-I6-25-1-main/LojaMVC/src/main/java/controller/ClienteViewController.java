package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Cliente;
import model.ClienteDAO;
import util.AlertaUtil;
import static util.AlertaUtil.mostrarAlerta;

public class ClienteViewController {

    Cliente cliente;
    
    @FXML
    private Button btnCadastarClientes;

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

    @FXML
    void OnClickCadastarClientes(ActionEvent event) {
   
    try {
        if (txtNome.getText().isEmpty() || txtEndereco.getText().isEmpty() 
            || txtTelefone.getText().isEmpty() || txtDTNSC.getText().isEmpty()) {

            AlertaUtil.mostrarErro("Erro", "Por favor, preencha todos os campos.");
            return;

        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dtnsc = LocalDate.parse(txtDTNSC.getText(), formatter);

            Cadastrar(txtNome.getText(), txtTelefone.getText(), txtEndereco.getText(), dtnsc);
        }
    } catch (Exception e) {
        AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar cliente: " + e.getMessage());
    }
}


public void Cadastrar(String nome, String telefone, String endereco, LocalDate data_nascimento) {
    Cliente cliente = new Cliente();  // instanciando o cliente
    cliente.setNome(nome);
    cliente.setTelefone(telefone);
    cliente.setEndereco(endereco);
    cliente.setDataNascimento(data_nascimento);

    ClienteDAO clienteDAO = new ClienteDAO();
    clienteDAO.inserirCliente(cliente);

   AlertaUtil.mostrarErro("Sucesso", "Cliente cadastrado com sucesso!");

    
}
}
