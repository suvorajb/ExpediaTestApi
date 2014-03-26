package com.apps.ean.hotels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.apps.ean.hotels.HotelInformationResponse.HotelDetails;
import com.apps.ean.hotels.HotelInformationResponse.HotelImages.HotelImage;
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
					  .append(StringEscapeUtils.escapeHtml4(cityname));
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
			HttpResponse httpResponse = CmnUtil.invokeUrl(ean_params.toString(), 1);
			HotelResults results = CmnUtil.parseEANResponseForHotelsList(httpResponse, gsnObj);
			
			logger.info("**** results from EAN service-" + results);
			
			if(results !=null) {
				List<com.apps.ean.hotels.HotelListResponse.HotelSummary> ean_hotel_list = results.getHotelListResponse().getHotelList().getHotelSummary();
				
				if(ean_hotel_list!=null && ean_hotel_list.size()>0) {
					logger.info("**** ean_hotel_list size -" + ean_hotel_list.size());
					
					for(com.apps.ean.hotels.HotelListResponse.HotelSummary hoteldata : ean_hotel_list) {
						
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

	
	
	@Override
	public HotelBO getHotelInfo(int hotelid) {
		Gson gsnObj = null;
		HotelBO hotelbo = new HotelBO();
		
		try{
			gsnObj = CmnUtil.getGson(gsnObj);
			HttpResponse httpResponse = CmnUtil.invokeUrl("&hotelId="+String.valueOf(hotelid).toString(), 2);
			HotelInfoResult result = CmnUtil.parseEANResponseForHotelDetailsInfo(httpResponse, gsnObj);
			if(result!=null) {
				//System.out.println("result.getHotelInformationResponse()-" + result.getHotelInformationResponse());
				com.apps.ean.hotels.HotelInformationResponse.HotelSummary hotelsumm = result.getHotelInformationResponse().getHotelSummary();
				
				hotelbo.setHotel_name(hotelsumm.getName());
				
				String addrss = hotelsumm.getAddress1() + ", "
								+ hotelsumm.getCity() + ", "
								+ hotelsumm.getCountryCode() + ", " 
								+ hotelsumm.getPostalCode();
				hotelbo.setHotel_address(addrss);
				
				//populate generic hotel information
				hotelbo.setHotel_booking_currency(hotelsumm.getRateCurrencyCode());
				hotelbo.setHotel_booking_path(hotelsumm.getDeepLink());
				hotelbo.setHotel_booking_rate_high(hotelsumm.getHighRate());
				hotelbo.setHotel_booking_rate_low(hotelsumm.getLowRate());
				hotelbo.setHotel_location_mark(hotelsumm.getLocationDescription());
				hotelbo.setHotel_tripadvisor_rating_url(hotelsumm.getTripAdvisorRatingUrl());
				hotelbo.setHotel_serial_no(Integer.valueOf(hotelsumm.getHotelId()));
				
				// populate details data
				HotelDetails hoteldtls = result.getHotelInformationResponse().getHotelDetails();
				hotelbo.setHotel_number_rooms(hoteldtls.getNumberOfRooms());
				hotelbo.setHotel_check_in_time(hoteldtls.getCheckInTime());
				hotelbo.setHotel_check_out_time(hoteldtls.getCheckOutTime());
				hotelbo.setHotel_property_information(hoteldtls.getPropertyInformation());
				hotelbo.setHotel_policy(hoteldtls.getHotelPolicy());
				hotelbo.setHotel_room_information(hoteldtls.getPropertyInformation());
				hotelbo.setHotel_driving_directions(hoteldtls.getDrivingDirections());
				
				// populate the hotel images
				List<String> hotelimgslist = new ArrayList<String>();
				//System.out.println("result.getHotelInformationResponse().getHotelImages()-" + result.getHotelInformationResponse().getHotelImages());
				
				if(result.getHotelInformationResponse().getHotelImages()!=null) {
					List<HotelImage> hotelimgs = result.getHotelInformationResponse().getHotelImages().getHotelImage();
					//System.out.println("img list - " + hotelimgs.size());
					
					for(HotelImage img : hotelimgs) {
						hotelimgslist.add(img.getUrl());
					}
				}
				
				hotelbo.setHotel_images(hotelimgslist);
				
			}
			
		}catch(Exception ex) {
			logger.severe("Error Occurred while the hotel details information using Expedia Service:: " + ex + " hotelid-"+hotelid);
		}
		
		
		return hotelbo;
	}

}
