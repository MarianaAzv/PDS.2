/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;

/**
 *
 * @author aluno
 */
public class AtualizarController {
    
    Stage stage;
    Cliente cliente;
    private Button btnSair;
     private Button btnSalvar;
      private TextField txtData;
      private TextField txtEndereco;
      private TextField txtn;
      private TextField txttel;
      
       public void setStage(Stage telaListagemUsuarios) {
        this.stage = telaListagemUsuarios;
        
        txtn.setText(cliente.getNome());
                txttel.setText(cliente.getTelefone());
                        txtEndereco.setText(cliente.getEndereco());
                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                txtData.setText(cliente.getDataNascimento().format(formatter));
    }
      
      void Onsair(ActionEvent event){
          stage.close();
         
      }
      
      void SalvarOnClick(ActionEvent event) {
        
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(txtData.getText(), formatter);
            
            cliente.setNome(txtn.getText());
                        cliente.setTelefone(txttel.getText());
                        cliente.setEndereco(txtEndereco.getText());
                        cliente.setDataNascimento(dataNascimento);
                        
                        ClienteDAO pdao = new ClienteDAO();
        try {
            pdao.atualizarCliente(cliente);
        } catch (SQLException ex) {
            System.out.println("Cliente ao atualizar");
        }
            
            
      }
}
