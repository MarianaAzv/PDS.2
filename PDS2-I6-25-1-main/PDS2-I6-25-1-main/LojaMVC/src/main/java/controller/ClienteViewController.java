package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClienteViewController  {

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
    private TextArea txtArea; // Descrição do cliente tipo;

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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dtnsc = LocalDate.parse(txtDTNSC.getText(), formatter);

            incluir(txtNome.getText(), txtTelefone.getText(), txtEndereco.getText(), dtnsc);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void incluir(String nome, String telefone, String endereco, LocalDate data_nascimento) {
        // Simulação de inclusão - para teste
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Endereço: " + endereco);
        System.out.println("Data de Nascimento: " + data_nascimento);
    }

   
}
