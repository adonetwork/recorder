package  com.adonetwork.recorder.application.controllers;  

import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.adonetwork.recorder.application.RecorderApplication;
import com.adonetwork.recorder.application.RecorderMessagesConfiguration;
import com.adonetwork.recorder.application.views.ViewHolder;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Slf4j
public class MainController {
    
    /**
     * Classe Controlleur de la vue principale de l'application
     * @author adonorio
     */

     // Liste des membres
     @Autowired
     private RecorderMessagesConfiguration recorderMessagesConfiguration;

    // Liste des vues
    @Qualifier("recorderView")
    @Autowired
    private ViewHolder recorderView;

     // Initialisation de l'interface UX
     @FXML // ResourceBundle that was given to the FXMLLoader
     private ResourceBundle resources;
 
     @FXML // URL location of the FXML file that was given to the FXMLLoader
     private URL location;
 
     @FXML // fx:id="ActionsPane"
     private AnchorPane ActionsPane; // Value injected by FXMLLoader
 
     @FXML // fx:id="ContentPane"
     private AnchorPane ContentPane; // Value injected by FXMLLoader

     @FXML // fx:id="quitButton"
     private ImageView btn_exit; // Value injected by FXMLLoader

     @FXML // fx:id="lbl_title"
     private Label lbl_title; // Value injected by FXMLLoader

     @FXML // fx:id="lbl_description"
     private Label lbl_description; // Value injected by FXMLLoader

     @FXML // fx:id="lbl_copyright"
     private Label lbl_copyright; // Value injected by FXMLLoader

     @FXML // fx:id="lbl_version"
     private Label lbl_version; // Value injected by FXMLLoader
 
     @FXML // This method is called by the FXMLLoader when initialization is complete
     void initialize() {
         assert ActionsPane != null : "fx:id=\"ActionsPane\" was not injected: check your FXML file 'MainView.fxml'.";
         assert ContentPane != null : "fx:id=\"ContentPane\" was not injected: check your FXML file 'MainView.fxml'.";
         assert btn_exit != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'mainView.fxml'.";
         assert lbl_title != null : "fx:id=\"lbl_title\" was not injected: check your FXML file 'mainView.fxml'.";
         assert lbl_description != null : "fx:id=\"lbl_description\" was not injected: check your FXML file 'mainView.fxml'.";
         assert lbl_copyright != null : "fx:id=\"lbl_copyright\" was not injected: check your FXML file 'mainView.fxml'.";
         assert lbl_version != null : "fx:id=\"lbl_version\" was not injected: check your FXML file 'mainView.fxml'.";

     }

     // Initialisation de l'interface
     @PostConstruct
     public void init() {
         log.info("MainView : Initialisation de l'interface");

         // Initialisation du bouton de fermeture de l'application
         this.btn_exit.setPickOnBounds(true);
         this.btn_exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                log.info("Main : Clic sur le bouton de fermeture de l'application");
                try {
                    RecorderApplication.quit();
                } catch (Exception e) {
                    log.error("Erreur sur la fermeture de l'application", e);
                }
            }
         });

         // Initialisation du titre de l'application
         this.lbl_title.setText(recorderMessagesConfiguration.getApplicationName());
         this.lbl_description.setText(recorderMessagesConfiguration.getApplicationDescription());;
         this.lbl_copyright.setText(recorderMessagesConfiguration.getCopyright());
         this.lbl_version.setText(recorderMessagesConfiguration.getApplicationVersion());

         // Chargement de la vue principale
         this.ContentPane.getChildren().clear();
         this.ContentPane.getChildren().add(recorderView.getView());
       
     }

     // Liste des méthodes
    
}
