package com.rozzer.adventure;


import com.rozzer.adventure.core.*;
import com.rozzer.adventure.core.exception.EndGame;
import com.rozzer.adventure.core.exception.NoPlayerException;
import com.rozzer.adventure.core.option.Option;
import com.rozzer.adventure.core.option.OptionEnemy;
import com.rozzer.adventure.core.option.OptionFight;
import com.rozzer.adventure.core.option.OptionTravel;


/**
 * Created by Rozzer on 15.11.2016.
 */
public class Speaker extends AbstractGameEngine implements Depiction, Constants {

    private final  Player player;

    public Speaker() {
        player = new PlayerImpl(this);
    }

    @Override
    public  void startGame() throws EndGame{
        say(MENU);
        while (true) {
            String playerCommand = getPlayer().answer();
            if (validMessage(playerCommand) && (Option.START.toString().equals(playerCommand))) {
                break;
            } else {
                error();
            }
        }
    }

    @Override
    public Player getPlayer() throws NoPlayerException {
        return player;
    }

    @Override
    public void heroDies(Hero hero) throws NoPlayerException {
        say(String.format(Depiction.HERO_FAIL,getPlayer().getPlayerHero().getName()));
    }

    @Override
    public void unitDies(Unit unit) {
        say(String.format(Depiction.DIES,unit.getDescription()));
    }


    @Override
    public void unitAttack(Unit unit, int attack) {
        say(String.format(Depiction.FORCE_ATTACK,unit.getDescription(),attack));
    }

    @Override
    public void unitDefense(Unit unit, int defense, int damage, boolean isSuccess) {
        if (isSuccess){
            say(String.format(Depiction.DEFENSE_SUCCESS, unit.getDescription(), damage));
            return;
        }
        say(String.format(Depiction.DEFENSE_FAIL,unit.getDescription(),damage,unit.getHealth()-damage));
    }

    @Override
    public void unitRun(Unit unit, boolean isSuccess) {
        if (isSuccess){
            say(String.format(Depiction.RUN_SUCCESS, unit.getDescription()));
            return;
        }
        say(String.format(Depiction.RUN_FAIL, unit.getDescription()));
    }

    public void greeting() throws EndGame{
        say(GREETING);
        while (true) {
            String playerCommand = getPlayer().answer();
            if (validMessage(playerCommand)) {
                getPlayer().getPlayerHero().setName(playerCommand);
                excellent(getPlayer().getPlayerHero().getName());
                break;
            } else {
                error();
            }
        }
    }
    public void askRace() throws EndGame {
        say(WHAT_RACE_YOU);
        while (true) {
            String playerCommand = getPlayer().answer();
            if (validMessage(playerCommand) && (RaceType.validString(playerCommand)) && (!RaceType.fromString(playerCommand).isBoss())) {
                getPlayer().getPlayerHero().setRace(RaceType.fromString(playerCommand));
                getPlayer().getPlayerHero().setWeapon(WeaponType.getRandom());
                String description = String.format(RACE_AND_NAME,
                        getPlayer().getPlayerHero().getRace().toString(),
                        getPlayer().getPlayerHero().getName());
                getPlayer().getPlayerHero().setDescription(description);
                excellent(description);
                break;
            } else {
                error();
            }
        }
    }


    public void launchPlayerInWorld() throws EndGame{
        say(String.format(LAUNCH_PLAYER_IN_WORLD,
                                        String.format(RACE_AND_NAME,
                                                    getPlayer().getPlayerHero().getRace().toString(),
                                                    getPlayer().getPlayerHero().getName()),
                                        getPlayer().getPlayerHero().getWeapon().toString(),
                                        getPlayer().getPlayerHero().getCurrentSite().getDescription()
                                        ));
        go();
    }

    public void whereToGo() throws EndGame {
        if (getPlayer().getPlayerHero().isLive()) {
            say(WHERE_TO_GO);
            go();
        } else {
            throw new EndGame();
        }
    }

    public void say(String text){
        System.out.println(text);
    }

    public void gameOver() {
        say(GAME_OVER);
    }

    public void whatToDoHere(){

    }

    private void whatToDoWithTheEnemy() throws EndGame{
        if (getPlayer().getPlayerHero().isFirstFight()){
            say(String.format(WHAT_TO_WITH_THE_ENEMY_LONG,WHAT_TO_WITH_THE_ENEMY_MINI));
        } else {
            say(WHAT_TO_WITH_THE_ENEMY_MINI);
        }
        while (true) {
            String playerCommand = getPlayer().answer();
            if (validMessage(playerCommand) && (OptionEnemy.validString(playerCommand))) {
                if (playerCommand.equalsIgnoreCase(OptionEnemy.ATTACK.toString())) {
                    playerAttack();
                    break;
                } else if (playerCommand.equalsIgnoreCase(OptionEnemy.SLIP.toString())) {
                    playerSlip();
                    break;
                } else if (playerCommand.equalsIgnoreCase(OptionEnemy.TALK.toString())) {
                    playerTalk();
                    break;
                } else if (playerCommand.equalsIgnoreCase(OptionEnemy.GO.toString())) {
                    playerGo();
                    break;
                }
            } else {
                error();
            }
        }

    }

    private void whatToDoWithTheBossEnemy() throws EndGame {
        say(String.format(YUO_SEE_AND_ATTACK, getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().getDescription()));
        fight();
        if (getPlayer().getPlayerHero().isLive()){
            getPlayer().getPlayerHero().addHealth(HEALING_HERO_AFTER_VICTORY);
            say(HERO_VICTORY);
        }
    }

    private void playerAttack() throws EndGame{
        if (getPlayer().getPlayerHero().isFirstFight()){
            say(String.format(START_FIGHT_LONG,START_FIGHT_MINI));
        } else {
            say(START_FIGHT_MINI);
        }
        say(YOU_DRAW_FIRST_HIT);
        if (getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy() != null) {
            getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().defense(getPlayer().getPlayerHero().attack());
            fight();
        }
    }

    private void playerSlip() throws EndGame{
        say(YOU_SLIP);
        if (getPlayer().getPlayerHero().slip()) {
            say(SLIP_SUCCESS);
        } else {
            say(SLIP_FAIL);
            fight();
        }
    }

    private void playerTalk() throws EndGame{
        say(YOU_TALK);
        if (getPlayer().getPlayerHero().talk()) {
            say(TALK_SUCCESS);
        } else {
            say(TALK_FAIL);
            fight();
        }
    }

    private void playerGo() throws EndGame{
        say(GET_AWAY_FROM_ENEMY);
        if (getPlayer().getPlayerHero().go()) {
            say(GET_AWAY_FROM_ENEMY_SUCCESS);
        } else {
            say(GET_AWAY_FROM_ENEMY_FAIL);
            getPlayer().getPlayerHero().die();
        }
    }

    private void fight() throws  EndGame{
        boolean mobAttack = true;
        boolean fightNotEnd = true;
        while (fightNotEnd &&
                getPlayer().getPlayerHero().getCurrentSite().isHaveLiveEnemy() &&
                getPlayer().getPlayerHero().isLive()){
            if (mobAttack){
                getPlayer().getPlayerHero().defense(getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().attack());
                mobAttack = false;
            } else {
                while (true) {
                    String playerCommand = getPlayer().answer();
                    if (validMessage(playerCommand) && (OptionFight.validString(playerCommand))) {
                        if (playerCommand.equalsIgnoreCase(OptionFight.HIT.toString())){
                            getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().defense(getPlayer().getPlayerHero().attack());
                            break;
                        } else if (playerCommand.equalsIgnoreCase(OptionFight.SMASH.toString())) {
                            getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().setDefenseBonus(RANDOM_BONUS[RANDOM.nextInt(RANDOM_BONUS.length)]); // FIXME: 17.11.2016
                            getPlayer().getPlayerHero().setAttackBonus(RANDOM_BONUS[RANDOM.nextInt(RANDOM_BONUS.length)]);
                            getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().defense(getPlayer().getPlayerHero().attack());
                            break;
                        } else if (playerCommand.equalsIgnoreCase(OptionFight.RUN.toString())){
                            fightNotEnd = !getPlayer().getPlayerHero().run();
                            break;
                        }
                    } else {
                        error();
                    }
                }
                mobAttack = true;
            }

        }
        if (getPlayer().getPlayerHero().isLive()){
            say(FIGHT_SUCCESS);
            getPlayer().getPlayerHero().addHealth(HEALING_HERO_AFTER_VICTORY);
            say(String.format(HERO_HEALING,HEALING_HERO_AFTER_VICTORY));
        }

    }

    private void go() throws EndGame{
        while (true) {
            String playerCommand = getPlayer().answer();
            if (validMessage(playerCommand) && (OptionTravel.validString(playerCommand))) {
                if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_EAST.toString())) {
                    getPlayer().getPlayerHero().setCurrentSite(getPlayer().getPlayerHero().getCurrentSite().getEastSite());
                } else if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_NORTH.toString())) {
                    getPlayer().getPlayerHero().setCurrentSite(getPlayer().getPlayerHero().getCurrentSite().getNorthSite());
                } else if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_SOUTH.toString())) {
                    getPlayer().getPlayerHero().setCurrentSite(getPlayer().getPlayerHero().getCurrentSite().getSouthSite());
                } else if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_WEST.toString())) {
                    getPlayer().getPlayerHero().setCurrentSite(getPlayer().getPlayerHero().getCurrentSite().getWestSite());
                }
                say(String.format(YOU_LOCATE,
                                                getPlayer().getPlayerHero().getName(),
                                                getPlayer().getPlayerHero().getCurrentSite().getDescription()));
                say(getPlayer().getPlayerHero().getCurrentSite().getUnitsDescription());
                if (getPlayer().getPlayerHero().getCurrentSite().isHaveLiveEnemy()){
                    if (!getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().getRace().isBoss()) {
                        whatToDoWithTheEnemy();
                    } else {
                        whatToDoWithTheBossEnemy();
                    }
                }
                break;
            } else {
                error();
            }
        }
    }

    private boolean needHelp(String command){
        if (command.equals(Option.HELP.toString())){
            say(MENU_HELP);
            return true;
        }
        return false;
    }

    private boolean exit(String command) throws EndGame{
        if (command.equals(Option.EXIT.toString())){
            say(BYE);
            throw new EndGame();
        }
        return false;
    }
    private boolean validMessage(String command) throws EndGame {
        return ((!needHelp(command) && !exit(command))
                && ((command != null) && (!command.equals(Option.EMPTY_LINE.toString()))));
    }

    private void excellent(String name){
        say(String.format(EXCELLENT, name));
    }

    public void error(){
        say(ERROR);
    }
}
