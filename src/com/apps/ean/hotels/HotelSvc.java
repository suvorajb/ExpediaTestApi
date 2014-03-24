package com.apps.ean.hotels;

import java.util.List;

public interface HotelSvc {

	// search by city and country code only
	public List<HotelBO> searchHotels(String cityname, String countrycode, 
								String checkindate, String checkoutdate, int roomcount);

	// search by city, state code, country code
	public List<HotelBO> searchHotels(String cityname, String statecode,
								String countrycode, String checkindate, String checkoutdate, int roomcount);
	
	// get hotel details information
	public HotelBO getHotelInfo(int hotelid);

}
