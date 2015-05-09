'use strict';

angular.module('starter')
    .controller('NavbarCtrl', function ($scope, PlaylistService) {
        var Playlist = PlaylistService.getResource();

        $scope.playlists = [];

        var updateList = function () {
            Playlist.query(function (playlists) {
                $scope.playlists = playlists;
            });
        };

        updateList();

        $scope.add = function () {
            var playlist = new Playlist();
            playlist.name = $scope.newPlaylist;
            playlist.$save().then(function (playlist) {
                $scope.edit(playlist);
                updateList();
            });
        };

        $scope.edit = function (playlist) {
            $scope.currentPlaylist = playlist;
        };

        $scope.itemPlaylistsColapse = false;
        $scope.togglePlaylistsItem = function () {
            $scope.itemPlaylistsColapse = !$scope.itemPlaylistsColapse;
        }
    });
