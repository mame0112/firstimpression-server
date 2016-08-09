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
})

.directive('topHeroshotDirective', function(){
	return {
		restrict: 'A',
		templateUrl: 'directive/top-heroshot-directive.html'
	};
})

.directive('topDescriptionDirective', function(){
	return {
		restrict: 'A',
		templateUrl: 'directive/top-description-directive.html'
	};
})

.directive('contactContentDirective', function(){
	return {
		restrict: 'A',
		templateUrl: 'directive/contact-content-directive.html'
	};
})

.directive('contactNoteDirective', function(){
	return {
		restrict: 'A',
		templateUrl: 'directive/contact-note-directive.html'
	};
})

.directive('toasterDirective', function(){
	return {
		restrict: 'A',
		templateUrl: 'directive/toaster-directive.html'
	};
});