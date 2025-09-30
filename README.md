Java Developer Internship - Task 6: Swing To-Do App
Objective: Build a simple, functional To-Do list application using Java Swing.

Features
This application implements the required functionality using the Java Swing library:

Add Task: Users can type a task into the text field and click the "Add Task" button to append it to the list.

Delete Task: Users can select a single task from the list and click the "Delete Selected Task" button to remove it.

Scroll Support: The task list is wrapped in a JScrollPane to automatically handle vertical scrolling when the number of tasks exceeds the visible area.

Key Concepts Demonstrated
GUI Framework: The application uses the lightweight, platform-independent Swing library (javax.swing.*).

Main Window: The top-level container is a JFrame.

Components: Standard building blocks used include JTextField (for input), JButton (for actions), JList (to display tasks), and JLabel (for the title).

Data Model: A DefaultListModel<String> is used to store and manage the list data separately from the view, which follows a basic MVC (Model-View-Controller) pattern.

Event Handling: The TodoApp class implements the ActionListener interface to respond to button click events (ActionEvent).

Layout Management: Components are organized using BorderLayout and FlowLayout to ensure a structured and adaptive layout.

Thread Safety: SwingUtilities.invokeLater() is used to ensure all GUI initialization is executed safely on the Event Dispatch Thread (EDT).

How to Run
Save the code as TodoApp.java.

Compile the file: javac TodoApp.java

Run the application: java TodoApp
