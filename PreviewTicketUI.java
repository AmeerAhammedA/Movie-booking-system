import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PreviewTicketUI extends JFrame {

    private DefaultTableModel tableModel;
    private JTable ticketTable;

    public PreviewTicketUI() {
        setTitle("Preview Ticket");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Root panel
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(root);

        // Header
        JLabel header = new JLabel("Your Tickets", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        root.add(header, BorderLayout.NORTH);

        // Table with Seat Number column
        String[] columns = {"Seat Number", "Movie Name", "Time", "Format", "Language", "Fare"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // prevent editing
            }
        };

        // Sample tickets (replace with actual selection)
        tableModel.addRow(new Object[]{"H2", "Inception", "11:00 AM — Sep 25", "2D", "English", "$50"});
        tableModel.addRow(new Object[]{"H3", "Inception", "11:00 AM — Sep 25", "2D", "English", "$50"});
        tableModel.addRow(new Object[]{"B1", "Interstellar", "06:00 PM — Sep 25", "3D", "English", "$75"});
        tableModel.addRow(new Object[]{"B2", "Interstellar", "06:00 PM — Sep 25", "3D", "English", "$75"});
        tableModel.addRow(new Object[]{"C3", "Interstellar", "06:00 PM — Sep 25", "3D", "English", "$50"});
        tableModel.addRow(new Object[]{"C4", "Interstellar", "06:00 PM — Sep 25", "3D", "English", "$50"});
        tableModel.addRow(new Object[]{"C5", "Interstellar", "06:00 PM — Sep 25", "3D", "English", "$50"});

        ticketTable = new JTable(tableModel);
        ticketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(ticketTable);
        root.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton cancelBtn = new JButton("Cancel Ticket");
        JButton closeBtn = new JButton("Back");
        cancelBtn.setPreferredSize(new Dimension(150, 35));
        closeBtn.setPreferredSize(new Dimension(120, 35));
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(closeBtn);
        root.add(buttonsPanel, BorderLayout.SOUTH);

        // Cancel ticket action
        cancelBtn.addActionListener((ActionEvent e) -> {
            int selectedRow = ticketTable.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel this ticket?",
                        "Confirm Cancel",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a ticket to cancel.");
            }
        });

        // Close button
        closeBtn.addActionListener(e -> dispose());
    }

    // Standalone main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PreviewTicketUI().setVisible(true);
        });
    }
}
