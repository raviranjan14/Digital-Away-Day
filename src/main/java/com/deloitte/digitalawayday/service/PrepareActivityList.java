/**
 * 
 */
package com.deloitte.digitalawayday.service;

import java.util.List;
import java.util.Map;

import com.deloitte.digitalawayday.domain.ParseFileTO;
import com.deloitte.digitalawayday.exception.BaseException;

/**
 * @author raviranjan
 *
 */
public interface PrepareActivityList {
	
	Map<String,Object> preapareList(List<ParseFileTO> data) throws BaseException;

}
