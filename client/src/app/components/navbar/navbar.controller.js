'use strict';

angular.module('starter')
    .controller('NavbarCtrl', function ($scope, PlaylistService, $stateParams, $state) {
        var Playlist = PlaylistService.getResource();

        $scope.itemPlaylistsColapse = true;

        $scope.playlists = [];
        $scope.selected = null;

        var updateList = function () {
            console.log($stateParams);
            Playlist.query(function (playlists) {
                $scope.playlists = playlists;
            });
        };
        (function() {
            updateList();
            if($state.current.name === 'playlist') {
                //$scope.itemPlaylistsColapse = true;
                $scope.selected = $stateParams.id;
            }
        })();

        $scope.selectedClass = function (id) {
            return $scope.selected === id ? 'active' : '';
        };

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

        $scope.togglePlaylistsItem = function () {
            $scope.itemPlaylistsColapse = !$scope.itemPlaylistsColapse;
        }
    });
