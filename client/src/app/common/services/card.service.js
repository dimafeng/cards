angular.module('starter').service("CardService", function ($resource, BASE_URL) {
        return {
            getResource: function () {
                return $resource(BASE_URL + 'cards/:id', {id: '@id'}, {
                        'byPlaylist': {
                            url: BASE_URL + 'playlists/:playlistId/cards',
                            method: 'GET',
                            isArray: true
                        },
                        'setByPlaylist': {
                            url: BASE_URL + 'playlists/:playlistId/cards/set',
                            method: 'GET',
                            isArray: true
                        }
                    }
                )
            }
        }
    }
);