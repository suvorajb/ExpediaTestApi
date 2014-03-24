package com.apps.ean.hotels;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class HotelController {

	private static final Logger logger = Logger.getLogger(HotelController.class.getCanonicalName());

	@Autowired
	private HotelSvc hotelSvc;

	@RequestMapping(value = "/ExpediaApp/hotelsearch", method = RequestMethod.GET)
	public String gotoSearchPge(HttpServletRequest req, Model uiModel) {

		return CmnUtil._VIEW_HOTELS_SRCH;
	}
	
	
	// used by angularjs to make Ajax call to get the Hotels list based on the search
	@RequestMapping(value = "/ExpediaApp/gethotels", method = RequestMethod.POST, headers = "Content-Type=application/json", produces = "application/json")
	public @ResponseBody
	String getHotelsResultJson(@RequestBody String srchJsonStr) {
		Gson gson = CmnUtil.getGson(new Gson());
		List<HotelBO> hotels = Collections.emptyList();
		SearchParam paramObj = null;

		logger.info("**** searchparam populated-" + srchJsonStr);
		
		try {
			paramObj = CmnUtil.parseSearchJsonData(srchJsonStr, gson);

		} catch (Exception ex) {
			logger.severe("**** Error while parsing the search params:: " + ex.getMessage());
		}
		
		logger.info("**** Search Param Object populated-" + paramObj);

		// This is used for testing purpose with hard coded data
		// List<HotelBO> hotels = hotelSvc.searchHotels("Minneapolis", "MN", "US", "04/20/2014", "04/25/2014", 1);

		if (paramObj != null) {
			
			hotels = hotelSvc.searchHotels(
					paramObj.getCitiname(),
					paramObj.getStatecode(), 
					paramObj.getCountrycode(),
					paramObj.getCheckindate(), 
					paramObj.getCheckoutdate(),
					paramObj.getRoomcount());
		}

		logger.info("**** hotels size-" + hotels.size());

		return gson.toJson(hotels);
	}
	
	
	// used by angularjs to make Ajax call to get the Hotels list based on the search
	@RequestMapping(value = "/ExpediaApp/gethotelinfo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getHotelInfoJson(@RequestParam final int hotelid) {
		Gson gson = CmnUtil.getGson(new Gson());
		HotelBO hotelinfo = new HotelBO();

		logger.info("**** hotelid sent-" + hotelid);
		
		hotelinfo = hotelSvc.getHotelInfo(hotelid);

		logger.info("**** hotel info-" + hotelinfo);

		return gson.toJson(hotelinfo);
	}	
}
