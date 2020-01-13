import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class BloodDonorTest {

	@Test
	void test() {
		Date date = new Date();
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy/MM/dd").parse("2019/10/12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BloodDonor.checkQuestion(24, "0");
		BloodDonor.getDateDiff(date1, date, TimeUnit.DAYS);
	}

}
