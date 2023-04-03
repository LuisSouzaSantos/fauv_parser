package com.fauv.parser.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import com.fauv.parser.entity.enums.HeaderType;

public class Header {

	private String fileName;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalDate endDate;
	private LocalTime endTime;
	private String partNumber;
	private String equipmentName;
	private String sampleId;
	private String inspectorName;
	private ZoneId zoneId;
	
	public void buildingHeader(HeaderType type, String information) {
		switch (type) {
			case FILE_NAME:
				this.setFileName(information);
				break;
			case START_DATE:
				this.setStartDate(information);
				break;
			case START_TIME:
				this.setStartTime(information);
				break;
			case END_DATE:
				this.setEndDate(information);
				break;
			case END_TIME:
				this.setEndTime(information);
				break;
			case EQUIPMENT_NAME:
				this.setEquipmentName(information);
				break;
			case INSPECTOR:
				this.setInspectorName(information);
				break;
			case PART_NUMBER:
				this.setPartNumber(information);
				break;
			case SAMPLE_ID: 
				this.setSampleId(information);
			default:
				break;
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = Header.sanitazerFileName(fileName);
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public void setStartDate(String startDateStringFormat) {
		this.setStartDate(Header.sanitazerDate(startDateStringFormat));
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public void setStartTime(String startTimeStringFormat) {
		this.setStartTime(Header.sanitazerTime(startTimeStringFormat));
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(String endDateStringFormat) {
		this.setEndDate(Header.sanitazerDate(endDateStringFormat));
	}
	
	public LocalTime getEndTime() {
		return endTime;
	}
	
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public void setEndTime(String endTimeStringFormat) {
		this.setEndTime(Header.sanitazerTime(endTimeStringFormat));
	}
	
	public String getPartNumber() {
		return partNumber;
	}
	
	public void setPartNumber(String partNumber) {
		this.partNumber = Header.sanitazerPartNumber(partNumber);
	}
	
	public String getEquipmentName() {
		return equipmentName;
	}
	
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = Header.sanitazerEquipmentName(equipmentName);
	}
	
	public String getSampleId() {
		return sampleId;
	}
	
	public void setSampleId(String sampleId) {
		this.sampleId = Header.sanitazerSampleId(sampleId);
	}
	
	public String getInspectorName() {
		return inspectorName;
	}
	
	public void setInspectorName(String inspectorName) {
		this.inspectorName = Header.sanitazerInspectorName(inspectorName);
	}
	
	public ZoneId getZoneId() {
		return zoneId;
	}
	
	public void setZoneId(ZoneId zoneId) {
		this.zoneId = zoneId;
	}
	
	public boolean isCompleted() {
		return this.getFileName() != null && this.getStartDate() != null && this.getStartTime() != null
				&& this.getEndDate() != null && this.getEndTime() != null && this.getPartNumber() != null
				&& this.getEquipmentName() != null && this.getSampleId() != null && this.getInspectorName() != null;
	}
	
	private static String sanitazerFileName(String extractedContent) {
		return extractedContent.replaceAll("'", "").trim();
	}
	
	private static LocalDate sanitazerDate(String extractedContent) {
		String[] parsedContent = extractedContent.trim().split("/");
		
		int year = Integer.parseInt(parsedContent[0]);
		int month = Integer.parseInt(parsedContent[1]);
		int day = Integer.parseInt(parsedContent[2]);
		
		return LocalDate.of(year, month, day);
	}
	
	private static LocalTime sanitazerTime(String extractedContent) {
		String[] parsedContent = extractedContent.trim().split(":");
		
		int hours = Integer.parseInt(parsedContent[0]);
		int minutes = Integer.parseInt(parsedContent[1]);
		int seconds = Integer.parseInt(parsedContent[2]);
		
		return LocalTime.of(hours, minutes, seconds);
	}
	
	private static String sanitazerPartNumber(String extractedContent) {
		return extractedContent.replaceAll("'", "").trim();
	}
	
	private static String sanitazerEquipmentName(String extractedContent) {
		return extractedContent.replaceAll("'", "").trim();
	}
	
	private static String sanitazerSampleId(String extractedContent) {
		return extractedContent.replaceAll("'", "").trim();
	}
	
	private static String sanitazerInspectorName(String extractedContent) {
		return extractedContent.replaceAll("'", "").trim();
	}
	
}
