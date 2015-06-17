'use strict';

angular.module('starter').service("RouteService", function ($state) {
    return {
        goAfterLogin: function () {
            $state.go('dashboard');
        },
        redirectIfNotLoggedIn: function () {
            $state.go('login');
        }
    };
});
