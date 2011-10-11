package mobi.hoos.engine;

import mobi.hoos.resultset.ResultSet;
import mobi.hoos.resultset.IntegerResultSet;
import mobi.hoos.dataset.DataSetFactory;
import mobi.hoos.dataset.DataSet;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.io.IOException;

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
            LOGGER.log(Level.INFO,
            "Failed to create log file handler: " + appdir + "/log/10.log");
        }
    }

    /**
     * The main method initiates execution of 10b.
     * @param args A string arry of the command line arguments.
     */
    public static final void main(final String[] args) {
        LOGGER.log(Level.INFO, "Starting 10b!");

        final CMDLineParser cmdLineParser = new CMDLineParser(args);
        //final TBEngine tbEngine = new TBEngine();
        final DataSet dataSet =
            tbEngine.getDataSet(DataSetFactory.INTEGER_TYPE);
        TBEngine.resultset = new IntegerResultSet();
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
