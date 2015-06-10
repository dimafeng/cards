'use strict';

angular.module('starter', ['ngSanitize', 'ngResource', 'ui.router', 'ui.bootstrap', 'chart.js'])
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider
            .state('playlist', {
                url: '/playlist/:id',
                templateUrl: 'app/main/playlist.html',
                controller: 'MainCtrl'
            })
            .state('signup', {
                url: '/signup',
                templateUrl: 'app/signup/signup.html',
                controller: 'SignupCtrl'
            })
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'app/dashboard/dashboard.html',
                controller: 'DashboardCtrl'
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
