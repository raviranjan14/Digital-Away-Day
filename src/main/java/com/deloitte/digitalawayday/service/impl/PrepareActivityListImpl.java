/**
 * 
 */
package com.deloitte.digitalawayday.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deloitte.digitalawayday.domain.ParseFileTO;
import com.deloitte.digitalawayday.domain.ResultTO;
import com.deloitte.digitalawayday.exception.BaseException;
import com.deloitte.digitalawayday.helper.TimeUtil;
import com.deloitte.digitalawayday.service.PrepareActivityList;

/**
 * @author raviranjan
 *
 */
@Service
public class PrepareActivityListImpl implements PrepareActivityList {
	
	@Value("${day.start.time}")
	private String startTime;

	@Value("${lunch.txt}")
	private String lunchTxt;

	@Value("${lunch.duratoin}")
	private String lunchDuration;
	private int count=0;

	private  Map<String,Object> finalResult = new HashMap<String,Object>();

	@Autowired
	TimeUtil tUtil;

	@Override
	public Map<String, Object> preapareList(List<ParseFileTO> data) throws BaseException {
		// TODO Auto-generated method stub

		while(data.size()>0)
		{
			try {
				data.removeAll(prepareList(data));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new BaseException("Error while parsing data from txt file",e);
			}
		}

		return finalResult;
	}

	private List<ParseFileTO> prepareList(List<ParseFileTO> data)throws BaseException {
		// TODO Auto-generated method stub
		List<ParseFileTO> toRemove = new ArrayList<ParseFileTO>();
		//TimeUtil tUtil = new TimeUtil();
		boolean newDay = true;
		boolean lunch = false;
		String myTime = startTime;
		count ++;
		try {
		List<ResultTO> activityList = new ArrayList<ResultTO>();

		for(ParseFileTO pft : data) {
			
			String activityName= pft.getEventName();
			int duration1 = pft.getDuration();
			String time = tUtil.get12HrTime(myTime);

			if(newDay)
			{

				activityList.add(new ResultTO(time, activityName,duration1+"min"));
				finalResult.put("Team"+count,activityList);
				myTime = tUtil.getEndTime(myTime,duration1);
				newDay = false;
				toRemove.add(pft);

			}
			else {
				if(!lunch)
				{

					activityList.add(new ResultTO(time, activityName,duration1+"min"));
					finalResult.put("Team"+count,activityList);
					myTime = tUtil.getEndTime(myTime,duration1);	
					toRemove.add(pft);
					LocalTime time1 = LocalTime.parse(myTime);

					if (tUtil.isBetween(time1, LocalTime.of(11, 0), LocalTime.of(12, 0)))
					{
						time = tUtil.get12HrTime(myTime);
						activityList.add(new ResultTO(time, lunchTxt, lunchDuration));
						finalResult.put("Team"+count,activityList);
						myTime = tUtil.getEndTime(myTime,60);
						lunch = true;
					}

				}
				else
				{
					String tempTime=myTime;					
					myTime = tUtil.getEndTime(myTime,duration1);
					LocalTime time1 = LocalTime.parse(myTime);
					if (time1.isAfter(LocalTime.of(17, 0)))
					{
						myTime= tempTime;
					}
					else
					{
						time = tUtil.get12HrTime(tempTime);
						activityList.add(new ResultTO(time, activityName,duration1+"min"));
						finalResult.put("Team"+count,activityList);
						toRemove.add(pft);
					}

				}
			}
		}
		String time = tUtil.get12HrTime(myTime);
		activityList.add(new ResultTO(time, "Staff Motivation Presentation",""));
		finalResult.put("Team"+count,activityList);

		}catch(Exception e)
		{
			throw new BaseException("Error while preparing activity List",e);
		}
		return toRemove;
	}

}
