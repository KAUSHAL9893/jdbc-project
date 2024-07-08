package util.jdbc.com;


import java.sql.*;
import java.util.Scanner;

public class instructor {
    private Connection connection;

    public instructor(Connection connection) {
        this.connection = connection;
    }

    public void createInstructor() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter instructor first name: ");
        String first_name = sc.nextLine();
        System.out.print("Enter instructor last name: ");
        String last_name = sc.nextLine();
        System.out.print("Enter instructor course");
        String instructor_course = sc.nextLine();
        System.out.print("Enter course ID: ");
        int course_id = sc.nextInt();
        

        String createSql = "INSERT INTO instructor (first_name, last_name, instructor_course, course_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement createStatement = connection.prepareStatement(createSql)) {
            createStatement.setString(1, first_name);
            createStatement.setString(2, last_name);
            createStatement.setString(3,  instructor_course);
            createStatement.setInt(4,  course_id);
            

            int rowsAffected = createStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Instructor added successfully.");
            } else {
                System.out.println("Failed to add instructor.");
            }
        }
    }

    public void retrieveInstructor() throws SQLException {
        String readSql = "SELECT * FROM instructor";
        try (Statement readStatement = connection.createStatement(); ResultSet resultSet = readStatement.executeQuery(readSql)) {
            while (resultSet.next()) {
                int  instructor_id = resultSet.getInt("instructor_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String instructor_course = resultSet.getString("instructor_course");
                int course_id = resultSet.getInt("course_id");

                System.out.println("Instructor ID: " + instructor_id + ", Instructor Name: " + first_name +" " + last_name + ", Instructor course: " + instructor_course + ", Course ID: " + course_id );
            }
        }
    }

    public void updateInstructor() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter instructor ID to update: ");
        int instructor_id = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.println("Choose what to update:");
        System.out.println("1. Update instructor name");
        System.out.println("2. Update course");
        System.out.println("4. Update course ID");
        System.out.print("Enter your choice: ");
        int updateChoice = sc.nextInt();
        sc.nextLine();  

        String updateSql;
        PreparedStatement updateStatement;

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new instructor first name: ");
                String first_name = sc.nextLine();
                System.out.print("Enter new instructor last name: ");
                String last_name = sc.nextLine();
               

                updateSql = "UPDATE instructor SET first_name = ?, last_name = ? WHERE instructor_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, first_name);
                updateStatement.setString(2, last_name);
                updateStatement.setInt(3, instructor_id);
                break;

            case 2:
                System.out.print("Enter new course: ");
                String instructor_course = sc.nextLine();

                updateSql = "UPDATE instructor SET instructor_course = ? WHERE instructor_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, instructor_course);
                updateStatement.setInt(2, instructor_id);
                break;

            

        
            case 3:
                System.out.print("Enter new course ID: ");
                int course_id = sc.nextInt();
                sc.nextLine();  

                updateSql = "UPDATE instructor SET course_id = ? WHERE instructor_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setInt(1, course_id);
                updateStatement.setInt(2, instructor_id);
                break;
    

            default:
                System.out.println("Invalid choice for update. Please try again.");
                return;
        }

        int rowsAffected = updateStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Instructor updated successfully.");
        } else {
            System.out.println("Instructor not found or update failed.");
        }
    }

    public void deleteInstructor() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter instructor ID to delete: ");
        int instructor_id = sc.nextInt();

        String deleteSql = "DELETE FROM instructor WHERE instructor_id = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, instructor_id);

            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Instructor deleted successfully.");
            } else {
                System.out.println("Failed to delete instructor.");
            }
        }
    }
}

