import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class BloodDonor {
	
	private static String[] answers = new String[51];
	
	public static void questionnaire() {
		Scanner sc = new Scanner(System.in);
		

			try {
			String url = "jdbc:mysql://localhost:3306/Donation?serverTimezone=UTC";
			            String driver = "com.mysql.cj.jdbc.Driver";
			            String userName = "root";
			            String password = "";
			Connection dbcon ;
			dbcon = DriverManager.getConnection(url, userName, password);
			Statement stmt = dbcon.createStatement();
			int i = 0;
			ResultSet rs = stmt.executeQuery("SELECT * FROM Questionnaire ");
			while (rs.next()) {
			String name = rs.getString("Q_id");
			String r = rs.getString("Question");
			System.out.println(name +" " + r);
			String a = sc.nextLine();
			answers[i] = a;
			i++;
			}
			rs.close();
			stmt.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		}
	


		public static void updateQuestionnaire() {
			Scanner sc = new Scanner(System.in);
			int a;
			System.out.println("1.Sex ,male/female\n2.Last Name\n3.First Name\n 4.Father's Name\n5.Year of Birth\n6.Place of Birth\n7.Profession\n8.ID Number\n9.Address\n"
		 		+ " 10.PostCode\n11.City\n12.Phone Number\n13.Have you ever gave blood before\n14.When was the last time you gave blood?\n"
		 		+ "15.Have you ever been excluded from a blood donation?\n16.Do you have any dangerous profession or hobby?\n17.Have you had any health problems before?\n"
		 		+ "18.jaundice or hepatitis\n19.syphilis\n20.malaria\n21.tuberculosis\n22.rheumatoid arthritis\n23.heart disease\n24.precardiac pan\n25.hypertension\n"
		 		+ "26.convulsions(as an adult)\n27.fainting\n28.stomach aliments\n29.ulcer\n30.kidney disease\n31.diabetes\n32.anemia\n33.allergy\n"
		 		+ "34.What are you allergic to?\n35.contagious disease in your environment?\n36.drug intake?\n37.taken aspirin the last 5 days?\n"
		 		+ "38.born or lived or traveled aboard?\n39.lost weight, had fever or swollen tonsils?\n40.have you ever had a cornea or scar implant in your eye?\n"
		 		+ "41.intake of growth hormone extract?\n42.tooth extraction or treatment the past week?\n43.vaccines the past week?\n"
		 		+ "44.surgery or medical examinations the past year?\n45.transfusion of blood or blood producers?\n46.tattoo or ear piercing or acupuncture\n"
		 		+ "47.piercedby a syringe needle\n48.any skin wounds or sores of yours or mucous membrane of your mouth came in contact with foreign blood?\n"
		 		+ "49.were you pregnant the past year?\n50.have you heard that your family is in danger of developing the Creutzfeldt-Jakob disease?\n");
			a = 1;
		 System.out.println("Which answer you want to change? press the number of the question if you want to exit press 0");
		 a = sc.nextInt();
		 changeQuestion(a);
		 while (a != 0 ) {
		 try {
		     if (a >= 1 && a <= 51) {
			 System.out.println("If you want to change another question press the number of the question or else press 0");
			 a = sc.nextInt();
			 changeQuestion(a);
		     }else { System.out.println("You must type the number of the question");
		     }
		 } catch (InputMismatchException e) { 
			 }
         }
	 
	}
		
		public static void changeQuestion(int a) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Update your answer");
			String a2 = sc.nextLine();
			// χειρισμό εξαιρέσεων (try-catch)
			answers[a-1] = a2;
		}
		
}
 

