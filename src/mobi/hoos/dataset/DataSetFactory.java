package mobi.hoos.dataset;

import java.util.logging.Logger;
import java.util.logging.Level;

import mobi.hoos.engine.TBEngine;

/**
 * The DataSetFactory class decouples the creation of different
 * dataset types from the users of those datasets.
 * @author Hussein Badakhchani
 */
public class DataSetFactory {

    private static final String integerType = "INTEGER";

    private static final Logger logger = Logger.getLogger(DataSetFactory.class.getName());

    public DataSet createDataSet(String dataSetType) {
        DataSet dataset = null;
        if (dataSetType.equals(DataSetFactory.integerType)) {
            dataset = new IntegerDataSet();
        } else {
            DataSetFactory.logger.log(Level.INFO, "Unknown data set type: " + dataSetType);
        }
        return dataset;
    }

}
