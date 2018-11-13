
/**
 * Direction and X, Y coordinates where move was made
 */
public class Move implements Cloneable
{
    private Direction direction;
    private int x;
    private int y;


    public Move()
    {
        
    }
    
    public Move(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public Direction getDirection()
    {
        return direction;
    }
    
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
        //return new Move(this.getX(), this.getY(), this.getDirection());
    }
}