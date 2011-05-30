package mobi.hoos.engine;

import junit.framework.TestCase;
import mobi.hoos.dataset.DataSet;
import mobi.hoos.dataset.DataSetFactory;

/**
 * The TBEngineTest class is the Junit test case suite for TBEngine.
 * @author Hussein Badakhchani
 */
public class TBEngineTest extends TestCase {

    private TBEngine tbEngine; 

    /**
     * Set up the test case by creating an instance of the Two Bit Engine class.
     */
    public void setUp() {
        this.tbEngine = new TBEngine();
    }

    public void testGetDataSet() throws Exception {
        DataSet dataSet = this.tbEngine.getDataSet(DataSetFactory.INTEGER_TYPE);
    }
}
