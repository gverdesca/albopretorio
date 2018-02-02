package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AttoAmministrativo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AttoAmministrativo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttoAmministrativoRepository extends JpaRepository<AttoAmministrativo, Long> {

}
