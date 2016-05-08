package game.component;

import java.awt.geom.Point2D;

import game.Map;
import game.Player;

public class KillBullet extends Bullet {
	public KillBullet(Player player) {
		super(player);

	}
	public KillBullet(Point2D.Double loc, double dir, Map mp, Player p) {	//allows debugging
		super(loc,dir,mp,p);
	}
}
