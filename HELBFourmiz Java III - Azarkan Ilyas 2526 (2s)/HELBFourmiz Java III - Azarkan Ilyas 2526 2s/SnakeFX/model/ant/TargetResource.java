package model.ant;

import model.resource.Resource;

/**
 * Représente une ressource ciblée par une fourmi.
 *
 * Cette classe permet de gérer l'absence de cible
 * sans utiliser la valeur null.
 */
public class TargetResource {

    /**
     * Indique si aucune ressource n'est ciblée.
     */
    private boolean empty;


    /**
     * Ressource ciblée.
     */
    private Resource resource;



    /**
     * Construit une cible vide.
     */
    public TargetResource() {

        empty = true;

    }



    /**
     * Vérifie si aucune ressource n'est ciblée.
     *
     * @return vrai si aucune cible existe
     */
    public boolean isEmpty() {

        return empty;

    }



    /**
     * Définit une nouvelle ressource cible.
     *
     * @param resource ressource trouvée
     */
    public void setResource(Resource resource) {

        this.resource = resource;

        empty = false;

    }



    /**
     * Retourne la ressource ciblée.
     *
     * @return ressource ciblée
     */
    public Resource getResource() {

        return resource;

    }



    /**
     * Supprime la cible actuelle.
     */
    public void clear() {

        empty = true;

    }

}