package com.adonetwork.recorder.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adonetwork.recorder.application.views.ViewHolder;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 
@SpringBootApplication
@Slf4j
public class RecorderApplication extends AbstractJavaFxApplicationSupport{

	// Liste des membres
	 // Messages de l'application
	 @Autowired
	 private RecorderMessagesConfiguration messagesConfiguration;
 
	 @Autowired
	 @Qualifier("mainView")
	 private ViewHolder view;

	 //init xy offsets
	 private double xOffset = 0;
	 private double yOffset = 0;
	 
	// Liste des méthodes 
	public static void main(String[] args) {
		log.info("Démarrage de l'application Recorder ...");
        launchApp(RecorderApplication.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(this.messagesConfiguration.getApplicationName());
		Scene root = new Scene(view.getView());
		root.setFill(Color.TRANSPARENT);

		//set mouse pressed
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
         //set mouse drag
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
		
        stage.setScene(root);
		stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/recorder.jpeg")));
        stage.centerOnScreen();
        stage.show();
        stage.toFront();
		
	}

	public static void quit() throws Exception{
        Platform.exit();
        System.exit(0);
    }

}
