import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginSignupUI extends JFrame {

    public LoginSignupUI() {
        setTitle("Login / Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        // panel with Border
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        // Welcome Label
        
        JLabel welcomeLabel = new JLabel("Welcome To Movie Booking Website", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setBorder(new EmptyBorder(20, 0, 30, 0));
        root.add(welcomeLabel, BorderLayout.NORTH);

        // Center panel for form

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(15, 15, 15, 15);
        gc.fill = GridBagConstraints.HORIZONTAL;

        // User ID
        gc.gridx = 0; gc.gridy = 0;
        JLabel userLabel = new JLabel("User ID:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(userLabel, gc);

        gc.gridx = 1; gc.gridy = 0;
        JTextField userField = new JTextField(20);
        userField.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(userField, gc);

        // Password

        gc.gridx = 0; gc.gridy = 1;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(passLabel, gc);

        gc.gridx = 1; gc.gridy = 1;
        JPasswordField passField = new JPasswordField(20);
        passField.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(passField, gc);

        root.add(centerPanel, BorderLayout.CENTER);

    
        // Buttons panel
    
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 18));
        loginBtn.setPreferredSize(new Dimension(120, 40));

        JButton signupBtn = new JButton("Sign In");
        signupBtn.setFont(new Font("Arial", Font.BOLD, 18));
        signupBtn.setPreferredSize(new Dimension(120, 40));

        buttons.add(loginBtn);
        buttons.add(signupBtn);

        root.add(buttons, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginSignupUI().setVisible(true);
        });
    }
}
