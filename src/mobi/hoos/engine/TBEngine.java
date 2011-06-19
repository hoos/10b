package mobi.hoos.engine;

import mobi.hoos.resultset.ResultSet;
import mobi.hoos.dataset.DataSetFactory;
import mobi.hoos.dataset.DataSet;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.io.IOException;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/**
 * The TBEngine class orchestrates all data analysis activities.
 * @author Hussein Badakhchani
 */
public class TBEngine {

    private static ResultSet resultset = null;
    private static FileHandler fileHandler = null;
    private static final Logger LOGGER = Logger.getLogger("");

    // Initialise our static variables.
    static {
        String logFile = "/home/husseinb/Projects/10b/dist/10b/log/10b.log";
        try {
            TBEngine.fileHandler = new FileHandler(logFile);
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create log file handler: ./log/10.log");
        }
    }

    /**
     * The main method initiates execution of 10b.
     * @param args A string arry of the command line arguments.
     */
    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Starting 10b!");

        // Create Options object
        Options options = new Options();

        // Setup options
        Option help = new Option("help", "print this message");
        Option version = new Option("version", "print the version information and exit");
        Option quiet = new Option("quiet", "be extra quiet");
        Option verbose = new Option("quiet", "be extra verbose");

        // Create the command line parser
        CommandLineParser parser = new GnuParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
        }  catch(ParseException exp) {
            LOGGER.log(Level.SEVERE, "Command line parsing failed: " + exp.getMessage());
        }

        TBEngine tbEngine = new TBEngine();
        DataSet dataSet = tbEngine.getDataSet(DataSetFactory.INTEGER_TYPE);
    }

    /**
     * Returns the data set representation of the data.
     * return DataSet a referencet to the data set.
     */
    public DataSet getDataSet(String dataSetType) {
        return DataSetFactory.getDataSet(dataSetType);
    }
}
