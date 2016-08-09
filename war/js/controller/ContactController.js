impressionApp.controller('contactController', ['$scope', 'log', '$translate', 'contactAPIService', 'toasterService', '$state', 
	function($scope, log, $translate, contactAPIService, toasterService, $state){
	log.d("contactController");

 	var name = null;
 	var email = null;
 	var message = null;

 	var message_send_result = null;
 	var successfully_sent = null;
 	var send_failed = null;

 	var param = {
 		"name": name,
 		"email": email,
 		"message": message
 	};

	$scope.initialize = function(){
		log.d("initialize");

		$translate([
			'contact.message_send_result',
			'contact.successfully_sent',
			'contact.send_failed'
			])
		.then(function (translations) {
			message_send_result = translations['contact.message_send_result'];
			successfully_sent = translations['contact.successfully_sent'];
			send_failed = translations['contact.send_failed'];
		});
	};

	$scope.isSubmitButtonDisable = function(){
		if($scope.name !== undefined && $scope.email !== undefined && $scope.message !== undefined){
 			if($scope.name.length !== 0 && $scope.email.length !== 0 && $scope.message.length !== 0){
 				return false;
 			}
 		}

 		return true;
	};

	$scope.sendMessage = function(){
		log.d("sendMessage");

 		log.d("result/ " + " name: " + param.name + " email: " + param.email + " message: " + param.message);

		contactAPIService.mail({param : param}, function(response){
	 		log.d("message sent");

	 		if(response !== null && response !== undefined){
	 			if(isMessageSentSuccessfully(response)){
	 				log.d("aaaa");
					toasterService.showSuccessToasterLong(message_send_result, successfully_sent);
					// $state.go('/');
	 			} else {
					toasterService.showErrorToasterLong(message_send_result, send_failed);
	 			}

	 		} else {
	 			//Error handling
				toasterService.showErrorToasterLong(message_send_result, send_failed);
	 		}

	 	});

	};

 	$scope.$watch('name', function(newValue, oldValue) {
		param.name = newValue;
	});

 	$scope.$watch('email', function(newValue, oldValue) {
		param.email = newValue;
	});

 	$scope.$watch('message', function(newValue, oldValue) {
		param.message = newValue;
	});

	isMessageSentSuccessfully = function(response){
		log.d("isMessageSentSuccessfully");
		return true;
	};



}]);