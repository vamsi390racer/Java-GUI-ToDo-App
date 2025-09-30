import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

/**
 * Task 6: Java GUI - ToDo App
 * Objective: Build a functional To-Do list using Java Swing, demonstrating the use of
 * JFrame, JButton, JTextField, JList, and event handling (ActionListener).
 */
public class TodoApp extends JFrame implements ActionListener {

    // --- GUI Components ---
    // A JList is ideal for displaying a selectable list of items (tasks).
    private JList<String> taskList;

    // DefaultListModel is used to manage the data displayed by the JList.
    private DefaultListModel<String> listModel;

    // JTextField for user input (new task description).
    private JTextField taskInput;

    // JButtons for actions.
    private JButton addButton;
    private JButton deleteButton;

    // --- Constants for clean code ---
    private static final String ADD_ACTION = "Add";
    private static final String DELETE_ACTION = "Delete";

    /**
     * Constructor for the ToDoApp GUI setup.
     */
    public TodoApp() {
        // 1. Setup the main JFrame
        super("Swing To-Do List by Elevate Labs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall structure

        // 2. Initialize the List Model and JList
        listModel = new DefaultListModel<>();
        // Add a couple of initial items for demonstration
        listModel.addElement("Example: Learn Java Swing");
        listModel.addElement("Example: Submit Task 6");

        taskList = new JList<>(listModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Wrap the JList in a JScrollPane to allow scrolling when the list grows.
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Tasks"));

        // 3. Initialize Input Panel (at the bottom)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5)); // BorderLayout for input field and buttons

        taskInput = new JTextField(30);
        taskInput.setFont(new Font("SansSerif", Font.PLAIN, 16));

        addButton = new JButton("Add Task");
        addButton.setActionCommand(ADD_ACTION);
        addButton.addActionListener(this); // Register the listener
        addButton.setBackground(new Color(60, 179, 113)); // Greenish color
        addButton.setForeground(Color.WHITE);

        // Add components to the input panel
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // 4. Initialize Controls Panel (for the delete button)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deleteButton = new JButton("Delete Selected Task");
        deleteButton.setActionCommand(DELETE_ACTION);
        deleteButton.addActionListener(this); // Register the listener
        deleteButton.setBackground(new Color(220, 20, 60)); // Reddish color
        deleteButton.setForeground(Color.WHITE);
        controlPanel.add(deleteButton);

        // 5. Add all panels to the main JFrame
        // North: Title/Header (optional, but good practice)
        JLabel header = new JLabel("To-Do Application", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        // Center: The scrollable task list
        add(scrollPane, BorderLayout.CENTER);

        // South: Input area (top) and control buttons (bottom)
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(inputPanel, BorderLayout.NORTH);
        southPanel.add(controlPanel, BorderLayout.SOUTH);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(southPanel, BorderLayout.SOUTH);

        // Final settings
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    /**
     * Handles all button clicks based on their ActionCommand.
     * This method implements the ActionListener interface.
     * @param e The event that occurred (a button click).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (ADD_ACTION.equals(command)) {
            addNewTask();
        } else if (DELETE_ACTION.equals(command)) {
            deleteSelectedTask();
        }
    }

    /**
     * Logic for adding a new task from the JTextField.
     */
    private void addNewTask() {
        String task = taskInput.getText().trim();
        
        // Input validation: Only add if the input field is not empty.
        if (!task.isEmpty()) {
            listModel.addElement(task); // Add the task to the model
            taskInput.setText("");      // Clear the input field
        } else {
            // Use JOptionPane for message boxes (instead of alert())
            JOptionPane.showMessageDialog(this,
                    "Please enter a task description.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Logic for deleting the selected task from the JList.
     */
    private void deleteSelectedTask() {
        // Get the index of the currently selected item.
        int selectedIndex = taskList.getSelectedIndex();

        // Check if an item is actually selected and the model is not empty.
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex); // Remove the task from the model
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a task to delete.",
                    "Deletion Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method to start the application.
     * Swing applications MUST be run on the Event Dispatch Thread (EDT) for thread safety.
     */
    public static void main(String[] args) {
        // SwingUtilities.invokeLater ensures that the GUI creation is done on the EDT.
        SwingUtilities.invokeLater(TodoApp::new);
    }
}
