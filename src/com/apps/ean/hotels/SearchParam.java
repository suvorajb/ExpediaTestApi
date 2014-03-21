package com.apps.ean.hotels;

import java.io.Serializable;

public class SearchParam implements Serializable {

	private static final long serialVersionUID = 7577558417208561917L;

	private String citiname = "";
	private String statecode = "";
	private String countrycode = "";
	private String checkindate = "";
	private String checkoutdate = "";
	private int roomcount = 1;

	@Override
	public String toString() {
		return "citiname [" + citiname + "] statecode [" + statecode
				+ "] countrycode [" + countrycode + "] checkindate ["
				+ checkindate + "] checkoutdate [" + checkoutdate
				+ "] roomcount[" + roomcount;
	}

	public String getCitiname() {
		return citiname;
	}

	public void setCitiname(String citiname) {
		this.citiname = citiname;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCheckindate() {
		return checkindate;
	}

	public void setCheckindate(String checkindate) {
		this.checkindate = checkindate;
	}

	public String getCheckoutdate() {
		return checkoutdate;
	}

	public void setCheckoutdate(String checkoutdate) {
		this.checkoutdate = checkoutdate;
	}

	public int getRoomcount() {
		return roomcount;
	}

	public void setRoomcount(int roomcount) {
		this.roomcount = roomcount;
	}

}
