'use strict';

angular.module('starter').service("RouteService", function ($state) {
    return {
        goAfterLogin: function () {
            $state.go('app.playlists');
        },
        redirectIfNotLoggedIn: function() {
            $state.go('login');
        }
    };
});