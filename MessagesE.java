

import java.sql.*;
import javax.swing.JOptionPane;

public class Messages {
	
	int choice;
	
	public Messages() {
		super();
	}
	
	/**This method informs a hospital about it's shortage of blood in a specific blood type*/
	public void shortageOfBlood(String bloodtype) {//must have parametre username and region of hospital!!
		JOptionPane.showMessageDialog(null, "SHORTAGE OF BLOOD TYPE " + bloodtype, "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
		boolean flag = true;
		while(flag) {
			String[] options = {"Blood borrow ", "Create donation day "};
			int choice = JOptionPane.showOptionDialog (null, "You can ask for blood borrow or create a blood donation day",
					"Message",JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,options,options[1]);
			if (choice == 0) {
				flag = false;
				Messages mes = new Messages();
	        	mes.bloodBorrow(region, bloodtype); 
			} else if (choice == 1) {
				flag = false;
				Hospital h = new Hospital();
	        		h.makeDonationDay();
			} else {
				JOptionPane.showMessageDialog(null, "You have to choose one of the above!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
	
	/**sends message to hospitals in order to borrow blood to a hospital in the same region*/
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

