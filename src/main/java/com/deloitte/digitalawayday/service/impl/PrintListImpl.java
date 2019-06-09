/**
 * 
 */
package com.deloitte.digitalawayday.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.deloitte.digitalawayday.domain.ResultTO;
import com.deloitte.digitalawayday.exception.BaseException;
import com.deloitte.digitalawayday.service.PrintList;

/**
 * @author raviranjan
 *
 */
@Service
public class PrintListImpl implements PrintList {

	private final Logger logger = LoggerFactory.getLogger(PrintListImpl.class);

	/* (non-Javadoc)
	 * @see com.deloitte.digitalawayday.service.PrintList#printAllActivityList()
	 */
	@Override
	public void printAllActivityList(Map<String,Object> finalResult) throws BaseException {

		List<String> teamName = new ArrayList<String>();

		try {

			for (String name : finalResult.keySet())  
			{
				teamName.add(0, name);
			}

			for (String team : teamName) {

				logger.info(team);
				@SuppressWarnings("unchecked")
				List<ResultTO> list  = (List<ResultTO>) finalResult.get(team);
				Iterator<ResultTO> itr = list.iterator();
				while(itr.hasNext())
				{
					ResultTO rst = itr.next();
					logger.info(rst.getTime()+" : "+ rst.getActivityName() +" "+rst.getDurationInMin());
				}
				logger.info("");
				logger.info("");
			}
		}catch(Exception e)
		{
			throw new BaseException("Error while printing Data",e);
		}

	}

}
