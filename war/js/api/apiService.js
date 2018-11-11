impressionApp.factory('contactAPIService', ['$resource', 'log', 'Constants',
 function($resource, log, Constants) {
 	log.d("contactAPIService");
    return $resource(Constants.API_V1 + Constants.CONTACT,
         {servlet_resp_id: 1},
         { 
            //Search widoms by given keyword
            mail: {method: 'GET', isArray: false},
        });
    }
])

.factory('listAPIService', ['$resource', 'log', 'Constants',
 function($resource, log, Constants){
	log.d("listAPIService");
	return $resource(Constants.API_V1 + Constants.LIST, 
		{param: "param"},
		{
	        get: {method: 'GET', isArray: true},
		})
	}
]);