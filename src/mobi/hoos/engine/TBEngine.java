package mobi.hoos.engine;

import mobi.hoos.resultset.ResultSet;
import mobi.hoos.resultset.IntegerResultSet;
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
import org.apache.commons.cli.HelpFormatter;


/**
 * The TBEngine class orchestrates all data analysis activities.
 * @author Hussein Badakhchani
 */
public class TBEngine {

    /**
     * The object which contains the results of the analysis.
     */
    private static ResultSet resultset = null;

    /**
     * The log file handler.
     */
    private static FileHandler fileHandler = null;

    /**
     * The logger object used by the TBEngine.
     */
    private static final Logger LOGGER = Logger.getLogger("");

    // Initialise our static variables.
    static {
        final String appdir = System.getProperty("APPLICATION_HOME");
        String logfile = "";
        if (appdir == null) {
            logfile = System.getProperty("java.io.tmpdir") + "/10b.log";
        } else {
            logfile = appdir + "/log/10b.log";
        }
        try {
            TBEngine.fileHandler = new FileHandler(logfile);
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
            "Failed to create log file handler: " + appdir + "/log/10.log");
        }
    }

    /**
     * The main method initiates execution of 10b.
     * @param args A string arry of the command line arguments.
     */
    public static final void main(final String[] args) {
        LOGGER.log(Level.INFO, "Starting 10b!");

        // Create Options object
        final Options options = new Options();

        // Setup options
        final Option help = new Option("help", "print this message");
        options.addOption(help);
        final Option version =
            new Option("version", "print the version information and exit");
        options.addOption(version);
        final Option quiet = new Option("quiet", "be extra quiet");
        options.addOption(quiet);
        final Option verbose = new Option("verbose", "be extra verbose");
        options.addOption(verbose);

        // Create the command line parser
        final CommandLineParser parser = new GnuParser();
        try {
            // parse the command line arguments
            final CommandLine line = parser.parse(options, args);
            if (line.hasOption(help.getOpt())) {
                LOGGER.log(Level.INFO, "Displaying help message");
                // automatically generate the help statement
                final HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("10b", options);
            } else if (line.hasOption(version.getOpt())) {
                LOGGER.log(Level.INFO, "Displaying version message");
            } else if (line.hasOption(quiet.getOpt())) {
                LOGGER.log(Level.INFO, "Changing verbosity to quite");
            } else if (line.hasOption(verbose.getOpt())) {
                LOGGER.log(Level.INFO, "Changing verbosity to verbose");
            }

        }  catch (ParseException exp) {
            LOGGER.log(Level.SEVERE, "Command line parsing failed: "
                       + exp.getMessage());
        }


        final TBEngine tbEngine = new TBEngine();
        final DataSet dataSet =
            tbEngine.getDataSet(DataSetFactory.INTEGER_TYPE);
        resultset = new IntegerResultSet();
    }

    /**
     * Returns the data set representation of the data.
     * @param dataSetType A string describing the type of dataset required.
     * @return DataSet a referencet to the data set.
     */
    public final DataSet getDataSet(final String dataSetType) {
        return DataSetFactory.getDataSet(dataSetType);
    }
}
