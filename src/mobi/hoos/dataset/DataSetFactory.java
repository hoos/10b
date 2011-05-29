package mobi.hoos.dataset;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * The DataSetFactory class decouples the creation of different
 * dataset types from the users of those datasets.
 * @author Hussein Badakhchani
 */
public class DataSetFactory {

    public static final String INTEGER_TYPE = "INTEGER";

    private static final Logger logger = Logger.getLogger("");

    public static final DataSet getDataSet(String dataSetType) {
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
