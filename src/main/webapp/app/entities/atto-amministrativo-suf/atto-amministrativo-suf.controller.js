(function() {
    'use strict';

    angular
        .module('albopretorioApp')
        .controller('AttoAmministrativoSufController', AttoAmministrativoSufController);

    AttoAmministrativoSufController.$inject = ['DataUtils', 'AttoAmministrativo'];

    function AttoAmministrativoSufController(DataUtils, AttoAmministrativo) {

        var vm = this;

        vm.attoAmministrativos = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            AttoAmministrativo.query(function(result) {
                vm.attoAmministrativos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
