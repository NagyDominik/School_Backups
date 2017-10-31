import greenfoot.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends SmoothMover
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private int speed;
    private int delay;
    private int bounceCounter = 0;
    private int level;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private boolean comesFromAbove = false;
    private boolean hasTouchedPaddle = false;
    private boolean hasTouchedAutoPaddle = false;
    private boolean hasTouchedAIPaddle = false;
    private boolean passed = true;

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        //createImage();
        setStyle();
        init();
    }

    /**
     * Sets the displayed level and increases the speed of the ball after every 10th bounce.
     */
    private void setLevel()
    {
        PingWorld world = (PingWorld)getWorld();
        if(bounceCounter == 10 && world.getMode() == 1){
            level++;
            speed++;
            bounceCounter = 0;
            
            world.setCounter(level);
        }
        else if(bounceCounter == 10 && world.getMode() == 0){
            speed++;
            bounceCounter = 0;
        }
    }
    
    /**
     * Returns the number of bounces.
     */
    public int getBounces()
    {
        return bounceCounter;
    }
    
    public boolean showPassed()
    {
        return passed;
    }
    
    /**
     * Sets the image of the ball and resizes it.
     */
    private void setStyle()
    {
        GreenfootImage fancy =  new GreenfootImage(getImage());
        fancy.scale(BALL_SIZE, BALL_SIZE);
        setImage(fancy);
    }
    
    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        ballImage.setColor(Color.BLACK);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            //getWorld().showText("Game Level: " + level, 400, 20);        
            setLevel();
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkBounceOffPaddle();
            checkBounceOffAutoPaddle();
            checkBounceOffAIPaddle();
            checkRestart();
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }
    
    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
                Greenfoot.playSound("wallbounce_v2.mp3");
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            if (! hasBouncedVertically)
            {
                comesFromAbove = true;
                revertVertically();
                Greenfoot.playSound("wallbounce_v2.mp3");
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }
    
    /**
     * Check to see if the ball should bounce off the paddle.
     * If touching the paddle the ball is bouncing off.
     */
    private void checkBounceOffPaddle()
    {
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if(paddle != null){
            comesFromAbove = false;
            if(! hasTouchedPaddle){
                bounceCounter++;
                revertVertically();
                passed = false;
                Greenfoot.playSound("paddlebounce_v1.mp3");
                hasTouchedPaddle = true;
            }
        }
        else{
            hasTouchedPaddle = false;
        }
    }
    
    /**
     * Check to see if the ball should bounce off the paddle.
     * If touching the paddle the ball is bouncing off.
     */
    private void checkBounceOffAutoPaddle()
    {
        Actor autopaddle = getOneIntersectingObject(AutoPaddle.class);
        if(autopaddle != null){
            if(! comesFromAbove && ! hasTouchedAutoPaddle){
                revertVertically();
                Greenfoot.playSound("paddlebounce_v1.mp3");
                hasTouchedAutoPaddle = true;
            }
        }
        else{
            hasTouchedAutoPaddle = false;
        }
    }
    
    /**
     * Check to see if the ball should bounce off the paddle.
     * If touching the paddle the ball is bouncing off.
     */
    private void checkBounceOffAIPaddle()
    {
        Actor aipaddle = getOneIntersectingObject(AIPaddle.class);
        if(aipaddle != null){
            if(! hasTouchedAIPaddle){
                revertVertically();
                passed = true;
                Greenfoot.playSound("paddlebounce_v1.mp3");
                hasTouchedAIPaddle = true;
            }
        }
        else{
            hasTouchedAIPaddle = false;
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        PingWorld world = (PingWorld)getWorld();
        if (isTouchingFloor() && world.getMode() == 1)
        {
            init();
            Greenfoot.playSound("gameover_v2.mp3");
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            world.setCounter(level);
            Greenfoot.setWorld(new OverWorld(true));
        }
        if(isTouchingFloor() && world.getMode() == 0){
            world.incA();
            setLocation(getWorld().getWidth()/2, getWorld().getHeight()/2);
            init();
        }
        if(isTouchingCeiling() && world.getMode() == 0){
            world.incB();
            setLocation(getWorld().getWidth()/2, getWorld().getHeight()/2);
            init();
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        if(getRotation() >= 75 && getRotation() <= 105){
            setRotation(getRotation() - Greenfoot.getRandomNumber(20));
        }
        hasBouncedVertically = true;
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        level = 1;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        comesFromAbove = false;
        hasTouchedPaddle = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }

}
