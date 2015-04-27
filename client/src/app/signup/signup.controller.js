'use strict';

angular.module('starter')
    .controller('SignupCtrl', function ($scope, UserService) {
        $scope.submit = function () {
            UserService.signUp($scope.credentials);
        }
    });
