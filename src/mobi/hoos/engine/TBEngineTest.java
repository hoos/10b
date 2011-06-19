package mobi.hoos.engine;

import org.junit.Assert;
import junit.framework.TestCase;
import mobi.hoos.dataset.DataSet;
import mobi.hoos.dataset.DataSetFactory;

/**
 * The TBEngineTest class is the Junit test case suite for TBEngine.
 * @author Hussein Badakhchani
 */
public class TBEngineTest extends TestCase {

    /**
     * The private constructor of the Two Bit Engine.
     */
    private transient TBEngine tbEngine;

    /**
     * Set up the test case by creating an instance of the Two Bit Engine class.
     */
    public void setUp() {
        this.tbEngine = new TBEngine();
    }

    /**
     * Ensures the dataset meets the criteria required to process it.
     */
    public void testGetDataSet() {
        final DataSet dataSet = this.tbEngine.getDataSet(DataSetFactory.INTEGER_TYPE);
        Assert.assertNotNull("Null DataSet!", dataSet);
    }
}
