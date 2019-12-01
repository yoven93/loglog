package com.accenture.baloise.cleva.tool.loganalyzer.service;

import com.accenture.baloise.cleva.tool.loganalyzer.model.LogParserOutput;

/**
 * {@link ReportGenerator} defines API to generate reports
 * based on the result ({@link LogParserOutput}) obtained by the {@link LogParser}.
 * 
 * @author yoven
 * @since v1.0.0
 * @see LogParser, LogParserOutput
 */
public interface ReportGenerator {

	/**
	 * Generates a report based on the {@link LogParserOutput} obtained from a {@link LogParser}
	 * and stores the report at a given output directory on the file system.
	 * 
	 * @param logParserOutput	{@link LogParserOutput} impl obtained from a {@link LogParser} impl.
	 * @param outputDirLocation	The directory on the file system to store the report.
	 * 
	 * @return true if successful, else false if an error occurred.
	 */
	public boolean generateReport(LogParserOutput logParserOutput, String outputDirLocation) throws Exception;
}
