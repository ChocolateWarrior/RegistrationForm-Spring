let requestApp = angular.module("requestApp", []);
requestApp.controller("RequestCtrl", function ($scope, $http) {
    $scope.auth = {};
    let resMessage = document.getElementById('requestMessage');
    let TypeExample = document.getElementById('TypeElement');
    let DescriptionExample = document.getElementById('DescriptionElement');

    let TypeLabel = document.getElementById('TypeLabel');
    let DescriptionLabel = document.getElementById('DescriptionLabel');

    $scope.sendForm = function (auth) {
        $http({
            method: 'POST',
            url: '/request',
            data: $.param(auth),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(
            (data) => {
                resMessage.style.color = 'green';
                $scope.message = 'Request successful!';
                TypeExample.value = '';
                DescriptionExample.value = '';
                TypeLabel.style.color = 'black';
                DescriptionLabel.style.color = 'black';
            },
            (error) => {
                console.log(error.data);
                resMessage.style.color = 'red';
                TypeLabel.style.color = 'red';
                DescriptionLabel.style.color = 'red';
                $scope.message = error.data.messages;
            }
        );
    }
});
