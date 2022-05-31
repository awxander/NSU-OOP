package tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.SortOrder;
import javax.swing.InputMap;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class LeaderboardForm extends JFrame {
    private JTable scoresTable;
    private DefaultTableModel defaultTableModel;
    private final String LEADERBOARD_FILE_NAME = "leaderboard";
    private TableRowSorter<TableModel> sorter;
    private final int SCORE_COLUMN_INDEX = 1;
    private final int LEADERBOARD_WIDTH = 500;
    private final int LEADERBOARD_HEIGHT = 500;

    public void addPlayer(String playerName, int score) {
        defaultTableModel.addRow(new Object[]{playerName, score});
        saveLeaderboardData();
    }

    private void initTableSorter() {
        sorter = new TableRowSorter<>(defaultTableModel);
        scoresTable.setRowSorter(sorter);

        ArrayList<SortKey> keys = new ArrayList<>();
        keys.add(new SortKey(SCORE_COLUMN_INDEX, SortOrder.DESCENDING));
        sorter.setSortKeys(keys);
    }

    private void initTableData() {

        Vector columnIdentifiers = new Vector();
        columnIdentifiers.add("Player");
        columnIdentifiers.add("score");

        defaultTableModel = new DefaultTableModel(new String[]{"player", "score"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == SCORE_COLUMN_INDEX) {
                    return Integer.class;
                }
                return Object.class;
            }

            ;
        };

        scoresTable.setModel(defaultTableModel);
        try {
            FileInputStream fileInputStream = new FileInputStream(LEADERBOARD_FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            defaultTableModel.setDataVector((Vector) objectInputStream.readObject(), columnIdentifiers);
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initControls() {
        InputMap inputMap = this.getRootPane().getInputMap();
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "esc");

        actionMap.put("esc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Main.showStartupForm();
            }
        });
    }


    public LeaderboardForm(StartupForm startupForm) {


        this.setSize(LEADERBOARD_WIDTH, LEADERBOARD_HEIGHT);
        //this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//set frame in the center of the screen

        initTableData();
        initControls();
        initTableSorter();

        this.add(new JScrollPane(scoresTable));
    }

    private void saveLeaderboardData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(LEADERBOARD_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(defaultTableModel.getDataVector());
            fileOutputStream.close();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
