package model.entity;

import model.position.Position;
import util.Direction;

/**
 * Représente une entité capable de se déplacer.
 *
 * Une entité mobile peut être temporairement figée
 * lors de l'exécution de la simulation.
 */
public abstract class MobileEntity extends Entity {


    /**
     * Indique si les déplacements sont bloqués.
     */
    private boolean frozen;



    /**
     * Construit une nouvelle entité mobile.
     *
     * @param position position initiale
     */
    public MobileEntity(Position position) {

        super(position);

        frozen = false;

    }



    /**
     * Vérifie si l'entité est figée.
     *
     * @return vrai si l'entité est bloquée
     */
    public boolean isFrozen() {

        return frozen;

    }



    /**
     * Modifie l'état de déplacement.
     *
     * @param frozen nouvel état
     */
    public void setFrozen(boolean frozen) {

        this.frozen = frozen;

    }



    /**
     * Calcule une future position.
     *
     * @param direction direction choisie
     * @return position calculée
     */
    public Position getNextPosition(
            Direction direction
    ) {

        Position currentPosition =
                getPosition();


        return new Position(
                currentPosition.getRow()
                        + direction.getRowChange(),

                currentPosition.getColumn()
                        + direction.getColumnChange()
        );

    }



    /**
     * Déplace l'entité vers une nouvelle position.
     *
     * Le déplacement n'est effectué
     * que si l'entité n'est pas figée.
     * La validation de la destination
     * est assurée par le gestionnaire du jeu.
     */
    public void moveTo(Position position) {

        if (!frozen) {

            setPosition(position);

        }

    }

}