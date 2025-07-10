package controller;

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

public class AtualizarouExcluirController {

    private Stage stage;
    private Cliente cliente;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtDAsc;

    @FXML
    private TextField txtEnd;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txttel;

    @FXML
    void OnClickAtualizar(ActionEvent event) {
        try {
            if (txtNome.getText().isEmpty() || txtEnd.getText().isEmpty()
                    || txttel.getText().isEmpty() || txtDAsc.getText().isEmpty()) {
                AlertaUtil.mostrarErro("Erro", "Por favor, preencha todos os campos.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(txtDAsc.getText(), formatter);

            cliente.setNome(txtNome.getText());
            cliente.setTelefone(txttel.getText());
            cliente.setEndereco(txtEnd.getText());
            cliente.setDataNascimento(dataNascimento);

            ClienteDAO dao = new ClienteDAO();
            dao.atualizarCliente(cliente);

            AlertaUtil.mostrarInformacao("Sucesso", "Cliente atualizado com sucesso!");
            stage.close();
        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @FXML
    void OnClickExcluir(ActionEvent event) {
        try {
            ClienteDAO dao = new ClienteDAO();
            dao.excluirCliente(cliente);

            AlertaUtil.mostrarInformacao("Sucesso", "Cliente excluído com sucesso!");
            stage.close();
        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Erro ao excluir cliente: " + e.getMessage());
        }
    }

    @FXML
    void OnClickVoltar(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void carregarDadosCliente() {
        if (cliente != null) {
            txtNome.setText(cliente.getNome());
            txttel.setText(cliente.getTelefone());
            txtEnd.setText(cliente.getEndereco());
            if (cliente.getDataNascimento() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                txtDAsc.setText(cliente.getDataNascimento().format(formatter));
            }
        }
    }
}
