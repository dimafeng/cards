"use strict";
angular.module('starter.controllers', [])

    .controller('AppCtrl', function ($scope, $ionicModal, $timeout, UserService, $rootScope) {
        // Form data for the login modal
        $scope.loginData = {};

        // Create the login modal that we will use later
        $ionicModal.fromTemplateUrl('templates/login.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modal = modal;
        });

        // Triggered in the login modal to close it
        $scope.closeLogin = function () {
            $scope.modal.hide();
        };

        // Open the login modal
        $scope.login = function () {
            $scope.modal.show();
        };

        // Perform the login action when the user submits the login form
        $scope.doLogin = function () {
            UserService.login($scope.loginData).then(function () {
                $scope.modal.hide();
                $rootScope.$broadcast('loggedIn');
            });
        };

        $scope.isLoggedIn = function () {
            return UserService.isLoggedIn();
        }
    })

    .controller('PlaylistsCtrl', function ($scope, UserService, $rootScope, $resource, BASE_URL) {

        var Playlist = $resource(BASE_URL + 'playlists/:id', {id: '@id'});

        $rootScope.$on('loggedIn', function () {
            updatePlaylists();
        });

        var updatePlaylists = function () {
            Playlist.query(function (playlists) {
                $scope.playlists = playlists;
            });
        };

        (function () {
            if (UserService.isLoggedIn()) {
                updatePlaylists();
            }
        })();
    })

    .controller('PlaylistCtrl', function ($scope, $stateParams, CardService) {

        var Card = CardService.getResource();

        var data = [];

        (function () {
            Card.byPlaylist({playlistId: $stateParams.id}, function (cards) {
                data = cards;
            });
        })();

        var position = 0;
        $scope.showTranslation = false;

        $scope.getCurrentCard = function () {
            return data[position];
        };

        $scope.remember = function () {
            position++;
            $scope.showTranslation = false;
        };

        $scope.dontRemember = function () {
            $scope.showTranslation = true;
        };

        $scope.finished = function () {
            return data.length <= position;
        };

        $scope.restart = function () {
            position = 0;
        };
    });
