package com.adonetwork.recorder.domain.model;

/*
* Recorder - Générateur de liste de lecture de fichiers musicaux
* Copyright 2022 - AdoNetwork tous droits réservés. * 
*/ 
public class FileGenerateException extends Exception{
    /**
     * Classe  FileGenerateException
     * @author adonorio
     */

    private static final long serialVersionUID = 1L;

    /*
     * Members
     */
    // Module de l'application
    public String layer;

    /*
     * Constructeurs
     */
    /**
     *  Construction de l'exception sans paramètre
     */
    public FileGenerateException()
    {
        super();
    }

    /**
     * Construction de l'exception à partir d'un message d'erreur
     * @param message Le message d'erreur
     */
    public FileGenerateException(String message)
    {
        super(message);
    }

    /**
     * Construction de l'exception à partir d'un message d'erreur et d'une exception
     * @param message  Le message d'erreur
     * @param ex L'exception remontée
     */
    public FileGenerateException(String message, Exception ex)
    {
        super(message, ex);
    }

    /**
     * Construction de l'exception à partir d'un message d'erreur et d'une exception
     * @param message  Le message d'erreur
     * @param layer la couche qui remonte l'exception.
     * @param ex L'exception remontée
     */
    public FileGenerateException(String layer, String message, Exception ex)
    {
        super(message, ex);
        this.layer = layer;
    }

    /**
     * Construction de l'exception à partir d'un message d'erreur 
     * @param layer la couche qui remonte l'exception.
     * @param message  Le message d'erreur
     */
    public FileGenerateException(String layer, String message){
        super(message);
    } 

    /**
     * Acesseurs
     */
    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        layer = layer;
    }

    /**
     * Methodes
     */
    /**
     * Une représentation de l'exception
     * @return Une représentation de l'exception
     */
    public String ToString()
    {
        return String.format("FileGenerateException[%1,%2]", this.getLayer(), this.getMessage());
    } 
}
