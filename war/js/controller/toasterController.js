impressionApp.controller('toasterController',
 ['log', '$scope', 'toaster', 'Constants', '$timeout',
    function(log, $scope, toaster, Constants, $timeout){

 	log.d("toasterController");

    var isUpdated = false;
    var promise = null;


    $scope.showSuccessToasterShort = function(title, text){
        toaster.pop('success', title, text, Constants.TOASTER_SHORT);
    };

    $scope.showWaitToasterShort = function(title, text){
	    toaster.pop('wait', title, text, Constants.TOASTER_SHORT);
    };

    $scope.showErrorToasterShort = function(title, text){
	    toaster.pop('error', title, text, Constants.TOASTER_SHORT);
    };

    $scope.showNoteToasterShort = function(title, text){
	    toaster.pop('note', title, text, Constants.TOASTER_SHORT);
    };

    $scope.showSuccessToasterLong = function(title, text){
        toaster.pop('success', title, text, Constants.TOASTER_LONG);
    };

    $scope.showWaitToasterLong = function(title, text){
	    toaster.pop('wait', title, text, Constants.TOASTER_LONG);
    };

    $scope.showErrorToasterLong = function(title, text){
	    toaster.pop('error', title, text, Constants.TOASTER_LONG);
    };

    $scope.showNoteToasterLong = function(title, text){
	    toaster.pop('note', title, text, Constants.TOASTER_LONG);
    };

    $scope.$on("$destroy", function(event){
        $timeout.cancel(promise);
    });

 }]);