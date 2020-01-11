import java.sql.*;
import javax.swing.JOptionPane;

public class Messages {
	
	int choice;
	
	public Messages() {
		super();
	}
	
	/**
	 * This method informs a hospital about it's shortage of blood in a specific blood type
	 * @param region the hospital's region
	 * @param bloodtype the specific blood type which is lacking in the specific hospital
	 * @param username the hospital's username*/
	public void shortageOfBlood(String region, String bloodtype, String username) {//must have parametre username and region of hospital!!
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
	 		       	mes.bloodBorrow(region, bloodtype, username); 
			} else if (choice == 1) {
				flag = false;
				Hospital h = new Hospital();
	        		h.makeDonationDay();
			} else {
				JOptionPane.showMessageDialog(null, "You have to choose one of the above!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
	
	/**sends message to hospitals in order to borrow blood to a hospital in the same region
	 * @param region the region the specific hospital belongs to
	 * @param bloodtype the type of blood the hospital is lacking
	 * @param username the hospital's username*/
	public void bloodBorrow(String region, String bloodtype, String username) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr:1433;"
		    		+ "databaseName=DB20;user=G520;password=94we99494";
			
		Connection dbcon ;
		try {
			dbcon = DriverManager.getConnection(url);
			Statement stmt = dbcon.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Region, Username, H_name FROM Hospital  WHERE region = '" + region+ "' and Username='" +username +"'");
			while (rs.next()) {
				String name = rs.getString("H_name");
				String r = rs.getString("Region");
					if (r.equals(region)) {
						JOptionPane.showMessageDialog(null, name + " has blood type " + bloodtype+ " shortage.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					}
			}
		rs.close();
		stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

