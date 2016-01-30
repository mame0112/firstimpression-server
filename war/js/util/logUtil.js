impressionApp.factory('log', function(){

    return {
        // public API
        d: function(value){
            return console.log(value);
        }
    };
});