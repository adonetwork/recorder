package com.adonetwork.recorder.domain.model;

import java.nio.file.Path;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Getter
@Setter
public final class PlayingList {
    /**
     * Objet Métier représentant la liste de lecture
     * @author adonorio
     */
    
    // Liste des membres
    
    // Répertoire contenant les fichiers musicaux
    private Path musicFolder;

    // Liste des fichiers musicaux
    private List<Path> musicFiles;

    // Chemin d'accès au fichier de lecture
    private String musicFilePath;



}
