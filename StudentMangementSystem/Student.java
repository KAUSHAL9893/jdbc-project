package util.jdbc.com;

import java.sql.*;
import java.util.Scanner;

public class Student {
    private Connection connection;

    public Student(Connection connection) {
        this.connection = connection;
    }

    public void createStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter student last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter student course: ");
        String course = sc.nextLine();
        System.out.print("Enter student year: ");
        int year = sc.nextInt();
        sc.nextLine();  // Consume newline
        System.out.print("Enter student contact: ");
        String contact = sc.nextLine();
        System.out.print("Enter student birthdate (YYYY-MM-DD): ");
        String birthdate = sc.nextLine();
        System.out.print("Enter student gender (M/F): ");
        String gender = sc.nextLine();
        System.out.print("Enter student stage: ");
        String stage = sc.nextLine();
        System.out.print("Course ID: ");
        int course_id = sc.nextInt();
        

        String createSql = "INSERT INTO Student (Stfname, Stlname, Stcourse, Styear, Stcontact, Stbirthdate, Stgender, stage, course_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement createStatement = connection.prepareStatement(createSql)) {
            createStatement.setString(1, firstName);
            createStatement.setString(2, lastName);
            createStatement.setString(3, course);
            createStatement.setInt(4, year);
            createStatement.setString(5, contact);
            createStatement.setString(6, birthdate);
            createStatement.setString(7, gender);
            createStatement.setString(8, stage);
            createStatement.setInt(9, course_id);

            int rowsAffected = createStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Failed to add student.");
            }
        }
    }

    public void retrieveStudents() throws SQLException {
        String readSql = "SELECT * FROM Student";
        try (Statement readStatement = connection.createStatement(); ResultSet resultSet = readStatement.executeQuery(readSql)) {
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String studentFirstName = resultSet.getString("Stfname");
                String studentLastName = resultSet.getString("Stlname");
                String studentCourse = resultSet.getString("Stcourse");
                int studentYear = resultSet.getInt("Styear");
                String studentContact = resultSet.getString("Stcontact");
                String studentBirthdate = resultSet.getString("Stbirthdate");
                String studentGender = resultSet.getString("Stgender");
                String studentStage = resultSet.getString("stage");
                int course_id = resultSet.getInt("course_id");

                System.out.println("ID: " + studentId + ", Name: " + studentFirstName + " " + studentLastName +
                        ", Course: " + studentCourse + ", Year: " + studentYear + ", Contact: " + studentContact +
                        ", Birthdate: " + studentBirthdate + ", Gender: " + studentGender + ", Stage: " + studentStage + ", course_id: " + course_id);
            }
        }
    }

    public void updateStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID to update: ");
        int studentIdToUpdate = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.println("Choose what to update:");
        System.out.println("1. Update name");
        System.out.println("2. Update course");
        System.out.println("3. Update year");
        System.out.println("4. Update contact");
        System.out.println("5. Update birthdate");
        System.out.println("6. Update gender");
        System.out.println("7. Update stage");
        System.out.println("8. Update course ID");
        System.out.print("Enter your choice: ");
        int updateChoice = sc.nextInt();
        sc.nextLine();  

        String updateSql;
        PreparedStatement updateStatement;

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new first name: ");
                String newFirstName = sc.nextLine();
                System.out.print("Enter new last name: ");
                String newLastName = sc.nextLine();

                updateSql = "UPDATE Student SET Stfname = ?, Stlname = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, newFirstName);
                updateStatement.setString(2, newLastName);
                updateStatement.setInt(3, studentIdToUpdate);
                break;

            case 2:
                System.out.print("Enter new course: ");
                String newCourse = sc.nextLine();

                updateSql = "UPDATE Student SET Stcourse = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, newCourse);
                updateStatement.setInt(2, studentIdToUpdate);
                break;

            case 3:
                System.out.print("Enter new year: ");
                int newYear = sc.nextInt();
                sc.nextLine();  

                updateSql = "UPDATE Student SET Styear = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setInt(1, newYear);
                updateStatement.setInt(2, studentIdToUpdate);
                break;

            case 4:
                System.out.print("Enter new contact: ");
                String newContact = sc.nextLine();

                updateSql = "UPDATE Student SET Stcontact = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, newContact);
                updateStatement.setInt(2, studentIdToUpdate);
                break;

            case 5:
                System.out.print("Enter new birthdate (YYYY-MM-DD): ");
                String newBirthdate = sc.nextLine();

                updateSql = "UPDATE Student SET Stbirthdate = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, newBirthdate);
                updateStatement.setInt(2, studentIdToUpdate);
                break;

            case 6:
                System.out.print("Enter new gender (M/F): ");
                String newGender = sc.nextLine();

                updateSql = "UPDATE Student SET Stgender = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, newGender);
                updateStatement.setInt(2, studentIdToUpdate);
                break;

            case 7:
                System.out.print("Enter new stage: ");
                String newStage = sc.nextLine();

                updateSql = "UPDATE Student SET stage = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, newStage);
                updateStatement.setInt(2, studentIdToUpdate);
                break;
                
            case 8:
                System.out.print("Enter new course ID: ");
                int course_id = sc.nextInt();
                sc.nextLine();  

                updateSql = "UPDATE Student SET course_id = ? WHERE student_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setInt(1, course_id);
                updateStatement.setInt(2, studentIdToUpdate);
                break;
    

            default:
                System.out.println("Invalid choice for update. Please try again.");
                return;
        }

        int rowsAffected = updateStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found or update failed.");
        }
    }

    public void deleteStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID to delete: ");
        int studentIdToDelete = sc.nextInt();

        String deleteSql = "DELETE FROM Student WHERE student_id = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, studentIdToDelete);

            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Failed to delete student.");
            }
        }
    }
}
