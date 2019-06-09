/**
 * 
 */
package com.deloitte.digitalawayday.service.impl;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deloitte.digitalawayday.domain.ParseFileTO;
import com.deloitte.digitalawayday.exception.BaseException;
import com.deloitte.digitalawayday.service.ParseFile;

/**
 * @author raviranjan
 *
 */
@Service
public class ParseFileImpl implements ParseFile {

	@Value("${activities.file}")
	private String activitiesFile;

	@Value("${sprint.duration}")
	private String sprintTime;

	/* (non-Javadoc)
	 * @see com.deloitte.digitalawayday.service.ParseFile#readData()
	 */
	@Override
	public List<ParseFileTO> readData() throws BaseException {
		// TODO Auto-generated method stub
		List<ParseFileTO> data = new ArrayList<ParseFileTO>();

		List<String> lines = null;
		try {
			lines = Files.readAllLines(getInputFilePath(activitiesFile));

			for (String line : lines) {
				//System.out.println(line);
				String string = line.trim();
				int duration=0;
				String timeDuration =string.substring(string.lastIndexOf(' ') + 1); 
				if(timeDuration.contains("min"))
				{
					duration = Integer.parseInt(timeDuration.substring(0,timeDuration.lastIndexOf("min")));
				}
				if(timeDuration.equalsIgnoreCase("sprint"))
				{
					duration=Integer.parseInt(sprintTime);
				}
				data.add(new ParseFileTO(string.substring(0,string.lastIndexOf(' ')), duration));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("Error while parsing data from txt file",e);
		} 
		return data;
	}


	private Path getInputFilePath(String inputFilePath) throws URISyntaxException, BaseException {
		Path path = null;
		try {
			path = Paths.get(inputFilePath);
			if (!path.toFile().exists())
				path = Paths.get(getClass().getResource(inputFilePath).toURI());			
		} catch (Exception e) {

			throw new BaseException("Error while reading File",e);
		}
		return path;
	}



}
