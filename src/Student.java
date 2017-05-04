import java.text.DecimalFormat;

/**
 * Created by jeffholloway on 4/30/17.
 * CMIS 242
 * Student class defines the student record and contains three methods
 */
public class Student {
    private String name;
    private String major;
    private int totalCredit;
    private int quatlityPoint;

    // Constructor used when new student records are created
    public Student(String name, String major) {
        this.name = name;
        this.major = major;
        this.totalCredit = 0;
        this.quatlityPoint = 0;
    }

    // this method accepts the course grade and credit hours and updates variables used to compute GPA
    public void courseCompleted(char grade, int credit) {
        this.totalCredit = credit;
        int value = 0;
        if (grade == 'A') {
            value = 10;
        } else if (grade == 'B') {
            value = 8;
        } else if (grade == 'C') {
            value = 6;
        } else if (grade == 'D') {
            value = 4;
        } else if (grade == 'F') {
            value = 2;
        }
        this.quatlityPoint = value * credit;
    }

    // this method overrides the toString and returns a labeled string containing the student name, major, and GPA
    @Override
    public String toString() {
        double gpa = 0;
        if (totalCredit == 0) {
            gpa = 4.0;
        } else {
            gpa = (quatlityPoint / (totalCredit * 10.0)) * 4.0;
        }
        DecimalFormat df = new DecimalFormat("#.0#");
        return "Name : " + name + "\n Major : " + major + "\n GPA : " + df.format(gpa);

    }
}
