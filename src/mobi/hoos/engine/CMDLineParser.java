package mobi.hoos.engine;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ResourceBundle;
import java.util.Locale;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.apache.commons.cli.HelpFormatter;

public class CMDLineParser {

    /**
     * The logger used by the class.
     *
     */
    private static final Logger LOGGER = Logger.getLogger("CMDLineParser");

    /**
     * The CMDLineParser class handles 10b's command line processing.
     * @author Hussein Badakhchani
     */
    public CMDLineParser(final String[] args) {

        final String language = System.getProperty("user.language");
        final String region = System.getProperty("user.country");

        LOGGER.log(Level.FINEST, 
            "Creating Locale: language=" + language  + " region=" + region);

        final Locale locale = new Locale(language, region);


        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        
        // Create Options collection
        final Options options = new Options();

        // Setup options
        final Option help = new Option("help", messages.getString("display_help"));
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
        final HelpFormatter formatter = new HelpFormatter();
        try {
            // parse the command line arguments
            final CommandLine line = parser.parse(options, args);
            if (line.hasOption(help.getOpt())) {
                LOGGER.log(Level.FINEST, messages.getString("display_help"));
                // automatically generate the help statement
                formatter.printHelp("10b", options);
            } else if (line.hasOption(version.getOpt())) {
                LOGGER.log(Level.FINEST, messages.getString("display_version"));
            } else if (line.hasOption(quiet.getOpt())) {
                LOGGER.log(Level.FINEST, messages.getString("increase_verbosity"));
            } else if (line.hasOption(verbose.getOpt())) {
                LOGGER.log(Level.FINEST, messages.getString("decrease_verbosity"));
            } else {
                LOGGER.log(Level.FINEST, messages.getString("no_cmd_line_arguments"));
                throw new MissingOptionException("No command line aruguments found!");
            }
        } catch (UnrecognizedOptionException ue) {
             LOGGER.log(Level.SEVERE, ue.getMessage());
        } catch (MissingOptionException me) {
             LOGGER.log(Level.SEVERE, me.getMessage());
             formatter.printHelp("10b", options);
        } catch (MissingArgumentException mae) {
             LOGGER.log(Level.SEVERE, mae.getMessage());
        } catch (ParseException pe) {
             LOGGER.log(Level.SEVERE, "Command line parsing failed: "
                 + pe.getMessage());
             formatter.printHelp("10b", options);
        }
    }
}
