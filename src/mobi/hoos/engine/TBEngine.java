package mobi.hoos.engine;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * The TBEngine class orchestrates all data analysis activities.
 * @author Hussein Badakhchani
 */
public class TBEngine {

    private final static Logger logger = Logger.getLogger(TBEngine.class.getName());

    public static void main(String[] args) {
        TBEngine.logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Starting 10b!");
    }

}
