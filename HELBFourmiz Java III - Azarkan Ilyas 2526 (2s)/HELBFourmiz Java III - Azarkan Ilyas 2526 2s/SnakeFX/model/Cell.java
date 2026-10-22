package model;

import model.position.Position;
import util.Constants;


/**
 * Représente une case de la grille.
 *
 * Une case possède :
 * - une position fixe dans la grille ;
 * - une couleur de base utilisée pour le pavage ;
 * - des traces laissées par les fourmis ;
 * - un état indiquant si elle est occupée.
 */
public class Cell {


    /**
     * Position de la case dans la grille.
     */
    private final Position position;



    /**
     * Couleur initiale de la case.
     *
     * Cette couleur correspond au pavage
     * avant le passage des fourmis.
     */
    private final String baseColor;



    /**
     * Quantité de traces laissées par les fourmis rouges.
     */
    private int redTrace;



    /**
     * Quantité de traces laissées par les fourmis bleues.
     */
    private int blueTrace;



    /**
     * Indique si un élément occupe actuellement la case.
     */
    private boolean occupied;



    /**
     * Construit une nouvelle case.
     *
     * La couleur de base est calculée automatiquement
     * afin de créer l'effet de damier demandé.
     *
     * Les traces sont initialisées à zéro
     * et la case commence libre.
     *
     * @param position position associée à cette case
     */
    public Cell(
            Position position
    ) {


        this.position = position;



        /*
         * Création du pavage.
         *
         * Les cases voisines alternent entre
         * les deux couleurs définies dans Constants.
         */
        if ((position.getRow()
                + position.getColumn())
                % Constants.GRID_COLOR_COUNT
                == Constants.EVEN_REMAINDER) {


            baseColor =
                    Constants.GRID_LIGHT_COLOR;


        } else {


            baseColor =
                    Constants.GRID_DARK_COLOR;

        }



        redTrace = Constants.ZERO;

        blueTrace = Constants.ZERO;


        occupied =
                Constants.CELL_FREE;

    }



    /**
     * Retourne la position de la case.
     *
     * @return position actuelle de la case
     */
    public Position getPosition() {

        return position;

    }



    /**
     * Retourne la couleur initiale.
     *
     * @return couleur avant ajout des traces
     */
    public String getBaseColor() {

        return baseColor;

    }



    /**
     * Retourne les traces rouges.
     *
     * @return quantité de traces rouges
     */
    public int getRedTrace() {

        return redTrace;

    }



    /**
     * Retourne les traces bleues.
     *
     * @return quantité de traces bleues
     */
    public int getBlueTrace() {

        return blueTrace;

    }



    /**
     * Retourne la fréquentation totale.
     *
     * Cette valeur est utilisée notamment
     * pour déterminer les zones peu fréquentées
     * par les fourmis.
     *
     * @return somme des traces rouges et bleues
     */
    public int getTotalTrace() {

        return redTrace + blueTrace;

    }



    /**
     * Vérifie si la case est occupée.
     *
     * @return vrai si un élément occupe la case
     */
    public boolean isOccupied() {

        return occupied;

    }



    /**
     * Rend la case occupée.
     */
    public void occupy() {

        occupied =
                Constants.CELL_OCCUPIED;

    }



    /**
     * Rend la case libre.
     */
    public void free() {

        occupied =
                Constants.CELL_FREE;

    }



    /**
     * Ajoute une trace de fourmi rouge.
     */
    public void addRedTrace() {

        redTrace += Constants.TRACE_DELTA;

    }



    /**
     * Ajoute une trace de fourmi bleue.
     */
    public void addBlueTrace() {

        blueTrace += Constants.TRACE_DELTA;

    }



    /**
     * Supprime toutes les traces rouges.
     */
    public void resetRedTrace() {

        redTrace = Constants.ZERO;

    }



    /**
     * Supprime toutes les traces bleues.
     */
    public void resetBlueTrace() {

        blueTrace = Constants.ZERO;

    }



    /**
     * Réinitialise toutes les traces de la case.
     */
    public void resetTraces() {

        resetRedTrace();

        resetBlueTrace();

    }



    /**
     * Retourne la couleur actuelle de la case.
     *
     * La couleur dépend de la couleur initiale
     * et des traces accumulées par les fourmis.
     *
     * @return couleur complète au format hexadécimal
     */
    public String getCurrentColor() {


        String red =
                convertToHex(redTrace);



        String green =
                baseColor.substring(
                        Constants.GREEN_COLOR_START,
                        Constants.GREEN_COLOR_END
                );



        String blue =
                convertToHex(blueTrace);



        return "#"
                + red
                + green
                + blue;

    }



    /**
     * Convertit une valeur numérique en hexadécimal.
     *
     * La valeur est limitée afin d'éviter
     * un dépassement de couleur.
     *
     * @param value valeur à convertir
     *
     * @return représentation hexadécimale sur deux caractères
     */
    private String convertToHex(
            int value
    ) {


        if (value > Constants.MAX_COLOR_VALUE) {

            value =
                    Constants.MAX_COLOR_VALUE;

        }


        return String.format(
                Constants.HEX_FORMAT,
                value
        );

    }

}