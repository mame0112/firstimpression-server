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
});