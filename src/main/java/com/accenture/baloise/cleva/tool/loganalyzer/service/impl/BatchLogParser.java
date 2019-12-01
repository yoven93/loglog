package com.accenture.baloise.cleva.tool.loganalyzer.service.impl;

import static com.accenture.baloise.cleva.tool.loganalyzer.constant.RegularExpression.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.accenture.baloise.cleva.tool.loganalyzer.model.BatchLogParserOutput;
import com.accenture.baloise.cleva.tool.loganalyzer.model.LogParserOutput;
import com.accenture.baloise.cleva.tool.loganalyzer.service.LogParser;

/**
 * {@link BatchLogParser}.
 * 
 * @author yoven
 * @since v1.0.0
 */
public class BatchLogParser implements LogParser {

	/**
	 * The {@link Pattern} to be used to match ERROR present in the log file.
	 */
	private final Pattern errorPattern;

	/**
	 * The {@link Pattern} to be used to match WARN present in the log file.
	 */
	private final Pattern warnPattern;

	/**
	 * The {@link Pattern} to be used to match INFO present in the log file.
	 */
	private final Pattern infoPattern;

	/**
	 * The {@link Pattern} to be used to match the 'Items Selected = $1' part of the
	 * log file.
	 */
	private final Pattern itemsSelectedPattern;

	/**
	 * The {@link Pattern} to be used to match the 'Elements Treated = $1' part of
	 * the log file.
	 */
	private final Pattern elementsTreatedPattern;
	
	/**
	 * The {@link Pattern} to be used to match the full ERROR together with its
	 * error message. The error message is group inside group 1 ($1).
	 */
	private final Pattern errorMsgPattern;

	/**
	 * No Argument Constructor. Use this to compile all the regular express patterns
	 * with {@link Pattern#compile(String)}.
	 */
	public BatchLogParser() {

		this.errorPattern = Pattern.compile(BATCH_LOG_ERROR_REGEX);
		this.warnPattern = Pattern.compile(BATCH_LOG_WARN_REGEX);
		this.infoPattern = Pattern.compile(BATCH_LOG_INFO_REGEX);
		this.itemsSelectedPattern = Pattern.compile(BATCH_LOG_ITEMS_SELECTED_REGEX);
		this.elementsTreatedPattern = Pattern.compile(BATCH_LOG_ELEMENTS_TREATED_REGEX);
		this.errorMsgPattern = Pattern.compile(BATCH_LOG_ERROR_MSG_REGEX);
	}

	/**
	 * {@inheritDoc}.
	 */
	public LogParserOutput parseLog(String fileLocation) throws FileNotFoundException {

		BatchLogParserOutput batchLogParserOutput = new BatchLogParserOutput();

		Path path = Paths.get(fileLocation);

		// Checks if the fileLocation provided exists
		if (!Files.exists(path)) {

			throw new FileNotFoundException(path + " does not exits");
		}
		
		// Set the file processed
		batchLogParserOutput.setFileProcessed(fileLocation);
		
		// Set the environment
		batchLogParserOutput.setEnvironment("V10");

		// Parse the input log file and populate the BatchLogParserOutput object
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

			String line;

			while ((line = reader.readLine()) != null) {

				// Get the number of ERROR in the log file
				this.getNumError(batchLogParserOutput, line);

				// Get the number of WARN in the log file
				this.getNumWarn(batchLogParserOutput, line);

				// Get the number of INFO in the log file
				this.getNumInfo(batchLogParserOutput, line);
				
				// Get the items selected value in the log file
				this.getNumItemsSelected(batchLogParserOutput, line);
				
				// Get the elements treated value in the log file
				this.getNumElementsTreated(batchLogParserOutput, line);
				
				// Extract the error messages
				this.extractErrorMsg(batchLogParserOutput, line);
			}

		} catch (IOException e) {

			return null;
		}

		return batchLogParserOutput;
	}

	/**
	 * 
	 * @param batchLogParserOutput
	 * @param input
	 */
	private void getNumError(BatchLogParserOutput batchLogParserOutput, String input) {

		Matcher numErrorMatcher = this.errorPattern.matcher(input);

		if (numErrorMatcher.find()) {
			batchLogParserOutput.setNumError(batchLogParserOutput.getNumError() + 1);
		}
	}

	/**
	 * 
	 * @param batchLogParserOutput
	 * @param input
	 */
	private void getNumWarn(BatchLogParserOutput batchLogParserOutput, String input) {

		Matcher numWarnMatcher = this.warnPattern.matcher(input);

		if (numWarnMatcher.find()) {
			batchLogParserOutput.setNumWarn(batchLogParserOutput.getNumWarn() + 1);
		}
	}

	/**
	 * 
	 * @param batchLogParserOutput
	 * @param input
	 */
	private void getNumInfo(BatchLogParserOutput batchLogParserOutput, String input) {

		Matcher numInfoMatcher = this.infoPattern.matcher(input);

		if (numInfoMatcher.find()) {
			batchLogParserOutput.setNumInfo(batchLogParserOutput.getNumInfo() + 1);
		}
	}

	/**
	 * 
	 * @param batchLogParserOutput
	 * @param input
	 */
	private void getNumItemsSelected(BatchLogParserOutput batchLogParserOutput, String input) {

		Matcher numItemsSelectedMatcher = this.itemsSelectedPattern.matcher(input);

		if (numItemsSelectedMatcher.find()) {
			batchLogParserOutput.setNumItemsSelected(numItemsSelectedMatcher.group(1));
		}
	}

	/**
	 * 
	 * @param batchLogParserOutput
	 * @param input
	 */
	private void getNumElementsTreated(BatchLogParserOutput batchLogParserOutput, String input) {

		Matcher numElementsTreatedMatcher = this.elementsTreatedPattern.matcher(input);

		if (numElementsTreatedMatcher.find()) {
			batchLogParserOutput.setNumElementsTreated(numElementsTreatedMatcher.group(1));
		}
	}
	
	/**
	 * 
	 * @param batchLogParserOutput
	 * @param input
	 */
	private void extractErrorMsg(BatchLogParserOutput batchLogParserOutput, String input) {
		
		Matcher errorMsgMatcher = this.errorMsgPattern.matcher(input);
		
		if (errorMsgMatcher.find()) {
			
			HashMap<String, Integer> map = batchLogParserOutput.getErrorMsgMap();
			
			String errorMsg = errorMsgMatcher.group(1);
			
			if (errorMsg.length() > 20) {
				
				errorMsg = errorMsg.substring(0, 20);
			}
			
			if (map.containsKey(errorMsg)) {
				
				map.put(errorMsg, map.get(errorMsg) + 1); 
				
			} else {
				
				map.put(errorMsg, 1);
			}
		}
	}
}
