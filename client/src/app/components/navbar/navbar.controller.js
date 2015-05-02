'use strict';

angular.module('starter')
  .controller('NavbarCtrl', function ($scope) {
    $scope.itemPlaylistsColapse = false;
    $scope.togglePlaylistsItem = function() {
      $scope.itemPlaylistsColapse = !$scope.itemPlaylistsColapse;
    }
  });
