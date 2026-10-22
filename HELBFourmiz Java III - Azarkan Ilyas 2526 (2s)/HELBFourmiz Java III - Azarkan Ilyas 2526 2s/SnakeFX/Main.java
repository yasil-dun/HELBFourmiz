import controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Point d'entrée de l'application.
 */
public class Main extends Application {

    /**
     * Lance l'application JavaFX.
     *
     * @param primaryStage la fenêtre principale
     */
    @Override
    public void start(Stage primaryStage) {
        new GameController(primaryStage);
    }

    /**
     * Point d'entrée du programme.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }

}