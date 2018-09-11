OVERVIEW
This project is a demo for a full-stack Maven web application using Spring Boot, MySQL, Thymeleaf template engine

The final product includes: (see screenshots below)
	A welcome page
	A login page where users have to provide credentials (stored in mysql database) to proceed
	A search page where users can search for singer or album (stored in mysql database)
---------------------------------------------------------------------------------------

ENVIROMENT SETUP
1. Download mysql https://dev.mysql.com/downloads/mysql/5.7.html
2. Download mysql installer https://dev.mysql.com/downloads/installer/ (make sure it's the same mysql version as above) follow instructions on the screen to create root password. Add one user named "ng_user" password "ngng"
3. Open command prompt, cd to \mysql-5.7.x-winx64\bin, > mysql -u ng_user -p to login (if you encounter problem, login as root once using MySQL Command Line Client)
4. > create database ng;
5. > use ng;
6. run 3 scripts in the create_tables.sql
7. > quit
8. Install the latests Maven (http://maven.apache.org/download.cgi)
9. Install the latest Eclipse (http://www.eclipse.org/)
10. Make sure your Eclipse has Maven installed (It should have been included in the latest version of the Eclipse)
11. Download mysql-connector jar https://dev.mysql.com/downloads/connector/j/5.1.html
12. Copy and paste ng_users.txt ng_singers.txt ng_albums.txt into C:\ProgramData\MySQL\MySQL Server 5.7\Data\ng
--------------------------------------------------------------------------------------

IMPORTING THE PROJECT INTO ECLIPSE

1. File->Import->Maven->Existing Maven Projects
2. Select the directory containing the pom.xml file
3. Finish
4. Right click on the project > buildpath > configure buildpath > choose libraries tab and remove the mysql-connector jar, then add the jar that you downloaded in step 11 of Envinroment setup
--------------------------------------------------------------------------------------

BUILDING THE PROJECT

1. Right-click on the project root folder->Maven->Update Project
--------------------------------------------------------------------------------------

RUNNING THE PROJECT

1. Locate the App.java in src/main/java source folder and right-click on it->Run As->Java Application
2. Verify the running process in your web browser by the following URLs:

- http://localhost:8080/

-------------------------------------------------------------------------------------
Notes: If you encounter SQLException: The MySQL server is running with the --secure-file-priv ...
go to C:\ProgramData\MySQL\MySQL Server 5.7\my.ini, search for secure-file-priv, then set it's value to ""



![Alt text](nextgate-music/screenshots/homePage.jpg)

![Alt text](nextgate-music/screenshots/loginPage.jpg)

![Alt text](nextgate-music/screenshots/searchResultsPage.jpg)
