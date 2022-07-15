package com.adonetwork.recorder.domain.ports;

import com.adonetwork.recorder.domain.model.FileGenerateException;
import com.adonetwork.recorder.domain.model.PlayingList;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

public interface PlayingListPort {
    
    /**
     * Interface représentant les services de gestion des listes de lecture
     * @author adonorio
     */

     /**
      * Charge le contenu du répertoire contenant les fichiers musicaux
      * @param maListe Ma liste de lecture
      * @return La liste de lecture mise à jour
      * @throws FileGenerateException Une exception est levée si le chargement a échoué
      */
     public PlayingList loadMusicList(PlayingList maListe) throws FileGenerateException;

     /**
      * Création du fichier de lecture
      * @param maListe Ma liste de lecture
      * @return La liste de lecture mise  à jour
      * @throws FileGenerateException Une exception est levée si la génération a échoué
      */
     public PlayingList createMusicFile(PlayingList maListe) throws FileGenerateException;
}
