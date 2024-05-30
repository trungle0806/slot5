package slot5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import com.google.gson.Gson;

public class DatabaseToJson {
    private String url;
    private String user;
    private String password;

    public DatabaseToJson(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public List<Student> fetchStudentsFromDatabase() {
        List<Student> studentList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Student";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Student student = new Student();
                student.setID(rs.getInt("ID"));
                student.setName(rs.getString("name"));
                student.setAddress(rs.getString("address"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setDOB(rs.getString("DOB"));
                studentList.add(student);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public void writeStudentsToJson(List<Student> studentList, String fileName) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(studentList, writer);
            System.out.println("Data has been written to " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
