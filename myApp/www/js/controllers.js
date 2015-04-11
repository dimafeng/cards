"use strict";
angular.module('starter.controllers', [])

    .controller('AppCtrl', function ($scope, $ionicModal, $timeout) {
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
            console.log('Doing login', $scope.loginData);

            // Simulate a login delay. Remove this and replace with your login
            // code if using a login system
            $timeout(function () {
                $scope.closeLogin();
            }, 1000);
        };
    })

    .controller('PlaylistsCtrl', function ($scope) {


        /**
         * TODO redo
         */
        $scope.playlists = [
            {title: 'Names', id: 1},
            {title: 'Test', id: 2},
        ];
    })

    .controller('PlaylistCtrl', function ($scope, $stateParams) {

        var data = [
            {word: 'test', translation: 'тест'},
            {word: 'cat', translation: 'кошка'}
        ];

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

        $scope.finished = function() {
            return data.length <= position;
        }

        $scope.restart = function () {
            position = 0;
        }
    });
