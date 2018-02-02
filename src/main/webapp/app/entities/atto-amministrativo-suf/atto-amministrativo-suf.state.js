(function() {
    'use strict';

    angular
        .module('albopretorioApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('atto-amministrativo-suf', {
            parent: 'entity',
            url: '/atto-amministrativo-suf',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AttoAmministrativos'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atto-amministrativo-suf/atto-amministrativossuf.html',
                    controller: 'AttoAmministrativoSufController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('atto-amministrativo-suf-detail', {
            parent: 'atto-amministrativo-suf',
            url: '/atto-amministrativo-suf/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AttoAmministrativo'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atto-amministrativo-suf/atto-amministrativo-suf-detail.html',
                    controller: 'AttoAmministrativoSufDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AttoAmministrativo', function($stateParams, AttoAmministrativo) {
                    return AttoAmministrativo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'atto-amministrativo-suf',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('atto-amministrativo-suf-detail.edit', {
            parent: 'atto-amministrativo-suf-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atto-amministrativo-suf/atto-amministrativo-suf-dialog.html',
                    controller: 'AttoAmministrativoSufDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AttoAmministrativo', function(AttoAmministrativo) {
                            return AttoAmministrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atto-amministrativo-suf.new', {
            parent: 'atto-amministrativo-suf',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atto-amministrativo-suf/atto-amministrativo-suf-dialog.html',
                    controller: 'AttoAmministrativoSufDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numdetermina: null,
                                datadetermina: null,
                                oggetto: null,
                                allegato: null,
                                allegatoContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('atto-amministrativo-suf', null, { reload: 'atto-amministrativo-suf' });
                }, function() {
                    $state.go('atto-amministrativo-suf');
                });
            }]
        })
        .state('atto-amministrativo-suf.edit', {
            parent: 'atto-amministrativo-suf',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atto-amministrativo-suf/atto-amministrativo-suf-dialog.html',
                    controller: 'AttoAmministrativoSufDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AttoAmministrativo', function(AttoAmministrativo) {
                            return AttoAmministrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atto-amministrativo-suf', null, { reload: 'atto-amministrativo-suf' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atto-amministrativo-suf.delete', {
            parent: 'atto-amministrativo-suf',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atto-amministrativo-suf/atto-amministrativo-suf-delete-dialog.html',
                    controller: 'AttoAmministrativoSufDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AttoAmministrativo', function(AttoAmministrativo) {
                            return AttoAmministrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atto-amministrativo-suf', null, { reload: 'atto-amministrativo-suf' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
