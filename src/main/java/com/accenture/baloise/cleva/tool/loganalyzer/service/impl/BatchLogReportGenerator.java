package com.accenture.baloise.cleva.tool.loganalyzer.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.accenture.baloise.cleva.tool.loganalyzer.model.BatchLogParserOutput;
import com.accenture.baloise.cleva.tool.loganalyzer.model.LogParserOutput;
import com.accenture.baloise.cleva.tool.loganalyzer.service.ReportGenerator;

/**
 * 
 * @author yoven
 * @since v1.0.0
 */
public class BatchLogReportGenerator implements ReportGenerator {

	/**
	 * {@inheritDoc}.
	 * 
	 * @throws Exception
	 */
	public boolean generateReport(LogParserOutput logParserOutput, String outputDirLocation) throws Exception {

		BatchLogParserOutput batchLogParserOutput = (BatchLogParserOutput) logParserOutput;

		Workbook workbook = null;

		if (outputDirLocation.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (outputDirLocation.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new Exception("Invalid file name, should be xls or xlsx");
		}
		
		// Create a sheet
		Sheet sheet = workbook.createSheet("report");
		
		// Create overview part
		this.createOverview(sheet, batchLogParserOutput);
		
		// Create the error summary part
		this.createErrorSummary(sheet, batchLogParserOutput);

		// Auto sizing the columns
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		
		// Create the excel report
		workbook.write(Files.newOutputStream(Paths.get(outputDirLocation)));
		workbook.close();

		return false;
	}

	/**
	 * Create the overview part of the report
	 * 
	 * @param sheet
	 * @param batchLogParserOutput
	 */
	private void createOverview(Sheet sheet, BatchLogParserOutput batchLogParserOutput) {
		
		Row row_0 = sheet.createRow(0);
		Cell cell_0_0 = row_0.createCell(0);
		cell_0_0.setCellValue("Overview");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
		
		Row row_1 = sheet.createRow(1);
		Cell cell_1_0 = row_1.createCell(0);
		cell_1_0.setCellValue("Log file processed");
		Cell cell_1_1 = row_1.createCell(1);
		cell_1_1.setCellValue(batchLogParserOutput.getFileProcessed());
		
		Row row_2 = sheet.createRow(2);
		Cell cell_2_0 = row_2.createCell(0);
		cell_2_0.setCellValue("Environment");
		Cell cell_2_1 = row_2.createCell(1);
		cell_2_1.setCellValue(batchLogParserOutput.getEnvironment());
		
		Row row_3 = sheet.createRow(3);
		Cell cell_3_0 = row_3.createCell(0);
		cell_3_0.setCellValue("Number of [ERROR] found");
		Cell cell_3_1 = row_3.createCell(1);
		cell_3_1.setCellValue(batchLogParserOutput.getNumError());
		
		Row row_4 = sheet.createRow(4);
		Cell cell_4_0 = row_4.createCell(0);
		cell_4_0.setCellValue("Number of [WARN] found");
		Cell cell_4_1 = row_4.createCell(1);
		cell_4_1.setCellValue(batchLogParserOutput.getNumWarn());
		
		Row row_5 = sheet.createRow(5);
		Cell cell_5_0 = row_5.createCell(0);
		cell_5_0.setCellValue("Number of [INFO] found");
		Cell cell_5_1 = row_5.createCell(1);
		cell_5_1.setCellValue(batchLogParserOutput.getNumInfo());
		
		Row row_6 = sheet.createRow(6);
		Cell cell_6_0 = row_6.createCell(0);
		cell_6_0.setCellValue("Number of Items Selected");
		Cell cell_6_1 = row_6.createCell(1);
		cell_6_1.setCellValue(batchLogParserOutput.getNumItemsSelected());
		
		Row row_7 = sheet.createRow(7);
		Cell cell_7_0 = row_7.createCell(0);
		cell_7_0.setCellValue("Number of Elements Treated");
		Cell cell_7_1 = row_7.createCell(1);
		cell_7_1.setCellValue(batchLogParserOutput.getNumElementsTreated());
	}
	
	/**
	 * Create the error summary part of the report.
	 * 
	 * @param sheet
	 * @param batchLogParserOutput
	 */
	private void createErrorSummary(Sheet sheet, BatchLogParserOutput batchLogParserOutput) {
		
		Row row_0 = sheet.getRow(0);
		Cell cell_0_3 = row_0.createCell(3);
		cell_0_3.setCellValue("Error Summary");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
		
		Row row_1 = sheet.getRow(1);
		Cell cell_1_3 = row_1.createCell(3);
		cell_1_3.setCellValue("Error Message");
		Cell cell_1_4 = row_1.createCell(4);
		cell_1_4.setCellValue("Count");
		
		HashMap<String, Integer> errorMsgMap = batchLogParserOutput.getErrorMsgMap();
		
		int rowIndex = 2;
		
		for (Entry<String, Integer> entry : errorMsgMap.entrySet()) {
		    
			Row row = sheet.getRow(rowIndex);
			
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}
			
			Cell cell_3 = row.createCell(3);
			cell_3.setCellValue(entry.getKey());
			
			Cell cell_4 = row.createCell(4);
			cell_4.setCellValue(entry.getValue());
			
			rowIndex++;
		}
	}
	
	/**
	 * 
	 * @param workbook
	 */
	private void styleOveriew(Workbook workbook) {
		
	}
	
	/**
	 * 
	 * @param workbook
	 */
	private void styleErrorSummary(Workbook workbook) {
		
	}
}
