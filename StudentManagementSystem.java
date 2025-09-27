import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Student class to hold student information
class Student {
    String name;
    String id;
    ArrayList<String> courses;
    ArrayList<Integer> grades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
}

// Main class for the Student Management System
public class StudentManagementSystem extends JFrame {
    private JTextField studentNameField;
    private JTextField studentIdField;
    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> studentComboBox;
    private JTextField gradeField;
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        createComponents();

        // Add components to frame
        add(createInputPanel(), BorderLayout.NORTH);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);
        add(createEnrollmentPanel(), BorderLayout.SOUTH);
    }

    private void createComponents() {
        // Input fields for adding students
        studentNameField = new JTextField(15);
        studentIdField = new JTextField(15);

        // Table for displaying students
        studentTableModel = new DefaultTableModel(new String[]{"Name", "ID"}, 0);
        studentTable = new JTable(studentTableModel);

        // Course selection
        courseComboBox = new JComboBox<>(new String[]{"Math", "Science", "History"});

        // Student selection for enrollment and grading
        studentComboBox = new JComboBox<>();

        // Grade input
        gradeField = new JTextField(5);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Student Name:"));
        panel.add(studentNameField);
        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new AddStudentAction());
        panel.add(addButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(new UpdateStudentAction());
        panel.add(updateButton);

        JButton viewButton = new JButton("View Students");
        viewButton.addActionListener(new ViewStudentsAction());
        panel.add(viewButton);

        return panel;
    }

    private JPanel createEnrollmentPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Course:"));
        panel.add(courseComboBox);

        panel.add(new JLabel("Select Student:"));
        panel.add(studentComboBox);

        JButton enrollButton = new JButton("Enroll Student");
        enrollButton.addActionListener(new EnrollStudentAction());
        panel.add(enrollButton);

        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);

        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(new AssignGradeAction());
        panel.add(assignGradeButton);

        return panel;
    }

    private class AddStudentAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = studentNameField.getText();
            String id = studentIdField.getText();
            if (name.isEmpty() || id.isEmpty()) {
                showError("Please enter both name and ID.");
                return;
            }
            Student student = new Student(name, id);
            students.add(student);
            studentTableModel.addRow(new Object[]{name, id});
            studentComboBox.addItem(name);
            studentNameField.setText("");
            studentIdField.setText("");
            JOptionPane.showMessageDialog(null, "Student added successfully!");
        }
    }

    private class UpdateStudentAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                showError("Please select a student to update.");
                return;
            }
            String newName = studentNameField.getText();
            String newId = studentIdField.getText();
            if (newName.isEmpty() || newId.isEmpty()) {
                showError("Please enter both name and ID.");
                return;
            }
            students.get(selectedRow).name = newName;
            students.get(selectedRow).id = newId;
            studentTableModel.setValueAt(newName, selectedRow, 0);
            studentTableModel.setValueAt(newId, selectedRow, 1);
            studentComboBox.removeItemAt(selectedRow);
            studentComboBox.insertItemAt(newName, selectedRow);
            studentNameField.setText("");
            studentIdField.setText("");
            JOptionPane.showMessageDialog(null, "Student updated successfully!");
        }
    }

    private class ViewStudentsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            studentTableModel.setRowCount(0);
            for (Student student : students) {
                studentTableModel.addRow(new Object[]{student.name, student.id});
            }
        }
    }

    private class EnrollStudentAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStudentName = (String) studentComboBox.getSelectedItem();
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            if (selectedStudentName == null || selectedCourse == null) {
                showError("Please select a student and a course.");
                return;
            }
            for (Student student : students) {
                if (student.name.equals(selectedStudentName)) {
                    student.courses.add(selectedCourse);
                    JOptionPane.showMessageDialog(null, "Student enrolled in " + selectedCourse);
                    break;
                }
            }
        }
    }

    private class AssignGradeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedStudentName = (String) studentComboBox.getSelectedItem();
            String gradeText = gradeField.getText();
            if (selectedStudentName == null || gradeText.isEmpty()) {
                showError("Please select a student and enter a grade.");
                return;
            }
            try {
                int grade = Integer.parseInt(gradeText);
                for (Student student : students) {
                    if (student.name.equals(selectedStudentName)) {
                        student.grades.add(grade);
                        JOptionPane.showMessageDialog(null, "Grade assigned successfully!");
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                showError("Please enter a valid grade.");
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem sms = new StudentManagementSystem();
            sms.setVisible(true);
        });
    }
}
