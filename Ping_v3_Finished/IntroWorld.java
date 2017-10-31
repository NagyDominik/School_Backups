import greenfoot.*;


/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK); 
        addObject(new Rules(),240,475);
        addObject(new StartGame(),240,400);
        addObject(new Practice(),240,440);
    }
    
    public IntroWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        
        if (gameStarted){
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK); 
            addObject(new Rules(),240,475);
            addObject(new StartGame(),240,400);
            addObject(new Practice(),240,440);
        }
        else{
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    public void act()
    {
        /*String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true, 1));
        } (For possible later uses!)*/
        
    }
    
}

