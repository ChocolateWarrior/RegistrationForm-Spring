let requestDisplayApp = angular.module("requestDisplayApp", []);
requestDisplayApp.controller("RequestDisplayAppCtrl", function($scope, $http){

    $scope.requests = [];

    $http.get('/api/request-display').then(function(resp){
        $scope.requests = resp.data;
    }, function () {
        $scope.requests = [{
            type: 'Undefined',
            description: 'Undefined',
            request_time: 'Undefined'
        }];
    });
});
