'use strict';

angular.module('starter')
    .controller('MainCtrl', function ($scope, $resource, BASE_URL) {

        var Playlist = $resource(BASE_URL + 'playlists/:id', {id: '@id'});
        var Card = $resource(BASE_URL + 'cards/:id', {id: '@id'}, {
            'byPlaylist': {
                url: BASE_URL + 'playlists/:playlistId/cards',
                method: 'GET',
                isArray: true
            }
        });

        $scope.playlists = [];
        $scope.currentPlaylist = null;
        $scope.currentCards = null;

        var updateList = function () {
            Playlist.query(function (playlists) {
                $scope.playlists = playlists;
            });
        };

        updateList();

        var updateEditedCards = function () {
            Card.byPlaylist({playlistId: $scope.currentPlaylist.id}, function (cards) {
                $scope.currentCards = cards;
                $scope.newCard = null;
            });
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
            updateEditedCards();
        };

        $scope.addNewSubmit = function () {
            var card = new Card();
            card.origin = $scope.newCard.origin;
            card.description = $scope.newCard.description;
            card.$save().then(function (res) {
                if ($scope.currentPlaylist.cardIds === null) {
                    $scope.currentPlaylist.cardIds = [];
                }
                $scope.currentPlaylist.cardIds.push(res.id);
                $scope.currentPlaylist.$save().then(function () {
                    updateEditedCards();
                });
            });
        };
    });
