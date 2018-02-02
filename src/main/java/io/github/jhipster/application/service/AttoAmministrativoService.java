package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.AttoAmministrativo;
import java.util.List;

/**
 * Service Interface for managing AttoAmministrativo.
 */
public interface AttoAmministrativoService {

    /**
     * Save a attoAmministrativo.
     *
     * @param attoAmministrativo the entity to save
     * @return the persisted entity
     */
    AttoAmministrativo save(AttoAmministrativo attoAmministrativo);

    /**
     * Get all the attoAmministrativos.
     *
     * @return the list of entities
     */
    List<AttoAmministrativo> findAll();

    /**
     * Get the "id" attoAmministrativo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    AttoAmministrativo findOne(Long id);

    /**
     * Delete the "id" attoAmministrativo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
