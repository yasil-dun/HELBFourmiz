package util;

import java.util.Random;


/**
 * Fournit des méthodes permettant de générer
 * des valeurs aléatoires utilisées dans le jeu.
 */
public class RandomGenerator {


    /**
     * Générateur aléatoire utilisé par la classe.
     */
    private final Random random;



    /**
     * Construit un générateur aléatoire.
     */
    public RandomGenerator() {

        random = new Random();

    }



    /**
     * Génère un nombre aléatoire compris
     * entre deux valeurs.
     *
     * @param minimum valeur minimale incluse
     * @param maximum valeur maximale incluse
     * @return nombre généré
     */
    public int generateInteger(
            int minimum,
            int maximum
    ) {

        return random.nextInt(
                maximum - minimum
                        + Constants.INCLUDED_BOUND
        ) + minimum;

    }



    /**
     * Génère une valeur booléenne aléatoire.
     *
     * @return vrai ou faux
     */
    public boolean generateBoolean() {

        return random.nextBoolean();

    }



    /**
     * Vérifie si un événement arrive
     * selon une probabilité donnée.
     *
     * @param probability probabilité utilisée
     * @return vrai si l'événement arrive
     */
    public boolean hasProbability(
            int probability
    ) {

        return generateInteger(
                Constants.INCLUDED_BOUND,
                probability
        ) == Constants.INCLUDED_BOUND;

    }



    /**
     * Génère une direction aléatoire.
     *
     * Cette méthode est utilisée par les entités
     * mobiles lors de leurs déplacements.
     *
     * @return direction choisie
     */
    public Direction generateDirection() {

        Direction[] directions =
                Direction.values();


        int index =
                generateInteger(
                        Constants.FIRST_INDEX,
                        directions.length
                                - Constants.INCLUDED_BOUND
                );


        return directions[index];

    }

}