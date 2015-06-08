"use strict";
angular.module('starter.controllers', [])

    .controller('AppCtrl', function ($scope, $timeout, UserService, $rootScope) {


        $scope.isLoggedIn = function () {
            return UserService.isLoggedIn();
        };

        $scope.logout = function () {

        };
    })

    .controller('PlaylistsCtrl', function ($scope, UserService, $rootScope, $resource, BASE_URL) {

        var Playlist = $resource(BASE_URL + 'playlists/:id', {id: '@id'});

        $rootScope.$on('loggedIn', function () {
            updatePlaylists();
        });

        $rootScope.doRefresh = function () {
            updatePlaylists();
        };

        var updatePlaylists = function () {
            Playlist.query(function (playlists) {
                $scope.$broadcast('scroll.refreshComplete');
                $scope.playlists = playlists;
            }, function () {
                $scope.$broadcast('scroll.refreshComplete');
            });
        };

        (function () {
            if (UserService.isLoggedIn()) {
                updatePlaylists();
            }
        })();
    })

    .controller('PlaylistCtrl', function ($scope, $stateParams, CardService, StatRecordService) {

        var Card = CardService.getResource();
        var StatRecord = StatRecordService.getResource();

        var data = [];

        (function () {
            Card.setByPlaylist({playlistId: $stateParams.id}, function (cards) {
                data = cards;
            });
        })();

        var position = 0;
        $scope.showTranslation = false;
        var stat = {};

        $scope.getCurrentCard = function () {
            return data[position];
        };

        var updateCard = function (level) {
            var card = $scope.getCurrentCard();
            card.level = level;
            card.lastCheck = new Date();
            card.$save();

            stat[card.id] = level;
        };

        $scope.remember = function () {
            updateCard(1);
            $scope.next();
        };

        $scope.next = function () {
            position++;
            if($scope.finished()) {
                var statRecord = new StatRecord();
                statRecord.statistic = stat;
                statRecord.$save();
            }
            $scope.showTranslation = false;
        };

        $scope.dontRemember = function () {
            updateCard(-1);
            $scope.showTranslation = true;
        };

        $scope.finished = function () {
            return data.length <= position;
        };

        $scope.restart = function () {
            position = 0;
            stat = {};
        };
    })
    .controller('LoginCtrl', function ($scope, $timeout, UserService, $rootScope) {

        $scope.loginData = {};

        $scope.doLogin = function () {
            UserService.login($scope.loginData).then(function () {
                $rootScope.$broadcast('loggedIn');
            });
        };

        $scope.isLoggedIn = function () {
            return UserService.isLoggedIn();
        };

        $scope.logout = function () {
            //UserService.isLoggedIn();
        };
    });
