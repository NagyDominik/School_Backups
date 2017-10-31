import greenfoot.*;


/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    private Ball playBall;
    private int levelCount = 1;
    private int GameMode;
    private int aWin = 0;
    private int bWin = 0;

    public PingWorld()
    { 
        // calling the other constructor with gameStarted = false.
        this(false, 1);       
    }
    
    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted, int gamemode)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            playBall = new Ball();
            GameMode = gamemode;
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(playBall, WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new Paddle(100,20), 60, WORLD_HEIGHT - 50);
            if(gamemode == 0){
                setBackgroundvs();
                addObject(new AIPaddle(100,20), 60, 50);
            }
            else if(gamemode == 1){
                setBackgroundpractice();
                addObject(new AutoPaddle(100,20), 30, Greenfoot.getRandomNumber(200));
            }
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    public void incA()
    {
        aWin++;
        setBackgroundvs();
    }
    
    public void incB()
    {
        bWin++;
        setBackgroundvs();
    }
    
    public int getMode()
    {
        return GameMode;
    }
    
    public void setCounter(int level)
    {
        levelCount = level;
        setBackgroundpractice();
    }
    
    private void setBackgroundpractice()
    {
        GreenfootImage background = getBackground();
        
        background.setColor(Color.BLACK);
        background.drawLine(0, WORLD_HEIGHT/2, WORLD_WIDTH, WORLD_HEIGHT/2);
        GreenfootImage text = new GreenfootImage("Game Level: " + levelCount, 20, Color.BLACK, Color.WHITE);
        background.drawImage(text, 375, 15);
    }
    
    private void setBackgroundvs()
    {
        GreenfootImage background = getBackground();
        
        background.setColor(Color.BLACK);
        background.drawLine(0, WORLD_HEIGHT/2, WORLD_WIDTH, WORLD_HEIGHT/2);
        GreenfootImage text = new GreenfootImage("Computer Score: " + aWin, 20, Color.BLACK, Color.WHITE);
        background.drawImage(text, 350, 15);
        GreenfootImage text2 = new GreenfootImage("Player Score: " + bWin, 20, Color.BLACK, Color.WHITE);
        background.drawImage(text2, 20, 15);
    }
}
