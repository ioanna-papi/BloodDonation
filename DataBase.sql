/*create schema Donation;
use Donation*/
use DB20;
/*
Drop table DonationDays;
Drop table Answers;
Drop table Questionnaire;
Drop table BloodDonor;
Drop table BloodBankStock;
Drop table BloodLimits;
Drop table Bloodtypes;
Drop table Hospital;
*/
Create table Hospital( 
                Username varchar(50) not null,
		H_name varchar(50) not null,
                H_pass varchar(50) not null,
                Telephone varchar(10) not null,
                Address varchar(100) not null,
		Region varchar(50) not null
		Primary key (Username));
			

                
create  table Bloodtypes (
		bloodtype varchar(3) primary key); 

Create table BloodLimits(
		H_Username varchar(50) not null,
                BloodType varchar(3) not null,
                BloodLimit float not null,
                Primary key (H_Username, BloodType),
                Foreign key (H_Username) references Hospital(Username),
		Foreign key (BloodType) references Bloodtypes(bloodtype));               


                
create table BloodBankStock(
		H_Username varchar(50) not null,
		BloodType varchar(5) not null,
                Blood float not null,
                Primary key (H_Username,BloodType),
                Foreign key (H_Username) references Hospital(Username));  
                
               
Create table BloodDonor(
		B_Name varchar(50) not null,
                B_Username varchar(50) not null,
                B_email varchar(50) not null,
		B_password varchar(50) not null,
                Gender varchar(10) not null,
                BloodType varchar(10) not null,
                SSN varchar(11) not null,
                Region varchar(50) not null
		Primary key (B_Username));
                
                
 
 create table Questionnaire(
		Q_id int primary key,
		Question varchar(100) not null); 

      
create table Answers(
		Q_id int not null,
                B_Username varchar(50) not null,
                Answer Varchar(50) not null,
                primary key (Q_id,B_Username),
                Foreign key (B_Username) references BloodDonor(B_Username),
                Foreign key (Q_id) references Questionnaire(Q_id));
               
                
 
create table DonationDays(
		D_Date date not null,
                D_Day varchar(50) not null);	
 
 insert into Bloodtypes(bloodtype)
 values ('O+'),
 	('O-'),
 	('A+'),
 	('A-'),
 	('B+'),
 	('B-'),
 	('AB+'),
 	('AB-');
 
 
insert into Hospital(Username,H_name, H_pass, Telephone, Address,Region)
Values ('AgKurHosp99' , 'Aglaias Kuriakou' , 'Kit6yweW', '2125673124', '', 'Peloponnisos'),
		('atticahospital12' , 'Attiko Nosokomeio' , 'att123hosLOdew', '2104678345', '', 'Attica'),
		('genhosp32' , 'Geniamatas' , 'gTre5rhkLp', '2104893002', '', 'Crete'),
		('hosPaidon43' , 'Paidon' , 'ghj7YheR', '2134567889', '', 'Attica'),
		('KratHospNi73' , 'Kratiko Nikaias' , 'juRet24fjkk', '2107573997', '', 'Attica');  
       
insert into BloodDonor (B_Name, B_Username,B_email,B_password,Gender,BloodType,SSN,Region)
values ('Alexia Tsakiri' , 'alextsak64' , 'alx.tsak@gmail.com' , 'ffflppo3265' , 'female', 'A-', '12345678908', 'Attica'),
		('Dimitra Chatzistamatiou' , 'dimi345' , 'dimichatzist@gmail.com' , 'gmara444' , 'female', 'A-', '12345678908', 'Attica'),
		('Elena Gkentzi' , 'egkentzi' , 'elena.gkentzi@gmail.com' , 'fdasfdsf123' , 'female', 'A-', '12345678908', 'Attica'),
		('Ioanna Papidaki' , 'iopapi45' , 'jannapapd@gmail.com' , 'harEW42' , 'female', 'O-', '12345678908', 'Peloponnisos'),
		('Maria Konti' , 'jstam8904' , 'mariapll@yahoo.com' , '2139vveW1' , 'female', 'A-', '12345678908', 'Attica'),
		('Rafaela Kasapaki' , 'mary123' , 'rafaelakasapaki@gmail.com' , 'fddfk235' , 'female', 'AB-', '12345678908', 'Attica'),
		('Myrto Charala' , 'mdhfr32' , 'myrtoh78@gmail.com' , 'mfhwef32' , 'female', 'AB-', '16345678908', 'Attica'),
		('Stamatia Papageorgiou' , 'stampap123' , 'stampapag7@gmail.com' , 'gste430' , 'female', 'A-', '12345678908', 'Crete');                
       
    
insert into DonationDays(D_Date, D_Day)
Values ('2019-04-07','World Health Day'),
	('2019-06-14','World Blood Donor Day'),
	('2019-07-28', 'World Hepatitsi Day'),
	('2019-09-26','World Heart Day'),
	('2019-12-01','World AIDS Day'),
	('2020-04-07','World Health Day'),
	('2020-06-14','World Blood Donor Day'),
	('2020-07-28','World Hepatitsi Day'),
	('2020-09-26','World Heart Day'),
	('2020-12-01','World AIDS Day'),
	('2021-04-07','World Health Day'),
	('2021-06-04','World Blood Donor Day'),
	('2021-07-28','World Hepatitsi Day'),
	('2021-09-26','World Heart Day'),
	('2021-12-01','World AIDS Day');
	
insert into BloodBankStock (H_Username, BloodType, Blood)
values 	('AgKurHosp99', 'O+' , 15),
		('AgKurHosp99','O-', 23),
		('AgKurHosp99', 'A+',6),
		('AgKurHosp99', 'A-', 9),
		('AgKurHosp99', 'B+', 2),
		('AgKurHosp99', 'B-', 1),
		('AgKurHosp99', 'AB+', 5),
		('AgKurHosp99', 'AB-', 11),
		('atticahospital12','O+', 13),
		('atticahospital12','O-', 4),
		('atticahospital12','A+', 5),
		('atticahospital12','A-', 3.5),
		('atticahospital12','B+', 2.9),
		('atticahospital12','B-', 11.3),
		('atticahospital12','AB+', 8.1),
		('atticahospital12','AB-', 9),
		('genhosp32', 'O+', 3),
		('genhosp32','O-', 9),
		('genhosp32' ,'A+', 21),
		('genhosp32' ,'A-', 5),
		('genhosp32' ,'B+', 8),
		('genhosp32' ,'B-', 5.9),
		('genhosp32' ,'AB+', 14),
		('genhosp32' ,'AB-', 18),
		('hosPaidon43' ,'O+', 8.9),
		('hosPaidon43' ,'O-', 3.5),
		('hosPaidon43' ,'A+', 5),
		('hosPaidon43' ,'A-', 1),
		('hosPaidon43' ,'B+', 9),
		('hosPaidon43' ,'B-', 4.7),
		('hosPaidon43' ,'AB+', 2.5),
		('hosPaidon43' ,'AB-', 9),
		('KratHospNi73','O+', 19),
		('KratHospNi73','O-', 13),
		('KratHospNi73','A+', 7.4),
		('KratHospNi73','A-', 7.9),
		('KratHospNi73','B+',11),
		('KratHospNi73','B-', 10),
		('KratHospNi73','AB+', 2.5),
		('KratHospNi73','AB-', 12);
	
insert into BloodLimits (H_Username, BloodLimit, BloodType)	
values 		('AgKurHosp99',12.5, 'O+' ),
		('AgKurHosp99',10, 'O-'),
		('AgKurHosp99',5, 'A+'),
		('AgKurHosp99',6, 'A-'),
		('AgKurHosp99',4, 'B+'),
		('AgKurHosp99',3, 'B-'),
		('AgKurHosp99',12, 'AB+'),
		('AgKurHosp99',10, 'AB-'),
		('atticahospital12',15.5,'O+'),
		('atticahospital12',14.5,'O-'),
		('atticahospital12',11,'A+'),
		('atticahospital12',5,'A-'),
		('atticahospital12',7,'B+'),
		('atticahospital12',6,'B-'),
		('atticahospital12',9,'AB+'),
		('atticahospital12',8.5,'AB-'),
		('genhosp32', 15,'O+'),
		('genhosp32', 10.5,'O-'),
		('genhosp32', 12.5,'A+'),
		('genhosp32', 9.5,'A-'),
		('genhosp32', 5,'B+'),
		('genhosp32', 7,'B-'),
		('genhosp32', 16,'AB+'),
		('genhosp32', 12,'AB-'),
		('hosPaidon43' ,14,'O+'),
		('hosPaidon43' ,13,'O-'),
		('hosPaidon43' ,5,'A+'),
		('hosPaidon43' ,7,'A-'),
		('hosPaidon43' ,9,'B+'),
		('hosPaidon43' ,11,'B-'),
		('hosPaidon43' ,10,'AB+'),
		('hosPaidon43' ,13,'AB-'),
		('KratHospNi73' ,10.5,'O+'),
		('KratHospNi73' ,12,'O-'),
		('KratHospNi73' ,5.5,'A+'),
		('KratHospNi73' ,4.5,'A-'),
		('KratHospNi73' ,7.5,'B+'),
		('KratHospNi73' ,6,'B-'),
		('KratHospNi73' ,14,'AB+'),
		('KratHospNi73' ,12,'AB-');     
    
insert into Questionnaire (Q_id, Question)
values 		(1, 'Please insert your gender'),
		(2, 'Please insert your Last Name'),
		(3, 'Please insert your First Name'),
		(4, 'Please insert your Fathers Name'),
		(5, 'Please insert the year you were born'),
		(6, 'Please insert where you were born'),
		(7, 'Please insert your Profession'),
		(8, 'Please insert your ID Number'),
		(9, 'Please insert your Address'),
		(10, 'Please insert your PostCode'),
		(11, 'Please insert your City'),
		(12, 'Please insert your Phone Number'),
		(13, 'Have you ever gave blood before?'),
		(14, 'When was the last time you gave blood? (yyyy/mm/dd)'),
		(15, 'Have you ever been excluded from a blood donation?'),
		(16, 'Do you have any dangerous profession or hobby?'),
		(17, 'Have you had any health problems before?'),
		(18, 'Have you ever had Jaundice or Hepatitis?'),
		(19, 'Have you ever had Syphilis?'),
		(20, 'Have you ever had Malaria?'),
		(21, 'Have you ever had Τuberculosis?'),
		(22, 'Have you ever had Rheumatoid arthritis?'),
		(23, 'Have you ever had Heart disease?'),
		(24, 'Have you ever had Precardiac pain?'),
		(25, 'Have you ever had Hypertension?'),
		(26, 'Have you ever had Convulsions as an adult?'),
		(27, 'Have you ever had fainting?'),
		(28, 'Have you ever had Stomach ailments?'),
		(29, 'Have you ever had Ulcer?'),
		(30, 'Have you ever had other surgeries?'),
		(31, 'Have you ever had Kidney diseases?'),
		(32, 'Have you ever had Diabetes?'), 
		(33, 'Have you ever had Allergies?'),
		(34, 'Have you ever had Anemia?'),
		(35, 'Have you ever had other diseases?'),
		(36, 'Have you ever had contagious diseases in your environment?'),
		(37, 'Have you ever taken medicine?'),
		(38, 'Did you take an aspirin in the last 5 days?'),
		(39, 'Were you born, have you lived or travelled abroad?'),
		(40, 'Did you lose weight, do you have a fever or are your glands swollen?'),
		(41, 'Have you ever had a cornea or hard tunic transplant?'),
		(42, 'Have you heard that your family is in danger of developing the Creutzfeldt–Jakob disease?'),
		(43, 'Have you ever taken growth hormones?'),
		(44, 'Did you have a tooth exctraction or treatment the past week?'),
		(45, 'Have been vaccinated in the past week?'),
		(46, 'Did you have surgery or medical exams this past year?'),
		(47, 'Have you had transfusion of blood or blood products?'),
		(48, 'Do you have tattoos, ear piercings or have you done acupuncture?'),
		(49, 'Have you been pierced by a syringe needle?'),
		(50, 'Do you have any skin wounds or scratches that came in contact with foreign blood?'),
		(51, 'Were you pregnant the past year?');
