package com.adonetwork.recorder.application;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 


public abstract class AbstractJavaFxApplicationSupport extends Application {
    /**
     * Classe abstraite pour le lancement de l'application en JavaFX
     * @author adonorio
     */

    /**
     * Members
     */
    private static String[] savedArgs;

    protected ConfigurableApplicationContext context;

    /**
     * Methodes
     */
    /**
     * Initialiation de l'application
     * @throws Exception une exception est levée si une erreur survient lors de l'initialisation de l'application.
     */
    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    /**
     * Arrêt de l'application
     * @throws Exception une exception est levée si une erreur survient lors de l'arrêt de l'application.
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    /**
     * Lancement de l'application JavaFX
     * @param appClass La classe de démarrage
     * @param args La liste des paramètres de l'application.
     */
    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> appClass, String[] args) {
        AbstractJavaFxApplicationSupport.savedArgs = args;
        Application.launch(appClass, args);
    }
}

