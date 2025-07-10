package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Cliente;
import model.ClienteDAO;

import java.util.List;
import javafx.stage.Stage;

public class ListaClienteController {

    Stage stage;
    @FXML
    private VBox Vbox;

    @FXML
    private Button btnVoltar;

    @FXML
    public void initialize() {
        carregarClientes();
    }
    
    public void setStage(Stage telaListagemUsuarios) {
        this.stage = telaListagemUsuarios;
    }

    @FXML
    void Voltar(ActionEvent event) {
     stage.close();
    }

    private void carregarClientes() {
        Vbox.getChildren().clear();
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarTodosClientes();

        for (Cliente c : clientes) {
            Label lbl = new Label(  c.getNome()  + c.getTelefone());
            lbl.getStyleClass().add("item-cliente");
            Vbox.getChildren().add(lbl);
        }
    }
}
