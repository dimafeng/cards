'use strict';

angular.module('starter')
    .controller('MainCtrl', function ($scope, $resource, BASE_URL, CardService, $stateParams, PlaylistService) {

        var Card = CardService.getResource();
        var Playlist = PlaylistService.getResource();

        $scope.currentCards = null;

        var updateEditedCards = function () {
            Card.byPlaylist({playlistId: $stateParams.id}, function (cards) {
                $scope.currentCards = cards;
                $scope.newCard = null;
            });
        };

        $scope.addNewSubmit = function () {
            var card = new Card();
            card.origin = $scope.newCard.origin;
            card.description = $scope.newCard.description;

            card.$save().then(function (res) {
                //Playlist.get({id: })
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
