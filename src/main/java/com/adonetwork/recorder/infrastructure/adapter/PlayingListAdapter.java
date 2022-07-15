package com.adonetwork.recorder.infrastructure.adapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adonetwork.recorder.application.RecorderConfiguration;
import com.adonetwork.recorder.domain.model.FileGenerateException;
import com.adonetwork.recorder.domain.model.PlayingList;
import com.adonetwork.recorder.domain.ports.PlayingListPort;

import lombok.extern.slf4j.Slf4j;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Slf4j
@Service("PlayingListAdapter")
public class PlayingListAdapter implements PlayingListPort{

    /**
     * Classe représentant les services de gestion des listes de lecture
     * @author adonorio
     */

    private static final String LAYER = "INFRASTRUCTURE";

    // Liste des membres
    @Autowired
    private RecorderConfiguration recorderConfiguration;

    // Liste des méthodes
    @Override
    public PlayingList loadMusicList(PlayingList maListe) throws FileGenerateException {
        log.info(LAYER + " : Chargement du contenu d'un répertoire");

       List<Path> files = new ArrayList<Path>();
        
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(maListe.getMusicFolder());
            for (Path file: stream) {
                log.info("Fichier trouvé dans le répertoire : " + file.getFileName());
                files.add(file);
            }   
            
            // Mise à jour de l'objet
            maListe.setMusicFiles(files);
        }
        catch (Exception e) {
            throw new FileGenerateException(LAYER, "Une erreur est survenue lors du chargement du contenu du répertoire",e);
        }

        return maListe;
    }

    @Override
    public PlayingList createMusicFile(PlayingList maListe) throws FileGenerateException {
        log.info(LAYER + " : Génération du fichier de lecture");

        String fileName = maListe.getMusicFolder().getParent() + "/" + recorderConfiguration.getDefaultNameFile();
        log.info("Fichier de lecture sélectionné " + fileName);

        String directoryName = maListe.getMusicFolder().getFileName().toString();
        log.info("Nom du répertoire de lecture " + directoryName);

        FileOutputStream fos = null;

         try {
            fos = new FileOutputStream(fileName);
            Iterator<Path> musicIterator = maListe.getMusicFiles().iterator();
            while(musicIterator.hasNext()) {
                Path musicFile = musicIterator.next();
                String name = directoryName + "/" + musicFile.toFile().getName() + "\n";
                fos.write(name.getBytes());
                fos.flush();
            }

            maListe.setMusicFilePath(fileName);
        }
        catch (FileNotFoundException e) {
            throw new FileGenerateException(LAYER, "Le fichier de lecture n'existe pas",e);
        }
        catch (Exception e) {
            throw new FileGenerateException(LAYER, "Une erreur est survenue lors de la création du fichier de lecture",e);
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                throw new FileGenerateException(LAYER, "Une erreur est survenue lors de la fermeture du fichier de lecture.",e);
            }
        }
        return null;
    }
    
}
