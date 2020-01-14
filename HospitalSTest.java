import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

public class HospitalSTest {

	@Test
	public void test() {
		HospitalS hs = new HospitalS();
		assertEquals("2020/01/9",hs.makeDonationDay());

	}

}

