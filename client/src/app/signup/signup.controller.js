'use strict';

angular.module('client')
    .controller('SignupCtrl', function ($scope, UserService) {
        $scope.submit = function () {
            UserService.signUp($scope.credentials);
        }
    });
