import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Jeff Holloway on 5/4/17.
 * CMIS 242
 * Project 4 GUI Class
 */


public class Project4GUI extends JFrame implements ActionListener {

    // Operation
    private static String[] operation = {"Insert", "Delete", "Find", "Update"};
    // Grade Choices
    private static String[] grades = {"A", "B", "C", "D", "F"};
    // Credit options
    private static String[] credit = {"3", "6"};
    private JLabel jLabel1 = new JLabel("Major: ");
    private JLabel jLabel2 = new JLabel("Id: ");
    private JLabel jLabel3 = new JLabel("Name: ");
    private JLabel jLabel4 = new JLabel("Choose Selection: ");
    private JComboBox<String> choose;
    private JTextField enterN = new JTextField(16);
    private JTextField textResult = new JTextField(16);
    private JTextField textStudent = new JTextField(16);
    private JButton computeBut = new JButton("Process Request");
    //This will save the entry
    private HashMap<String, Student> database = new HashMap<>();

    public Project4GUI() throws IOException {
        choose = new JComboBox<>(operation);
        setTitle("Project4");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(6, 2));
        getContentPane().add(jLabel2);
        getContentPane().add(textResult);
        textResult.setEditable(true);
        getContentPane().add(jLabel3);
        getContentPane().add(textStudent);
        getContentPane().add(jLabel1);
        getContentPane().add(enterN);
        getContentPane().add(jLabel4);
        getContentPane().add(choose);
        computeBut.addActionListener(this);
        getContentPane().add(computeBut);
        textStudent.setEditable(true);
        pack();

    }

    public static void main(String[] args) throws IOException {
        Project4GUI project4GUI = new Project4GUI();
        project4GUI.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        // Get ID
        String ID = textResult.getText();

        // Get Name and Major
        String studentName = textStudent.getText();
        String major = enterN.getText();
        Student temp = new Student(studentName, major);

        // Get selected Item
        String selected = choose.getSelectedItem().toString();
        switch (selected) {
            case "Insert":
                if (database.containsKey(ID)) {
                    JOptionPane.showConfirmDialog(null, "Student already present!");
                } else {
                    database.put(ID, temp);
                    JOptionPane.showConfirmDialog(null, "Student inserted");
                }

                break;
            case "Delete":
                if (database.containsKey(ID)) {
                    database.remove(ID);
                    JOptionPane.showConfirmDialog(null, "Student Removed");

                } else {
                    JOptionPane.showConfirmDialog(null, "No record found!");
                }
                break;
            case "Update":
                String gradesType = (String) JOptionPane.showInputDialog(null, "Choose Grades: ", "", JOptionPane.INFORMATION_MESSAGE, null, grades, "check");
                String credits = (String) JOptionPane.showInputDialog(null, "Choose Credit: ", "", JOptionPane.INFORMATION_MESSAGE, null, credit, "check");
                temp.courseCompleted(gradesType.charAt(0), Integer.parseInt(credits));
                database.put(ID, temp);

                break;
            default:
                // Find
                if (database.size() == 0) {
                    JOptionPane.showConfirmDialog(null, "No record found!");
                } else if (database.containsKey(ID) && database.size() > 0) {
                    temp = database.get(ID);
                    JOptionPane.showConfirmDialog(null, temp.toString());
                } else {
                    JOptionPane.showConfirmDialog(null, "No record found!");
                }
                break;
        }

    }
}