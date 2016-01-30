impressionApp.directive('headerDirective', function(){
	return {
		restrict: 'A',
		templateUrl: '/directive/header-directive.html'
	};
})

.directive('footerDirective', function(){
	return {
		restrict: 'A',
		templateUrl: 'directive/footer-directive.html'
	};
});