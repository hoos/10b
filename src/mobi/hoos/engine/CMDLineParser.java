package mobi.hoos.engine;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

public class CMDLineParser {

    /**
     * The logger used by the class.
     *
     */
    private static final Logger LOGGER = Logger.getLogger("CMDLineParser");

    public CMDLineParser(final String[] args) {

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
                LOGGER.log(Level.FINEST, "Displaying help message");
                // automatically generate the help statement
                final HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("10b", options);
            } else if (line.hasOption(version.getOpt())) {
                LOGGER.log(Level.FINEST, "Displaying version message");
            } else if (line.hasOption(quiet.getOpt())) {
                LOGGER.log(Level.FINEST, "Changing verbosity to quite");
            } else if (line.hasOption(verbose.getOpt())) {
                LOGGER.log(Level.FINEST, "Changing verbosity to verbose");
            } else {
                LOGGER.log(Level.FINEST, "No command line arguments");
            }
 
        }  catch (ParseException exp) {
            LOGGER.log(Level.SEVERE, "Command line parsing failed: "
                       + exp.getMessage());
        }

    }
}
