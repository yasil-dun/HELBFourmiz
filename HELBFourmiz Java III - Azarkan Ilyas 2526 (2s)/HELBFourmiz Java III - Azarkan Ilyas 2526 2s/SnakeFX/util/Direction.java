package util;

/**
 * Représente les directions possibles
 * pour le déplacement des éléments mobiles.
 *
 * Les déplacements peuvent être horizontaux,
 * verticaux ou diagonaux.
 */
public enum Direction {

    /**
     * Déplacement vers le haut.
     */
    UP(
            Constants.DIRECTION_UP_ROW,
            Constants.NO_DIRECTION_CHANGE
    ),


    /**
     * Déplacement vers le bas.
     */
    DOWN(
            Constants.DIRECTION_DOWN_ROW,
            Constants.NO_DIRECTION_CHANGE
    ),


    /**
     * Déplacement vers la gauche.
     */
    LEFT(
            Constants.NO_DIRECTION_CHANGE,
            Constants.DIRECTION_LEFT_COLUMN
    ),


    /**
     * Déplacement vers la droite.
     */
    RIGHT(
            Constants.NO_DIRECTION_CHANGE,
            Constants.DIRECTION_RIGHT_COLUMN
    ),


    /**
     * Déplacement diagonal haut gauche.
     */
    UP_LEFT(
            Constants.DIRECTION_UP_ROW,
            Constants.DIRECTION_LEFT_COLUMN
    ),


    /**
     * Déplacement diagonal haut droite.
     */
    UP_RIGHT(
            Constants.DIRECTION_UP_ROW,
            Constants.DIRECTION_RIGHT_COLUMN
    ),


    /**
     * Déplacement diagonal bas gauche.
     */
    DOWN_LEFT(
            Constants.DIRECTION_DOWN_ROW,
            Constants.DIRECTION_LEFT_COLUMN
    ),


    /**
     * Déplacement diagonal bas droite.
     */
    DOWN_RIGHT(
            Constants.DIRECTION_DOWN_ROW,
            Constants.DIRECTION_RIGHT_COLUMN
    );


    /**
     * Variation de ligne associée à la direction.
     */
    private final int rowChange;


    /**
     * Variation de colonne associée à la direction.
     */
    private final int columnChange;


    /**
     * Construit une direction.
     *
     * @param rowChange variation de ligne
     * @param columnChange variation de colonne
     */
    Direction(
            int rowChange,
            int columnChange
    ) {

        this.rowChange = rowChange;

        this.columnChange = columnChange;

    }


    /**
     * Retourne la variation de ligne.
     *
     * @return variation de ligne
     */
    public int getRowChange() {

        return rowChange;

    }


    /**
     * Retourne la variation de colonne.
     *
     * @return variation de colonne
     */
    public int getColumnChange() {

        return columnChange;

    }

}