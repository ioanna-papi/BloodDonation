

import java.sql.*;
import javax.swing.JOptionPane;

public class Messages {
	
	int choice;
	
	public Messages() {
		super();
	}
	
	public static void main(String[] args) {
		Messages m = new Messages();
		m.bloodBorrow("Attiki");
		//m.donationCalendar();
		//m.shortageOfBlood("A+");
	}
	
	public void shortageOfBlood(String bloodtype) {
		JOptionPane.showMessageDialog(null, "SHORTAGE OF BLOOD TYPE " + bloodtype, "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(null, "You can ask for blood borrow or create a blood donation day");
		choice = Integer.parseInt(JOptionPane.showInputDialog("Type \"1\" for blood borrow or \"2\" for donation day"));
		//exception
	}
	
	//sends message to hospitals in order to borrow blood to a hospital in the same region
	public void bloodBorrow(String region) {
		try {
			String url = "jdbc:mysql://localhost:3306/Donation?serverTimezone=UTC";
            String userName = "root"; 
            String password = "";
			Connection dbcon ;
			dbcon = DriverManager.getConnection(url, userName, password);
			Statement stmt = dbcon.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Hospital ");
				while (rs.next()) {
					String r = rs.getString("Region");
						if (r.equals(region)) {
							System.out.println(rs.getInt("H_id"));
						}
				}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

