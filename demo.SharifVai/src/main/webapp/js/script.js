var app = angular.module("myApp", []);
app.controller("myController", function($scope, $http) {
	$scope.ip = 'http://localhost:8081';
	$scope.product = [];
	refreshCatagoryData();
	function refreshCatagoryData() {
		$http({
			method : 'GET',
			url : $scope.ip + '/allProduct'
		}).then(function(response) {
			$scope.product = response.data;
			console.log($scope.product);
		});
	}
});