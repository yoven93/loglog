package com.accenture.baloise.cleva.tool.loganalyzer.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * {@link ReportGeneratorExcelUtil} provides utility methods for working with
 * excel file using the library apache POI ooxml.
 * 
 * One example of such utility is styling the cells of an excel sheet.
 * 
 * @author yoven
 * @since v1.0.0
 */
public abstract class ReportGeneratorExcelUtil {

	/**
	 * 
	 * 
	 * @author yoven
	 * @since v1.0.0
	 */
	public abstract static class CellStyling {

		/**
		 * Align the content of the provided cell.
		 * 
		 * @param cell
		 * @param alignment
		 */
		public static void setAlignment(Cell cell, HorizontalAlignment alignment) {

			final CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();
			cellStyle.setAlignment(alignment);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cell.setCellStyle(cellStyle);
		}

		/**
		 * Align the content of the provided cell and set content to bold
		 * 
		 * @param cell
		 * @param alignment
		 */
		public static void setAlignmentAndBold(Cell cell, HorizontalAlignment alignment) {

			final CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();

			final Font font = cell.getSheet().getWorkbook().createFont();
			font.setBold(true);

			cellStyle.setAlignment(alignment);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setFont(font);
			
			cell.setCellStyle(cellStyle);
		}

		/**
		 * Align the content of the provided cell and set content to bold. And also
		 * apply font and background color.
		 * 
		 * @param cell
		 * @param alignment
		 * @param fontColor
		 * @param backgroundColor
		 */
		public static void setAlignmentAndBoldAndColor(Cell cell, HorizontalAlignment alignment,
				IndexedColors fontColor, IndexedColors backgroundColor) {

			final CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();

			final Font font = cell.getSheet().getWorkbook().createFont();
			font.setBold(true);
			font.setColor(fontColor.index);

			cellStyle.setAlignment(alignment);
			cellStyle.setFillBackgroundColor(backgroundColor.index);
			cellStyle.setFillForegroundColor(backgroundColor.index);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setFont(font);

			cell.setCellStyle(cellStyle);
		}
	}
}
