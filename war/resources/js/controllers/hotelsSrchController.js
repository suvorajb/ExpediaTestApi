hotelsapp.controller('hotelsSrchController', ['$scope', '$http', 'toaster', '$modal', function ($scope, $http, toaster, $modal)  {
	// for date picker used in checkin and checkout fields
	$scope.format = 'MM/dd/yyyy';
	
	$scope.hotelslist = [];
	
	// param values
	$scope.searchparam = {
			citiname: "Minneapolis",
			statecode: "MN",
			countrycode: "US",
			checkindate: "05/05/2014",
			checkoutdate: "05/10/2014",
			roomcount: 1
		    };

	
	$scope.doSearch = function() {
		console.log(JSON.stringify($scope.searchparam));
		
		$http({
			method: 'POST',
			url: '/ExpediaApp/gethotels',
			data: $scope.searchparam, 
	        headers : { 'Accept': 'application/json', 'Content-Type': 'application/json' }
		})
		.success(function (results, status, headers, config) {
			//console.log(JSON.stringify(results))
			$scope.hotelslist = results;
			toaster.pop('success', 
						"Hotels List load successful Notification", 
						"Hotels loaded successfully for "+$scope.searchparam.citiname);
		})
		.error(function(err){"ERR", console.log(err)});
		
	};
	
	$scope.viewHotelDtls = function(hotelid) {
		try{
			var modalInstance = $modal.open({
				templateUrl: 'hotelDtls.htm',
				controller: hotelDtlsController,
				resolve: {
					hotelid: function () {
					  return hotelid;
					}
				}
			});
		}catch(err) {alert(err.message);}
		
	};
	
}]);