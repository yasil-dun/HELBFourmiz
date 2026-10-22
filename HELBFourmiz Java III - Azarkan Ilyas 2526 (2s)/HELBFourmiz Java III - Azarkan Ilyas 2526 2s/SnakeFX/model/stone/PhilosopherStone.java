package model.stone;

import model.entity.Entity;
import model.position.Position;

/**
 * Représente une pierre philosophale présente sur la grille.
 *
 * Une pierre philosophale possède un type
 * déterminant son effet lorsqu'une fourmi 
 * la collecte.
 */
public class PhilosopherStone extends Entity {

    /**
     * Type de la pierre philosophale.
     */
    private final TypePhilosopherStone type;


    /**
     * Construit une nouvelle pierre philosophale.
     *
     * @param position position de la pierre
     * @param type type de la pierre
     */
    public PhilosopherStone(
            Position position,
            TypePhilosopherStone type
    ) {

        super(position);

        this.type = type;

    }


    /**
     * Retourne le type de la pierre.
     *
     * @return type associé à la pierre
     */
    public TypePhilosopherStone getType() {

        return type;

    }

}