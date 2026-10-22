package model.ant;

import java.util.List;

import model.Cell;
import model.position.Position;
import model.resource.Resource;

import util.Constants;

/**
 * Représente une fourmi bleue.
 *
 * Une fourmi bleue recherche la ressource
 * la plus éloignée de sa position actuelle.
 */
public class BlueAnt extends Ant {

    /**
     * Construit une nouvelle fourmi bleue.
     *
     * @param position la position initiale de la fourmi
     */
    public BlueAnt(Position position) {

        super(position);

    }


    /**
     * Dépose une trace bleue
     * sur la case occupée.
     *
     * @param cell case où déposer la trace
     */
    @Override
    public void leaveTrace(
            Cell cell
    ) {

        cell.addBlueTrace();

    }


    /**
     * Recherche la ressource la plus éloignée.
     *
     * @param resources ressources disponibles
     * @return la cible trouvée
     */
    @Override
    public TargetResource findTarget(
            List<Resource> resources
    ) {

        TargetResource target = new TargetResource();

        int biggestDistance =
                Constants.INITIAL_MAX_DISTANCE;


        for (Resource resource : resources) {

            int distance =
                    getPosition()
                            .getDistance(
                                    resource.getPosition()
                            );


            if (distance > biggestDistance) {

                biggestDistance = distance;

                target.setResource(resource);

            }

        }

        return target;

    }

    /**
     * Crée une nouvelle fourmi bleue.
     *
     * @param position position de la nouvelle fourmi
     *
     * @return nouvelle fourmi bleue
     */
    @Override
    public Ant createChild(
            Position position
    ) {

        return new BlueAnt(position);

    }
    
    /**
     * Indique que cette fourmi est bleue.
     *
     * @return faux car cette fourmi est bleue
     */
    @Override
    public boolean isRed() {

        return false;

    }
}