let displayApp = angular.module("displayApp", []);
displayApp.controller("DisplayAppCtrl", function($scope, $http){

    $scope.users = [];

    $http.get('/api/display').then(function(resp){
        $scope.users = resp.data;
    }, function () {
        $scope.users = [{
            firstName: 'Undefined',
            lastName: 'Undefined',
            login: 'Undefined',
            password: 'Undefined'
        }];
    });
});