package com.apps.ean.hotels;

import java.util.Collections;
import java.util.List;

public class HotelInformationResponse {
	
	private HotelSummary hotelSummary;
	
	private HotelDetails hotelDetails;
	
	private HotelImages hotelImages;
	
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

	public static class HotelDetails {
		
		private int numberOfRooms;
		private String checkInTime;
		private String checkOutTime;
		private String propertyInformation;
		private String propertyDescription;
		private String hotelPolicy;
		private String roomInformation;
		private String drivingDirections;
		public int getNumberOfRooms() {
			return numberOfRooms;
		}
		public String getCheckInTime() {
			return checkInTime;
		}
		public String getCheckOutTime() {
			return checkOutTime;
		}
		public String getPropertyInformation() {
			return propertyInformation;
		}
		public String getPropertyDescription() {
			return propertyDescription;
		}
		public String getHotelPolicy() {
			return hotelPolicy;
		}
		public String getRoomInformation() {
			return roomInformation;
		}
		public String getDrivingDirections() {
			return drivingDirections;
		}
	}
	
	public static class HotelImages {
		private List<HotelImage> HotelImage = Collections.emptyList();

		public List<HotelImage> getHotelImage() {
			return HotelImage;
		}
		
		public static class HotelImage {
			private String url;

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}
		}
	}
	
	// main getter setters
	public HotelSummary getHotelSummary() {
		return hotelSummary;
	}

	public HotelDetails getHotelDetails() {
		return hotelDetails;
	}

	public HotelImages getHotelImages() {
		return hotelImages;
	}

}