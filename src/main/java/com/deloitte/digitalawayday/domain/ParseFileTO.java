package com.deloitte.digitalawayday.domain;

import java.io.Serializable;

public class ParseFileTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6706827650849076398L;
	
	private String eventName;
	private int duration;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "ParseFile [eventName=" + eventName + ", duration=" + duration + "]";
	}
	/**
	 * @param eventName
	 * @param duration
	 */
	public ParseFileTO(String eventName, int duration) {
		super();
		this.eventName = eventName;
		this.duration = duration;
	}
	
	
}
