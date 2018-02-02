(function() {
    'use strict';

    angular
        .module('albopretorioApp')
        .controller('AttoAmministrativoSufDetailController', AttoAmministrativoSufDetailController);

    AttoAmministrativoSufDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'AttoAmministrativo'];

    function AttoAmministrativoSufDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, AttoAmministrativo) {
        var vm = this;

        vm.attoAmministrativo = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('albopretorioApp:attoAmministrativoUpdate', function(event, result) {
            vm.attoAmministrativo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
