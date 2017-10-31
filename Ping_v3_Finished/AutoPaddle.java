import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class AutoPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public AutoPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        setStyle();
    }

    /**
     * Sets the image of the autopaddle and resizes it.
     */
    private void setStyle()
    {
        GreenfootImage fancy =  new GreenfootImage(getImage());
        fancy.scale(width, height);
        setImage(fancy);
    }
    
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getX() + dx, getY());
        tryChangeDirection();
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection()
    {
        /*Check to see if we are touching the outer boundaries of the world:
          IF we are touching the right boundary OR we are touching the left boundary:
        */
        if(getX() + width/3 >= getWorld().getWidth() || getX() - width/3 <= 0)
        {
            RandomPaddle();
            //getWorld().removeObject(this); (Unoptimized version 1)
        }
    }
    
    /**
     * Moves the AutoPaddle to random height and resizes it. (Optimized)
     */
    public void RandomPaddle()
    {
        /*AutoPaddle newpaddle = new AutoPaddle(width, height);
        getWorld().addObject(newpaddle, 1+width/3, 30+Greenfoot.getRandomNumber(220)); (Unoptimized version 1)*/
        this.setLocation(1+width/3, 30+Greenfoot.getRandomNumber(220));
        GreenfootImage autopaddle = getImage();
        autopaddle.scale(50 + Greenfoot.getRandomNumber(100), 20);
        setImage(autopaddle);
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }

}
