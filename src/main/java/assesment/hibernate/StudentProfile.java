package assesment.hibernate;

import org.hibernate.Session;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class StudentProfile extends JFrame {
    private JTextField nameField, ageField, idField, newNameField;
    private JTextArea textArea;

    // Dark blue color
    private static final Color DARK_BLUE = new Color(21, 67, 96);
    
 // Set the font to Georgia
    private static final Font GEORGIA_FONT = new Font("Georgia", Font.PLAIN, 14);
 
    public StudentProfile() {
        setTitle("Library Management System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center window

        // Creating Input Fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Creating input labels and text fields with placeholder text
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(GEORGIA_FONT);  // Set Georgia font
        nameField = createPlaceholderField("Enter user's name");
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(GEORGIA_FONT);
        ageField = createPlaceholderField("Enter user's age");
        JLabel idLabel = new JLabel("ID (for Update/Delete):");
        idLabel.setFont(GEORGIA_FONT);
        idField = createPlaceholderField("Enter user ID");
        JLabel newNameLabel = new JLabel("New Name (for Update):");
        newNameLabel.setFont(GEORGIA_FONT);
        newNameField = createPlaceholderField("Enter new name");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(newNameLabel);
        inputPanel.add(newNameField);

        // Creating Buttons with dark blue color and Georgia font
        JButton insertButton = createStyledButton("Insert", "icons/insert.png");
        JButton deleteButton = createStyledButton("Delete", "icons/delete.png");
        JButton updateButton = createStyledButton("Update", "icons/update.png");
        JButton viewButton = createStyledButton("View", "icons/view.png");

        inputPanel.add(insertButton);
        inputPanel.add(deleteButton);
        inputPanel.add(updateButton);
        inputPanel.add(viewButton);
        
        // Creating text area to display results with custom formatting and Georgia font
        textArea = new JTextArea(10, 40);
        textArea.setFont(GEORGIA_FONT);  // Set Georgia font
        textArea.setEditable(false);
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        JScrollPane scrollPane = new JScrollPane(textArea);

        // Adding components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button actions
        insertButton.addActionListener(e -> {
            String name = nameField.getText();
            String ageText = ageField.getText();
            if (!name.isEmpty() && !ageText.isEmpty()) {
                try {
                    int age = Integer.parseInt(ageText);
                    insertUser(name, age);
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Name and Age cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            String idText = idField.getText();
            if (!idText.isEmpty()) {
                int userId = Integer.parseInt(idText);
                deleteUser(userId);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            String idText = idField.getText();
            String newName = newNameField.getText();
            if (!idText.isEmpty() && !newName.isEmpty()) {
                int userId = Integer.parseInt(idText);
                updateUser(userId, newName);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID and new name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        viewButton.addActionListener(e -> {
        	List<Students> users = viewAllUsers();
            textArea.setText(""); // Clear previous content
            textArea.append(String.format("%-5s %-20s %-5s\n", "ID", "Name", "Age"));
            textArea.append("-------------------------------\n");
            for (Students user : users) {
                textArea.append(String.format("%-5s %-20s %-5s\n", user.getId(), user.getName(), user.getAge()));
            }
        });
    }

    private List<Students> viewAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Students", Students.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void updateUser(int userId, String newName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Students user = session.get(Students.class, userId);
            if (user != null) {
                user.setName(newName);
                session.update(user);
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "User updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating user!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


	private void deleteUser(int userId) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        session.beginTransaction();
	        Students user = session.get(Students.class, userId);
	        if (user != null) {
	            session.delete(user);
	            session.getTransaction().commit();
	            JOptionPane.showMessageDialog(this, "User deleted successfully!");
	        } else {
	            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error deleting user!", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void insertUser(String name, int age) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        session.beginTransaction();
	        Students user = new Students();
	        user.setName(name);
	        user.setAge(age);
	        session.save(user);
	        session.getTransaction().commit();
	        JOptionPane.showMessageDialog(this, "User added successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error adding user!", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	// Method to create a JButton with Georgia font and dark blue color
    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath));
        button.setFont(new Font("Georgia", Font.BOLD, 14));  // Set Georgia font
        button.setBackground(DARK_BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        button.setToolTipText(text);
        button.setOpaque(true);
        return button;
    }

    // Method to create a JTextField with Georgia font and placeholder
    private JTextField createPlaceholderField(String placeholderText) {
        JTextField textField = new JTextField(placeholderText);
        textField.setFont(GEORGIA_FONT);  // Set Georgia font
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholderText);
                }
            }
        });
        return textField;
    }
    
    // Method to clear input fields after an action
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        idField.setText("");
        newNameField.setText("");
    }

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Connected to the database successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database!");
        }

        // Launch the GUI
        SwingUtilities.invokeLater(() -> {
            new StudentProfile().setVisible(true);
        });
    }
}