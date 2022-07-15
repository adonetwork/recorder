package com.adonetwork.recorder.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 

@Configuration
@PropertySource(value="classpath:messages.properties",encoding="UTF-8")
@ConfigurationProperties(prefix = "application.recorder")
@Getter
@Setter
public class RecorderMessagesConfiguration {
    /**
     * Classe de Configuration de l'application (Libellés des messages)
     * @author adonorio
     */ 

    // Liste des membres

    // Nom de l'application
    private String applicationName;

    // Description de l'application
    private String applicationDescription;

    // Version de l'application
    private String applicationVersion;

    // Copyright de l'application
    private String copyright;

    // Génération Statut OK
    private String generateFileOk;

    // Génération Statut KO
    private String generateFileKO;

}
