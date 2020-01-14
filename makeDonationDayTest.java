import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

public class makeDonationDayTest {

	@Test
	public void test() {
		Hospital hs = new Hospital();
		assertEquals("2020/01/9",hs.makeDonationDay());

	}

}
