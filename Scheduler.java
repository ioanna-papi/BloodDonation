import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * This class runs PrintTask method run() at a specific time of the day every day*/
public class Scheduler {
   public static void main(String []args) {
      Timer timer = new Timer();
  
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, 10);
      calendar.set(Calendar.MINUTE, 00);
      calendar.set(Calendar.SECOND, 00);
      Date time = calendar.getTime();
  
      timer.schedule(new PrintTask(),
                     time);    
   }   
}

