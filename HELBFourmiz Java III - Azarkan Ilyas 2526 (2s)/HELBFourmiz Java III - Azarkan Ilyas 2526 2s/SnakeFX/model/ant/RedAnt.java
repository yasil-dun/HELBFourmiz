package model.ant;

import java.util.List;

import model.Cell;
import model.position.Position;
import model.resource.Resource;

import util.Constants;

/**
 * Représente une fourmi rouge.
 *
 * Une fourmi rouge recherche la ressource
 * la plus proche de sa position actuelle.
 */
public class RedAnt extends Ant {

    /**
     * Construit une nouvelle fourmi rouge.
     *
     * @param position la position initiale de la fourmi
     */
    public RedAnt(Position position) {

        super(position);

    }


    /**
     * Dépose une trace rouge
     * sur la case occupée.
     *
     * @param cell case où déposer la trace
     */
    @Override
    public void leaveTrace(
            Cell cell
    ) {

        cell.addRedTrace();

    }


    /**
     * Recherche la ressource la plus proche.
     *
     * @param resources ressources disponibles
     * @return la cible trouvée
     */
    @Override
    public TargetResource findTarget(
            List<Resource> resources
    ) {

        TargetResource target =
                new TargetResource();

        int smallestDistance =
                Constants.INITIAL_MIN_DISTANCE;


        for (
                Resource resource :
                resources
        ) {

            int distance =
                    getPosition()
                            .getDistance(
                                    resource.getPosition()
                            );


            if (
                    distance < smallestDistance
            ) {

                smallestDistance =
                        distance;

                target.setResource(
                        resource
                );

            }

        }

        return target;

    }


    /**
     * Crée une nouvelle fourmi rouge.
     *
     * @param position position de la nouvelle fourmi
     *
     * @return nouvelle fourmi rouge
     */
    @Override
    public Ant createChild(
            Position position
    ) {

        return new RedAnt(
                position
        );

    }


    /**
     * Indique que cette fourmi est rouge.
     *
     * @return vrai car cette fourmi est rouge
     */
    @Override
    public boolean isRed() {

        return true;

    }

}