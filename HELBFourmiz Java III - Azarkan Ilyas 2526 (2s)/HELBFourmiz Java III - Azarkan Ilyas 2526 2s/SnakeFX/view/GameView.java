package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import model.Game;
import model.ant.Ant;
import model.resource.Resource;

import model.seed.Seed;

import model.stone.PhilosopherStone;
import model.stone.TypePhilosopherStone;

import util.Constants;


/**
 * Représente la vue principale du jeu.
 */
public class GameView {


    /**
     * Fenêtre principale.
     */
    private final Stage stage;


    /**
     * Partie actuellement affichée.
     */
    private final Game game;


    /**
     * Scène principale.
     */
    private final Scene scene;


    /**
     * Zone de dessin.
     */
    private final Canvas canvas;


    /**
     * Contexte graphique.
     */
    private final GraphicsContext graphicsContext;

    /**
     * Vue chargée de dessiner
     * les différentes cases de la grille.
     */
    private final CellView cellView;
    /**
     * Image représentant une fourmi rouge.
     */
    private final Image redAntImage;

    /**
     * Image représentant une fourmi bleue.
     */
    private final Image blueAntImage;

    /**
     * Image représentant le camp.
     */
    private final Image campImage;

    /**
     * Image représentant une ressource.
     *
     * Même si cette image est actuellement identique
     * à celle d'une graine, une variable distincte
     * est conservée afin de bien distinguer les deux
     * éléments du modèle.
     */
    private final Image resourceImage;

    /**
     * Image représentant une graine.
     */
    private final Image seedImage;

    /**
     * Image représentant une pierre jaune.
     */
    private final Image yellowStoneImage;

    /**
     * Image représentant une pierre orange.
     */
    private final Image orangeStoneImage;

    /**
     * Image représentant une pierre rouge.
     */
    private final Image redStoneImage;



    /**
     * Constructeur de la vue.
     *
     * @param stage fenêtre principale
     * @param game modèle du jeu
     */
    public GameView(
            Stage stage,
            Game game
    ) {

        this.stage = stage;

        this.game = game;


        Group root = new Group();


        canvas = new Canvas(
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT
        );


        root.getChildren().add(canvas);


        scene = new Scene(root);


        graphicsContext =
                canvas.getGraphicsContext2D();

        /*
        * Création de la vue responsable
        * du dessin des cases.
        */
        cellView =
                new CellView(
                        graphicsContext
                );



        redAntImage =
                new Image(
                        Constants.RED_ANT_IMAGE_PATH
                );


        blueAntImage =
                new Image(
                        Constants.BLUE_ANT_IMAGE_PATH
                );


        campImage =
                new Image(
                        Constants.CAMP_IMAGE_PATH
                );
        
        resourceImage =
                new Image(
                        Constants.RESOURCE_IMAGE_PATH
                );

        seedImage =
                new Image(
                        Constants.SEED_IMAGE_PATH
                );


        yellowStoneImage =
                new Image(
                        Constants.YELLOW_STONE_IMAGE_PATH
                );


        orangeStoneImage =
                new Image(
                        Constants.ORANGE_STONE_IMAGE_PATH
                );


        redStoneImage =
                new Image(
                        Constants.RED_STONE_IMAGE_PATH
                );



        stage.setScene(scene);

        stage.setTitle(
                Constants.WINDOW_TITLE
        );


        draw();


        stage.show();

    }



    /**
     * Actualise l'affichage.
     */
    public void update() {

        draw();

    }


    /**
     * Dessine tous les éléments présents
     * dans la simulation.
     */
    private void draw() {

        drawBackground();

        drawCamp();

        drawResources();

        drawSeeds();

        drawStones();

        drawAnts();
        }



   /**
     * Dessine le fond de la grille.
     *
     * Chaque case est dessinée par
     * la vue spécialisée CellView.
     */
    private void drawBackground() {

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

                cellView.draw(
                    game.getGrid()
                        .getCell(row, column)
            );

            }

        }

    }

  
    /**
     * Dessine le camp.
     */
    private void drawCamp() {


        drawImage(
                campImage,
                game.getCamp().getPosition().getRow(),
                game.getCamp().getPosition().getColumn()
        );

    }



    /**
     * Dessine les ressources présentes sur la grille.
     *
     * Les ressources utilisent l'image Seed.png
     * fournie par le projet.
     */
    private void drawResources() {


        for (Resource resource : game.getResources()) {
                drawImage(
                resourceImage,
                resource.getPosition().getRow(),
                resource.getPosition().getColumn()
        );
            
        }

    }

    /**
     * Dessine les graines présentes
     * dans la simulation.
     */
    private void drawSeeds() {

        for (Seed seed : game.getSeeds()) {

            drawImage(
                    seedImage,
                    seed.getPosition().getRow(),
                    seed.getPosition().getColumn()
            );

        }

    }



    /**
     * Dessine toutes les fourmis.
     *
     * Chaque type de fourmi possède
     * sa propre image.
     */
    private void drawAnts() {

        for (Ant ant : game.getAnts()) {

            Image image;

            if (ant.isRed()) {

                image = redAntImage;

            } else {

                image = blueAntImage;

            }

            drawImage(
                    image,
                    ant.getPosition().getRow(),
                    ant.getPosition().getColumn()
            );

        }

    }



    /**
     * Dessine les pierres philosophales.
     */
    private void drawStones() {


        for (
                PhilosopherStone stone :
                game.getPhilosopherStones()
        ) {


            Image image =
                    yellowStoneImage;



            if (
                    stone.getType()
                            == TypePhilosopherStone.ORANGE
            ) {


                image = orangeStoneImage;


            }


            if (
                    stone.getType()
                            == TypePhilosopherStone.RED
            ) {


                image = redStoneImage;

            }


            drawImage(
                    image,
                    stone.getPosition().getRow(),
                    stone.getPosition().getColumn()
            );

        }

    }



    /**
     * Dessine une image sur une case.
     *
     * @param image image à afficher
     * @param row ligne
     * @param column colonne
     */
    private void drawImage(
            Image image,
            int row,
            int column
    ) {


        graphicsContext.drawImage(
                image,
                column * Constants.CELL_SIZE,
                row * Constants.CELL_SIZE,
                Constants.CELL_SIZE,
                Constants.CELL_SIZE
        );

    }

    /**
     * Retourne la scène principale.
     *
     * @return scène JavaFX
     */
    public Scene getScene() {

        return scene;

    }


}