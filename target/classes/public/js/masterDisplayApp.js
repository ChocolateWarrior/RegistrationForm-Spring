let masterDisplayApp = angular.module("masterDisplayApp", []);
masterDisplayApp.controller("MasterDisplayAppCtrl", function($scope, $http){

    $scope.masters = [];

    $http.get('/api/master-display').then(function(resp){
        $scope.masters = resp.data;
    }, function () {
        $scope.masters = [{
            firstName: 'Undefined',
            lastName: 'Undefined',
            login: 'Undefined',
            password: 'Undefined'
        }];
    });
});