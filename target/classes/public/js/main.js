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

let registrationApp = angular.module("registrationApp", []);
registrationApp.controller("RegistrationCtrl", function ($scope, $http) {
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
            url: '/registration',
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


let loginApp = angular.module("loggingApp", []);
loginApp.controller("LoggingCtrl", function ($scope, $http){
    $scope.auth = {};
    let loggingMessage = document.getElementById('loggingMessageElement');
    let exampleLogin = document.getElementById('exampleLoginElement');
    let examplePassword = document.getElementById('examplePasswordElement');

    let inputLoginLabel = document.getElementById('exampleLoginLabel');
    let inputPasswordLabel = document.getElementById('examplePasswordLabel');

    exampleLogin.addEventListener('input', () => {
        inputLoginLabel.style.color = 'black';
        inputPasswordLabel.style.color = 'black';
        $scope.message = '';
    });
    $scope.sendForm = function (auth) {
        console.log(auth);
        $http({
            method: 'POST',
            url: '/login',
            data: $.param(auth),
            headers: {'Content-Type' : 'application/x-www-form-urlencoded'}
        }).then(
            (data) => {
                $scope.message = 'Logged in';
                loggingMessage.style.color = 'green';
                exampleLogin.value = '';
                examplePassword.value = '';
                inputLoginLabel.style.color = 'black';
                console.log(data);
            },
            (error) => {
                console.log(error.data);

                loggingMessage.style.color = 'red';
                inputLoginLabel.style.color = 'red';
                inputPasswordLabel.style.color = 'red';

                examplePassword.value = '';
                $scope.message = error.data.messages;
            }
        );
    };
});

