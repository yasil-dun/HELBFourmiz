package model.position;

import java.util.ArrayList;
import java.util.List;

import util.Constants;
import util.RandomGenerator;

/**
 * Génère des positions utilisables dans la grille.
 *
 * Cette classe permet de créer des positions aléatoires,
 * de rechercher des positions libres et de trouver
 * des positions adjacentes disponibles.
 */
public class PositionGenerator {


    /**
     * Générateur aléatoire utilisé pour créer les positions.
     */
    private final RandomGenerator randomGenerator;



    /**
     * Construit un nouveau générateur
     * de positions.
     *
     * Le générateur aléatoire utilisé pour
     * créer les positions est également
     * initialisé.
     */
    public PositionGenerator() {

        randomGenerator = new RandomGenerator();

    }



    /**
     * Génère une position aléatoire dans la grille.
     *
     * @return nouvelle position générée
     */
    public Position generatePosition() {
        /*
        * Génère une ligne aléatoire
        * dans les limites de la grille.
        */
        int row =
                randomGenerator.generateInteger(
                        Constants.FIRST_INDEX,
                        Constants.GRID_ROWS
                                - Constants.INCLUDED_BOUND
                );

        /*
        * Génère une colonne aléatoire
        * dans les limites de la grille.
        */
        int column =
                randomGenerator.generateInteger(
                        Constants.FIRST_INDEX,
                        Constants.GRID_COLUMNS
                                - Constants.INCLUDED_BOUND
                );

        /*
        * Construit la nouvelle position.
        */
        return new Position(
                row,
                column
        );

    }



    /**
     * Génère une position qui n'est pas présente
     * dans une liste de positions occupées.
     *
     * @param occupiedPositions positions déjà occupées
     * @return position libre générée
     */
    public Position generateFreePosition(
            List<Position> occupiedPositions
    ) {
        /*
        * Génère une première position.
        */
        Position position =
                generatePosition();


        /*
        * Tant que la position est déjà
        * occupée, une nouvelle position
        * est générée.
        */
        while (
                containsPosition(
                        occupiedPositions,
                        position
                )
        ) {

            position =
                    generatePosition();

        }



        return position;

    }



    /**
     * Génère une position libre adjacente
     * à une position donnée.
     *
     * Cette méthode est utilisée notamment
     * pour faire apparaître les fourmis
     * autour du camp.
     *
     * @param center position centrale
     * @param occupiedPositions positions déjà occupées
     * @return position adjacente libre ou position invalide
     * si aucune case n'est disponible
     */
    public Position generateFreeAdjacentPosition(
            Position center,
            List<Position> occupiedPositions
    ) {


        List<Position> possiblePositions =
                new ArrayList<>();



        /*
         * Parcourt les 8 cases autour
         * de la position centrale.
         */
        for (
                int row = Constants.DIRECTION_UP_ROW;
                row <= Constants.DIRECTION_DOWN_ROW;
                row++
        ) {


            for (
                    int column = Constants.DIRECTION_LEFT_COLUMN;
                    column <= Constants.DIRECTION_RIGHT_COLUMN;
                    column++
            ) {


                /*
                 * Ignore la position centrale
                 * car une entité ne peut pas apparaître
                 * sur la même case.
                 */
                if (
                        row == Constants.NO_DIRECTION_CHANGE
                        &&
                        column == Constants.NO_DIRECTION_CHANGE
                ) {

                    continue;

                }



                Position position =
                        new Position(
                                center.getRow()
                                        + row,

                                center.getColumn()
                                        + column
                        );



                /*
                 * Ajoute uniquement les positions
                 * présentes dans la grille et libres.
                 */
                if (
                        isInsideGrid(position)
                        &&
                        !containsPosition(
                                occupiedPositions,
                                position
                        )
                ) {


                    possiblePositions.add(position);

                }

            }

        }



        /*
        * Si aucune case libre n'est disponible,
        * une position invalide est retournée.
        *
        * La classe appelante décidera
        * du comportement à adopter.
        */
        if (
                possiblePositions.size()
                == Constants.ZERO
        ) {

            return new Position(
                    Constants.NO_POSITION,
                    Constants.NO_POSITION
            );

        }



        /*
         * Sélectionne une position libre
         * aléatoire parmi les possibilités.
         */
        int randomIndex =
                randomGenerator.generateInteger(
                        Constants.FIRST_INDEX,
                        possiblePositions.size()
                                - Constants.INCLUDED_BOUND
                );



        return possiblePositions.get(randomIndex);

    }



    /**
     * Vérifie si une position appartient
     * aux limites de la grille.
     *
     * @param position position vérifiée
     * @return vrai si la position est valide
     */
    private boolean isInsideGrid(
            Position position
    ) {

        return position.getRow()
                >= Constants.FIRST_INDEX

                &&
                position.getRow()
                < Constants.GRID_ROWS

                &&
                position.getColumn()
                >= Constants.FIRST_INDEX

                &&
                position.getColumn()
                < Constants.GRID_COLUMNS;

    }



    /**
     * Vérifie si une position existe déjà
     * dans une liste.
     *
     * @param positions positions existantes
     * @param position position recherchée
     * @return vrai si la position existe
     */
    private boolean containsPosition(
            List<Position> positions,
            Position position
    ) {

        for (
                Position currentPosition :
                positions
        ) {


            if (
                    currentPosition.isSamePosition(
                            position
                    )
            ) {

                return true;

            }

        }

        return false;

    }

}