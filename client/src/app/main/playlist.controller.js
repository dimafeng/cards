'use strict';

angular.module('starter')
    .controller('MainCtrl', function ($scope, $resource, BASE_URL, CardService, $stateParams, PlaylistService) {

        var Card = CardService.getResource();
        var Playlist = PlaylistService.getResource();

        $scope.currentCards = null;
        $scope.currentPlaylist = null;

        var updateEditedCards = function () {
            Card.byPlaylist({playlistId: $stateParams.id}, function (cards) {
                $scope.currentCards = cards;
                $scope.newCard = null;
            });
        };

        (function () {
            updateEditedCards();
            Playlist.get({id: $stateParams.id}, function (currentPlaylist) {
                $scope.currentPlaylist = currentPlaylist;
            })
        })();

        $scope.addNewSubmit = function () {
            var card = new Card();
            card.origin = $scope.newCard.origin;
            card.description = $scope.newCard.description;

            card.$save().then(function (res) {
                Playlist.get({id: $stateParams.id}, function (playlist) {
                    if (playlist.cardIds === null) {
                        playlist.cardIds = [];
                    }
                    playlist.cardIds.push(res.id);
                    playlist.$save().then(function () {
                        updateEditedCards();
                    });
                });
            });
        };
    });
