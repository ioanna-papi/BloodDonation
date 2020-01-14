import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class checkQuestionTest {

	@Test
	void test() {
		boolean flag = false;
		Date date1 = new Date();
		if (qid == 14) {
			try {
				date1=new SimpleDateFormat("yyyy/MM/dd").parse(an);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(System.currentTimeMillis());
			long diff = getDateDiff(date1,date,TimeUnit.DAYS);
			if (diff >= 90){
				flag = true;
			}
		} else if (qid > 14) {
			if (an.equals("1")) {
				flag = true; //if the users answers no, he is compatible
			}
		} else {
			flag = true; //the rest of the answers don't need checking
		}
		return flag;
		fail("Not yet implemented");
	}

}

