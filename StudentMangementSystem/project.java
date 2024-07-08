package util.jdbc.com;


import java.sql.*;
import java.util.Scanner;

public class project {
    private Connection connection;

    public project(Connection connection) {
        this.connection = connection;
    }

    public void createproject() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter project name: ");
        String projectName = sc.nextLine();
        System.out.print("Enter project technology ");
        String topic = sc.nextLine();
        System.out.print("Enter project date (YYYY-MM-DD): ");
        String pdate = sc.nextLine();
        System.out.print("Enter student ID: ");
        int student_id = sc.nextInt();
        

        String createSql = "INSERT INTO project (project_name, Technology, pdate, student_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement createStatement = connection.prepareStatement(createSql)) {
            createStatement.setString(1, projectName);
            createStatement.setString(2, topic);
            createStatement.setDate(3,  Date.valueOf(pdate));
            createStatement.setInt(4,  student_id);
            

            int rowsAffected = createStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Project added successfully.");
            } else {
                System.out.println("Failed to add project.");
            }
        }
    }

    public void retrieveproject() throws SQLException {
        String readSql = "SELECT * FROM project";
        try (Statement readStatement = connection.createStatement(); ResultSet resultSet = readStatement.executeQuery(readSql)) {
            while (resultSet.next()) {
                int  project_id = resultSet.getInt("project_id");
                String projectName = resultSet.getString("project_name");
                String topic = resultSet.getString("Technology");
                Date pdate  = resultSet.getDate("pdate");
                int student_id = resultSet.getInt("student_id");

                System.out.println("Project ID: " + project_id + ", Project Name: " + projectName +", Technology: " + topic + ", Date: " + pdate + ", Student ID: " + student_id );
            }
        }
    }

    public void updateproject() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter project ID to update: ");
        int project_id = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.println("Choose what to update:");
        System.out.println("1. Update project name");
        System.out.println("2. Update technology");
        System.out.println("3. Update date");
        System.out.println("4. Update student ID");
        System.out.print("Enter your choice: ");
        int updateChoice = sc.nextInt();
        sc.nextLine();  

        String updateSql;
        PreparedStatement updateStatement;

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new project name: ");
                String projectName = sc.nextLine();
               

                updateSql = "UPDATE project SET project_name = ? WHERE project_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, projectName);
                updateStatement.setInt(2, project_id);
                break;

            case 2:
                System.out.print("Enter new topic: ");
                String topic = sc.nextLine();

                updateSql = "UPDATE Student SET Technology = ? WHERE project_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setString(1, topic);
                updateStatement.setInt(2, project_id);
                break;

            case 3:
                System.out.print("Enter new date: ");
                int pdate = sc.nextInt();
                sc.nextLine();  

                updateSql = "UPDATE project SET pdate = ? WHERE project_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setInt(1,  pdate);
                updateStatement.setInt(2, project_id);
                break;

        
            case 4:
                System.out.print("Enter new student ID: ");
                int student_id = sc.nextInt();
                sc.nextLine(); 

                updateSql = "UPDATE Student SET student_id = ? WHERE project_id = ?";
                updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setInt(1, student_id);
                updateStatement.setInt(2, project_id);
                break;
    

            default:
                System.out.println("Invalid choice for update. Please try again.");
                return;
        }

        int rowsAffected = updateStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Project updated successfully.");
        } else {
            System.out.println("Project not found or update failed.");
        }
    }

    public void deleteproject() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter project ID to delete: ");
        int project_id = sc.nextInt();

        String deleteSql = "DELETE FROM project WHERE project_id = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, project_id);

            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Project deleted successfully.");
            } else {
                System.out.println("Failed to delete project.");
            }
        }
    }
}

