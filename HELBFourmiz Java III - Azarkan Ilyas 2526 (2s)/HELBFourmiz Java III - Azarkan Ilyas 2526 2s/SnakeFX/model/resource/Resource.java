package model.resource;

import model.entity.Entity;
import model.position.Position;

/**
 * Représente une ressource présente sur la grille.
 *
 * Une ressource est un élément fixe pouvant être
 * collecté par une fourmi.
 */
public class Resource extends Entity {

    /**
     * Construit une nouvelle ressource.
     *
     * @param position position de la ressource
     */
    public Resource(Position position) {

        super(position);

    }
}