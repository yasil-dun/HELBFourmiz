package model.camp;

import model.entity.Entity;
import model.position.Position;
import util.Constants;

/**
 * Représente le camp des fourmis.
 *
 * Le camp est le point de départ des fourmis
 * et permet de stocker les ressources rapportées.
 */
public class Camp extends Entity {

    /**
     * Nombre de ressources déposées dans le camp.
     */
    private int collectedResources;


    /**
     * Construit un nouveau camp.
     *
     * @param position position du camp
     */
    public Camp(Position position) {

        super(position);

        collectedResources = Constants.ZERO;

    }


    /**
     * Ajoute une ressource déposée par une fourmi.
     */
    public void addCollectedResource() {

        collectedResources++;

    }


    /**
     * Retourne le nombre de ressources déposées.
     *
     * @return nombre de ressources collectées
     */
    public int getCollectedResources() {

        return collectedResources;

    }

}