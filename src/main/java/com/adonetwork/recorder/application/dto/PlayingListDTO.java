package com.adonetwork.recorder.application.dto;

import lombok.Getter;
import lombok.Setter;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Getter
@Setter
public final class PlayingListDTO {
    /**
     * Classe DTO représentant la liste de lecture
     */

     // Liste des membres
     
     // Le chemin d'accès aux fichiers musicaux
     private String musicFolder;

}
