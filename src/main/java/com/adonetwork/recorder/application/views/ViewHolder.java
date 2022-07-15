package com.adonetwork.recorder.application.views;

import javafx.scene.Parent;
import lombok.Getter;
import lombok.Setter;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/

@Setter
@Getter
public class ViewHolder {
   /**
     * Classe utilitaire Wrapper sur les vues FXML
     * @author adonorio
     */

    // Liste des membres
    private Parent view;
    private Object controller;

    /**
     * Constructeurs
     */
    public ViewHolder(Parent view, Object controller) {
        this.view = view;
        this.controller = controller;
    } 
}
