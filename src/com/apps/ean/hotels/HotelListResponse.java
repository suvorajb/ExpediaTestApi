package com.apps.ean.hotels;

import java.util.Collections;
import java.util.List;

public class HotelListResponse {
	
	private HotelList HotelList;

	public HotelList getHotelList() {
		return HotelList;
	}
	
	public static class HotelList {
		private List<HotelSummary> HotelSummary = Collections.emptyList();

		public List<HotelSummary> getHotelSummary() {
			return HotelSummary;
		}
	}
	
	public static class HotelSummary {
		private String hotelId;
		private String name;
		private String address1;
		private String city;
		private String stateProvinceCode;
		private String countryCode;
		private String postalCode;
		private String hotelRating;
		private String tripAdvisorRating;
		private String tripAdvisorRatingUrl;
		private String locationDescription;
		private String highRate;
		private String lowRate;
		private String rateCurrencyCode;
		private String thumbNailUrl;
		private String deepLink;
		
		@Override
		public String toString() {
			return "hotelId ["+hotelId+"name["+name+"]";
		}
		
		public String getHotelId() {
			return hotelId;
		}
		public String getName() {
			return name;
		}
		public String getAddress1() {
			return address1;
		}
		public String getCity() {
			return city;
		}
		public String getStateProvinceCode() {
			return stateProvinceCode;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public String getHotelRating() {
			return hotelRating;
		}
		public String getTripAdvisorRating() {
			return tripAdvisorRating;
		}
		public String getTripAdvisorRatingUrl() {
			return tripAdvisorRatingUrl;
		}
		public String getLocationDescription() {
			return locationDescription;
		}
		public String getHighRate() {
			return highRate;
		}
		public String getLowRate() {
			return lowRate;
		}
		public String getRateCurrencyCode() {
			return rateCurrencyCode;
		}
		public String getThumbNailUrl() {
			return thumbNailUrl;
		}
		public String getDeepLink() {
			return deepLink;
		}
	}
}
