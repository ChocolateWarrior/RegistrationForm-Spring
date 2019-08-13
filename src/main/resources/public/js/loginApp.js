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