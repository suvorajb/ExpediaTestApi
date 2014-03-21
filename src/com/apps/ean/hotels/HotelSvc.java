package com.apps.ean.hotels;

import java.util.List;

public interface HotelSvc {

	// search by city and country code only
	public List<HotelBO> searchHotels(String cityname, String countrycode, 
								String checkindate, String checkoutdate, int roomcount);

	// search by city, statecode, country code
	public List<HotelBO> searchHotels(String cityname, String statecode,
								String countrycode, String checkindate, String checkoutdate, int roomcount);

}
