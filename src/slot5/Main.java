package slot5;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/addressbookdb";
        String user = "yourusername";  // Thay đổi thành tên người dùng của bạn
        String password = "yourpassword";  // Thay đổi thành mật khẩu của bạn

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        DatabaseToJson databaseToJson = new DatabaseToJson(url, user, password);
        List<Student> studentList = databaseToJson.fetchStudentsFromDatabase();
        databaseToJson.writeStudentsToJson(studentList, "students.json");

        JsonManager jsonManager = new JsonManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add new student");
            System.out.println("2. Display all students");
            System.out.println("3. Search by name");
            System.out.println("4. Search by email");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    jsonManager.addStudent(scanner);
                    break;
                case 2:
                    jsonManager.displayAllStudents();
                    break;
                case 3:
                    jsonManager.searchByName(scanner);
                    break;
                case 4:
                    jsonManager.searchByEmail(scanner);
                    break;
                case 5:
                    jsonManager.saveData();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
