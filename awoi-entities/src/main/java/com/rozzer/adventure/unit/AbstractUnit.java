package com.rozzer.adventure.unit;


import com.rozzer.adventure.core.*;

/**
 * Created by Rozzer on 16.11.2016.
 */
public abstract class AbstractUnit implements Unit, Constants {

    private int health;
    private boolean isLive = true;
    private RaceType race;
    private WeaponType weapon;
    private String description;
    private int attackBonus = STANDART_BONUS;
    private int defenseBonus = STANDART_BONUS;
    private final GameEngine gameEngine;

    protected AbstractUnit(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public RaceType getRace() {
        return race;
    }

    @Override
    public void setRace(RaceType race) {
        this.race = race;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public WeaponType getWeapon() {
        return this.weapon;
    }

    @Override
    public void setWeapon(WeaponType weapon) {
        this.weapon = weapon;
    }

    @Override
    public int addHealth(int health) {
        this.health = this.health + health;
        return this.health;
    }

    @Override
    public int removeHealth(int damage) {
        this.health = this.health - damage;
        if (this.health <= 0){
            this.die();
        }
        return this.health;
    }

    @Override
    public boolean isLive() {
        return this.isLive;
    }

    @Override
    public void die()  {
        gameEngine.unitDies(this);
        this.isLive = false;
    }

    @Override
    public int attack() {
        int attack = (int) (this.getRace().getStrength()
                * (this.getRace().getAgility() * FACTOR_ATTACK_AGILITY)
                * RANDOM_FACTOR[RANDOM.nextInt(RANDOM_FACTOR.length)]);
        switch (this.getWeapon()){
            case AXE: attack = attack + this.getWeapon().getAttack() + this.getRace().getAxeBonus(); break;
            case SWORD: attack = attack + this.getWeapon().getAttack() + this.getRace().getSwordBonus(); break;
            case BOW: attack = attack + this.getWeapon().getAttack() + this.getRace().getBowBonus(); break;
        }
        attack = attack * this.attackBonus;
        this.attackBonus = STANDART_BONUS;
        gameEngine.unitAttack(this, attack);
        return attack;
    }



    @Override
    public boolean defense(int damage) {
        int defense = (int) (this.getRace().getGuile() * (this.getRace().getAgility() * FACTOR_DEFENSE_AGILITY));
        defense = defense * this.defenseBonus;
        this.defenseBonus = STANDART_BONUS;
        if (RANDOM.nextInt(RATE_DEFENSE) > defense){
            gameEngine.unitDefense(this, defense, damage, false);
            this.removeHealth(damage);
            return false;
        }
        gameEngine.unitDefense(this, defense, damage, true);
        return true;
    }

    @Override
    public boolean run() {
        int run = (int) (this.getRace().getGuile() * (this.getRace().getAgility() * FACTOR_DEFENSE_AGILITY));
        if (RANDOM.nextInt(RATE_DEFENSE) > run){
            gameEngine.unitRun(this, false);
            return false;
        }
        gameEngine.unitRun(this, true);
        return true;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public void setDefenseBonus(int defenseBonus) {
        this.defenseBonus = defenseBonus;
    }
}
