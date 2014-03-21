package com.apps.ean.hotels;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.apps.ean.hotels.HotelListResponse.HotelSummary;
import com.google.api.client.http.HttpResponse;
import com.google.gson.Gson;

@Service("HotelSvc")
public class HotelSvcImpl implements HotelSvc{
	
	private static final Logger logger = Logger.getLogger(HotelSvcImpl.class.getCanonicalName());

	@Override
	public List<HotelBO> searchHotels(String cityname, String countrycode,
			String checkindate, String checkoutdate, int roomcount) {
		return searchHotels(cityname, "", countrycode, checkindate, checkoutdate, roomcount);
	}

	@Override
	public List<HotelBO> searchHotels(String cityname, String statecode,
			String countrycode, String checkindate, String checkoutdate,
			int roomcount) {
		List<HotelBO> hotellist = new ArrayList<HotelBO>();
		
		Gson gsnObj = null;
		
		StringBuilder ean_params = new StringBuilder();
		
		if(StringUtils.isBlank(cityname)) {
			ean_params.append("city=New%20York");
		}else {
			ean_params.append("city=")
					  .append(cityname);
		}
		
		if(StringUtils.isNotBlank(statecode)) {
			ean_params.append("&stateProvinceCode=")
					  .append(statecode);
		}
		
		if(StringUtils.isBlank(countrycode)) {
			ean_params.append("&countryCode=US");
		}else {
			ean_params.append("&countryCode=")
					  .append(countrycode);
		}
		
		ean_params.append("&arrivalDate=").append(checkindate);
		ean_params.append("&departureDate=").append(checkoutdate);
		ean_params.append("&room1=").append(roomcount);
		
		try{
			gsnObj = CmnUtil.getGson(gsnObj);
			HttpResponse httpResponse = CmnUtil.invokeUrl(ean_params.toString());
			HotelResults results = CmnUtil.parseEANResponse(httpResponse, gsnObj);
			
			logger.info("**** results from EAN service-" + results);
			
			if(results !=null) {
				List<HotelSummary> ean_hotel_list = results.getHotelListResponse().getHotelList().getHotelSummary();
				
				if(ean_hotel_list!=null && ean_hotel_list.size()>0) {
					logger.info("**** ean_hotel_list size -" + ean_hotel_list.size());
					
					for(HotelSummary hoteldata : ean_hotel_list) {
						
						HotelBO hotelbo = new HotelBO();
						hotelbo.setHotel_name(hoteldata.getName());
						
						String addrss = hoteldata.getAddress1() + ", "
										+ hoteldata.getCity() + ", "
										+ hoteldata.getCountryCode() + ", " 
										+ hoteldata.getPostalCode();
						hotelbo.setHotel_address(addrss);
						
						hotelbo.setHotel_booking_currency(hoteldata.getRateCurrencyCode());
						hotelbo.setHotel_booking_path(hoteldata.getDeepLink());
						hotelbo.setHotel_booking_rate_high(hoteldata.getHighRate());
						hotelbo.setHotel_booking_rate_low(hoteldata.getLowRate());
						hotelbo.setHotel_location_mark(hoteldata.getLocationDescription());
						hotelbo.setHotel_tripadvisor_rating_url(hoteldata.getTripAdvisorRatingUrl());
						hotelbo.setHotel_serial_no(Integer.valueOf(hoteldata.getHotelId()));
						
						String hotelImg = StringUtils.removeEndIgnoreCase(hoteldata.getThumbNailUrl(), "_t.jpg");
						
						// change the thumbnail image with good picture
						hotelbo.setHotel_img_thumb("http://media.expedia.com" + hotelImg + "_b.jpg");
						
						hotellist.add(hotelbo);
					}
				}
			}
		}catch(Exception ex) {
			logger.severe("Error Occurred while searching for hotels using Expedia Service:: " + ex.getMessage());
		}
		return hotellist;
	}

}
