package model;

import model.position.Position;
import util.Constants;


/**
 * Représente la grille du jeu.
 *
 * La grille correspond à l'espace de simulation.
 * Elle est composée de cases organisées sous forme
 * de tableau à deux dimensions.
 *
 * Chaque élément du jeu possède une position sur cette grille.
 */
public class Grid {


    /**
     * Tableau contenant toutes les cases de la grille.
     *
     * La première dimension représente les lignes.
     * La deuxième dimension représente les colonnes.
     */
    private final Cell[][] cells;



    /**
     * Construit une nouvelle grille complète.
     *
     * La grille est créée avec le nombre de lignes
     * et de colonnes défini dans les constantes.
     *
     * Chaque position reçoit une nouvelle case associée.
     */
    public Grid() {


        /*
         * Création du tableau représentant la grille.
         */
        cells =
                new Cell[
                        Constants.GRID_ROWS
                ][
                        Constants.GRID_COLUMNS
                ];



        /*
         * Parcours de toutes les positions possibles
         * afin de créer chaque case de la grille.
         */
        for (
                int row = Constants.FIRST_INDEX;
                row < Constants.GRID_ROWS;
                row++
        ) {


            for (
                    int column = Constants.FIRST_INDEX;
                    column < Constants.GRID_COLUMNS;
                    column++
            ) {


                Position position =
                        new Position(
                                row,
                                column
                        );


                cells[row][column] =
                        new Cell(position);

            }

        }

    }



    /**
     * Retourne une case selon ses coordonnées.
     *
     * @param row numéro de ligne recherché
     * @param column numéro de colonne recherché
     *
     * @return case correspondant aux coordonnées données
     */
    public Cell getCell(
            int row,
            int column
    ) {

        return cells[row][column];

    }



    /**
     * Retourne une case selon une position.
     *
     * Cette méthode permet d'éviter de récupérer
     * manuellement la ligne et la colonne d'une position.
     *
     * @param position position de la case recherchée
     *
     * @return case correspondant à la position
     */
    public Cell getCell(
            Position position
    ) {

        return getCell(
                position.getRow(),
                position.getColumn()
        );

    }



    /**
     * Vérifie si une position appartient à la grille.
     *
     * Cette vérification empêche l'accès à une case
     * située en dehors des limites du tableau.
     *
     * @param position position à vérifier
     *
     * @return vrai si la position est dans la grille,
     *         faux sinon
     */
    public boolean contains(
            Position position
    ) {


        return position.getRow()
                >= Constants.FIRST_INDEX

                && position.getRow()
                < Constants.GRID_ROWS

                && position.getColumn()
                >= Constants.FIRST_INDEX

                && position.getColumn()
                < Constants.GRID_COLUMNS;

    }



    /**
     * Vérifie si une case peut accueillir un élément.
     *
     * Une case est considérée comme libre lorsqu'elle
     * ne contient aucun élément du jeu.
     *
     * @param position position de la case testée
     *
     * @return vrai si la case est libre,
     *         faux si elle est occupée ou invalide
     */
    public boolean isFree(
            Position position
    ) {


        if (!contains(position)) {

            return false;

        }


        return getCell(position)
                .isOccupied()
                == Constants.CELL_FREE;

    }



    /**
     * Rend une case occupée.
     *
     * Cette méthode est utilisée lorsqu'un élément
     * du jeu apparaît ou se déplace sur une case.
     *
     * @param position position de la case à occuper
     */
    public void occupy(
            Position position
    ) {

        getCell(position).occupy();

    }



    /**
     * Rend une case disponible.
     *
     * Cette méthode est utilisée lorsqu'un élément
     * quitte une case.
     *
     * @param position position de la case à libérer
     */
    public void free(
            Position position
    ) {

        getCell(position).free();

    }



    /**
     * Retourne le nombre total de lignes.
     *
     * @return nombre de lignes de la grille
     */
    public int getRowCount() {

        return cells.length;

    }



    /**
     * Retourne le nombre total de colonnes.
     *
     * @return nombre de colonnes de la grille
     */
    public int getColumnCount() {

        return cells[
                Constants.FIRST_INDEX
        ].length;

    }

}