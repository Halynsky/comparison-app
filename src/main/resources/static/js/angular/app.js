var app = angular.module('mainApp', [
  'ngCookies',
  'ngRoute',
  'ngResource',
  'ngAnimate',
  'ngMessages',
  'ngSanitize',
  'ngMaterial',
  'material.svgAssetsCache',
  'validation.match',
  'ui.mask',
  'material.components.expansionPanels',
  'angularTreeview',
]);

app.config(function ($routeProvider, $httpProvider, $locationProvider) {

  $locationProvider.html5Mode(true);

  $routeProvider
      .when('/', {
        templateUrl: 'static/views/index.html'
      })
      .when('/porch', {
        templateUrl: 'static/views/vendor.html'
      })
      .when('/thumbtack', {
        templateUrl: 'static/views/vendor.html'
      })
      .otherwise('/');

  $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});

app.run(function ($rootScope, $location, $timeout) {
});

app.config(function ($mdIconProvider) {
  $mdIconProvider
      .defaultIconSet('../styles/images/icons/material-design/core-icons.svg',
                      24)
      .iconSet('action',
               '../styles/images/icons/material-design/action-icons.svg', 24)
      .iconSet('alert',
               '../styles/images/icons/material-design/alert-icons.svg', 24)
      .iconSet('av', '../styles/images/icons/material-design/av-icons.svg', 24)
      .iconSet('communication',
               '../styles/images/icons/material-design/communication-icons.svg',
               24)
      .iconSet('content',
               '../styles/images/icons/material-design/content-icons.svg', 24)
      .iconSet('device',
               '../styles/images/icons/material-design/device-icons.svg', 24)
      .iconSet('editor',
               '../styles/images/icons/material-design/editor-icons.svg', 24)
      .iconSet('file', '../styles/images/icons/material-design/file-icons.svg',
               24)
      .iconSet('hardware',
               '../styles/images/icons/material-design/hardware-icons.svg', 24)
      .iconSet('icons',
               '../styles/images/icons/material-design/icons-icons.svg', 24)
      .iconSet('image',
               '../styles/images/icons/material-design/image-icons.svg', 24)
      .iconSet('maps', '../styles/images/icons/material-design/maps-icons.svg',
               24)
      .iconSet('navigation',
               '../styles/images/icons/material-design/navigation-icons.svg',
               24)
      .iconSet('notification',
               '../styles/images/icons/material-design/notification-icons.svg',
               24)
      .iconSet('social',
               '../styles/images/icons/material-design/social-icons.svg', 24)
      .iconSet('toggle',
               '../styles/images/icons/material-design/toggle-icons.svg', 24);
});

app.controller('mdDialogBasicController', function DemoCtrl ($mdDialog) {
  this.settings = {
    printLayout: true,
    showRuler: true,
    showSpellingSuggestions: true,
    presentationMode: 'edit',
  };
});

app.config(function ($mdThemingProvider) {

  var customPrimary = {
    '50': '#b3cce6',
    '100': '#a0bfdf',
    '200': '#8cb2d9',
    '300': '#79a6d3',
    '400': '#6699cc',
    '500': '#538cc6',
    '600': '#407fc0',
    '700': '#3972ad',
    '800': '#33669a',
    '900': '#2d5986',
    'A100': '#c6d9ec',
    'A200': '#d9e6f2',
    'A400': '#ecf2f9',
    'A700': '#264c73',

    'contrastDefaultColor': 'light',    // whether, by default, text (contrast)

    'contrastDarkColors': [
      '50', '100', //hues which contrast should be 'dark' by default
      '200', '300', '400', 'A100'],
    'contrastLightColors': 'dark'    // could also specify this if default was 'dark'
  };

  $mdThemingProvider
      .definePalette('customPrimary',
                     customPrimary);

  var customAccent = {
    '50': '#264c73',
    '100': '#2d5986',
    '200': '#336699',
    '300': '#3973ac',
    '400': '#407fbf',
    '500': '#538cc6',
    '600': '#79a6d2',
    '700': '#8cb2d9',
    '800': '#9fbfdf',
    '900': '#b3cce6',
    'A100': '#79a6d2',
    'A200': '#6699CC',
    'A400': '#538cc6',
    'A700': '#c6d9ec',
  };

  $mdThemingProvider
      .definePalette('customAccent', customAccent);

  var customBackground = {
    '50': '#737373',
    '100': '#666666',
    '200': '#595959',
    '300': '#4d4d4d',
    '400': '#404040',
    '500': '#333',
    '600': '#262626',
    '700': '#1a1a1a',
    '800': '#0d0d0d',
    '900': '#000000',
    'A100': '#808080',
    'A200': '#8c8c8c',
    'A400': '#999999',
    'A700': '#000000',
  };

  $mdThemingProvider
      .definePalette('customBackground', customBackground);

  $mdThemingProvider.theme('default')
      .primaryPalette('customPrimary')
      .accentPalette('customAccent');
});