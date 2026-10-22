package model.seed;

import model.entity.MobileEntity;
import model.position.Position;

/**
 * Représente une graine.
 *
 * Une graine est générée lorsqu'une ressource est récoltée.
 * Elle se déplace ensuite jusqu'à son lieu de germination.
 */
public class Seed extends MobileEntity {

    /**
     * Position de germination.
     */
    private Position targetPosition;


    /**
     * Construit une nouvelle graine.
     *
     * @param position position initiale
     */
    public Seed(Position position) {

        super(position);

        targetPosition = position;

    }


    /**
     * Retourne la destination de la graine.
     *
     * @return position cible
     */
    public Position getTargetPosition() {

        return targetPosition;

    }


    /**
     * Modifie la destination de la graine.
     *
     * @param targetPosition nouvelle destination
     */
    public void setTargetPosition(
            Position targetPosition
    ) {

        this.targetPosition = targetPosition;

    }

}