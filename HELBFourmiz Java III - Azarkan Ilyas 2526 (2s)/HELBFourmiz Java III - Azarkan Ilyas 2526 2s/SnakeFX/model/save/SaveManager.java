package model.save;

import java.io.FileWriter;
import java.io.IOException;

import model.Game;
import model.ant.Ant;
import util.Constants;


/**
 * Gère la sauvegarde de la partie.
 */
public class SaveManager {


    /**
     * Constructeur privé.
     *
     * Empêche la création inutile d'un objet
     * SaveManager.
     */
    private SaveManager() {

    }


    /**
     * Sauvegarde l'état final du jeu.
     *
     * Le fichier contient exactement trois lignes :
     * - nombre de fourmis rouges
     * - nombre de fourmis bleues
     * - nombre de ressources déposées au camp
     *
     * @param game partie à sauvegarder
     */
    public static void save(
            Game game
    ) {

        int redAntCount =
                Constants.ZERO;

        int blueAntCount =
                Constants.ZERO;


        for (
                Ant ant :
                game.getAnts()
        ) {

            if (
                    ant.isRed()
            ) {

                redAntCount++;

            } else {

                blueAntCount++;

            }

        }


        try (
                FileWriter writer =
                        new FileWriter(
                                Constants.SAVE_FILE
                        )
        ) {

            writer.write(
                    redAntCount
                    + "\n"
            );

            writer.write(
                    blueAntCount
                    + "\n"
            );

            writer.write(
                    game.getCamp()
                            .getCollectedResources()
                    + "\n"
            );

        }
        catch (
                IOException exception
        ) {

            System.out.println(
                    "Error while saving game."
            );

        }

    }

}