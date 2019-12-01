package com.accenture.baloise.cleva.tool.loganalyzer;

import com.accenture.baloise.cleva.tool.loganalyzer.model.BatchLogParserOutput;
import com.accenture.baloise.cleva.tool.loganalyzer.service.LogParser;
import com.accenture.baloise.cleva.tool.loganalyzer.service.ReportGenerator;
import com.accenture.baloise.cleva.tool.loganalyzer.service.impl.BatchLogParser;
import com.accenture.baloise.cleva.tool.loganalyzer.service.impl.BatchLogReportGenerator;

public class LogAnalyzer {

	public static void main(String[] args) throws Exception {
		
		//String fileToProcess = "/Users/yoven/Desktop/LogAnalyzer/loggings/BatchAddressRiskTransferImpl.log";
		String fileToProcess = "/Users/yoven/Desktop/LogAnalyzer/loggings/BatchImportClevaContractImpl.log";
		
		LogParser logParser = new BatchLogParser();
		
		
		// Parsing
		long start = System.currentTimeMillis();

		BatchLogParserOutput output = (BatchLogParserOutput) logParser
				.parseLog(fileToProcess);
		
		System.out.println("Time to parse: " + (System.currentTimeMillis() - start));
		
		
		// Report Generation
		start = System.currentTimeMillis();
		
		ReportGenerator reportGenerator = new BatchLogReportGenerator();
		reportGenerator.generateReport(output, "/Users/yoven/Desktop/LogAnalyzer/output/test_1.xlsx");
		
		System.out.println("Time to generate report: " + (System.currentTimeMillis() - start));
		
	}

}
