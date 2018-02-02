(function() {
    'use strict';

    angular
        .module('albopretorioApp')
        .controller('AttoAmministrativoSufDeleteController',AttoAmministrativoSufDeleteController);

    AttoAmministrativoSufDeleteController.$inject = ['$uibModalInstance', 'entity', 'AttoAmministrativo'];

    function AttoAmministrativoSufDeleteController($uibModalInstance, entity, AttoAmministrativo) {
        var vm = this;

        vm.attoAmministrativo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AttoAmministrativo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
