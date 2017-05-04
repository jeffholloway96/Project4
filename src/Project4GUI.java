import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Jeff Holloway on 4/30/17.
 */


public class Project4GUI extends JFrame implements ActionListener {

    // Operation
    private static String[] operation = {"Insert", "Delete", "Find", "Update"};
    private static JOptionPane chooseGrade;
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
    private JTextField textEfficiency = new JTextField(16);
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
        //jLabel2.setText("Id: ");
        getContentPane().add(jLabel2);
        getContentPane().add(textResult);
        textResult.setEditable(true);
        //jLabel3.setText("Name: ");
        getContentPane().add(jLabel3);
        getContentPane().add(textEfficiency);
        // jLabel1.setText("Major: ");
        getContentPane().add(jLabel1);
        getContentPane().add(enterN);
        //jLabel4.setText("Choose Selection: ");
        getContentPane().add(jLabel4);
        getContentPane().add(choose);
        //computeBut.setText("Process Request");
        computeBut.addActionListener(this);
        getContentPane().add(computeBut);
        textEfficiency.setEditable(true);
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
        String studentName = textEfficiency.getText();
        String major = enterN.getText();
        Student temp = new Student(studentName, major);

        // Get selected Item
        String selected = choose.getSelectedItem().toString();
        switch (selected) {
            case "Insert":
                if (database.containsKey(ID)) {
                    JOptionPane.showConfirmDialog(null, "Student already present");
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
                    JOptionPane.showConfirmDialog(null, "Invalid Delete entry!");
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
                    JOptionPane.showConfirmDialog(null, "No record found");
                } else if (database.containsKey(ID) && database.size() > 0) {
                    temp = database.get(ID);
                    JOptionPane.showConfirmDialog(null, temp.toString());
                } else {
                    JOptionPane.showConfirmDialog(null, "No record found");
                }
                break;
        }

    }
}