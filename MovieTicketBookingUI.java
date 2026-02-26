import javax.swing.*;  
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MovieTicketBookingUI extends JFrame {

    public MovieTicketBookingUI() {
        setTitle("Movie Ticket Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(18, 18, 18, 18));
        setContentPane(root);

        // Top panel with logout and header
        JPanel topPanel = new JPanel(new BorderLayout());
        root.add(topPanel, BorderLayout.NORTH);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setPreferredSize(new Dimension(80, 20));
        logoutBtn.addActionListener(e -> dispose());
        topPanel.add(logoutBtn, BorderLayout.WEST);

        JLabel header = new JLabel("Movie Ticket Booking", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 28f));
        header.setBorder(new EmptyBorder(8, 0, 18, 0));
        topPanel.add(header, BorderLayout.CENTER);

        // Main content: shows + seat map
        JPanel main = new JPanel(new GridBagLayout());
        root.add(main, BorderLayout.CENTER);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8, 8, 8, 8);

        // Upcoming shows list
        JPanel left = new JPanel(new BorderLayout(8, 8));
        left.setPreferredSize(new Dimension(360, 480));
        left.setBorder(BorderFactory.createTitledBorder("Upcoming Shows"));

        JComboBox<String> movieCombo = new JComboBox<>(new String[]{"Inception", "The Matrix", "Interstellar"});
        movieCombo.setPreferredSize(new Dimension(320, 32));
        JPanel comboWrap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboWrap.add(new JLabel("Movie:"));
        comboWrap.add(movieCombo);
        left.add(comboWrap, BorderLayout.NORTH);

        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Time", "Language", "Format"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tm.addRow(new Object[]{"11:00 AM — Sep 25", "English", "2D"});
        tm.addRow(new Object[]{"02:30 PM — Sep 25", "English", "IMAX"});
        tm.addRow(new Object[]{"06:00 PM — Sep 25", "English", "3D"});

        JTable showTable = new JTable(tm);
        showTable.setFillsViewportHeight(true);
        showTable.setRowSelectionAllowed(true);
        left.add(new JScrollPane(showTable), BorderLayout.CENTER);

        // Seat layout
        JPanel right = new JPanel(new BorderLayout(8, 8));
        right.setPreferredSize(new Dimension(560, 480));
        right.setBorder(BorderFactory.createTitledBorder("Seat Map"));

        JLabel screenLabel = new JLabel("SCREEN", SwingConstants.CENTER);
        screenLabel.setFont(screenLabel.getFont().deriveFont(Font.BOLD, 16f));
        screenLabel.setBorder(new EmptyBorder(8, 8, 8, 8));
        right.add(screenLabel, BorderLayout.NORTH);

        JPanel seats = new JPanel(new GridBagLayout());
        GridBagConstraints sg = new GridBagConstraints();
        sg.insets = new Insets(6, 8, 6, 8);

        int normalRows = 6, balconyRows = 3, cols = 8;
        ArrayList<JButton> seatButtons = new ArrayList<>();

        // Column numbers
        for (int c = 0; c < cols; c++) {
            sg.gridx = c + 1; sg.gridy = 0;
            JLabel col = new JLabel(String.valueOf(c + 1), SwingConstants.CENTER);
            col.setPreferredSize(new Dimension(46, 18));
            seats.add(col, sg);
        }

        Font seatFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);

        // Normal rows
        for (int r = 0; r < normalRows; r++) {
            char rowChar = (char) ('A' + r);
            sg.gridx = 0; sg.gridy = r + 1;
            JLabel rowLabel = new JLabel(String.valueOf(rowChar));
            rowLabel.setPreferredSize(new Dimension(18, 28));
            seats.add(rowLabel, sg);

            for (int c = 0; c < cols; c++) {
                sg.gridx = c + 1; sg.gridy = r + 1;
                String seatID = rowChar + String.valueOf(c + 1);
                JButton b = new JButton(seatID);
                b.setHorizontalAlignment(SwingConstants.CENTER);
                b.setFont(seatFont);
                b.setPreferredSize(new Dimension(46, 28));
                b.setOpaque(true);
                b.setContentAreaFilled(true);
                b.setBorderPainted(true);
                b.setFocusable(false);
                b.setMargin(new Insets(2, 2, 2, 2));
                seats.add(b, sg);
                seatButtons.add(b);
            }
        }

        // Balcony label
        sg.gridx = 0; sg.gridy = normalRows + 1;
        sg.gridwidth = cols + 1;
        JLabel balconyLabel = new JLabel("Balcony", SwingConstants.CENTER);
        balconyLabel.setBorder(new EmptyBorder(6, 6, 6, 6));
        balconyLabel.setFont(balconyLabel.getFont().deriveFont(Font.BOLD, 13f));
        balconyLabel.setPreferredSize(new Dimension((cols + 1) * 46, 20));
        seats.add(balconyLabel, sg);
        sg.gridwidth = 1;

        // Balcony rows
        for (int br = 0; br < balconyRows; br++) {
            int rowIndex = normalRows + br;
            char rowChar = (char) ('A' + rowIndex);
            int displayY = normalRows + 2 + br;
            sg.gridx = 0; sg.gridy = displayY;
            JLabel rowLabel = new JLabel(String.valueOf(rowChar));
            rowLabel.setPreferredSize(new Dimension(18, 28));
            seats.add(rowLabel, sg);

            for (int c = 0; c < cols; c++) {
                sg.gridx = c + 1; sg.gridy = displayY;
                String seatID = rowChar + String.valueOf(c + 1);
                JButton b = new JButton(seatID);
                b.setHorizontalAlignment(SwingConstants.CENTER);
                b.setFont(seatFont);
                b.setPreferredSize(new Dimension(46, 28));
                b.setOpaque(true);
                b.setContentAreaFilled(true);
                b.setBorderPainted(true);
                b.setFocusable(false);
                b.setToolTipText("Balcony - " + seatID);
                b.setMargin(new Insets(2, 2, 2, 2));
                seats.add(b, sg);
                seatButtons.add(b);
            }
        }

        // Seat coloring
        int totalSeats = seatButtons.size();
        int bookedCount = Math.max(1, totalSeats * 15 / 100);
        int reservedCount = Math.max(1, totalSeats * 10 / 100);

        Set<Integer> chosen = new HashSet<>();
        Random rnd = new Random();
        while (chosen.size() < bookedCount) chosen.add(rnd.nextInt(totalSeats));
        Set<Integer> bookedSet = new HashSet<>(chosen);
        while (chosen.size() < bookedCount + reservedCount) chosen.add(rnd.nextInt(totalSeats));
        Set<Integer> reservedSet = new HashSet<>(chosen);
        reservedSet.removeAll(bookedSet);

        Color bookedColor = Color.RED;
        Color reservedColor = new Color(148, 0, 211);
        Color availableColor = new Color(34, 139, 34);
        Color textOnDark = Color.WHITE;

        for (int i = 0; i < totalSeats; i++) {
            JButton seat = seatButtons.get(i);
            seat.setForeground(textOnDark);
            if (bookedSet.contains(i)) {
                seat.setBackground(bookedColor);
                seat.setEnabled(false);
                seat.setToolTipText(seat.getText() + " - Booked");
            } else if (reservedSet.contains(i)) {
                seat.setBackground(reservedColor);
                seat.setEnabled(false);
                seat.setToolTipText(seat.getText() + " - Reserved");
            } else {
                seat.setBackground(availableColor);
                seat.setEnabled(true);
                seat.setToolTipText(seat.getText() + " - Available");
                seat.addActionListener(e -> {
                    JButton s = (JButton) e.getSource();
                    if (s.getBackground().equals(availableColor)) {
                        s.setBackground(availableColor.brighter());
                    } else {
                        s.setBackground(availableColor);
                    }
                });
            }
        }

        right.add(new JScrollPane(seats), BorderLayout.CENTER);

        // Bottom actions
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        JButton book = new JButton("Book");
        JButton preview = new JButton("Preview Ticket");
        actions.add(book);
        actions.add(preview);

        // Seat fare label
        JLabel fareLabel = new JLabel("Fare: Normal seats - $50      Balcony seats - $75");
        fareLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fareLabel.setBorder(new EmptyBorder(4, 0, 4, 0));
        fareLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Legend
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8));
        legend.setBorder(new EmptyBorder(6, 6, 6, 6));
        legend.add(createLegendItem(bookedColor, "Booked (Red)"));
        legend.add(createLegendItem(reservedColor, "Reserved (Violet)"));
        legend.add(createLegendItem(availableColor, "Available (Green)"));

        // Combine bottom components
        JPanel southWrap = new JPanel();
        southWrap.setLayout(new BoxLayout(southWrap, BoxLayout.Y_AXIS));
        southWrap.add(actions);
        southWrap.add(fareLabel);
        southWrap.add(legend);
        right.add(southWrap, BorderLayout.SOUTH);

        // Add left and right panels
        gc.gridx = 0; gc.gridy = 0; gc.weightx = 0.55; gc.weighty = 1.0; gc.fill = GridBagConstraints.BOTH;
        main.add(left, gc);
        gc.gridx = 1; gc.gridy = 0; gc.weightx = 0.45; gc.fill = GridBagConstraints.BOTH;
        main.add(right, gc);
    }

    private JPanel createLegendItem(Color color, String text) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        JLabel swatch = new JLabel();
        swatch.setOpaque(true);
        swatch.setBackground(color);
        swatch.setPreferredSize(new Dimension(18, 12));
        swatch.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        JLabel lbl = new JLabel(text);
        p.add(swatch);
        p.add(lbl);
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieTicketBookingUI m = new MovieTicketBookingUI();
            m.setVisible(true);
        });
    }
}
