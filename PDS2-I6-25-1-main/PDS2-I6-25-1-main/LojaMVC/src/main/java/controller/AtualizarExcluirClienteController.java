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

    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        txtNome.setText(cliente.getNome());
        txtTelefone.setText(cliente.getTelefone());
        txtEndereco.setText(cliente.getEndereco());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtDTNSC.setText(cliente.getDataNascimento().format(formatter));
    }

    @FXML
    void OnClickAtualizar(ActionEvent event) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dtnsc = LocalDate.parse(txtDTNSC.getText(), formatter);

            // Atualiza o cliente atual
            cliente.setNome(txtNome.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setEndereco(txtEndereco.getText());
            cliente.setDataNascimento(dtnsc);

            ClienteDAO pdao = new ClienteDAO();
            pdao.atualizarCliente(cliente);
            System.out.println("Cliente atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void OnClickExcluir(ActionEvent event) {
        try {
            if (cliente != null) {
                ClienteDAO pdao = new ClienteDAO();
                pdao.excluir(cliente);
                System.out.println("Cliente excluído com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            
        }
    }
    public void carregarDadosCliente() {
    txtNome.setText(cliente.getNome());
    txtTelefone.setText(cliente.getTelefone());
    txtEndereco.setText(cliente.getEndereco());
    txtDTNSC.setText(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
}
}
