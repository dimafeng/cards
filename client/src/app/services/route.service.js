'use strict';

angular.module('starter').service("RouteService", function ($state) {
    return {
        goAfterLogin: function () {
            $state.go('playlist');
        },
        redirectIfNotLoggedIn: function () {
            $state.go('login');
        }
    };
});
