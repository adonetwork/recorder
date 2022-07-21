package  com.adonetwork.recorder.application.controllers;  

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import com.adonetwork.recorder.application.RecorderMessagesConfiguration;
import com.adonetwork.recorder.application.dto.PlayingListDTO;
import com.adonetwork.recorder.domain.model.FileGenerateException;
import com.adonetwork.recorder.usescases.api.FileGenerateService;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import lombok.extern.slf4j.Slf4j;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Slf4j
public class RecorderController {
    
    /**
     * Classe Controlleur de la vue principale de l'application
     * @author adonorio
     */

     // Liste des membres
     @Autowired
     private RecorderMessagesConfiguration recorderMessagesConfiguration;

     @Autowired
     private FileGenerateService fileGenerateService;

     // Initialisation de l'interface UX
     @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="RecorderPane"
    private AnchorPane RecorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="btn_cancel"
    private JFXButton btn_cancel; // Value injected by FXMLLoader

    @FXML // fx:id="btn_display"
    private JFXButton btn_display; // Value injected by FXMLLoader

    @FXML // fx:id="btn_generate"
    private JFXButton btn_generate; // Value injected by FXMLLoader

    @FXML // fx:id="btn_select"
    private ImageView btn_select; // Value injected by FXMLLoader

    @FXML // fx:id="lbl_status"
    private Label lbl_status; // Value injected by FXMLLoader

    @FXML // fx:id="tfd_folder"
    private TextField tfd_folder; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert RecorderPane != null : "fx:id=\"RecorderPane\" was not injected: check your FXML file 'recorderView.fxml'.";
        assert btn_cancel != null : "fx:id=\"btn_cancel\" was not injected: check your FXML file 'recorderView.fxml'.";
        assert btn_display != null : "fx:id=\"btn_display\" was not injected: check your FXML file 'recorderView.fxml'.";
        assert btn_generate != null : "fx:id=\"btn_generate\" was not injected: check your FXML file 'recorderView.fxml'.";
        assert btn_select != null : "fx:id=\"btn_select\" was not injected: check your FXML file 'recorderView.fxml'.";
        assert lbl_status != null : "fx:id=\"lbl_status\" was not injected: check your FXML file 'recorderView.fxml'.";
        assert tfd_folder != null : "fx:id=\"tfd_folder\" was not injected: check your FXML file 'recorderView.fxml'.";

    }

     // Initialisation de l'interface
     @PostConstruct
     public void init() {
         log.info("RecorderView : Initialisation de l'interface");

        // Gestion des boutons
        this.btn_display.setDisable(true);
        this.btn_generate.setDisable(true);
        this.btn_display.setVisible(false);

        // Gestion de la zone de texte
        this.tfd_folder.setText("");
        this.tfd_folder.setFocusTraversable(true);
        this.tfd_folder.textProperty().addListener((observable, oldValue, newValue) -> {
            this.btn_generate.setDisable(this.tfd_folder.getText() == null || this.tfd_folder.getText().equals(""));
        });

        // Gestion de la zone de statut
        this.lbl_status.setText("");

         // Initialisation du bouton de sélection du répertoire musical
         this.btn_select.setPickOnBounds(true);
         this.btn_select.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                log.info("RecorderView : Sélection du répertoire musical");

                DirectoryChooser dc = new DirectoryChooser();
                File selectedFolder = dc.showDialog(RecorderController.this.RecorderPane.getParent().getScene().getWindow());

                if (selectedFolder != null) {
                    // Stockage du chemin du répertoire sélectionné
                    log.info("RecorderView : Répertoire sélectionné {}", selectedFolder.getAbsolutePath());
                    RecorderController.this.tfd_folder.setText(selectedFolder.getAbsolutePath());

                    // Activation du bouton de génération
                    RecorderController.this.btn_generate.setDisable(false);

                    // Réinitialisation du status
                    RecorderController.this.lbl_status.setText("");
                }
           }   
         });
    
        }

     // Liste des méthodes
    /**
     * Réinitialisation du formulaire
     * @param event l'évènement clic sur le bouton.
     */
    @FXML
    void cancelAction(ActionEvent event) {
        log.info("RecorderView : Réinitialisation du formulaire");

        this.tfd_folder.setText("");
        this.lbl_status.setText("");;
    }

    /**
     * Génération du fichier musical
     * @param event l'évènement clic sur le bouton.
     */
    @FXML
    void generateAction(ActionEvent event) {
        log.info("RecorderView : Lancement de la génération du fichier musical");

        // Création du DTO
        PlayingListDTO musics = new PlayingListDTO();
        musics.setMusicFolder(this.tfd_folder.getText());

        // Lancement de la génération de la liste de lecture
        try {
            boolean status = this.fileGenerateService.generateMusicFile(musics);
            
            if (status) {
                this.lbl_status.setText(this.recorderMessagesConfiguration.getGenerateFileOk());
            }
            else {
                this.lbl_status.setText(this.recorderMessagesConfiguration.getGenerateFileKO());
            }
        } catch (FileGenerateException e) {
            this.lbl_status.setText(this.recorderMessagesConfiguration.getGenerateFileKO());
        }

    }
    
}
