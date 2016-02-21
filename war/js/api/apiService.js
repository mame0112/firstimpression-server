impressionApp.factory('contactAPIService', ['$resource', 'log', 'Constants',
 function($resource, log, Constants) {
 	log.d("AAAA");
    return $resource(Constants.API_V1 + Constants.CONTACT,
         {servlet_resp_id: 1},
         { 
            //Search widoms by given keyword
            mail: {method: 'GET', isArray: false},
        });
    }
]);