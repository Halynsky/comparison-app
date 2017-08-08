app.filter('keyboardShortcut', function($window) {
    return function(str) {
        if (!str) return;
        var keys = str.split('-');
        var isOSX = /Mac OS X/.test($window.navigator.userAgent);

        var seperator = (!isOSX || keys.length > 2) ? '+' : '';

        var abbreviations = {
            M: isOSX ? '' : 'Ctrl',
            A: isOSX ? 'Option' : 'Alt',
            S: 'Shift'
        };

        return keys.map(function(key, index) {
            var last = index == keys.length - 1;
            return last ? key : abbreviations[key];
        }).join(seperator);
    };
});

app.filter('getById', function() {
    return function(input, id) {

        for (var i=0; i<input.length; i++) {
            if (input[i].id == id) {
                return input[i];
            }
        }

        return null;

    }
});

app.filter('getByTrue', function() {
    return function(input, idJson) {

        var idArr = [];
        var resultArr = [];

        for (var key in idJson) {
            if (idJson.hasOwnProperty(key)) {
                idArr.push(key)
            }
        }

        for (var i=0; i<input.length; i++) {
            if (idArr.indexOf(input[i].id.toString()) > -1) {
                resultArr.push(input[i]) ;
            }
        }

        return resultArr;

    }
});

app.filter('getDateFromDateTime', function() {
    return function(input) {
        return input.split(" ")[0];
    }
});