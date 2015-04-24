'use strict';

angular.module('client').service("UserService", function ($http, BASE_URL, $state) {
    var currentUser = null;

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
                currentUser = res;
                $state.go('home');
            });
        },
        isLoggedIn: function () {
            return currentUser != null;
        },
        checkAuth: function() {
          $http.get(BASE_URL + 'users').success(function (res) {
            currentUser = res;
            $state.go('home');
          });
        }
    };
});
