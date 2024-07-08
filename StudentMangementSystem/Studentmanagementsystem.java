package util.jdbc.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Studentmanagementsystem {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Step 1: Loading the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Step 2: Establishing a database connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");

        // Step 3: Creating a Statement
        Statement stmt = connection.createStatement();

        // Scanner class to take input from user
        Scanner sc = new Scanner(System.in);

        // Integer input for variable operation
        int operation;

        // Create instances of Student and Project classes
        Student studentManager = new Student(connection);
        project projectManager = new project(connection);
        instructor instructorManager = new instructor(connection);
        course courseManager = new course(connection);

        do {
            // Display menu for CRUD operations
            System.out.println("Available Operations:");
            System.out.println("1. Create Student");
            System.out.println("2. Retrieve Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Create Project");
            System.out.println("6. Retrieve Projects");
            System.out.println("7. Update Project");
            System.out.println("8. Delete Project");
            System.out.println("9. Instructor");
            System.out.println("10. Retrieve Instructor");
            System.out.println("11. Update Instructor");
            System.out.println("12. Delete Instructor");
            System.out.println("13. Create Course");
            System.out.println("14. Retrieve Course");
            System.out.println("15. Delete Course");
            System.out.println("16. Exit");
            // Prompts user to enter an operation number
            System.out.print("Please Enter an Operation number you want to Perform: ");
            operation = sc.nextInt();

            // Consume newline
            sc.nextLine();

            // Use a switch statement to perform different actions based on the user's choice
            switch (operation) {
                case 1:
                    studentManager.createStudent();
                    break;

                case 2:
                    studentManager.retrieveStudents();
                    break;

                case 3:
                    studentManager.updateStudent();
                    break;

                case 4:
                    studentManager.deleteStudent();
                    break;

                case 5:
                    projectManager.createproject();
                    break;

                case 6:
                    projectManager.retrieveproject();
                    break;

                case 7:
                    projectManager.updateproject();
                    break;

                case 8:
                    projectManager.deleteproject();
                    break;
                    
                case 9:
                    instructorManager.createInstructor();
                    break;
                    
                    
                case 10:
                    instructorManager.retrieveInstructor();
                    break;    
                    
                case 11:
                    instructorManager.updateInstructor();
                    break;   
                    
                case 12:
                    instructorManager.deleteInstructor();
                    break; 
                
                case 13:
                    courseManager.createCourse();
                    break; 
                    
                case 14:
                    courseManager.retrieveCourse();
                    break; 
                    
                case 15:
                    courseManager.deleteCourse();
                    break;     

                case 16:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (operation != 16);

        sc.close();
        stmt.close();
        connection.close();
    }
}
