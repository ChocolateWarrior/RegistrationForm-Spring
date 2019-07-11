let masterRegistrationApp = angular.module("masterRegistrationApp", []);
masterRegistrationApp.controller("MasterRegistrationCtrl", function ($scope, $http) {
    $scope.auth = {};
    let resMessage = document.getElementById('registrationMessage');
    let firstNameExample = document.getElementById('firstNameExampleElement');
    let lastNameExample = document.getElementById('lastNameExampleElement');
    let loginExample = document.getElementById('loginExampleElement');
    let passwordExample = document.getElementById('passwordExampleElement');

    let inputNameLabel = document.getElementById('firstNameInputLabel');
    let inputLoginLabel = document.getElementById('loginInputLabel');

    loginExample.addEventListener('input', () => {
        inputNameLabel.style.color = 'black';
        inputLoginLabel.style.color = 'black';
        $scope.message = 'msg';
    });

    $scope.sendForm = function (auth) {
        $http({
            method: 'POST',
            url: '/master-registration',
            data: $.param(auth),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(
            (data) => {
                resMessage.style.color = 'green';
                $scope.message = 'Successfully registered';
                firstNameExample.value = '';
                lastNameExample.value = '';
                loginExample.value = '';
                passwordExample.value = '';
                inputLoginLabel.style.color = 'black';
            },
            (error) => {
                console.log(error.data);
                resMessage.style.color = 'red';
                inputLoginLabel.style.color = 'red';
                $scope.message = error.data.messages;
            }
        );
    }
});