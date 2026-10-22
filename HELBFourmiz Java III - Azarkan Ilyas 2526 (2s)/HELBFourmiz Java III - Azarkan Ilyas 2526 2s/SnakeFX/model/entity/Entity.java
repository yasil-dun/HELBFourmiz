package model.entity;

import model.position.Position;

/**
 * Représente une entité présente sur la grille.
 *
 * Chaque entité possède une position dans l'espace de jeu.
 */
public abstract class Entity {

    /**
     * Position actuelle de l'entité.
     */
    private Position position;

    /**
     * Construit une nouvelle entité.
     *
     * @param position position initiale de l'entité
     */
    public Entity(Position position) {

        this.position = position;

    }

    /**
     * Retourne la position actuelle de l'entité.
     *
     * @return position de l'entité
     */
    public Position getPosition() {

        return position;

    }

    /**
     * Définit la nouvelle position de l'entité.
     *
     * @param position nouvelle position de l'entité
     */
    public void setPosition(Position position) {

        this.position = position;

    }

    /**
     * Vérifie si l'entité se trouve
     * sur une position donnée.
     *
     * @param position position comparée
     * @return vrai si les positions correspondent
     */
    public boolean isAtPosition(Position position) {

        return this.position.isSamePosition(position);

    }

}