app.controller('mainController', [
  '$scope',
  'catalogService',
  'utils',
  function ($scope, catalogService, utils) {
    var vendor = window.location.pathname.substring(1);
    var selectedCategoryIndex, selectedCategoryId,
        selectedServiceIndex, selectedServiceId;

    main();

    $scope.getServices = function (event, index) {
      if (!$scope.tree[index].services.length) {
        selectedCategoryIndex = index;
        selectedCategoryId = $scope.tree[selectedCategoryIndex].id;

        catalogService
            .getServices(vendor, selectedCategoryId)
            .then(function (response) {
              $scope.tree[selectedCategoryIndex].services = response.data;
              utils.collapseInTree(event);
            });
      } else {
        utils.collapseInTree(event);
      }
    };

    $scope.getQuestions = function (event, index) {
      if (!$scope.tree[selectedCategoryIndex].services[index].questions.length) {
        selectedServiceIndex = index;
        selectedServiceId = $scope
            .tree[selectedCategoryIndex]
            .services[selectedServiceIndex]
            .id;

        catalogService
            .getQuestions(vendor, selectedCategoryId, selectedServiceId)
            .then(function (response) {
              $scope
                  .tree[selectedCategoryIndex]
                  .services[selectedServiceIndex]
                  .questions = response.data;

              utils.collapseInTree(event);
            });
      } else {
        utils.collapseInTree(event);
      }
    };

    $scope.collapseInTree = function (event, haveChildrens) {
      haveChildrens && utils.collapseInTree(event);
    };

    function main () {
      if (vendor !== '') {
        catalogService
            .getCategories(vendor)
            .then(function (response) {
              $scope.tree = response.data;
            });

        catalogService
            .getStatistic(vendor)
            .then(function (response) {
              $scope.statistic = response.data;
            });
      }
    };
  },
]);