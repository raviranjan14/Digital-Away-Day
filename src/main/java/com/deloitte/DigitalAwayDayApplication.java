/**
 * 
 */
package com.deloitte;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deloitte.digitalawayday.domain.ParseFileTO;
import com.deloitte.digitalawayday.exception.BaseException;
import com.deloitte.digitalawayday.service.ParseFile;
import com.deloitte.digitalawayday.service.PrepareActivityList;
import com.deloitte.digitalawayday.service.PrintList;
/**
 * @author raviranjan
 *
 */
@SpringBootApplication
public class DigitalAwayDayApplication implements CommandLineRunner {


	@Autowired
	ParseFile parseFile;

	@Autowired
	PrepareActivityList prepareList;

	@Autowired
	PrintList printAllList;

	private Map<String,Object> finalResult = new HashMap<String,Object>();
	private List<ParseFileTO> data = new ArrayList<ParseFileTO>();




	public static void main(String[] args) throws ParseException {

		SpringApplication application = new SpringApplication(DigitalAwayDayApplication.class);
		application.run(args);

	}

	public void run(String... args) throws BaseException {

		try {
			//-------Read the txt file---------
			data = parseFile.readData();

			//--------Prepare List--------------
			finalResult = prepareList.preapareList(data);

			//--------Print List----------------
			printAllList.printAllActivityList(finalResult);
			
		}catch(Exception e)
		{
			throw new BaseException("Error occured",e);
		}

	}

}
