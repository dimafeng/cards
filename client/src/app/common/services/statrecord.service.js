angular.module('starter').service("StatRecordService", function ($resource, BASE_URL) {
    return {
        getResource: function () {
            return $resource(BASE_URL + 'statRecords/:id', {id: '@id'});
        }
    }
});
