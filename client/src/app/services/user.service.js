'use strict';

angular.module('client').service("UserService", function ($http, BASE_URL) {
  return {
    signUp: function(credentials) {
      $http.post(BASE_URL + 'users/signup', credentials).success(function(res) {
        console.log(res);
      });
    }
  };
});
