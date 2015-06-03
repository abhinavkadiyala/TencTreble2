package component;



public class VisionBullet extends Bullet
{
	protected final int myRadius;
	public VisionBullet(Player player)
	{
		super(player);
		myRadius = 5; //change value as needed
	}
}
