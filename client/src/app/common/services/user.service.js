'use strict';

angular.module('starter').service("UserService", function ($http, BASE_URL, RouteService, $q) {
    var currentUser = null;

    return {
        signUp: function (credentials) {
            $http.post(BASE_URL + 'users/signup', credentials).success(function (res) {
                console.log(res);
            });
        },
        login: function (credentials) {
            return $q(function (resolve, reject) {
                $http.post(BASE_URL + 'login', $.param(credentials), {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).success(function (res) {
                    currentUser = res;
                    resolve();
                    RouteService.goAfterLogin();
                }).error(function(){
                    reject();
                });
            });
        },
        isLoggedIn: function () {
            return currentUser != null;
        },
        checkAuth: function () {
            $http.get(BASE_URL + 'users').success(function (res) {
                currentUser = res;
                RouteService.goAfterLogin();
            }).error(function() {
                RouteService.redirectIfNotLoggedIn();
            });
        }
    };
});
