'use strict';

angular.module('client')
    .controller('LoginCtrl', function ($scope, UserService) {
        $scope.submit = function () {
            UserService.login($scope.credentials);
        }
    });
