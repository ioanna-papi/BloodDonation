import java.sql.*;
public class Messages{

	public Messages() {
		super();
	}
	public void donationCalendar() {
		try {
			//get a connection to db
		    String url = "jdbc:sqlserver://195.251.249.161:1433;";
		    //String driver = "com.mysql.cj.jdbc.Driver";
		   String databaseName = "DB56";
		   String user = "G520";
		   String password = "94we99494";
			Connection dbcon ;
			dbcon = DriverManager.getConnection(url, user, password);
			Statement stmt = dbcon.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DonationDays");
			rs.close();
			stmt.close();SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			while ((rs.next())) {
				Date d_date = rs.getDate("D_Date");
				if (formatter.format(d).equals(d_date)){
					Date date = rs.getDate("D_Date");
					String day = rs.getString("D_Day");
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
					String strDate = dateFormat.format(date);  
					JOptionPane.showMessageDialog(null,day, strDate, JOptionPane.INFORMATION_MESSAGE);					
				}
			
				//System.out.println(rs.getString("D_Date"));
					//System.out.println(rs.getString("D_Day"));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
	
