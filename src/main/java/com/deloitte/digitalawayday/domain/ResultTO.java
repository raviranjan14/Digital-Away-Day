package com.deloitte.digitalawayday.domain;

import java.io.Serializable;

public class ResultTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2591362860327045897L;
	
	private String time;
	private String activityName;
	private String durationInMin;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	@Override
	public String toString() {
		return "ResultTO [time=" + time + ", activityName=" + activityName + ", durationInMin=" + durationInMin + "]";
	}
	/**
	 * @param time
	 * @param activityName
	 * @param durationInMin
	 */
	public ResultTO(String time, String activityName, String durationInMin) {
		super();
		this.time = time;
		this.activityName = activityName;
		this.durationInMin = durationInMin;
	}
	public String getDurationInMin() {
		return durationInMin;
	}
	public void setDurationInMin(String durationInMin) {
		this.durationInMin = durationInMin;
	}


}
