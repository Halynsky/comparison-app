app.directive("impRequestServiceForm", function() {
    return {
        replace: true,
        templateUrl : "static/partials/serviceRequestDialog/serviceRequestDialogTemplate.html"
    };
});

app.directive("impRequestQuestion", function() {
    return {
        replace: true,
        templateUrl : "static/partials/serviceRequestDialog/serviceRequestDialogQuestionTemplate.html"
    };
});

app.directive('showDuringResolve', function($rootScope) {

    return {
        link: function(scope, element) {

            var routeChangeStart = $rootScope.$on('$routeChangeStart', function() {
                element.removeClass('ng-hide');
            });

            var routeChangeSuccess = $rootScope.$on('$routeChangeSuccess', function() {
                element.addClass('ng-hide');
            });

            scope.$on('$destroy', routeChangeStart);
            scope.$on('$destroy', routeChangeSuccess);
        }
    };
});

app.directive('hideDuringResolve', function($rootScope) {

    return {
        link: function(scope, element) {

            var routeChangeStart = $rootScope.$on('$routeChangeStart', function() {
                element.addClass('ng-hide');
            });

            var routeChangeSuccess = $rootScope.$on('$routeChangeSuccess', function() {
                element.removeClass('ng-hide');
            });

            scope.$on('$destroy', routeChangeStart);
            scope.$on('$destroy', routeChangeSuccess);
        }
    };
});

app.directive('impNumeric', function () {

    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ctrl) {

            function numericValidator(ngModelValue) {
                ctrl.$setValidity('numeric', /^[+]?\d+([.]\d+)?$/.test(ngModelValue));
                return ngModelValue;
            }

            ctrl.$parsers.push(numericValidator);

        }
    }


});

app.directive('myRepeatDirective', function() {
    return function (scope, element, attrs) {
        console.log("myRepeatDirective");
        if (scope.$last) {
            console.log("im the last!");
        }
    };
});

