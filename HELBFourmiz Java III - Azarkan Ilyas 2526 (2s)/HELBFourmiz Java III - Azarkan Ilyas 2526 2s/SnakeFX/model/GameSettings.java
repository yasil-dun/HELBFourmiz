package model;

import util.Constants;

/**
 * Contient les paramètres de configuration
 * utilisés par la simulation.
 *
 * Cette classe centralise les paramètres principaux
 * afin de faciliter leurs modifications futures.
 */
public class GameSettings {


    /**
     * Nombre de ressources présentes au lancement du jeu.
     */
    private final int initialResourceCount;


    /**
     * Nombre initial de fourmis disponibles.
     */
    private final int initialAntCount;


    /**
     * Pourcentage de fourmis rouges dans la colonie.
     */
    private final int redAntPercentage;


    /**
     * Pourcentage de fourmis bleues dans la colonie.
     */
    private final int blueAntPercentage;


    /**
     * Temps entre deux mises à jour du jeu.
     */
    private final int refreshDelay;



    /**
     * Crée les paramètres du jeu avec les valeurs par défaut
     * définies dans la classe Constants.
     */
    public GameSettings() {


        initialResourceCount =
                Constants.INITIAL_RESOURCE_COUNT;


        initialAntCount =
                Constants.INITIAL_ANT_COUNT;


        redAntPercentage =
                Constants.RED_ANT_PERCENTAGE;


        blueAntPercentage =
                Constants.BLUE_ANT_PERCENTAGE;


        refreshDelay =
                Constants.GAME_REFRESH_DELAY;

    }



    /**
     * Retourne le nombre initial de ressources.
     *
     * @return nombre initial de ressources
     */
    public int getInitialResourceCount() {

        return initialResourceCount;

    }



    /**
     * Retourne le nombre initial de fourmis.
     *
     * @return nombre initial de fourmis disponibles
     */
    public int getInitialAntCount() {

        return initialAntCount;

    }



    /**
     * Retourne le pourcentage de fourmis rouges.
     *
     * @return pourcentage de fourmis rouges
     */
    public int getRedAntPercentage() {

        return redAntPercentage;

    }



    /**
     * Retourne le pourcentage de fourmis bleues.
     *
     * @return pourcentage de fourmis bleues
     */
    public int getBlueAntPercentage() {

        return blueAntPercentage;

    }



    /**
     * Retourne le délai de rafraîchissement.
     *
     * @return délai en millisecondes
     */
    public int getRefreshDelay() {

        return refreshDelay;

    }

}