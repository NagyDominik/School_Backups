import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class AIPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public AIPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        setStyle();
    }
    
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!getPassed()){
            if(checkBallX() < getX()){
                setLocation(getX() - dx, getY());
            }
            if(checkBallX() > getX()){
                setLocation(getX() + dx, getY());
            }
        }
        else if(getPassed()){
            if(getX() < (getWorld().getWidth()/2)){
                setLocation(getX() + dx, getY());
            }
            if(getX() > (getWorld().getWidth()/2)){
                setLocation(getX() - dx, getY());
            }
        }
    }
    
    private boolean getPassed()
    {
        Ball ball = getWorld().getObjects(Ball.class).get(0);
        return ball.showPassed();
    }
    
    private int checkBallX()
    {
        Ball ball = getWorld().getObjects(Ball.class).get(0);
        return ball.getX();
    }
    
    private int checkBallY()
    {
       Ball ball = (Ball)getWorld().getObjects(Ball.class).get(0);
       return ball.getX();
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
