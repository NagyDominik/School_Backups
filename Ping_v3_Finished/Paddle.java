import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    private int width;
    private int height;
    private int dx;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 3;
        //createImage();
        setStyle();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkControl();
    }    

    /**
     * Sets the image of the paddle and resizes it.
     */
    private void setStyle()
    {
        GreenfootImage fancy =  new GreenfootImage(getImage());
        fancy.scale(width, height);
        setImage(fancy);
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
    
    /**
     * The control mechanism of the ball. IF you press "Left" the paddle goes to the left
     * and if you press "Right" the paddle goes to the right;
     */
    private void checkControl()
    {
        if(Greenfoot.isKeyDown("left")){
            setLocation(getX() - dx, getY());
        }
        
        if(Greenfoot.isKeyDown("right")){
            setLocation(getX() + dx, getY());
        }
    }

}
