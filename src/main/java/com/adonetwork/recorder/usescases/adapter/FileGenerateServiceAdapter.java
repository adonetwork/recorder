package com.adonetwork.recorder.usescases.adapter;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.adonetwork.recorder.application.dto.PlayingListDTO;
import com.adonetwork.recorder.domain.model.FileGenerateException;
import com.adonetwork.recorder.domain.model.PlayingList;
import com.adonetwork.recorder.infrastructure.adapter.PlayingListAdapter;
import com.adonetwork.recorder.usescases.api.FileGenerateService;

import lombok.extern.slf4j.Slf4j;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/

@Service("FileGenerateService")
@Slf4j
public class FileGenerateServiceAdapter implements FileGenerateService{
    /**
     * Classe d'implémentation des cas d'utilisation de génération de liste de lecture
     * @author adonorio
     */

    private static final String LAYER = "USES-CASES";

    // Liste des membres
    @Autowired
    @Qualifier("PlayingListAdapter")
    private PlayingListAdapter playingListAdapter;


    // Liste des méthodes
    @Override
    public boolean generateMusicFile(PlayingListDTO musicFolder) throws FileGenerateException {
        log.info(LAYER + " : Génération du fichier contenant la liste de lecture");

        if (musicFolder != null && !musicFolder.getMusicFolder().equals("")) {
            // Génération du fichier
            File musicPath = new File(musicFolder.getMusicFolder());
            
            if (musicPath.isDirectory()) {
                // Création de l'objet Métier
                PlayingList list = new PlayingList();
                list.setMusicFolder(Paths.get(musicFolder.getMusicFolder()));

                // Lecture du répertoire contenant les fichiers musicaux
                playingListAdapter.loadMusicList(list);

                // Génération du fichier de lecture
                if (list.getMusicFiles().size() > 0) {
                    playingListAdapter.createMusicFile(list);

                    return true;
                }
                else {
                    throw new FileGenerateException(LAYER, "Le contenu du répertoire est vide.");
                }
            }
            else {
                throw new FileGenerateException(LAYER, "Le chemin n'est pas un répertoire valide");
            } 
        }
        return false;
    }

}
