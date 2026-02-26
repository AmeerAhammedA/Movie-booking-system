import javax.swing.*;
import java.awt.*;

public class HomePageUI extends JFrame {

    public HomePageUI() {
        setTitle("Movie Booking Manager");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Root panel
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        // Header
        JLabel header = new JLabel("Movie Booking Manager", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        root.add(header);

        // Add Seat panel
        JPanel addSeatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JLabel addSeatLabel = new JLabel("Add Seat:");
        JTextField addSeatField = new JTextField(10);
        JButton addSeatBtn = new JButton("Add");
        addSeatBtn.setPreferredSize(new Dimension(100, 30));
        addSeatPanel.add(addSeatLabel);
        addSeatPanel.add(addSeatField);
        addSeatPanel.add(addSeatBtn);
        root.add(addSeatPanel);

        // Remove Seat panel
        JPanel removeSeatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JLabel removeSeatLabel = new JLabel("Remove Seat:");
        JTextField removeSeatField = new JTextField(10);
        JButton removeSeatBtn = new JButton("Remove");
        removeSeatBtn.setPreferredSize(new Dimension(100, 30));
        removeSeatPanel.add(removeSeatLabel);
        removeSeatPanel.add(removeSeatField);
        removeSeatPanel.add(removeSeatBtn);
        root.add(removeSeatPanel);

        // Change Fare panel
        JPanel changeFarePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JLabel normalFareLabel = new JLabel("Normal Fare ($):");
        JTextField normalFareField = new JTextField(6);
        normalFareField.setText("50"); // Autofill Normal Fare
        JLabel balconyFareLabel = new JLabel("Balcony Fare ($):");
        JTextField balconyFareField = new JTextField(6);
        balconyFareField.setText("75"); // Autofill Balcony Fare
        JButton changeFareBtn = new JButton("Change Fare");
        changeFareBtn.setPreferredSize(new Dimension(150, 30));
        changeFarePanel.add(normalFareLabel);
        changeFarePanel.add(normalFareField);
        changeFarePanel.add(balconyFareLabel);
        changeFarePanel.add(balconyFareField);
        changeFarePanel.add(changeFareBtn);
        root.add(changeFarePanel);

        // Spacer
        root.add(Box.createVerticalStrut(30));

        // Logout button panel
        JPanel logoutPanel = new JPanel();
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setPreferredSize(new Dimension(120, 35));
        logoutPanel.add(logoutBtn);
        root.add(logoutPanel);

        // Align all labels nicely
        addSeatLabel.setPreferredSize(new Dimension(120, 25));
        removeSeatLabel.setPreferredSize(new Dimension(120, 25));
        normalFareLabel.setPreferredSize(new Dimension(120, 25));
        balconyFareLabel.setPreferredSize(new Dimension(120, 25));

        // Logout action
        logoutBtn.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePageUI().setVisible(true);
        });
    }
}
