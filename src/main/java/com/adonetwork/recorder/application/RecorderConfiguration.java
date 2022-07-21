package com.adonetwork.recorder.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "application.recorder")
@ComponentScan (basePackages={"com.adonetwork.recorder.usescases","com.adonetwork.recorder.infrastructure"})
@Getter
@Setter
public class RecorderConfiguration {
   
    /**
     * Classe de Configuration de l'application
     * @author adonorio
     */

     // Liste des membres

    // Nom du fichier de lecture
    private String defaultNameFile;

    // Nom de l'extension du fichier de lecture
    private String extensionNameFile;

}
