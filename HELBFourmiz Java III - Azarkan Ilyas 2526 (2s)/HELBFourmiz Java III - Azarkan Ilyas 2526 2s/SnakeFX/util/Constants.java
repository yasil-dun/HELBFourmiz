package util;

/**
 * Contient toutes les constantes utilisées dans l'application.
 *
 * Cette classe centralise les valeurs fixes du programme
 * afin d'éviter l'utilisation de valeurs magiques dans le code.
 */
public final class Constants {


    /**
     * Empêche l'instanciation de cette classe utilitaire.
     */
    private Constants() {

    }


    /**
     * Largeur de la fenêtre principale du jeu.
     */
    public static final int WINDOW_WIDTH = 800;


    /**
     * Hauteur de la fenêtre principale du jeu.
     */
    public static final int WINDOW_HEIGHT = 800;


    /**
     * Titre affiché dans la fenêtre JavaFX.
     */
    public static final String WINDOW_TITLE = "HELBFourmiz";


    /**
     * Nombre de lignes composant la grille du jeu.
     */
    public static final int GRID_ROWS = 20;


    /**
     * Nombre de colonnes composant la grille du jeu.
     */
    public static final int GRID_COLUMNS = 20;


    /**
     * Première valeur utilisée pour parcourir un tableau.
     */
    public static final int FIRST_INDEX = 0;


    /**
     * Valeur représentant un reste nul lors d'une opération modulo.
     */
    public static final int EVEN_REMAINDER = 0;


    /**
     * Nombre de couleurs utilisées pour créer le damier initial.
     */
    public static final int GRID_COLOR_COUNT = 2;


    /**
     * Taille en pixels d'une case de la grille.
     */
    public static final int CELL_SIZE =
            WINDOW_WIDTH / GRID_COLUMNS;


    /**
     * Couleur claire utilisée pour le pavage initial.
     */
    public static final String GRID_LIGHT_COLOR = "#005900";


    /**
     * Couleur foncée utilisée pour le pavage initial.
     */
    public static final String GRID_DARK_COLOR = "#009500";


    /**
     * Temps d'attente entre deux mises à jour
     * de la simulation en millisecondes.
     */
    public static final int GAME_REFRESH_DELAY = 500;


    /**
     * Valeur représentant zéro.
     */
    public static final int ZERO = 0;


    /**
     * Valeur maximale possible pour une composante RGB.
     */
    public static final int MAX_COLOR_VALUE = 255;


    /**
     * Nombre de ressources présentes
     * lors du lancement de la simulation.
     */
    public static final int INITIAL_RESOURCE_COUNT = 6;


    /**
     * Nombre initial de fourmis pouvant sortir du camp.
     */
    public static final int INITIAL_ANT_COUNT = 6;


    /**
     * Valeur maximale utilisée pour générer un pourcentage aléatoire.
     */
    public static final int PERCENTAGE_LIMIT = 100;


    /**
     * Distance représentant deux cases adjacentes.
     */
    public static final int ADJACENT_DISTANCE = 1;



    /**
     * Chemin vers l'image d'une fourmi rouge.
     */
    public static final String RED_ANT_IMAGE_PATH =
            "/img/RedAnt.png";


    /**
     * Chemin vers l'image d'une fourmi bleue.
     */
    public static final String BLUE_ANT_IMAGE_PATH =
            "/img/BlueAnt.png";


    /**
     * Chemin vers l'image du camp.
     */
    public static final String CAMP_IMAGE_PATH =
            "/img/Camp.png";


    /**
     * Chemin vers l'image d'une graine.
     */
    public static final String SEED_IMAGE_PATH =
            "/img/Seed.png";


    /**
     * Chemin de l'image représentant
     * une ressource.
     */
    public static final String RESOURCE_IMAGE_PATH =
            "/img/Resource.png";

    /**
     * Chemin vers l'image d'une pierre philosophale jaune.
     */
    public static final String YELLOW_STONE_IMAGE_PATH =
            "/img/YellowStone.png";


    /**
     * Chemin vers l'image d'une pierre philosophale orange.
     */
    public static final String ORANGE_STONE_IMAGE_PATH =
            "/img/OrangeStone.png";


    /**
     * Chemin vers l'image d'une pierre philosophale rouge.
     */
    public static final String RED_STONE_IMAGE_PATH =
            "/img/RedStone.png";



    /**
     * Probabilité associée à une pierre jaune.
     */
    public static final int YELLOW_STONE_PROBABILITY = 2;


    /**
     * Probabilité associée à une pierre orange.
     */
    public static final int ORANGE_STONE_PROBABILITY = 3;


    /**
     * Probabilité associée à une pierre rouge.
     */
    public static final int RED_STONE_PROBABILITY = 4;



    /**
     * Valeur ajoutée aux traces lorsqu'une fourmi passe.
     */
    public static final int TRACE_DELTA = 12;


    /**
     * Distance maximale initiale utilisée
     * lors des recherches de ressources.
     */
    public static final int INITIAL_MAX_DISTANCE = 0;


    /**
     * Distance minimale initiale utilisée
     * lors des recherches de ressources.
     */
    public static final int INITIAL_MIN_DISTANCE =
            Integer.MAX_VALUE;



    /**
     * Variation de ligne lorsqu'une entité monte.
     */
    public static final int DIRECTION_UP_ROW = -1;


    /**
     * Variation de ligne lorsqu'une entité descend.
     */
    public static final int DIRECTION_DOWN_ROW = 1;


    /**
     * Variation de colonne lorsqu'une entité va à gauche.
     */
    public static final int DIRECTION_LEFT_COLUMN = -1;


    /**
     * Variation de colonne lorsqu'une entité va à droite.
     */
    public static final int DIRECTION_RIGHT_COLUMN = 1;


    /**
     * Valeur utilisée lorsqu'aucun déplacement
     * n'est effectué sur une coordonnée.
     */
    public static final int NO_DIRECTION_CHANGE = 0;



    /**
     * Valeur permettant de générer une borne incluse.
     */
    public static final int INCLUDED_BOUND = 1;



    /**
     * Pourcentage de fourmis rouges dans la colonie.
     */
    public static final int RED_ANT_PERCENTAGE = 40;


    /**
     * Pourcentage de fourmis bleues dans la colonie.
     */
    public static final int BLUE_ANT_PERCENTAGE = 60;



    /**
     * Etat indiquant qu'une case est libre.
     */
    public static final boolean CELL_FREE = false;


    /**
     * Etat indiquant qu'une case est occupée.
     */
    public static final boolean CELL_OCCUPIED = true;

    /**
     * Indice de début de la composante verte.
     *
     * Exemple :
     * 005900 -> 59.
     */
    public static final int GREEN_COLOR_START = 3;



    /**
     * Indice de fin de la composante verte.
     */
    public static final int GREEN_COLOR_END = 5;


    /**
     * Format utilisé pour convertir un entier
     * en valeur hexadécimale sur deux caractères.
     *
     * Exemple :
     * 12 devient 0C.
     */
    public static final String HEX_FORMAT = "%02X";

    /**
    * Valeur indiquant qu'aucune position valide n'a été trouvée.
    */
    public static final int NO_POSITION = -1;

    /**
     * Valeur maximale utilisée
     * pour rechercher la plus petite trace.
     */
    public static final int INITIAL_MIN_TRACE =
            Integer.MAX_VALUE;
    /**
     * Nom du fichier utilisé pour sauvegarder
     * l'état final de la partie.
     */
    public static final String SAVE_FILE =
            "save.jds";
}