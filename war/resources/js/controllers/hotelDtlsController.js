/*	hotelsapp.controller('hotelDtlsController', ['$scope', '$modalInstance', 'hotelId', function ($scope, $modalInstance, hotelId)  {

		  $scope.hotelid = hotelId;

		  $scope.ok = function () {
		    $modalInstance.close();
		  };

		  $scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		  };
		  
	}]);*/

var hotelDtlsController = function ($scope, $http, $modalInstance, hotelid) {

	$scope.cancel = function () {
	  $modalInstance.dismiss('cancel');
	};
	  
	$scope.hoteldata = {};
  
	$http({
		method: 'GET',
		url: '/ExpediaApp/gethotelinfo?hotelid='+hotelid,
	    headers : { 'Accept': 'application/json' }
	})
	.success(function (results, status, headers, config) {
		//console.log(JSON.stringify(results))
		$scope.hoteldata = results;
	})
	.error(function(err){"ERR", console.log(err)});
	
};