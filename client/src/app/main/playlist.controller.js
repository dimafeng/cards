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
                $scope.resetEditing();
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

        $scope.editingCard = null;
        var editingHeader = false;
        var editingDescription = false;
        var setEditing = function (card) {
            editingHeader = false;
            editingDescription = false;
            $scope.editingCard = angular.copy(card);
        };

        $scope.isEditingHeader = function (card) {
            return editingHeader && card.id === $scope.editingCard.id;
        };

        $scope.setEditingHeader = function (card) {
            setEditing(card);
            editingHeader = true;
        };

        $scope.isEditingDescription = function (card) {
            return editingDescription && card.id === $scope.editingCard.id;
        };

        $scope.setEditingDescription = function (card) {
            setEditing(card);
            editingDescription = true;
        };

        $scope.saveEditing = function () {
            new Card($scope.editingCard).$save().then(function () {
                updateEditedCards();
            });
        };

        $scope.resetEditing = function () {
            editingHeader = false;
            editingDescription = false;
            $scope.editingCard = null;
        };

        $scope.removeCard = function (card) {
            card.$remove().then(function () {
                updateEditedCards();
            });
        }
    });
