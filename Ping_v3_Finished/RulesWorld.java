import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RulesWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RulesWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class RulesWorld.
     * 
     */
    public RulesWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted){
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            background.fill();
            GreenfootImage image1 = new GreenfootImage("Welcome to The Best Game Ever",40,Color.GREEN,Color.BLACK);
            background.drawImage(image1,10,50);
            GreenfootImage image2 = new GreenfootImage("You can control the paddle with the arrow keys",20,Color.GREEN,Color.BLACK);
            background.drawImage(image2,20,150);
            GreenfootImage image3 = new GreenfootImage("But remember that the paddle can move only left and right",20,Color.GREEN,Color.BLACK);
            background.drawImage(image3,20,200);
            GreenfootImage image4 = new GreenfootImage("If the ball hits the self-moving paddle from above the ball will",20,Color.GREEN,Color.BLACK);
            background.drawImage(image4,20,250);
            GreenfootImage image5 = new GreenfootImage("go through it",20,Color.GREEN,Color.BLACK);
            background.drawImage(image5,20,275);
            GreenfootImage image6 = new GreenfootImage("Each time the ball has been hit by the paddle 10 times,",20,Color.GREEN,Color.BLACK);
            background.drawImage(image6,20,325);
            GreenfootImage image7 = new GreenfootImage("the speed of the ball is increased",20,Color.GREEN,Color.BLACK);
            background.drawImage(image7,20,350);
            GreenfootImage image8 = new GreenfootImage("HAVE FUN!",40,Color.RED,Color.BLACK);
            background.drawImage(image8,175,420);
           
            addObject(new Back(),250,600);
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
        
    }
}
