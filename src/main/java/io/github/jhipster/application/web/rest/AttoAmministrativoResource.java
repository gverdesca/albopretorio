package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.AttoAmministrativo;
import io.github.jhipster.application.service.AttoAmministrativoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AttoAmministrativo.
 */
@RestController
@RequestMapping("/api")
public class AttoAmministrativoResource {

    private final Logger log = LoggerFactory.getLogger(AttoAmministrativoResource.class);

    private static final String ENTITY_NAME = "attoAmministrativo";

    private final AttoAmministrativoService attoAmministrativoService;

    public AttoAmministrativoResource(AttoAmministrativoService attoAmministrativoService) {
        this.attoAmministrativoService = attoAmministrativoService;
    }

    /**
     * POST  /atto-amministrativos : Create a new attoAmministrativo.
     *
     * @param attoAmministrativo the attoAmministrativo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attoAmministrativo, or with status 400 (Bad Request) if the attoAmministrativo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atto-amministrativos")
    @Timed
    public ResponseEntity<AttoAmministrativo> createAttoAmministrativo(@Valid @RequestBody AttoAmministrativo attoAmministrativo) throws URISyntaxException {
        log.debug("REST request to save AttoAmministrativo : {}", attoAmministrativo);
        if (attoAmministrativo.getId() != null) {
            throw new BadRequestAlertException("A new attoAmministrativo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttoAmministrativo result = attoAmministrativoService.save(attoAmministrativo);
        return ResponseEntity.created(new URI("/api/atto-amministrativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atto-amministrativos : Updates an existing attoAmministrativo.
     *
     * @param attoAmministrativo the attoAmministrativo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attoAmministrativo,
     * or with status 400 (Bad Request) if the attoAmministrativo is not valid,
     * or with status 500 (Internal Server Error) if the attoAmministrativo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atto-amministrativos")
    @Timed
    public ResponseEntity<AttoAmministrativo> updateAttoAmministrativo(@Valid @RequestBody AttoAmministrativo attoAmministrativo) throws URISyntaxException {
        log.debug("REST request to update AttoAmministrativo : {}", attoAmministrativo);
        if (attoAmministrativo.getId() == null) {
            return createAttoAmministrativo(attoAmministrativo);
        }
        AttoAmministrativo result = attoAmministrativoService.save(attoAmministrativo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attoAmministrativo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atto-amministrativos : get all the attoAmministrativos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of attoAmministrativos in body
     */
    @GetMapping("/atto-amministrativos")
    @Timed
    public List<AttoAmministrativo> getAllAttoAmministrativos() {
        log.debug("REST request to get all AttoAmministrativos");
        return attoAmministrativoService.findAll();
        }

    /**
     * GET  /atto-amministrativos/:id : get the "id" attoAmministrativo.
     *
     * @param id the id of the attoAmministrativo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attoAmministrativo, or with status 404 (Not Found)
     */
    @GetMapping("/atto-amministrativos/{id}")
    @Timed
    public ResponseEntity<AttoAmministrativo> getAttoAmministrativo(@PathVariable Long id) {
        log.debug("REST request to get AttoAmministrativo : {}", id);
        AttoAmministrativo attoAmministrativo = attoAmministrativoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(attoAmministrativo));
    }

    /**
     * DELETE  /atto-amministrativos/:id : delete the "id" attoAmministrativo.
     *
     * @param id the id of the attoAmministrativo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atto-amministrativos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttoAmministrativo(@PathVariable Long id) {
        log.debug("REST request to delete AttoAmministrativo : {}", id);
        attoAmministrativoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
