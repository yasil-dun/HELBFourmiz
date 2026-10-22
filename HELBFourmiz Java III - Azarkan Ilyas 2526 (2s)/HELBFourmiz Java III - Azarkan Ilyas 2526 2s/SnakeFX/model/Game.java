package model;

import java.util.ArrayList;
import java.util.List;

import model.ant.Ant;
import model.ant.BlueAnt;
import model.ant.RedAnt;
import model.ant.TargetResource;
import model.camp.Camp;
import model.position.Position;
import model.position.PositionGenerator;
import model.resource.Resource;
import model.seed.Seed;
import model.stone.PhilosopherStone;
import model.stone.TypePhilosopherStone;

import util.Constants;
import util.Direction;
import util.RandomGenerator;


/**
 * Représente une partie de HELBFourmiz.
 *
 * Cette classe centralise les éléments présents
 * dans la simulation et gère leur évolution.
 */
public class Game {


    /**
     * Grille représentant l'espace de jeu.
     */
    private final Grid grid;



    /**
     * Fourmilière principale de la colonie.
     */
    private final Camp camp;



    /**
     * Paramètres de configuration.
     */
    private final GameSettings settings;



    /**
     * Générateur de positions.
     */
    private final PositionGenerator positionGenerator;



    /**
     * Générateur aléatoire.
     */
    private final RandomGenerator randomGenerator;



    /**
     * Liste des fourmis présentes.
     */
    private final List<Ant> ants;



    /**
     * Liste des ressources présentes.
     */
    private final List<Resource> resources;



    /**
     * Liste des graines présentes.
     */
    private final List<Seed> seeds;



    /**
     * Liste des pierres philosophales présentes.
     */
    private final List<PhilosopherStone> philosopherStones;



    /**
     * Nombre de fourmis pouvant encore apparaître.
     */
    private int availableAntCount;


    /**
     * Indique si toutes les fourmis
     * doivent retourner au camp.
     */
    private boolean forceReturnToCamp;

    /**
     * Construit une nouvelle partie.
     */
    public Game() {

    grid = new Grid();

    camp =
            new Camp(
                    new Position(
                            Constants.FIRST_INDEX,
                            Constants.FIRST_INDEX
                    )
            );

    /*
    * Le camp occupe sa case.
    */
    grid.occupy(
            camp.getPosition()
    );

    settings =
            new GameSettings();

    positionGenerator =
            new PositionGenerator();

    randomGenerator =
            new RandomGenerator();

    ants =
            new ArrayList<>();

    resources =
            new ArrayList<>();

    seeds =
            new ArrayList<>();

    philosopherStones =
            new ArrayList<>();

    availableAntCount =
            settings.getInitialAntCount();

    forceReturnToCamp = false;

    initializeGame();

    }




    /**
     * Initialise les éléments présents
     * au lancement de la simulation.
     */
    private void initializeGame() {


        initializeResources();


        initializePhilosopherStone();


    }




    /**
     * Génère les ressources initiales.
     */
    private void initializeResources() {

        List<Position> occupiedPositions =
                getOccupiedPositions();

        for (
                int index = Constants.FIRST_INDEX;
                index < settings.getInitialResourceCount();
                index++
        ) {

            Position position =
                    positionGenerator.generateFreePosition(
                            occupiedPositions
                    );

            Resource resource =
                    new Resource(position);

            addResource(resource);

            occupiedPositions.add(position);

        }

    }




   /**
    * Initialise les pierres philosophales
    * présentes au lancement de la partie.
    */
    private void initializePhilosopherStone() {

        List<Position> occupiedPositions =
                getOccupiedPositions();

        Position position =
                positionGenerator.generateFreePosition(
                        occupiedPositions
                );

        PhilosopherStone philosopherStone =
                new PhilosopherStone(
                        position,
                        generateRandomStoneType()
                );

        addPhilosopherStone(
                philosopherStone
        );

    }




    /**
     * Génère un type aléatoire de pierre.
     *
     * @return type de pierre choisi
     */
    private TypePhilosopherStone generateRandomStoneType() {

        TypePhilosopherStone[] types =
                TypePhilosopherStone.values();

        int randomValue =
                randomGenerator.generateInteger(
                        Constants.FIRST_INDEX,
                        types.length
                                - Constants.INCLUDED_BOUND
                );

        return types[randomValue];

    } 
        
    
    /**
    * Fait apparaître une nouvelle fourmi
    * à proximité du camp.
    */
    public void spawnAnt() {

        if (
                availableAntCount
                        <= Constants.ZERO
        ) {

            return;

        }

        List<Position> occupiedPositions =
                getOccupiedPositions();

        Position position =
                positionGenerator.generateFreeAdjacentPosition(
                        camp.getPosition(),
                        occupiedPositions
                );

        if (
                position.getRow()
                        == Constants.NO_POSITION
        ) {

            return;

        }

        int randomPercentage =
                randomGenerator.generateInteger(
                        Constants.FIRST_INDEX,
                        Constants.PERCENTAGE_LIMIT
                );
                
        /*
        * Fourmi qui sera créée.
        */
        Ant ant;

        if (
                randomPercentage
                        < settings.getRedAntPercentage()
        ) {

            ant =
                    new RedAnt(position);

        } else {

            ant =
                    new BlueAnt(position);

        }

        addAnt(ant);

        availableAntCount--;

    }



    /**
     * Retourne toutes les positions occupées.
     *
     * @return liste des positions occupées
     */
    private List<Position> getOccupiedPositions() {

        List<Position> occupiedPositions =
                new ArrayList<>();

        occupiedPositions.add(
                camp.getPosition()
        );

        for (Ant ant : ants) {

            occupiedPositions.add(
                    ant.getPosition()
            );

        }

        for (Resource resource : resources) {

            occupiedPositions.add(
                    resource.getPosition()
            );

        }

        for (
                PhilosopherStone philosopherStone
                : philosopherStones
        ) {

            occupiedPositions.add(
                    philosopherStone.getPosition()
            );

        }

        for (Seed seed : seeds) {

            occupiedPositions.add(
                    seed.getPosition()
            );

        }

        return occupiedPositions;

    }



    /**
     * Met à jour la simulation.
     *
     * Cette méthode est appelée
     * à chaque cycle du jeu.
     */
    public void update() {

        moveAnts();

        moveSeeds();

        spawnAnt();

    }

    /**
     * Déplace toutes les graines.
     */
    private void moveSeeds() {

        List<Seed> seedsToMove =
                new ArrayList<>(seeds);

        for (Seed seed : seedsToMove) {

            /*
            * Si la graine est déjà arrivée,
            * elle devient immédiatement
            * une nouvelle ressource.
            */
            if (
                    seed.isAtPosition(
                            seed.getTargetPosition()
                    )
            ) {

                removeSeed(seed);

                addResource(
                        new Resource(
                                seed.getPosition()
                        )
                );

                continue;

            }

            Direction direction =
                    calculateDirection(
                            seed.getPosition(),
                            seed.getTargetPosition()
                    );

            Position nextPosition =
                    seed.getNextPosition(direction);

            /*
            * Si la case est occupée,
            * la graine attend simplement
            * le prochain tour.
            */
            if (!grid.contains(nextPosition)
                    || !grid.isFree(nextPosition)) {

                continue;

            }

            grid.free(
                    seed.getPosition()
            );

            seed.moveTo(nextPosition);

            grid.occupy(
                    seed.getPosition()
            );

            handleSeedArrival(seed);

        }

    }

  

    /**
     * Déplace toutes les fourmis.
     */
    private void moveAnts() {

        List<Ant> antsToMove =
                new ArrayList<>(ants);

        for (Ant ant : antsToMove) {

            Position targetPosition;

            if (forceReturnToCamp) {

                targetPosition =
                        camp.getPosition();

            } else if (ant.isCarryingResource()) {

                targetPosition =
                        camp.getPosition();

            } else {

                TargetResource target =
                        ant.findTarget(resources);

                if (target.isEmpty()) {

                    continue;

                }

                targetPosition =
                        target.getResource()
                                .getPosition();

            }

            Direction direction =
                    calculateDirection(
                            ant.getPosition(),
                            targetPosition
                    );

            Position nextPosition =
                    ant.getNextPosition(direction);

            if (!canMoveTo(nextPosition)) {

                Direction[] directions = Direction.values();

                boolean moved = false;

                for (Direction alternative : directions) {

                    Position alternativePosition =
                            ant.getNextPosition(alternative);

                    if (canMoveTo(alternativePosition)) {

                        nextPosition = alternativePosition;
                        moved = true;
                        break;

                    }

                }

                if (!moved) {

                    continue;

                }

            }

            Position previousPosition =
                    ant.getPosition();

            /*
            * Libère l'ancienne case.
            */
            grid.free(previousPosition);

            /*
            * Déplace la fourmi.
            */
            ant.moveTo(nextPosition);

            /*
            * Dépose une trace.
            */
            ant.leaveTrace(
                    grid.getCell(nextPosition)
            );

            /*
            * La fourmi occupe sa nouvelle case.
            */
            grid.occupy(
                    ant.getPosition()
            );

            /*
            * Traite les interactions
            * avec les éléments présents
            * sur la case d'arrivée.
            */
            handleArrival(ant);

        }

    }    
    
    /**
     * Vérifie si une entité peut se déplacer
     * vers une position donnée.
     *
     * Une case occupée par une ressource,
     * une pierre philosophale ou le camp
     * reste accessible.
     *
     * @param position position testée
     * @return vrai si le déplacement est autorisé
     */
    private boolean canMoveTo(
            Position position
    ) {

        if (!grid.contains(position)) {

            return false;

        }

        /*
        * Une case libre est toujours accessible.
        */
        if (grid.isFree(position)) {

            return true;

        }

        /*
        * Le camp est accessible.
        */
        if (
                camp.getPosition()
                        .isSamePosition(position)
        ) {

            return true;

        }

        /*
        * Les ressources peuvent être
        * traversées afin d'être récoltées.
        */
        for (Resource resource : resources) {

            if (
                    resource.getPosition()
                            .isSamePosition(position)
            ) {

                return true;

            }

        }

        /*
        * Une pierre philosophale est accessible.
        */
        for (
                PhilosopherStone stone
                : philosopherStones
        ) {

            if (
                    stone.getPosition()
                            .isSamePosition(position)
            ) {

                return true;

            }

        }

        return false;

    }


    /**
     * Gère l'arrivée d'une fourmi
     * sur une case particulière.
     *
     * @param ant fourmi déplacée
     */
    private void handleArrival(
            Ant ant
    ) {

        /*
        * Vérifie la présence
        * d'une pierre philosophale.
        */
        for (
                PhilosopherStone stone
                : new ArrayList<>(philosopherStones)
        ) {

            if (
                    ant.isAtPosition(
                            stone.getPosition()
                    )
            ) {

                applyPhilosopherStoneEffect(
                        ant,
                        stone
                );

                return;

            }

        }

        /*
        * Vérifie la présence
        * d'une ressource.
        */
        for (
                Resource resource
                : new ArrayList<>(resources)
        ) {

            if (
                    ant.isAtPosition(
                            resource.getPosition()
                    )
            ) {

               Position collectionPosition =
                        resource.getPosition();

                ant.collectResource(resource);

                removeResource(resource);

                /*
                * Génère immédiatement la graine.
                */
                createSeed(collectionPosition);

                return;

            }

        }


        if (
                ant.isAtPosition(
                        camp.getPosition()
                )
                && ant.isCarryingResource()
        ) {

            ant.dropResource();

            camp.addCollectedResource();
        }

    }

    /**
     * Gère l'arrivée d'une graine.
     *
     * Si une graine atteint une pierre
     * philosophale, celle-ci disparaît
     * puis réapparaît ailleurs.
     *
     * @param seed graine déplacée
     */
    private void handleSeedArrival(
            Seed seed
    ) {

        for (
                PhilosopherStone stone :
                new ArrayList<>(philosopherStones)
        ) {

            if (
                    seed.isAtPosition(
                            stone.getPosition()
                    )
            ) {

                removePhilosopherStone(
                        stone
                );

                respawnPhilosopherStone();

                return;

            }

        }

    }

    /**
     * Crée une graine à partir
     * d'une position de collecte.
     *
     * @param collectionPosition position où la ressource
     *                           a été récoltée
     */
    private void createSeed(
            Position collectionPosition
    ) {

        Position targetPosition =
                generateSeedTargetPosition();

        if (
                targetPosition.getRow()
                        == Constants.NO_POSITION
        ) {

            return;

        }

        Position seedPosition =
                generateSeedStartPosition(
                        collectionPosition
                );

        if (
                seedPosition.getRow()
                        == Constants.NO_POSITION
        ) {

            return;

        }

        Seed seed =
                new Seed(
                        seedPosition
                );

        seed.setTargetPosition(
                targetPosition
        );

        addSeed(
                seed
        );

    }


    /**
     * Détermine la direction permettant
     * de se rapprocher d'une cible.
     *
     * @param current position actuelle
     * @param target position cible
     * @return direction choisie
     */
    private Direction calculateDirection(
            Position current,
            Position target
    ) {


        int rowDifference =
                target.getRow()
                        - current.getRow();



        int columnDifference =
                target.getColumn()
                        - current.getColumn();



        int rowDirection =
                Integer.compare(
                        rowDifference,
                        Constants.ZERO
                );



        int columnDirection =
                Integer.compare(
                        columnDifference,
                        Constants.ZERO
                );



        if (
                rowDirection < Constants.ZERO
                && columnDirection < Constants.ZERO
        ) {


            return Direction.UP_LEFT;

        }



        if (
                rowDirection < Constants.ZERO
                && columnDirection > Constants.ZERO
        ) {


            return Direction.UP_RIGHT;

        }



        if (
                rowDirection > Constants.ZERO
                && columnDirection < Constants.ZERO
        ) {


            return Direction.DOWN_LEFT;

        }



        if (
                rowDirection > Constants.ZERO
                && columnDirection > Constants.ZERO
        ) {


            return Direction.DOWN_RIGHT;

        }



        if (
                rowDirection < Constants.ZERO
        ) {


            return Direction.UP;

        }



        if (
                rowDirection > Constants.ZERO
        ) {


            return Direction.DOWN;

        }



        if (
                columnDirection < Constants.ZERO
        ) {


            return Direction.LEFT;

        }



        return Direction.RIGHT;

    }
    
    /**
     * Ajoute une fourmi dans la simulation.
     *
     * @param ant fourmi ajoutée
     */
    public void addAnt(
            Ant ant
    ) {


        ants.add(ant);



        grid.getCell(
                ant.getPosition()
        ).occupy();

    }





    /**
     * Retire une fourmi.
     *
     * @param ant fourmi retirée
     */
    public void removeAnt(
            Ant ant
    ) {


        ants.remove(ant);



        grid.getCell(
                ant.getPosition()
        ).free();

    }





    /**
     * Ajoute une ressource.
     *
     * @param resource ressource ajoutée
     */
    public void addResource(
        Resource resource
    ) {

       resources.add(resource);
       grid.getCell(resource.getPosition()).occupy();

    }  





    /**
     * Retire une ressource.
     *
     * @param resource ressource retirée
     */
    public void removeResource(
           Resource resource
    ) {

           resources.remove(resource);
           grid.getCell(resource.getPosition()).free();

    }





    /**
     * Ajoute une graine.
     *
     * @param seed graine ajoutée
     */
    public void addSeed(
            Seed seed
    ) {


        seeds.add(seed);



        grid.getCell(
                seed.getPosition()
        ).occupy();

    }





    /**
     * Retire une graine.
     *
     * @param seed graine retirée
     */
    public void removeSeed(
            Seed seed
    ) {


        seeds.remove(seed);



        grid.getCell(
                seed.getPosition()
        ).free();

    }

    /**
     * Ajoute une pierre philosophale.
     *
     * @param philosopherStone pierre ajoutée
     */
    public void addPhilosopherStone(
        PhilosopherStone philosopherStone
        ) {

        philosopherStones.add(
                philosopherStone
        );

        grid.getCell(
                philosopherStone.getPosition()
        ).occupy();

        }


    /**
     * Retire une pierre philosophale.
     *
     * @param philosopherStone pierre retirée
     */
        public void removePhilosopherStone(
                PhilosopherStone philosopherStone
        ) {

        philosopherStones.remove(
                philosopherStone
        );

        grid.getCell(
                philosopherStone.getPosition()
        ).free();

        }

    /**
     * Fait réapparaître une pierre philosophale
     * sur une case libre.
     */
    private void respawnPhilosopherStone() {

        List<Position> occupiedPositions =
                getOccupiedPositions();

        Position position =
                positionGenerator.generateFreePosition(
                        occupiedPositions
                );

        if (
                position.getRow()
                        == Constants.NO_POSITION
        ) {

            return;

        }

        PhilosopherStone philosopherStone =
                new PhilosopherStone(
                        position,
                        generateRandomStoneType()
                );

        addPhilosopherStone(
                philosopherStone
        );

    }

    /**
     * Applique l'effet d'une pierre philosophale.
     *
     * @param ant fourmi ayant trouvé la pierre
     * @param stone pierre collectée
     */
    private void applyPhilosopherStoneEffect(
            Ant ant,
            PhilosopherStone stone
    ) {

        removePhilosopherStone(
                stone
        );

        if (
                randomGenerator.hasProbability(
                        stone.getType().getProbability()
                )
        ) {

            addResource(
                    new Resource(
                            ant.getPosition()
                    )
            );

            removeAnt(
                    ant
            );

        } else {

            List<Position> occupiedPositions =
                    getOccupiedPositions();

            Position position =
                    positionGenerator.generateFreeAdjacentPosition(
                            ant.getPosition(),
                            occupiedPositions
                    );

            if (
                    position.getRow()
                            != Constants.NO_POSITION
            ) {

                addAnt(
                        ant.createChild(position)
                );

            }

        }

        respawnPhilosopherStone();

    }

    /**
     * Recherche la case libre la plus proche
     * du lieu où la ressource a été collectée.
     *
     * @param collectionPosition position de collecte
     * @return position de départ de la graine
     */
    private Position generateSeedStartPosition(
            Position collectionPosition
    ) {

        Position bestPosition =
                new Position(
                        Constants.NO_POSITION,
                        Constants.NO_POSITION
                );

        int smallestDistance =
                Constants.INITIAL_MIN_DISTANCE;

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

                Position position =
                        new Position(
                                row,
                                column
                        );

                if (
                        !grid.isFree(position)
                ) {

                    continue;

                }

                int distance =
                position.getDistance(
                        collectionPosition
                );

                if (
                        distance < smallestDistance
                ) {

                    smallestDistance = distance;

                    bestPosition = position;

                }

            }

        }

        return bestPosition;

    }

    /**
     * Recherche une case libre parmi les moins
     * fréquentées par les fourmis.
     *
     * @return position choisie
     */
    private Position generateSeedTargetPosition() {

        List<Position> bestPositions =
                new ArrayList<>();

        int smallestTrace =
                Constants.INITIAL_MIN_TRACE;

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

                Position position =
                        new Position(
                                row,
                                column
                        );

                if (!grid.isFree(position)) {

                    continue;

                }

                int trace =
                        grid.getCell(position)
                                .getTotalTrace();

                /*
                * Une nouvelle case possède
                * une trace plus faible.
                */
                if (trace < smallestTrace) {

                    smallestTrace = trace;

                    bestPositions.clear();

                    bestPositions.add(position);

                }

                /*
                * Même niveau de traces :
                * on la garde également.
                */
                else if (trace == smallestTrace) {

                    bestPositions.add(position);

                }

            }

        }

        if (bestPositions.isEmpty()) {

            return new Position(
                    Constants.NO_POSITION,
                    Constants.NO_POSITION
            );

        }

        /*
        * Choisit aléatoirement une case
        * parmi les moins fréquentées.
        */
        int randomIndex =
                randomGenerator.generateInteger(
                        Constants.FIRST_INDEX,
                        bestPositions.size()
                                - Constants.INCLUDED_BOUND
                );

        return bestPositions.get(randomIndex);

    }   

    /**
     * Réinitialise toutes les traces
     * laissées par les fourmis rouges.
     */
    public void resetRedTraces() {

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

                grid.getCell(
                        row,
                        column
                ).resetRedTrace();

            }

        }

    }

    /**
     * Réinitialise toutes les traces
     * laissées par les fourmis bleues.
     */
    public void resetBlueTraces() {

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

                grid.getCell(
                        row,
                        column
                ).resetBlueTrace();

            }

        }

    }

    /**
     * Réinitialise toutes les traces
     * de la grille.
     */
    public void resetAllTraces() {

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

                grid.getCell(
                        row,
                        column
                ).resetTraces();

            }

        }

    }

    /**
     * Génère une fourmi rouge
     * à une position libre aléatoire.
     */
    public void spawnRedAntCheat() {

        Position position =
                positionGenerator.generateFreePosition(
                        getOccupiedPositions()
                );

        
        RedAnt ant =
                new RedAnt(position);


        addAnt(ant);

    }

    /**
     * Génère une fourmi bleue
     * à une position libre aléatoire.
     */
    public void spawnBlueAntCheat() {

        Position position =
                positionGenerator.generateFreePosition(
                        getOccupiedPositions()
                );


        BlueAnt ant =
                new BlueAnt(position);


        addAnt(ant);

    }

    /**
     * Génère une ressource
     * à une position libre aléatoire.
     */
    public void spawnResourceCheat() {

        Position position =
                positionGenerator.generateFreePosition(
                        getOccupiedPositions()
                );


        Resource resource =
                new Resource(position);


        addResource(resource);

    }

    /**
     * Génère une graine
     * à une position libre aléatoire.
     */
    public void spawnSeedCheat() {

        Position position =
                positionGenerator.generateFreePosition(
                        getOccupiedPositions()
                );


        Seed seed =
                new Seed(position);


        addSeed(seed);

    }

    /**
     * Génère une pierre philosophale
     * de type aléatoire.
     */
    public void spawnStoneCheat() {

        Position position =
                positionGenerator.generateFreePosition(
                        getOccupiedPositions()
                );


        PhilosopherStone stone =
                new PhilosopherStone(
                        position,
                        generateRandomStoneType()
                );


        addPhilosopherStone(stone);

    }

    /**
     * Fige ou libère toutes les fourmis rouges.
     */
    public void freezeRedAnts() {

        for (Ant ant : ants) {

            if (ant.isRed()) {

                ant.setFrozen(
                        !ant.isFrozen()
                );

            }

        }

    }

    /**
     * Fige ou libère toutes les fourmis bleues.
     */
    public void freezeBlueAnts() {

        for (Ant ant : ants) {

            if (!ant.isRed()) {

                ant.setFrozen(
                        !ant.isFrozen()
                );

            }

        }

    }

    /**
     * Fige ou libère toutes les graines.
     */
    public void freezeSeeds() {

        for (Seed seed : seeds) {

            seed.setFrozen(
                    !seed.isFrozen()
            );

        }

    }

    /**
     * Fige ou libère tous les éléments mobiles.
     */
    public void freezeAllMobiles() {

        freezeRedAnts();

        freezeBlueAnts();

        freezeSeeds();

    }

    /**
     * Force la collecte de toutes
     * les ressources présentes.
     */
    public void collectAllResources() {

        for (
                Resource resource :
                new ArrayList<>(resources)
        ) {

            createSeed(
                    resource.getPosition()
            );

            removeResource(
                    resource
            );

        }

    }

    /**
     * Force toutes les fourmis
     * à retourner au camp.
     */
    public void forceReturnToCamp() {

        forceReturnToCamp = true;

    }

    /**
     * Annule le retour forcé.
     */
    public void cancelReturnToCamp() {

        forceReturnToCamp = false;

    }
    /**
     * Retourne la grille du jeu.
     *
     * @return grille utilisée par la simulation
     */
    public Grid getGrid() {

        return grid;

    }

    /**
     * Retourne le camp principal.
     *
     * @return camp de la colonie
     */
    public Camp getCamp() {

        return camp;

    }


    /**
     * Retourne la liste des fourmis.
     *
     * @return fourmis présentes dans la simulation
     */
    public List<Ant> getAnts() {

        return ants;

    }

    /**
     * Retourne la liste des ressources.
     *
     * @return ressources présentes sur la grille
     */
    public List<Resource> getResources() {

        return resources;

    }





    /**
     * Retourne la liste des graines.
     *
     * @return graines présentes dans la simulation
     */
    public List<Seed> getSeeds() {

        return seeds;

    }





    /**
     * Retourne la liste des pierres philosophales.
     *
     * @return pierres présentes sur la grille
     */
    public List<PhilosopherStone> getPhilosopherStones() {

        return philosopherStones;

    }





    /**
     * Retourne le nombre de fourmis présentes.
     *
     * @return nombre total de fourmis
     */
    public int getAntCount() {

        return ants.size();

    }





    /**
     * Retourne le nombre de ressources présentes.
     *
     * @return nombre total de ressources
     */
    public int getResourceCount() {

        return resources.size();

    }





    /**
     * Retourne le nombre de graines présentes.
     *
     * @return nombre total de graines
     */
    public int getSeedCount() {

        return seeds.size();

    }





    /**
     * Retourne le nombre de pierres présentes.
     *
     * @return nombre total de pierres philosophales
     */
    public int getPhilosopherStoneCount() {

        return philosopherStones.size();

    }





    /**
     * Retourne le nombre de fourmis
     * pouvant encore apparaître.
     *
     * @return nombre de fourmis disponibles
     */
    public int getAvailableAntCount() {

        return availableAntCount;

    }

}