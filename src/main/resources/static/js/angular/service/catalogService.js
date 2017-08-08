app.service('catalogService', catalogService);

function catalogService ($http) {
  this.getStatistic = function (vendor) {
    return $http.get('/api/catalog/' + vendor + '/statistic');
  };

  this.getCategories = function (vendor) {
    return $http.get('/api/catalog/' + vendor + '/categories');
  };

  this.getServices = function (vendor, categoryId) {
    return $http.get(
        '/api/catalog/' + vendor + '/categories/' + categoryId + '/services');
  };

  this.getQuestions = function (vendor, categoryId, serviceId) {
    return $http.get(
        '/api/catalog/' + vendor + '/categories/' + categoryId + '/services/' +
        serviceId + '/questions');
  };
}