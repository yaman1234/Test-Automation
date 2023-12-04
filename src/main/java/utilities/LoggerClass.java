/**
 * 
 */
package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoggerClass {

	private static final Logger LOGGER = Logger.getGlobal();
	private final String logPath = System.getProperty("user.dir") + "/logs";
	private FileHandler fileHandler;
	private ConsoleHandler consoleHandler;
	private Formatter formatter;
	private String logName = Thread.currentThread().getStackTrace()[2].getClassName() + "_" + System.currentTimeMillis();

	public static final Level FINEST = Level.FINEST;
	public static final String bracketFormat = "[%1$ta, %1$tF %1$tT] [%2$-7s] [%3$s - %4$s] [line no.: %5$d] - %6$s%n%7$s%n";
	public static final String customFormat = "[%1$tF %1$tT] [%3$s - %4$s] [%5$d] [%2$-2s] - %6$s%n";

	public LoggerClass() {
		File logPlace = new File(logPath);
		if (!logPlace.exists())
			logPlace.mkdir();
		
	     // Initialize consoleHandler and set its level to OFF
        consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);
        
	}

	public void setDefaults() {
		this.setLevel(Level.FINE);
		this.setConsoleAppender(true);
		this.setFileAppender(true);
		this.useParentHandlers(false);
		this.setFormatter(customFormat);
	}

	public void setLogName(String filename) {
		logName = filename;
	}

	public void useParentHandlers(boolean bool) {
		LOGGER.setUseParentHandlers(bool);
	}

	public void setLevel(Level level) {
		LOGGER.setLevel(level);
	}

	public Level getLevel() {
		return LOGGER.getLevel();
	}

	public void setFormatter(final String format) {

		formatter = new Formatter() {
			@Override
			public synchronized String format(LogRecord record) {

				try {
					return String.format(format, new Date(record.getMillis()), record.getLevel().getLocalizedName(), Thread.currentThread().getStackTrace()[9].getClassName(),
							Thread.currentThread().getStackTrace()[9].getMethodName(), Thread.currentThread().getStackTrace()[9].getLineNumber(), record.getMessage(),
							record.getThrown());
				} catch (Exception ex) {
					return String.format(format, new Date(record.getMillis()), record.getLevel().getLocalizedName(), Thread.currentThread().getStackTrace()[9].getClassName(),
							Thread.currentThread().getStackTrace()[9].getMethodName(), Thread.currentThread().getStackTrace()[9].getLineNumber(), record.getMessage(), "");
				}
			}
		};

		if (fileHandler != null) {
			fileHandler.setFormatter(formatter);
		}

		if (consoleHandler != null) {
			consoleHandler.setFormatter(formatter);
		}
	}

	public void setFileAppender(boolean bool) {
		if (bool) {
			try {
				fileHandler = new FileHandler(logPath + "/" + logName + ".txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
		} else
			LOGGER.removeHandler(fileHandler);
	}

	public void setConsoleAppender(boolean bool) {
		if (bool) {
			consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(consoleHandler);
		} else
			LOGGER.removeHandler(consoleHandler);
	}

	public void log(Level level, String msgToLog) {
		LOGGER.log(level, msgToLog);
	}

	public void log(Level level, String msgToLog, Exception ex) {
		LOGGER.log(level, msgToLog, ex);
	}

	public void config(String msg) {
		LOGGER.config(msg);
	}

	public void fine(String msg) {
		LOGGER.fine(msg);
	}

	public void finer(String msg) {
		LOGGER.finer(msg);
	}

	public void finest(String msg) {
		LOGGER.finest(msg);
	}

	public void info(String msg) {
		LOGGER.info(msg);
	}

	public void severe(String msg) {
		LOGGER.severe(msg);
	}

	public void warning(String msg) {
		LOGGER.warning(msg);
	}
}
