package BusinessLayer.Game;


import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.InputCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;

public class Level {
    private Board board;
    private int levelNumber;
    MessageCallback messageCallback;
    private InputCallback inputCallback;
    private String systemCMDInput;
    private Position playerPosition;

    public Level(Board board, int levelNumber, MessageCallback messageCallback, Position playerPosition, InputCallback inputCallback) {
        this.board = board;
        this.levelNumber = levelNumber;
        this.inputCallback = inputCallback;
        this.messageCallback = messageCallback;
        this.systemCMDInput = null;
        this.playerPosition = playerPosition;
    }

    public Board getBoard() {
        return board;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setPredefinedCommandInput(String sysInput) {
        this.systemCMDInput = sysInput;
    }

    public void onPlayerGameTick() {
        String input = systemCMDInput != null ? systemCMDInput : inputCallback.getInput().toLowerCase();
        while(!("q".equals(input) || "w".equals(input) || "e".equals(input) || "a".equals(input) || "s".equals(input) || "d".equals(input)))
        { 
            input = inputCallback.getInput().toLowerCase();
        }
        char command = input.charAt(0);
        switch(command) 
        {
            case 'q':
                break;
            case 'e':
                board.getPlayer().abilityCast();
                break;
            case 'w':
                board.getPlayer().interact(board.getTile(new Position(board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY() - 1)));
                //board.getTile(new Position(board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY() - 1)).accept(board.getPlayer());
                break;
            case 'a':
                board.getPlayer().interact(board.getTile(new Position(board.getPlayer().getPosition().getX() - 1, board.getPlayer().getPosition().getY())));
                //board.getTile(new Position(board.getPlayer().getPosition().getX() - 1, board.getPlayer().getPosition().getY())).accept(board.getPlayer());
                break;
            case 's':
                board.getPlayer().interact(board.getTile(new Position(board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY() + 1)));
                //board.getTile(new Position(board.getPlayer().getPosition().getX(), board.getPlayer().getPosition().getY() + 1)).accept(board.getPlayer());
                break;
            case 'd':
                board.getPlayer().interact(board.getTile(new Position(board.getPlayer().getPosition().getX() + 1, board.getPlayer().getPosition().getY())));
                //board.getTile(new Position(board.getPlayer().getPosition().getX() + 1, board.getPlayer().getPosition().getY())).accept(board.getPlayer());
                break;
            default:
                break;
        }
        
        
    }

    public void onEnemyGameTick()
    {
        for(Enemy enemy : board.getEnemies())
        {
            enemy.onEnemyTurn();
        }
    }

    public void onGameTick()
    {
        onPlayerGameTick();
        board.updateBoard();
        onEnemyGameTick();
        board.updateBoard();
        board.getPlayer().onGameTick();
    }

    public int startLevel()
    {
        board.getPlayer().setGameContext(board);
        while(board.getPlayer().alive() && !board.getEnemies().isEmpty()) // while player is alive and there are enemies
        {
            messageCallback.send(board.toString());
            messageCallback.send(board.getPlayer().printStats());
            onGameTick();
        }
        if(board.getPlayer().alive()) // if player is alive
        {
            endLevel();
            return 1;
        }
        else // if player is dead
        {
            messageCallback.send("Game Over");
            messageCallback.send(board.toString());
            return 0;
        }
    }

    public void startLevelSys() //for testing purposes
    {
        messageCallback.send(board.toString());
        messageCallback.send(board.getPlayer().printStats());
        board.getPlayer().setGameContext(board);
    }

    public boolean startLevelSysTick(String action) //for testing purposes
    {
        setPredefinedCommandInput(action);
        onGameTick();
        messageCallback.send("Action inserted: " + action);
        messageCallback.send(board.toString());
        messageCallback.send(board.getPlayer().printStats());
        if(board.getEnemies().size() == 0)
        {
            endLevel();
            return true;
        }
        return false;
    }

    public void endLevel()
    {
        messageCallback.send("Level " + levelNumber + " completed");
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }
}
