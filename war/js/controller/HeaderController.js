impressionApp.controller('headerController', ['$scope', 'log',
	function($scope, log){
	log.d("headerController");

	$scope.navbarCollapsed = true;

	$scope.switchNavivarStatus = function(){
		log.d("switchNavivarStatus");
		$scope.navbarCollapsed = !$scope.navbarCollapsed;
	};

}]);
