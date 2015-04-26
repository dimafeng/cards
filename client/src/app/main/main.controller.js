'use strict';

angular.module('client')
    .controller('MainCtrl', function ($scope, $resource, BASE_URL) {
        var Playlist = $resource(BASE_URL + 'playlists/:id', {id: '@id'}, {
            //'draft': { url: '/admin/articles/:id/draft', method: 'POST' }
        });

        $scope.playlists = [];
        $scope.currentPlaylist = null;

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

        $scope.addNewSubmit = function () {
            console.log($scope.newCard.origin);
            console.log($scope.newCard.description);
        };
    });
