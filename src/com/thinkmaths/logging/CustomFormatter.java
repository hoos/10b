/*
 * Created Jul 8, 2008
 *
 * Copyright ThinkTank Maths Limited 2008
 *
 * This file is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This file is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this file. If not, see <http://www.gnu.org/licenses/>.
 */

package com.thinktankmaths.logging;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

/**
 * A {@link Formatter} that may be customised in a {@code logging.properties}
 * file. The syntax of the property
 * {@code com.thinktankmaths.logging.TerseFormatter.format}
 * specifies the output. A newline will be appended to the string and the
 * following special characters will be expanded (case sensitive):-
 * <ul>
 * <li>{@code %L} - log level</li>
 * <li>{@code %m} - message</li>
 * <li>{@code %n} - name of the logger</li>
 * <li>{@code %t} - a timestamp in ISO-8601 "yyyy-MM-dd HH:mm:ss Z" format</li>
 * <li>{@code %M} - source method name (if available, otherwise "?")</li>
 * <li>{@code %c} - source class name (if available, otherwise "?")</li>
 * <li>{@code %C} - source simple class name (if available, otherwise "?")</li>
 * <li>{@code %T} - thread ID</li>
 * </ul>
 * The default format is {@value #DEFAULT_FORMAT}. Curly brace characters are
 * not allowed.
 *
 * @author Samuel Halliday
 * @author Hussein Badakhchani
 */
public class CustomFormatter extends Formatter {

    /**
     * The default format of the log file expressed as the RE %L: %m [%c.%M %t].
     */
    private static final String DEFAULT_FORMAT = "%L: %m [%c.%M %t]";

    /**
     * The expected integer index position of the log level argument position.
     */
    private static final int LOGLEVEL = 0;

    /**
     * The expected integer index position of the log message argument position.
     */
    private static final int MESSAGE = 1;

    /**
     * The expected integer index position of the logger argument.
     */
    private static final int LOGGER = 2;

    /**
     * The expected integer index position of the log timestamp argument.
     */
    private static final int TIMESTAMP = 3;

    /**
     * The expected integer index position of the method argument.
     */
    private static final int METHOD = 4;

    /**
     * The expected integer index position of the thread ID argument.
     */
    private static final int THREAD_ID = 5;

    /**
     * The expected integer index position of the logger name argument.
     */
    private static final int LOGGER_NAME = 6;

    /**
     * Aviod the checksyle Magic Number warning for 7!.
     */
    private static final int SEVEN = 7;

    /**
     * Aviod the checksyle Magic Number warning for 8!.
     */
    private static final int EIGHT = 8;

    /**
     * Aviod the checksyle Magic Number warning for 128!.
     */
    private static final int ONETWOEIGHT = 128;

    /**
     * The message format.
     */
    private final transient MessageFormat messageFormat;

    /**
     * The date format used in the log file: yyyy-MM-dd HH:mm:ss Z.
     */
    private final transient DateFormat dateFormat =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    /**
     * stringBuilder is used to optimise the message formatting.
     */
    private final transient StringBuilder stringBuilder = new StringBuilder(ONETWOEIGHT);

    /** */
    public CustomFormatter() {
        super();

        // load the format from logging.properties
        final String propName = getClass().getName() + ".format";
        String format = LogManager.getLogManager().getProperty(propName);
        if (format == null || format.trim().length() == 0) {
            format = DEFAULT_FORMAT;
        }
        if (format.contains("{") || format.contains("}")) {
            throw new IllegalArgumentException("curly braces not allowed");
        }
        // convert it into the MessageFormat form
        format = format.replace("%L", "{0}").replace("%m", "{1}").
            replace("%M", "{2}").replace("%t", "{3}").replace("%c", "{4}").
            replace("%T", "{5}").replace("%n", "{6}").
            replace("%C", "{7}") + "\n";

        messageFormat = new MessageFormat(format);
    }

    @Override
    public final String format(final LogRecord record) {
        String[] arguments = new String[EIGHT];
        // %L
        arguments[CustomFormatter.LOGLEVEL] = record.getLevel().toString();
        // %m
        arguments[MESSAGE] = record.getMessage();
        // sometimes the message is empty, but there is a throwable
        if (arguments[MESSAGE] == null || arguments[MESSAGE].length() == 0) {
            final Throwable thrown = record.getThrown();
            if (thrown != null) {
                arguments[MESSAGE] = thrown.getMessage();
            }
        }
        // %M
        if (record.getSourceMethodName() != null) {
            arguments[LOGGER] = record.getSourceMethodName();
        } else {
            arguments[LOGGER] = "?";
        }
        // %t
        final Date date = new Date(record.getMillis());
        synchronized (dateFormat) {
            arguments[CustomFormatter.TIMESTAMP] = dateFormat.format(date);
        }
        // %c
        if (record.getSourceClassName() != null) {
            arguments[CustomFormatter.METHOD] = record.getSourceClassName();
        } else {
            arguments[CustomFormatter.METHOD] = "?";
        }
        // %T
        arguments[THREAD_ID] = Integer.toString(record.getThreadID());
        // %n
        arguments[LOGGER_NAME] = record.getLoggerName();
        // %C
        final int start = arguments[METHOD].lastIndexOf('.') + 1;
        if (start > 0 && start < arguments[METHOD].length()) {
            //arguments[SEVEN] = arguments[METHOD].substring(start);
            stringBuilder.append(arguments[METHOD]);
            arguments[SEVEN] = stringBuilder.substring(start);
        } else {
            arguments[SEVEN] = arguments[METHOD];
        }

        synchronized (messageFormat) {
            return messageFormat.format(arguments);
        }
    }
}
