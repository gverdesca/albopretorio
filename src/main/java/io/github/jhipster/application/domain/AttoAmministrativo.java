package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A AttoAmministrativo.
 */
@Entity
@Table(name = "atto_amministrativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttoAmministrativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numdetermina")
    private Integer numdetermina;

    @Column(name = "datadetermina")
    private LocalDate datadetermina;

    @NotNull
    @Size(max = 500)
    @Column(name = "oggetto", length = 500, nullable = false)
    private String oggetto;

    @Lob
    @Column(name = "allegato")
    private byte[] allegato;

    @Column(name = "allegato_content_type")
    private String allegatoContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumdetermina() {
        return numdetermina;
    }

    public AttoAmministrativo numdetermina(Integer numdetermina) {
        this.numdetermina = numdetermina;
        return this;
    }

    public void setNumdetermina(Integer numdetermina) {
        this.numdetermina = numdetermina;
    }

    public LocalDate getDatadetermina() {
        return datadetermina;
    }

    public AttoAmministrativo datadetermina(LocalDate datadetermina) {
        this.datadetermina = datadetermina;
        return this;
    }

    public void setDatadetermina(LocalDate datadetermina) {
        this.datadetermina = datadetermina;
    }

    public String getOggetto() {
        return oggetto;
    }

    public AttoAmministrativo oggetto(String oggetto) {
        this.oggetto = oggetto;
        return this;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public byte[] getAllegato() {
        return allegato;
    }

    public AttoAmministrativo allegato(byte[] allegato) {
        this.allegato = allegato;
        return this;
    }

    public void setAllegato(byte[] allegato) {
        this.allegato = allegato;
    }

    public String getAllegatoContentType() {
        return allegatoContentType;
    }

    public AttoAmministrativo allegatoContentType(String allegatoContentType) {
        this.allegatoContentType = allegatoContentType;
        return this;
    }

    public void setAllegatoContentType(String allegatoContentType) {
        this.allegatoContentType = allegatoContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttoAmministrativo attoAmministrativo = (AttoAmministrativo) o;
        if (attoAmministrativo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attoAmministrativo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttoAmministrativo{" +
            "id=" + getId() +
            ", numdetermina=" + getNumdetermina() +
            ", datadetermina='" + getDatadetermina() + "'" +
            ", oggetto='" + getOggetto() + "'" +
            ", allegato='" + getAllegato() + "'" +
            ", allegatoContentType='" + getAllegatoContentType() + "'" +
            "}";
    }
}
