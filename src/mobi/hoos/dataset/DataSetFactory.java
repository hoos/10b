package mobi.hoos.dataset;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * The DataSetFactory class decouples the creation of different
 * dataset types from the users of those datasets.
 * @author Hussein Badakhchani
 */
public final class DataSetFactory {

    /**
     * Private constructor becuase no one should ever have to create
     * this class.
     */
    private DataSetFactory() {}

    /**
     * A String used by the factory to determine the type of dataset 
     * to create and return.
     */
    public static final String INTEGER_TYPE = "INTEGER";

    /**
     * The logger used by the class.
     */
    private static final Logger logger = Logger.getLogger("DataSetFactory");


    /** 
     * Returns the dataset type determined by the datasetType argument. 
     *
     * @param dataSetType the integer describing the data set type.
     * @return dataset a reference to the newly created dataset.
     */
    public static DataSet getDataSet(final String dataSetType) {
        DataSet dataset = null;
        if (dataSetType.equals(DataSetFactory.INTEGER_TYPE)) {
            dataset = new IntegerDataSet();
        } else {
            DataSetFactory.logger.log(Level.SEVERE, "Unknown data set type: "
            + dataSetType);
        }
            DataSetFactory.logger.log(Level.INFO, "Created Dataset: "
            + dataSetType);
        return dataset;
    }
}
