impressionApp.controller('headerController', ['$scope', 'log',
	function($scope, log){

	$scope.navbarCollapsed = true;

	$scope.switchNavivarStatus = function(){
		$scope.navbarCollapsed = !$scope.navbarCollapsed;
	};

}]);
