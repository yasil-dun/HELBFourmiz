package controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import model.Game;
import model.save.SaveManager;

import util.Constants;
import view.GameView;


/**
 * Contrôleur principal du jeu.
 */
public class GameController {


    /**
     * Le modèle du jeu.
     */
    private final Game game;


    /**
     * La vue principale du jeu.
     */
    private final GameView gameView;


    /**
     * Boucle principale du jeu.
     */
    private final Timeline timeline;


    /**
     * Fenêtre principale du jeu.
     */
    private final Stage stage;



    /**
     * Construit le contrôleur principal.
     *
     * @param stage fenêtre principale
     */
    public GameController(
            Stage stage
    ) {

        this.stage = stage;


        game =
                new Game();


        gameView =
                new GameView(
                        stage,
                        game
                );

        /*
         * Associe les touches clavier
         * aux différents cheat codes.
         */
        gameView.getScene()
                .setOnKeyPressed(
                        this::handleKeyPressed
                );



        timeline =
                new Timeline(
                        new KeyFrame(
                                Duration.millis(
                                        Constants.GAME_REFRESH_DELAY
                                ),
                                event -> update()
                        )
                );


        timeline.setCycleCount(
                Animation.INDEFINITE
        );


        timeline.play();

    }




    /**
     * Met à jour le jeu.
     */
    private void update() {

        /*
         * Met à jour le modèle.
         */
        game.update();


        /*
         * Met à jour la vue.
         */
        gameView.update();

    }




    /**
     * Gère les touches clavier.
     *
     * @param event évènement clavier
     */
    private void handleKeyPressed(
            KeyEvent event
    ) {

        switch (
                event.getCode()
        ) {

            case A:

                game.spawnRedAntCheat();

                break;


            case Z:

                game.spawnBlueAntCheat();

                break;


            case E:

                game.spawnResourceCheat();

                break;


            case R:

                game.spawnSeedCheat();

                break;


            case U:

                game.spawnStoneCheat();

                break;


            case I:

                game.resetRedTraces();

                break;


            case O:

                game.resetBlueTraces();

                break;


            case P:

                game.resetAllTraces();

                break;


            case M:

                /*
                * Sauvegarde l'état final
                * puis arrête la simulation.
                */
                SaveManager.save(game);

                timeline.stop();

                stage.close();

                break;

            case W:

                game.freezeRedAnts();

                break;

            case X:

                game.freezeBlueAnts();

                break;

            case C:

                game.freezeSeeds();

                break;

            case V:

                game.freezeAllMobiles();

                break;

            case J:

                game.collectAllResources();

                break;

            case K:

                game.forceReturnToCamp();

                break; 
                 
            case L:

                game.cancelReturnToCamp();

                break;

            default:

                break;

        }

    }

}