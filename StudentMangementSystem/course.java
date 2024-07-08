package util.jdbc.com;

import java.sql.*;
import java.util.Scanner;

public class course {
    private Connection connection;

    public course(Connection connection) {
        this.connection = connection;
    }

    public void createCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Course name: ");
        String course_name = sc.nextLine();
       

        String createSql = "INSERT INTO Course (course_name) VALUES (?)";
        try (PreparedStatement createStatement = connection.prepareStatement(createSql)) {
            createStatement.setString(1, course_name);
           
            int rowsAffected = createStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course added successfully.");
            } else {
                System.out.println("Failed to add course.");
            }
        } catch (SQLException e) {
            System.out.println("Error creating course: " + e.getMessage());
        }
    }

    public void retrieveCourse() {
        String readSql = "SELECT * FROM Course";
        try (Statement readStatement = connection.createStatement();
             ResultSet resultSet = readStatement.executeQuery(readSql)) {

            while (resultSet.next()) {
                int course_id = resultSet.getInt("course_id");
                String course_name = resultSet.getString("course_name");
                

                System.out.println("Course ID: " + course_id);
                System.out.println("Course Name: " + course_name);
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving course: " + e.getMessage());
        }
    }

   

    public void deleteCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course ID to delete: ");
        int course_id = sc.nextInt();

        String deleteSql = "DELETE FROM Course WHERE course_id = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, course_id);

            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("Failed to delete course.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }
}
