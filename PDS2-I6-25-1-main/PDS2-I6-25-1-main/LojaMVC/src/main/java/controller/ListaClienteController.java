package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Cliente;
import model.ClienteDAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ClienteSelecionado;

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

   public void carregarClientes() {
        Vbox.getChildren().clear();
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarTodosClientes();

        for (Cliente c : clientes) {
            Label lbl = new Label(  c.getNome()  + c.getTelefone());
            lbl.getStyleClass().add("item-cliente");
            
            lbl.setOnMouseClicked(event->{
                ClienteSelecionado.setCliente(c);
                try {
                    abrirAtualizar();
                } catch (IOException ex) {
                    Logger.getLogger(ListaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Vbox.getChildren().add(lbl);
        }
    }
   
   public void  abrirAtualizar() throws IOException{
       URL url = new File("src/main/java/view/Atualizar.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        
        Stage telaListagemUsuarios = new Stage();
        
       AtualizarController luc = loader.getController();

        luc.setStage(telaListagemUsuarios);

        

        Scene scene = new Scene(root);
        
        telaListagemUsuarios.setTitle("Listagem de Usuários");
        telaListagemUsuarios.setScene(scene);
        telaListagemUsuarios.show();
   }
}
