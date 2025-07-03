
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
 import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextArea txtArea;//Descicao do cliente tipo;

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

        try{
        DateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtnsc = LocalDate.parse(txtDTNSC.getText(),formatter);
               
        incluir(txtNome.getText(),txtTelefone.getText(),txtEndereco.getText(),dtnsc);
                
                }catch{
                    
                }
    }

    public void incluir(String nome,String telefone,String endereco,LocalDate data_nascimento ){
        
    }

       
    
}
