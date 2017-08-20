package com.rozzer.adventure;


import com.rozzer.adventure.core.exception.EndGame;
import com.rozzer.adventure.core.option.Option;
import com.rozzer.adventure.core.option.OptionEnemy;
import com.rozzer.adventure.core.option.OptionFight;
import com.rozzer.adventure.core.option.OptionTravel;
import com.rozzer.adventure.unit.RaceType;
import com.rozzer.adventure.unit.thing.WeaponType;
import com.rozzer.adventure.world.Depiction;


/**
 * Created by Rozzer on 15.11.2016.
 */
public class Speaker implements Depiction, Constants {
    private static Speaker speaker;

    private Speaker() {
    }
    public static Speaker getSpeaker(){
        if (speaker == null) {
            speaker = new Speaker();
        }
        return speaker;
    }

    public  void startGame() throws EndGame{
        say(MENU);
        while (true) {
            String playerCommand = Player.getPlayer().answer();
            if (validMessage(playerCommand) && (Option.START.toString().equals(playerCommand))) {
                break;
            } else {
                error();
            }
        }
    }

    public void greeting() throws EndGame{
        say(GREETING);
        while (true) {
            String playerCommand = Player.getPlayer().answer();
            if (validMessage(playerCommand)) {
                Player.getPlayer().getPlayerHero().setName(playerCommand);
                excellent(Player.getPlayer().getPlayerHero().getName());
                break;
            } else {
                error();
            }
        }
    }
    public void askRace() throws EndGame {
        say(WHAT_RACE_YOU);
        while (true) {
            String playerCommand = Player.getPlayer().answer();
            if (validMessage(playerCommand) && (RaceType.validString(playerCommand)) && (!RaceType.fromString(playerCommand).isBoss())) {
                Player.getPlayer().getPlayerHero().setRace(RaceType.fromString(playerCommand));
                Player.getPlayer().getPlayerHero().setWeapon(WeaponType.getRandom());
                String description = String.format(RACE_AND_NAME,
                        Player.getPlayer().getPlayerHero().getRace().toString(),
                        Player.getPlayer().getPlayerHero().getName());
                Player.getPlayer().getPlayerHero().setDescription(description);
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
                                                    Player.getPlayer().getPlayerHero().getRace().toString(),
                                                    Player.getPlayer().getPlayerHero().getName()),
                                        Player.getPlayer().getPlayerHero().getWeapon().toString(),
                                        Player.getPlayer().getPlayerHero().getCurrentSite().getDescription()
                                        ));
        go();
    }

    public void whereToGo() throws EndGame {
        if (Player.getPlayer().getPlayerHero().isLive()) {
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
        if (Player.getPlayer().getPlayerHero().isFirstFight()){
            say(String.format(WHAT_TO_WITH_THE_ENEMY_LONG,WHAT_TO_WITH_THE_ENEMY_MINI));
        } else {
            say(WHAT_TO_WITH_THE_ENEMY_MINI);
        }
        while (true) {
            String playerCommand = Player.getPlayer().answer();
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
        say(String.format(YUO_SEE_AND_ATTACK,Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().getDescription()));
        fight();
        if (Player.getPlayer().getPlayerHero().isLive()){
            Player.getPlayer().getPlayerHero().addHealth(HEALING_HERO_AFTER_VICTORY);
            say(HERO_VICTORY);
        }
    }

    private void playerAttack() throws EndGame{
        if (Player.getPlayer().getPlayerHero().isFirstFight()){
            say(String.format(START_FIGHT_LONG,START_FIGHT_MINI));
        } else {
            say(START_FIGHT_MINI);
        }
        say(YOU_DRAW_FIRST_HIT);
        if (Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy() != null) {
            Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().defense(Player.getPlayer().getPlayerHero().attack());
            fight();
        }
    }

    private void playerSlip() throws EndGame{
        say(YOU_SLIP);
        if (Player.getPlayer().getPlayerHero().slip()) {
            say(SLIP_SUCCESS);
        } else {
            say(SLIP_FAIL);
            fight();
        }
    }

    private void playerTalk() throws EndGame{
        say(YOU_TALK);
        if (Player.getPlayer().getPlayerHero().talk()) {
            say(TALK_SUCCESS);
        } else {
            say(TALK_FAIL);
            fight();
        }
    }

    private void playerGo() throws EndGame{
        say(GET_AWAY_FROM_ENEMY);
        if (Player.getPlayer().getPlayerHero().go()) {
            say(GET_AWAY_FROM_ENEMY_SUCCESS);
        } else {
            say(GET_AWAY_FROM_ENEMY_FAIL);
            Player.getPlayer().getPlayerHero().die();
        }
    }

    private void fight() throws  EndGame{
        boolean mobAttack = true;
        boolean fightNotEnd = true;
        while (fightNotEnd &&
                Player.getPlayer().getPlayerHero().getCurrentSite().isHaveLiveEnemy() &&
                Player.getPlayer().getPlayerHero().isLive()){
            if (mobAttack){
                Player.getPlayer().getPlayerHero().defense(Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().attack());
                mobAttack = false;
            } else {
                while (true) {
                    String playerCommand = Player.getPlayer().answer();
                    if (validMessage(playerCommand) && (OptionFight.validString(playerCommand))) {
                        if (playerCommand.equalsIgnoreCase(OptionFight.HIT.toString())){
                            Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().defense(Player.getPlayer().getPlayerHero().attack());
                            break;
                        } else if (playerCommand.equalsIgnoreCase(OptionFight.SMASH.toString())) {
                            Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().setDefenseBonus(RANDOM_BONUS[RANDOM.nextInt(RANDOM_BONUS.length)]); // FIXME: 17.11.2016
                            Player.getPlayer().getPlayerHero().setAttackBonus(RANDOM_BONUS[RANDOM.nextInt(RANDOM_BONUS.length)]);
                            Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().defense(Player.getPlayer().getPlayerHero().attack());
                            break;
                        } else if (playerCommand.equalsIgnoreCase(OptionFight.RUN.toString())){
                            fightNotEnd = !Player.getPlayer().getPlayerHero().run();
                            break;
                        }
                    } else {
                        error();
                    }
                }
                mobAttack = true;
            }

        }
        if (Player.getPlayer().getPlayerHero().isLive()){
            say(FIGHT_SUCCESS);
            Player.getPlayer().getPlayerHero().addHealth(HEALING_HERO_AFTER_VICTORY);
            say(String.format(HERO_HEALING,HEALING_HERO_AFTER_VICTORY));
        }

    }

    private void go() throws EndGame{
        while (true) {
            String playerCommand = Player.getPlayer().answer();
            if (validMessage(playerCommand) && (OptionTravel.validString(playerCommand))) {
                if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_EAST.toString())) {
                    Player.getPlayer().getPlayerHero().setCurrentSite(Player.getPlayer().getPlayerHero().getCurrentSite().getEastSite());
                } else if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_NORTH.toString())) {
                    Player.getPlayer().getPlayerHero().setCurrentSite(Player.getPlayer().getPlayerHero().getCurrentSite().getNorthSite());
                } else if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_SOUTH.toString())) {
                    Player.getPlayer().getPlayerHero().setCurrentSite(Player.getPlayer().getPlayerHero().getCurrentSite().getSouthSite());
                } else if (playerCommand.equalsIgnoreCase(OptionTravel.GO_TO_WEST.toString())) {
                    Player.getPlayer().getPlayerHero().setCurrentSite(Player.getPlayer().getPlayerHero().getCurrentSite().getWestSite());
                }
                say(String.format(YOU_LOCATE,
                                                Player.getPlayer().getPlayerHero().getName(),
                                                Player.getPlayer().getPlayerHero().getCurrentSite().getDescription()));
                say(Player.getPlayer().getPlayerHero().getCurrentSite().getUnitsDescription());
                if (Player.getPlayer().getPlayerHero().getCurrentSite().isHaveLiveEnemy()){
                    if (!Player.getPlayer().getPlayerHero().getCurrentSite().getRandomEnemy().getRace().isBoss()) {
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
