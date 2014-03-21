<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="EanHotelsSearchApp">
<head>
    <meta name="keywords" content="angularjs,SPA,Single Page Application,bootstrap,bootstrap ui,tutorial,JS framework,foursquare,foursquare api,api,web api,Taiseer Joudeh" />
    <meta name="description" content="SPA built using angularjs, this tutorial integrates with foursquare APIs, back end built using Asp.net web api,creatd by Taiseer Joudeh" />
    <meta content="IE=edge, chrome=1" http-equiv="X-UA-Compatible" />
    <title>Explore Hotels ...</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/resources/css/amelia-bootstrap-theme.min.css" rel="stylesheet" />
    <link href="/resources/css/toaster.css" rel="stylesheet" />
    <link href="/resources/css/loading-bar.min.css" rel="stylesheet" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>

    <div id="page">
    	<header class="container-fluid">
            <div id="menu" class="navbar navbar-default">
                <div class="navbar-header">
                    <button class="btn btn-success navbar-toggle" ng-click="navbarExpanded = !navbarExpanded">
                        <span class="glyphicon glyphicon-chevron-down"></span>
                    </button>
                    <div id="logo">
                        <a href='#/'>
                            <h4>Explore Hotels Tonight</h4>
                        </a>
                    </div>
                </div>
                <div class="navbar-collapse collapse" collapse="!navbarExpanded">
                    <ul class="nav navbar-nav navbar-right">
                        <li ng-class="{'active':isActive('/explore')}"><a href="#/explore">Explore</a></li>
                        <li ng-class="{'active':isActive('/places')}"><a href="#/places">My Places</a></li>
                        <li ng-class="{'active':isActive('/about')}"><a href="#/about">About</a></li>
                    </ul>
                </div>
            </div>
        </header>
        <section id="body" class="container-fluid" ng-controller="hotelsSrchController">
	        <div class="col-xs-12 col-sm-12 col-md-offset-2 col-md-8 col-md-offset-2">
			    <form class="form-horizontal">	
			    	<div class="row">
				        <div class="form-group">
				            <div class="col-md-4">
				                <label>Citi:</label> <input type="text" ng-model="searchparam.citiname" class="form-control" placeholder="City Name e.g. Minneapolis" />
				            </div>
				            <div class="col-md-4">
				                <label>State:</label> <input type="text" ng-model="searchparam.statecode" class="form-control" placeholder="State e.g. MN" />
				            </div>
				            <div class="col-md-4">
				                <label>Country Code:</label> <input type="text" ng-model="searchparam.countrycode" class="form-control" placeholder="Country Code e.g. US" />
				            </div>
				        </div>
			        </div>
			    	<div class="row">
				        <div class="form-group">
				            <div class="col-md-4">
				                <label>Checkin:</label> <input type="text" ng-model="searchparam.checkindate" class="form-control" placeholder="Checkin e.g. 04/25/2014" />
				            </div>
				            <div class="col-md-4">
				                <label>Checkout:</label> <input type="text" ng-model="searchparam.checkoutdate" class="form-control" placeholder="Checkout e.g. 04/29/2014" />
				            </div>
				            <div class="col-md-4">
				                <label>Total Rooms?</label> <input type="text" ng-model="searchparam.roomcount" class="form-control" placeholder="Total Room (e.g. 2) " /> <br/>
				                <a class="btn btn-primary pull-right" ng-click="doSearch()"><span class="glyphicon glyphicon-search"></span> Search</a>
				            </div>
				        </div>
			        </div>
			        <hr/>			        
			        <toaster-container toaster-options="{'time-out': 1700}"></toaster-container>
			        
			        <div>
			            <ul class="list-group" ng-repeat="hotel in hotelslist">
			                <li class="list-group-item well">
			                    <div class="row">
			                        <div class="col-xs-2 col-md-1">
			                            <p>{{hotel.hotel_serial_no}}</p>
			                        </div>
			                        <div class="col-xs-10 col-md-11">
			                            <a href="" ng-click="showVenuePhotos(hotel.hotel_serial_no)"><h2 class="venueName">{{hotel.hotel_name}}</h2></a>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="col-xs-4 col-md-3">
			                            <a href=""><img ng-src="{{hotel.hotel_img_thumb}}" class="img-thumbnail" /></a>
			                        </div>			                    
			                        <div class="col-xs-8 col-md-9">
			                            <hr class="seperator" />
			                            <h4>{{hotel.hotel_address}} | <img src="{{hotel.hotel_tripadvisor_rating_url}}" /></h4>
			                            <p>
			                            	{{hotel.hotel_location_mark}} | <small><i>From {{hotel.hotel_booking_rate_low}} {{hotel.hotel_booking_currency}} to {{hotel.hotel_booking_rate_high}} {{hotel.hotel_booking_currency}}</i></small>
			                            </p>
			                            <p class="pull-right"> 
			                            	<a href="{{hotel.hotel_booking_path}}" class="btn btn-success" target="_blank">Book Now</a>&nbsp;
			                            	<a href="" class="btn btn-warning">View More</a>
			                            </p>
			                        </div>

			                    </div>
			
			                </li>
			            </ul>
			        </div>

			    </form>
			</div>
	        	    
        </section>
        
        <hr />
        
    </div>

    <!-- 3rd party libraries -->
   
    <script src="/resources/js/angular.min.js"></script>
    <script src="/resources/js/angular-animate.min.js"></script>
    <script src="/resources/js/ui-bootstrap-tpls-0.10.0.min.js"></script>
    <script src="/resources/js/toaster.js"></script>
    <script src="/resources/js/loading-bar.min.js"></script>


    <!-- Load app main script -->
    <script src="/resources/js/app.js"></script>

    <!-- Load controllers -->
    <script src="/resources/js/controllers/hotelsSrchController.js"></script>



</body>
</html>