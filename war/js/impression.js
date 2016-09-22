var impressionApp = angular.module('impressionApp', [
	'ngResource', 
	// 'ngCookies', 
	'ui.bootstrap',
	'ui.router',
	'pascalprecht.translate',
	'toaster'
	])
.controller('ImpressionController', ['$scope', 'log', function($scope, log){
	log.d("ImpressionController");
}])

.config(['$stateProvider', '$urlRouterProvider', '$translateProvider',
 function($stateProvider, $urlRouterProvider, $translateProvider) {

	// Translate setting
	$translateProvider.useStaticFilesLoader({
        prefix : '../lang/lang_',
        suffix : '.json'
    });

	$translateProvider.preferredLanguage('ja');
	$translateProvider.fallbackLanguage('en');
	$translateProvider.useMissingTranslationHandlerLog();
	// $translateProvider.useLocalStorage();
	$translateProvider.useSanitizeValueStrategy('escaped');

 	$urlRouterProvider.otherwise('/');
	$stateProvider.
		state('/', {
			url: '/',
			templateUrl: 'view/toppage.html'
		}).
		state('overview', {
			url: '/overview',
			templateUrl: 'view/overview.html'
		}).
		state('tos', {
			url: '/tos',
			templateUrl: 'view/tos.html'
		}).
		state('privacy', {
			url: '/privacy',
			templateUrl: 'view/privacypolicy.html'
		}).
		state('contact', {
			url: '/contact',
			templateUrl: 'view/contact.html'
		}).
		state('debug', {
			url: '/debug',
			templateUrl: 'view/debug.html'
		});
	
}]);