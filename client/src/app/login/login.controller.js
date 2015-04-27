'use strict';

angular.module('starter')
    .controller('LoginCtrl', function ($scope, UserService) {
        $scope.submit = function () {
            UserService.login($scope.credentials);
        }
    });
