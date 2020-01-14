import java.util.TimerTask;

/**This method calls donationCalendar method from class Messages,
 * which is responsible to display messages to donors about the
 * default donation days*/
public class PrintTask extends TimerTask {
      public void run() {
         Messages.donationCalendar();
      }
}

