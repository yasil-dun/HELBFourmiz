package model.ant;

import model.resource.Resource;

/**
 * Représente la ressource transportée par une fourmi.
 *
 * Cette classe permet de gérer l'état du transport
 * sans utiliser la valeur null.
 */
public class CarriedResource {

    /**
     * Indique si aucune ressource n'est transportée.
     */
    private boolean empty;


    /**
     * Ressource transportée.
     */
    private Resource resource;



    /**
     * Construit un transport vide.
     */
    public CarriedResource() {

        empty = true;

    }



    /**
     * Vérifie si le transport est vide.
     *
     * @return vrai si aucune ressource n'est transportée
     */
    public boolean isEmpty() {

        return empty;

    }



    /**
     * Ajoute une ressource au transport.
     *
     * @param resource ressource récupérée
     */
    public void setResource(Resource resource) {

        this.resource = resource;

        empty = false;

    }



    /**
     * Retourne la ressource transportée.
     *
     * @return ressource transportée
     */
    public Resource getResource() {

        return resource;

    }



    /**
     * Vide le transport.
     */
    public void clear() {

        empty = true;

    }

}