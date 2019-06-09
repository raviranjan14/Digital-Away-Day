/**
 * 
 */
package com.deloitte.digitalawayday.service;

import java.util.List;

import com.deloitte.digitalawayday.domain.ParseFileTO;
import com.deloitte.digitalawayday.exception.BaseException;

/**
 * @author raviranjan
 *
 */
public interface ParseFile {

	List<ParseFileTO> readData() throws BaseException;

	
}
