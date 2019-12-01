package com.accenture.baloise.cleva.tool.loganalyzer.model;

import java.util.HashMap;

/**
 * 
 * @author yoven
 * @since v1.0.0
 */
public class BatchLogParserOutput implements LogParserOutput {

	/**
	 * The file that was processed.
	 */
	private String fileProcessed;

	/**
	 * The environment (e.g. V10 or V14).
	 */
	private String environment;

	/**
	 * Number of INFO that appears in the log.
	 */
	private int numInfo;

	/**
	 * Number of WARN that appears in the log.
	 */
	private int numWarn;

	/**
	 * Number of ERROR that appears in the log.
	 */
	private int numError;

	/**
	 * The Items Selected as it appears in the log. This appears at the end of the
	 * log.
	 */
	private String numItemsSelected;

	/**
	 * The Elements Treated as it appears in the log. This appears at the end of the
	 * log.
	 */
	private String numElementsTreated;

	/**
	 * {@link HashMap} to store the error messages and its count.
	 * 
	 * The key is the error message. The value is the amount of the time the error
	 * message occurred.
	 */
	private HashMap<String, Integer> errorMsgMap;
	
	/**
	 * Zero Argument Constructor.
	 */
	public BatchLogParserOutput() {
		
		this.errorMsgMap = new HashMap<>();
	}

	/*
	 * Getters and Setters Start.
	 */
	public String getFileProcessed() {
		return fileProcessed;
	}

	public void setFileProcessed(String fileProcessed) {
		this.fileProcessed = fileProcessed;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public int getNumInfo() {
		return numInfo;
	}

	public void setNumInfo(int numInfo) {
		this.numInfo = numInfo;
	}

	public int getNumWarn() {
		return numWarn;
	}

	public void setNumWarn(int numWarn) {
		this.numWarn = numWarn;
	}

	public int getNumError() {
		return numError;
	}

	public void setNumError(int numError) {
		this.numError = numError;
	}

	public String getNumItemsSelected() {
		return numItemsSelected;
	}

	public void setNumItemsSelected(String numItemsSelected) {
		this.numItemsSelected = numItemsSelected;
	}

	public String getNumElementsTreated() {
		return numElementsTreated;
	}

	public void setNumElementsTreated(String numElementsTreated) {
		this.numElementsTreated = numElementsTreated;
	}

	public HashMap<String, Integer> getErrorMsgMap() {
		return errorMsgMap;
	}

	public void setErrorMsgMap(HashMap<String, Integer> errorMsgMap) {
		this.errorMsgMap = errorMsgMap;
	}
	/*
	 * Getters and Setter End.
	 */

	@Override
	public String toString() {
		return "BatchLogParserOutput [fileProcessed=" + fileProcessed + ", environment=" + environment + ", numInfo="
				+ numInfo + ", numWarn=" + numWarn + ", numError=" + numError + ", numItemsSelected=" + numItemsSelected
				+ ", numElementsTreated=" + numElementsTreated + ", errorMsgMap=" + errorMsgMap + "]";
	}
}
