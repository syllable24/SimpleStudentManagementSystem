-- Zeichensatz beachten

LOAD DATA LOCAL INFILE '/home/ralph/Documents/FH/SWE02/WebApp-Git/SimpleStudentManagementSystem/StudentManager/DB-Data/Gruppen.csv'
INTO TABLE TestSchema.Gruppen FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/home/ralph/Documents/FH/SWE02/WebApp-Git/SimpleStudentManagementSystem/StudentManager/DB-Data/Kurse.csv'
INTO TABLE TestSchema.Kurse FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/home/ralph/Documents/FH/SWE02/WebApp-Git/SimpleStudentManagementSystem/StudentManager/DB-Data/Lehrer.csv'
INTO TABLE TestSchema.Lehrer FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/home/ralph/Documents/FH/SWE02/WebApp-Git/SimpleStudentManagementSystem/StudentManager/DB-Data/LogIns.csv'
INTO TABLE TestSchema.LogIns FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/home/ralph/Documents/FH/SWE02/WebApp-Git/SimpleStudentManagementSystem/StudentManager/DB-Data/Nutzerrechte.csv'
INTO TABLE TestSchema.Nutzerrechte FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/home/ralph/Documents/FH/SWE02/WebApp-Git/SimpleStudentManagementSystem/StudentManager/DB-Data/Studenten.csv'
INTO TABLE TestSchema.Studenten FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE '/home/ralph/Documents/FH/SWE02/WebApp-Git/SimpleStudentManagementSystem/StudentManager/DB-Data/Studiengaenge.csv'
INTO TABLE TestSchema.Studiengaenge FIELDS TERMINATED BY ','
ENCLOSED BY '"' LINES TERMINATED BY '\n';


