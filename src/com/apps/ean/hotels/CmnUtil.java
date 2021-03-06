package com.apps.ean.hotels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CmnUtil {
	
	private static final Logger logger = Logger.getLogger(CmnUtil.class.getCanonicalName());
	
	public final static String _VIEW_HOTELS_SRCH = "hotelsearch";
	
	private final static String _EAN_API_KEYS 		= "apiKey=ayevrmvcukc2hf76tb9b2sma&cid=454871";
	private final static String _EAN_SEARCH_URL 	= "http://api.ean.com/ean-services/rs/hotel/v3/list?minorRev=26&locale=en_US&numberOfResults=50&searchRadius=100&supplierCacheTolerance=MED_ENHANCED";
	private final static String _EAN_HOTEL_INFO_URL = "http://api.ean.com/ean-services/rs/hotel/v3/info?minorRev=26&locale=en_US";
	
	public static HttpResponse invokeUrl(String url_params, int invkType) throws IOException, MalformedURLException, Exception {
		
		StringBuilder url_to_invk = null;
		
		switch(invkType) {
			case 1: url_to_invk = new StringBuilder(_EAN_SEARCH_URL); 
					break;
			case 2: url_to_invk = new StringBuilder(_EAN_HOTEL_INFO_URL);
		}
		
		url_to_invk.append("&")
				   .append(_EAN_API_KEYS)
				   .append("&")
				   .append(url_params);
		
		logger.info("URL to invoke: " + url_to_invk.toString());
		
		UrlFetchTransport urlTrnsprt = new UrlFetchTransport();
		HttpRequestFactory httpRqstFctry = urlTrnsprt.createRequestFactory();
		HttpRequest httpRqst = httpRqstFctry.buildGetRequest(new GenericUrl(url_to_invk.toString()));
		HttpResponse httpRspnse = httpRqst.execute();
		
		return httpRspnse;
	}

	public static HotelResults parseEANResponseForHotelsList(HttpResponse response,	Gson gson) throws IOException {
		return gson.fromJson(new InputStreamReader(response.getContent()),
				HotelResults.class);
	}
	
	public static HotelInfoResult parseEANResponseForHotelDetailsInfo(HttpResponse response, Gson gson) throws IOException {
		InputStreamReader in = new InputStreamReader(response.getContent());
		//getEanReasultContent(in);
		return gson.fromJson(in, HotelInfoResult.class);
	}
	
	public static SearchParam parseSearchJsonData(String jsonStr, Gson gson) throws IOException {
		return gson.fromJson(jsonStr, SearchParam.class);
	}
	
	public static Gson getGson(Gson gson) {
		GsonBuilder gb = new GsonBuilder();
		gb.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
		gson = gb.create();

		return gson;
	}
	
	public static void getEanReasultContent(InputStreamReader in) {
		try {	
			BufferedReader reader = new BufferedReader(in);
	        StringBuilder out = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	        
	        System.out.println(out.toString());   //Prints the string content read from input stream
       
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
