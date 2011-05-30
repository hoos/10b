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
    private static final Logger logger = Logger.getLogger("");

    // Initialise our static variables.
    static {
        try {
            TBEngine.fileHandler = new FileHandler("/home/husseinb/Projects/10b/dist/10b/log/10b.log");
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to create log file handler: ./log/10.log");
    }

    public static void main(String[] args) {
        logger.log(Level.INFO, "Starting 10b!");

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
            CommandLine line = parser.parse( options, args );
        }  catch( ParseException exp ) {
            logger.log(Level.SEVERE, "Command line parsing failed: " + exp.getMessage() );
        }

        TBEngine tbEngine = new TBEngine();
        DataSet dataSet = tbEngine.getDataSet(DataSetFactory.INTEGER_TYPE);
    }

    public DataSet getDataSet(String dataSetType) {
        DataSet dataSet = DataSetFactory.getDataSet(dataSetType);
        return dataSet;
    }
}
