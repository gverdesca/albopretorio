package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.AttoAmministrativoService;
import io.github.jhipster.application.domain.AttoAmministrativo;
import io.github.jhipster.application.repository.AttoAmministrativoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing AttoAmministrativo.
 */
@Service
@Transactional
public class AttoAmministrativoServiceImpl implements AttoAmministrativoService {

    private final Logger log = LoggerFactory.getLogger(AttoAmministrativoServiceImpl.class);

    private final AttoAmministrativoRepository attoAmministrativoRepository;

    public AttoAmministrativoServiceImpl(AttoAmministrativoRepository attoAmministrativoRepository) {
        this.attoAmministrativoRepository = attoAmministrativoRepository;
    }

    /**
     * Save a attoAmministrativo.
     *
     * @param attoAmministrativo the entity to save
     * @return the persisted entity
     */
    @Override
    public AttoAmministrativo save(AttoAmministrativo attoAmministrativo) {
        log.debug("Request to save AttoAmministrativo : {}", attoAmministrativo);
        return attoAmministrativoRepository.save(attoAmministrativo);
    }

    /**
     * Get all the attoAmministrativos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttoAmministrativo> findAll() {
        log.debug("Request to get all AttoAmministrativos");
        return attoAmministrativoRepository.findAll();
    }

    /**
     * Get one attoAmministrativo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AttoAmministrativo findOne(Long id) {
        log.debug("Request to get AttoAmministrativo : {}", id);
        return attoAmministrativoRepository.findOne(id);
    }

    /**
     * Delete the attoAmministrativo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttoAmministrativo : {}", id);
        attoAmministrativoRepository.delete(id);
    }
}
