package tetris;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Testing extends JFrame {

    private static final long serialVersionUID = 6666606L;

    private final Scanner in = new Scanner(System.in); // Make Scanner class global

    public Testing() {
        // Better not to build your form in here.
        initForm();
    }

    private void initForm() {
        // Array for Column Names
        String[] columnNames = {"Player 1", "Player 2"};

        // Declare DefaultTableModel and apply column names and 0 rows.
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);

        // Add table rows data to table model.
        dtm.addRow(new Object[]{"14 inch Buck Knife", "Rubber Boots and a Baseball Bat"});
        dtm.addRow(new Object[]{"AK47 with Grenade Launcher", "Umbrella"});
        dtm.addRow(new Object[]{"1000 Rounds of 7.62 Ammo", "Banana Flavor Juice Boxes"});
        dtm.addRow(new Object[]{"Cammo Gear & B/P Flak Jacket", "Yellow Rain Coat (with collapsible hood)"});
        dtm.addRow(new Object[]{"4 Door HumV with .50cal M2 BMG", "5 Speed Bike with cool Banana Seat"});
        dtm.addRow(new Object[]{"Satellite Phone", "Real Good Vocal Cords"});
        dtm.addRow(new Object[]{"12x Binoculars + Nite Vision", "Same (but made in WW1) c/w neck string"});

        JTable table = new JTable();
        table.setModel(dtm);   // Apply the table model to JTable
        table.setBackground(Color.pink);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("JTable Demo");
        setAlwaysOnTop(true);

        //Upper Panel
        JPanel panelA = new JPanel();
        panelA.setPreferredSize(new Dimension(500, 100));
        JLabel label = new JLabel("<html> <font size='5'>As you can see, when the battle begins,<br>"
                + "<center>Player 2 is in a world of hurt!</center></font></html>");

        // Lower Panel
        JPanel panelB = new JPanel();
        JScrollPane scrollpane = new JScrollPane(table);

        // JScrollPane & JTable
        scrollpane.setPreferredSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);

        // Add components to their respective panels
        panelA.add(label);
        panelB.add(scrollpane);
        add(panelA, BorderLayout.PAGE_START);
        add(panelB, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null); // Place window in middle of screen.
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(Testing::new);
    }
}