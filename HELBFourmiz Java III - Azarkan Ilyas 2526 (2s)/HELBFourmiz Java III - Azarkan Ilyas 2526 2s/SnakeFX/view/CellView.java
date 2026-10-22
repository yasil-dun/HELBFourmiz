package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Cell;
import util.Constants;


/**
 * Représente l'affichage d'une case de la grille.
 *
 * Cette classe est responsable du dessin
 * du fond d'une case.
 */
public class CellView {

    /**
     * Contexte graphique utilisé pour dessiner.
     */
    private final GraphicsContext graphicsContext;

    /**
     * Construit une nouvelle vue de case.
     *
     * Cette vue réutilise le contexte graphique
     * fourni par la vue principale afin de
     * dessiner les cases de la grille.
     *
     * @param graphicsContext contexte graphique utilisé
     *                        pour le dessin
     */
    public CellView(
            GraphicsContext graphicsContext
    ) {

        this.graphicsContext = graphicsContext;

    }

    /**
	 * Dessine une case.
	 *
	 * La couleur affichée dépend des traces
	 * laissées par les fourmis.
	 *
	 * @param cell case à dessiner
	 */
    public void draw(
        Cell cell
    ) {


        graphicsContext.setFill(
                Color.web(
                        cell.getCurrentColor()
                )
        );


        graphicsContext.fillRect(
                cell.getPosition().getColumn()
                        * Constants.CELL_SIZE,

                cell.getPosition().getRow()
                        * Constants.CELL_SIZE,

                Constants.CELL_SIZE,

                Constants.CELL_SIZE
        );

    }

}