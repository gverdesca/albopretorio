(function() {
    'use strict';
    angular
        .module('albopretorioApp')
        .factory('AttoAmministrativo', AttoAmministrativo);

    AttoAmministrativo.$inject = ['$resource', 'DateUtils'];

    function AttoAmministrativo ($resource, DateUtils) {
        var resourceUrl =  'api/atto-amministrativos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datadetermina = DateUtils.convertLocalDateFromServer(data.datadetermina);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datadetermina = DateUtils.convertLocalDateToServer(copy.datadetermina);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datadetermina = DateUtils.convertLocalDateToServer(copy.datadetermina);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
