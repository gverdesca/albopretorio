(function() {
    'use strict';

    angular
        .module('albopretorioApp')
        .controller('AttoAmministrativoSufDialogController', AttoAmministrativoSufDialogController);

    AttoAmministrativoSufDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'AttoAmministrativo'];

    function AttoAmministrativoSufDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, AttoAmministrativo) {
        var vm = this;

        vm.attoAmministrativo = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.attoAmministrativo.id !== null) {
                AttoAmministrativo.update(vm.attoAmministrativo, onSaveSuccess, onSaveError);
            } else {
                AttoAmministrativo.save(vm.attoAmministrativo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('albopretorioApp:attoAmministrativoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datadetermina = false;

        vm.setAllegato = function ($file, attoAmministrativo) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        attoAmministrativo.allegato = base64Data;
                        attoAmministrativo.allegatoContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
