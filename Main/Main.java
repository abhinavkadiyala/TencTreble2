import java.util.*;

public class TimerDemo {
   public static void main(String[] args) {
   // creating timer task, timer
   TimerTask tasknew = new TimerSchedulePeriod();
   Timer timer = new Timer();
      
   // scheduling the task at interval
   timer.schedule(tasknew,100, 100);      
   }
   // this method performs the task
   public void run() {
   System.out.println("timer working");      
   }    
}
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

}
