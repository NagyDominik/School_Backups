import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class OverWorld.
     * 
     */
    public OverWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted){
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            background.fill();
            GreenfootImage image = new GreenfootImage("Game Over",60,Color.RED,Color.BLACK);
            background.drawImage(image,130,250);
            addObject(new Restart(),250,360);
            addObject(new MainMenu(),250,400);
        }
        else{
            Greenfoot.setWorld(new IntroWorld());
        }
    }
}
