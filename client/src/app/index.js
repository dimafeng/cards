'use strict';

angular.module('starter', ['ngSanitize', 'ngResource', 'ui.router', 'ui.bootstrap'])
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'app/main/main.html',
                controller: 'MainCtrl'
            })
            .state('signup', {
                url: '/signup',
                templateUrl: 'app/signup/signup.html',
                controller: 'SignupCtrl'
            })
            .state('login', {
                url: '/login',
                templateUrl: 'app/login/login.html',
                controller: 'LoginCtrl'
            });

        $urlRouterProvider.otherwise('/');
        $httpProvider.defaults.withCredentials = true;

    })
    .run(function (UserService) {
        UserService.checkAuth();
    });
