import javax.swing.JOptionPane;


public class HospitalS {

	public String makeDonationDay() {

		Object[] year = {"2019","2020","2021","2022", "2023","2024","2025","2026","2027","2028","2029"};
		String y =(String)JOptionPane.showInputDialog(null, "Year", "Choose the year of the donation", JOptionPane.PLAIN_MESSAGE, null, year, "2019" );

		Object[] month = {"01","02","03","04", "05","06","07","08","09","10","11","12"};
		String m =(String)JOptionPane.showInputDialog(null, "Month", "Choose the month of the donation", JOptionPane.PLAIN_MESSAGE, null, month, "01" );
		String d;
		if (m == "01" || m == "03" || m == "5" || m == "07" || m == "08" || m == "10" || m == "12") {

			Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
			d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );

		} else if (m == "04" || m == "06" || m == "09" || m == "11") {

			Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
			d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );

		} else if (y == "2020" || y == "2024" || y == "2028") {

			Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
			d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );

		} else {
			Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
			d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );
		}

		String date = String.join(d, "/", m, "/", y);
		return date;

	}
} 
