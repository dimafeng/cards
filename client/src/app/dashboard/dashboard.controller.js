'use strict';

angular.module('starter')
    .controller('DashboardCtrl', function ($scope, $http, BASE_URL) {
        $scope.labels = [];
        $scope.series = ['Помню', 'Не помню'];

        $scope.data = [[],[]];

        var loadData = function () {
            $http.get(BASE_URL + "statRecords/stat").success(function (data) {
                for(var i in data) {
                    $scope.labels.push(moment(new Date(data[i].value0)).format("MMM DD"));
                    $scope.data[0].push(data[i].value1);
                    $scope.data[1].push(data[i].value2);
                }
            });
        };

        loadData();
    });
