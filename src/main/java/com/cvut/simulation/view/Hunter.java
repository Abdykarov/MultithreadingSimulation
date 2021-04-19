package com.cvut.simulation.view;

public class Hunter extends Entity{


    Hunter(Tile tilePos,int id, int aEnergy, int aHealth, int aSpeed, int aHunger, int aLifeLenght){
        this.aEnergy = aEnergy;
        this.aHealth = aHealth;
        this.aHunger = aHunger;
        this.aLifeLenght = aLifeLenght;
        this.aSpeed = aSpeed;
        this.aType = "Hunter";
        this.image = "wolf.png";
        this.id = id;
        this.width = 50;
        this.height = 35;
        this.currentPosition = tilePos;
        this.nextPosition = calculateNextPosition(tilePos);
    }


    /**
     * Entity moves to next tile
     *
     * @param tileToMove
     */
    @Override
    public void move(Tile tileToMove) {

    }

    /**
     * Entity eats other entity, which is placed in the next tile
     *
     * @param entityToEat
     */
    @Override
    public void eat(Entity entityToEat) {

    }

    /**
     * Calculates nextPosition. Finds the best tile around
     *
     * @param currentPosition
     * @return
     */
    @Override
    public Tile calculateNextPosition(Tile currentPosition) {
        return null;
    }

    @Override
    public void die() {

    }

    /**
     * Drinks water
     *
     * @param waterObject
     */
    @Override
    public void drinkWater(Water waterObject) {

    }
}
