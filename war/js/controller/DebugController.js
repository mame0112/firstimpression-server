impressionApp.controller('debugController', ['$scope', 'log', '$translate', 'listAPIService', 'toasterService', '$state', 'Constants',
	function($scope, log, $translate, listAPIService, toasterService, $state, Constants){
	log.d("debugController");

 	var param = {

 	};

	$scope.initialize = function(){
		log.d("initialize");
			listAPIService.get().$promise.then(function(response){
				log.d("Success");
			}).catch(function(data, status){
				log.d("Error");
			});
				// {param : param}, function(response){
	 		// log.d("message sent");

	 		// if(response !== null && response !== undefined){

				// // toasterService.showSuccessToasterLong(message_send_result, successfully_sent);
	 		// } else {
	 		// 	//Error handling
				// // toasterService.showErrorToasterLong(message_send_result, send_failed);
	 		// }

	 	// });
	};


}]);