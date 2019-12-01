package com.accenture.baloise.cleva.tool.loganalyzer.service;

import java.io.FileNotFoundException;

import com.accenture.baloise.cleva.tool.loganalyzer.model.LogParserOutput;

/**
 * 
 * @author yoven
 * @since v1.0.0
 */
public interface LogParser {

	/**
	 * Parse a log file.
	 * 
	 * @param fileLocation The absolute path to the log file to be parsed.
	 * 
	 * @return {@link LogParserOutput} if successful, else null is returned if
	 *         something wrong happens.
	 * 
	 * @throws FileNotFoundException If the fileLocation provided does not exists.
	 */
	public LogParserOutput parseLog(String fileLocation) throws FileNotFoundException;
}
