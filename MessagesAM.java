import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Messages{
	static Connection dbcon;
	static String url;
	public Messages(){
		super();
	}

	public static void connect() {
		url = "jdbc:sqlserver://195.251.249.161:1433;databaseName = DB56;user = G520;password = 94we99494";
		try {
			dbcon = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void donationDay(String date){
		try {
			connect();
			dbcon = DriverManager.getConnection(url);
			Statement stmt = dbcon.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BloodDonor");
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void donationCalendar() {
		try {
			Messages.connect();
			Statement stmt = dbcon.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DonationDays");
			ResultSet RS = stmt.executeQuery("SELECT * FROM BloodDonor");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			int d ,m;
			while ((rs.next())) {
				Date d_date = rs.getDate("D_Date");
				String strDate = formatter.format(date);
				String strDateDay = strDate.substring(8);
				String strDateMonth = strDate.substring(5,6);
				String strDateYear = strDate.substring(0,3);
				d = Integer.parseInt(strDateDay);
				m = Integer.parseInt(strDateMonth);
				if (d-5 <= 0) {
					if(m == 2 || m == 4 || m == 6 || m == 9 || m == 11) {
						d = 30 + (d-5);
						m -= 1;
					} else {
						d = 31 + (d-5);
						if(m == 1){
							m = 12;
						}else{
							m -= 1;
						}
					}
				}
				String strd = Integer.toString(d); 
				String strm = Integer.toString(m);
				if (d < 10) {
					 strd = "0" + d;	 
				}
				if (m < 10) {
					 strm = "0" + m;
				}   
				String messageDate = strDateYear + "-" + strm + "-" + strd;
				if (formatter.format(date).equals(messageDate)) {
					String day = rs.getString(i);
					JOptionPane.showMessageDialog(null, day, strDate, JOptionPane.INFORMATION_MESSAGE);					
				}
				rs.close();
				RS.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
