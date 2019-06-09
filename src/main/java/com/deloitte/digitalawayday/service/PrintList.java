/**
 * 
 */
package com.deloitte.digitalawayday.service;

import java.util.Map;

import com.deloitte.digitalawayday.exception.BaseException;

/**
 * @author raviranjan
 *
 */
public interface PrintList {
	
	void printAllActivityList(Map<String,Object> finalResult) throws BaseException;

}
