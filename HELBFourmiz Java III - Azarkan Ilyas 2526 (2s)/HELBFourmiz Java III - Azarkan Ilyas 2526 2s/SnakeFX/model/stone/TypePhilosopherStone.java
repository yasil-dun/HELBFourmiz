package model.stone;

import util.Constants;

/**
 * Représente les différents types de pierres philosophales.
 *
 * Chaque type possède une probabilité P utilisée
 * lors de l'effet alchimique appliqué aux fourmis.
 */
public enum TypePhilosopherStone {

    /**
     * Pierre jaune avec P = 2.
     */
    YELLOW(Constants.YELLOW_STONE_PROBABILITY),

    /**
     * Pierre orange avec P = 3.
     */
    ORANGE(Constants.ORANGE_STONE_PROBABILITY),

    /**
     * Pierre rouge avec P = 4.
     */
    RED(Constants.RED_STONE_PROBABILITY);


    /**
     * Probabilité associée à la pierre.
     */
    private final int probability;


    /**
     * Construit un type de pierre.
     *
     * @param probability valeur de P associée au type
     */
    TypePhilosopherStone(int probability) {

        this.probability = probability;

    }


    /**
     * Retourne la probabilité associée au type.
     *
     * @return valeur de P
     */
    public int getProbability() {

        return probability;

    }

}