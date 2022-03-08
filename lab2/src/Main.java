
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.lang.String;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

enum EnumSet{

}

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        BufferedReader reader;
        System.getProperty("java.util.logging.config.file");
        try {

            LogManager.getLogManager().readConfiguration();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("file reading start");
        log.finer("ovo java");
        log.warning("warning ");
        log.info("reading was successful");

        /*try {
            String filename;
            if (0 < args.length) {
                filename = args[0];
            } else {
                filename = JOptionPane.showInputDialog(null, "enter reading filename");
            }
            reader = new BufferedReader(new FileReader("input.txt"));
            Calculator.calculate(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
