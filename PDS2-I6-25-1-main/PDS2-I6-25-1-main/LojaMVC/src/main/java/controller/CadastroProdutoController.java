package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroProdutoController {

    Stage stage;
    @FXML
    private Button btnsalvar;

    @FXML
    private Button btnvoltar;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtEst;

    @FXML
    private TextField txtValor;

    @FXML
    void OnClicksalvar(ActionEvent event) {

    }

    @FXML
    void Voltar(ActionEvent event) {
stage.close();
    }

}
