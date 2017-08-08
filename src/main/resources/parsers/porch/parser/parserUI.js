var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.2.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

var csrf ='a3fc2d';
var catalogLink =  'https://porch.com/api-consumer//autocompleteCompanyType?_csrf=' + csrf + '&lang=en-US&returnMeta=true';
var serviceLink = 'https://porch.com/api-consumer//guidedFormPublished;tradeId=0000?_csrf=' + csrf + '&lang=en-US&returnMeta=true';

var catalog = [];

var getInfo = function () {

    $.ajax( catalogLink )
        .done(function(data) {
            console.log( "success" );
            catalog = data.data;
            getQuestions(catalog);
            // console.log(JSON.stringify(catalog));
        })
        .fail(function(data) {
            console.log( "error" );

        })
        .always(function(data) {
            console.log( "complete" );
        });
};

function getQuestions(catalog) {

    console.log( "catalog.length = " + catalog.length);

    var timeout = 0;

    $.each(catalog, function( index, value ) {

        timeout+= 1000;

        setTimeout(function(){
            var link = serviceLink.replace('0000', value.id);

            $.ajax( link )
                .done(function(data) {
                    console.log( "success" );
                    value.questionary = data.data;
                })
                .fail(function(data) {
                    value.questionary = {};
                })
                .always(function(data) {
                    console.log( "complete" );
                    console.log(index);
                    // console.log(JSON.stringify(value));
                });
        }, timeout);

    });

    setTimeout(function(){
        console.log(JSON.stringify(catalog))
    }, (catalog.length + 10) * 1000 );

}

