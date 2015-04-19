'use strict';

angular.module('client', ['ngSanitize', 'ngResource', 'ui.router', 'ui.bootstrap'])
  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .state('signup', {
        url: '/',
        templateUrl: 'app/signup.html',
        controller: 'SignupCtrl'
      });

    $urlRouterProvider.otherwise('/');
  })
;
