angular.module('starter').service("PlaylistService", function ($resource, BASE_URL) {
    return {
        getResource: function () {
            return $resource(BASE_URL + 'playlists/:id', {id: '@id'});
        }
    }
});
