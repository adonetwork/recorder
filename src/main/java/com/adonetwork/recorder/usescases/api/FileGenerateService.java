package com.adonetwork.recorder.usescases.api;

import com.adonetwork.recorder.application.dto.PlayingListDTO;
import com.adonetwork.recorder.domain.model.FileGenerateException;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 
public interface FileGenerateService {
  /**
     * Liste des cas d'utilisation de génération de fichiers
     * @author adonorio
     */
    
    // Liste des methodes

    /**
     * Génération de la liste de lecture
     * @param musicFolder le répertoire de stockage des fichiers musicaux
     * @return true si la génération a réussi sinon false
     * @throws FileGenerationException Une exception est levée si le processus de génération n'a pas pu aboutir
     */
    public boolean generateMusicFile (PlayingListDTO musicFolder) throws FileGenerateException;
}
