import java.util.*;


public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		GUI gui = new GUI();// STILL NEED TO CHANGE BOTH OF THESE BEACUSE UNSURE OF CONSTRUCTORS
		// TODO Auto-generated method stub
		TimerTask periodicTask = new TimerSchedulePeriod();
		Timer timer = new Timer();
		timer.schedule(tasknew, 0, 20);
	}
	public void run(){
		game.update();
		gui.update();
	}
	}


