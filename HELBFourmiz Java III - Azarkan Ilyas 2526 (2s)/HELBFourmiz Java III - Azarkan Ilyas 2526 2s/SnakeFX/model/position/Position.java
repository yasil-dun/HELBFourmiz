package model.position;

import util.Constants;

/**
 * Représente une position dans la grille du jeu.
 *
 * Une position est définie par une ligne et une colonne.
 */
public class Position {

    /**
     * Ligne de la position.
     */
    private final int row;


    /**
     * Colonne de la position.
     */
    private final int column;


    /**
     * Construit une nouvelle position.
     *
     * @param row ligne de la position
     * @param column colonne de la position
     */
    public Position(int row, int column) {

        this.row = row;

        this.column = column;

    }


    /**
     * Retourne la ligne de la position.
     *
     * @return ligne de la position
     */
    public int getRow() {

        return row;

    }


    /**
     * Retourne la colonne de la position.
     *
     * @return colonne de la position
     */
    public int getColumn() {

        return column;

    }


    /**
     * Vérifie si deux positions sont identiques.
     *
     * @param other position comparée
     * @return vrai si les deux positions correspondent
     */
    public boolean isSamePosition(Position other) {

        return row == other.row
                && column == other.column;

    }


    /**
     * Calcule la distance entre deux positions.
     *
     * La distance prend en compte les déplacements horizontaux,
     * verticaux et diagonaux autorisés dans la grille.
     *
     * @param other position de destination
     * @return distance entre les deux positions
     */
    public int getDistance(Position other) {

        return Math.max(
                Math.abs(row - other.row),
                Math.abs(column - other.column)
        );

    }


    /**
     * Vérifie si deux positions sont voisines.
     *
     * @param other position comparée
     * @return vrai si les positions sont adjacentes
     */
    public boolean isAdjacent(Position other) {

        return getDistance(other)
                == Constants.ADJACENT_DISTANCE;

    }

}