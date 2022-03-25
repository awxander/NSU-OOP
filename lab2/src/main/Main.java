
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.logging.Logger;


public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filename;
            if (0 < args.length) {
                filename = args[0];
                reader = new BufferedReader(new FileReader(filename));
            } else {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            log.info("start of reading ");
            Calculator calculator = new Calculator();
            calculator.calculate(reader);
            log.info("end of reading ");
        } catch (IOException e) {
            log.warning("reading failed: " + e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
