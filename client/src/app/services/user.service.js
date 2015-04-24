'use strict';

angular.module('client').service("UserService", function ($http, BASE_URL) {
    var currentUser = null;

    (function () {
        $http.get(BASE_URL + 'users/').success(function (res) {
            currentUser = res;
        });
    })();

    return {
        signUp: function (credentials) {
            $http.post(BASE_URL + 'users/signup', credentials).success(function (res) {
                console.log(res);
            });
        },
        login: function (credentials) {
            $http.post(BASE_URL + 'login', $.param(credentials), {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).success(function (res) {
                currentUser = JSON.parse(res);
            });
        },
        isLoggedIn: function () {
            return currentUser != null;
        }
    };
});
