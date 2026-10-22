package model.ant;

import java.util.List;

import model.Cell;
import model.entity.MobileEntity;
import model.position.Position;
import model.resource.Resource;

/**
 * Représente une fourmi.
 *
 * Une fourmi est une entité mobile capable
 * de transporter une ressource.
 */
public abstract class Ant extends MobileEntity {

    /**
     * Ressource transportée par la fourmi.
     */
    private final CarriedResource carriedResource;

    /**
     * Construit une nouvelle fourmi.
     *
     * @param position position initiale de la fourmi
     */
    public Ant(Position position) {

        super(position);

        carriedResource = new CarriedResource();

    }

    /**
     * Retourne la ressource transportée.
     *
     * @return conteneur de ressource
     */
    public CarriedResource getCarriedResource() {

        return carriedResource;

    }

    /**
     * Vérifie si la fourmi transporte une ressource.
     *
     * @return vrai si une ressource est transportée
     */
    public boolean isCarryingResource() {

        return !carriedResource.isEmpty();

    }

    /**
     * Récupère une ressource.
     *
     * @param resource ressource récupérée
     */
    public void collectResource(
            Resource resource
    ) {

        carriedResource.setResource(resource);

    }

    /**
     * Dépose la ressource transportée.
     */
    public void dropResource() {

        carriedResource.clear();

    }

    /**
     * Dépose une trace sur une case.
     *
     * Chaque type de fourmi laisse
     * une trace différente.
     *
     * @param cell case où déposer la trace
     */
    public abstract void leaveTrace(
            Cell cell
    );

    /**
     * Recherche une ressource cible.
     *
     * Le comportement dépend du type de fourmi.
     *
     * @param resources ressources disponibles
     * @return cible trouvée
     */
    public abstract TargetResource findTarget(
            List<Resource> resources
    );

    /**
     * Crée une nouvelle fourmi du même type.
     *
     * @param position position de la nouvelle fourmi
     * @return nouvelle fourmi
     */
    public abstract Ant createChild(
            Position position
    );

    /**
     * Indique si la fourmi est rouge.
     *
     * @return vrai si la fourmi est rouge
     */
    public abstract boolean isRed();

}