package slot5;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonManager {
    private List<Student> studentList;
    private static final String JSON_FILE = "students.json";
    private Gson gson = new Gson();

    public JsonManager() {
        try {
            File file = new File(JSON_FILE);
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                Type studentListType = new TypeToken<List<Student>>() {}.getType();
                studentList = gson.fromJson(reader, studentListType);
                reader.close();
            } else {
                studentList = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Scanner scanner) {
        Student student = new Student();
        System.out.print("Enter ID: ");
        student.setID(scanner.nextInt());
        scanner.nextLine(); // consume newline
        System.out.print("Enter name: ");
        student.setName(scanner.nextLine());
        System.out.print("Enter address: ");
        student.setAddress(scanner.nextLine());
        System.out.print("Enter email: ");
        student.setEmail(scanner.nextLine());
        System.out.print("Enter phone: ");
        student.setPhone(scanner.nextLine());
        System.out.print("Enter DOB (YYYY-MM-DD): ");
        student.setDOB(scanner.nextLine());

        studentList.add(student);
        System.out.println("Student added.");
    }

    public void displayAllStudents() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void searchByName(Scanner scanner) {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        for (Student student : studentList) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println(student);
            }
        }
    }

    public void searchByEmail(Scanner scanner) {
        System.out.print("Enter email to search: ");
        String email = scanner.nextLine();
        for (Student student : studentList) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                System.out.println(student);
            }
        }
    }

    public void saveData() {
        try {
            FileWriter writer = new FileWriter(JSON_FILE);
            gson.toJson(studentList, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
