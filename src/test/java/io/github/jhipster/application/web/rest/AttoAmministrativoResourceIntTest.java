package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AlbopretorioApp;

import io.github.jhipster.application.domain.AttoAmministrativo;
import io.github.jhipster.application.repository.AttoAmministrativoRepository;
import io.github.jhipster.application.service.AttoAmministrativoService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AttoAmministrativoResource REST controller.
 *
 * @see AttoAmministrativoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlbopretorioApp.class)
public class AttoAmministrativoResourceIntTest {

    private static final Integer DEFAULT_NUMDETERMINA = 1;
    private static final Integer UPDATED_NUMDETERMINA = 2;

    private static final LocalDate DEFAULT_DATADETERMINA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATADETERMINA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OGGETTO = "AAAAAAAAAA";
    private static final String UPDATED_OGGETTO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_ALLEGATO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ALLEGATO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ALLEGATO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ALLEGATO_CONTENT_TYPE = "image/png";

    @Autowired
    private AttoAmministrativoRepository attoAmministrativoRepository;

    @Autowired
    private AttoAmministrativoService attoAmministrativoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttoAmministrativoMockMvc;

    private AttoAmministrativo attoAmministrativo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttoAmministrativoResource attoAmministrativoResource = new AttoAmministrativoResource(attoAmministrativoService);
        this.restAttoAmministrativoMockMvc = MockMvcBuilders.standaloneSetup(attoAmministrativoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttoAmministrativo createEntity(EntityManager em) {
        AttoAmministrativo attoAmministrativo = new AttoAmministrativo()
            .numdetermina(DEFAULT_NUMDETERMINA)
            .datadetermina(DEFAULT_DATADETERMINA)
            .oggetto(DEFAULT_OGGETTO)
            .allegato(DEFAULT_ALLEGATO)
            .allegatoContentType(DEFAULT_ALLEGATO_CONTENT_TYPE);
        return attoAmministrativo;
    }

    @Before
    public void initTest() {
        attoAmministrativo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttoAmministrativo() throws Exception {
        int databaseSizeBeforeCreate = attoAmministrativoRepository.findAll().size();

        // Create the AttoAmministrativo
        restAttoAmministrativoMockMvc.perform(post("/api/atto-amministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attoAmministrativo)))
            .andExpect(status().isCreated());

        // Validate the AttoAmministrativo in the database
        List<AttoAmministrativo> attoAmministrativoList = attoAmministrativoRepository.findAll();
        assertThat(attoAmministrativoList).hasSize(databaseSizeBeforeCreate + 1);
        AttoAmministrativo testAttoAmministrativo = attoAmministrativoList.get(attoAmministrativoList.size() - 1);
        assertThat(testAttoAmministrativo.getNumdetermina()).isEqualTo(DEFAULT_NUMDETERMINA);
        assertThat(testAttoAmministrativo.getDatadetermina()).isEqualTo(DEFAULT_DATADETERMINA);
        assertThat(testAttoAmministrativo.getOggetto()).isEqualTo(DEFAULT_OGGETTO);
        assertThat(testAttoAmministrativo.getAllegato()).isEqualTo(DEFAULT_ALLEGATO);
        assertThat(testAttoAmministrativo.getAllegatoContentType()).isEqualTo(DEFAULT_ALLEGATO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAttoAmministrativoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attoAmministrativoRepository.findAll().size();

        // Create the AttoAmministrativo with an existing ID
        attoAmministrativo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttoAmministrativoMockMvc.perform(post("/api/atto-amministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attoAmministrativo)))
            .andExpect(status().isBadRequest());

        // Validate the AttoAmministrativo in the database
        List<AttoAmministrativo> attoAmministrativoList = attoAmministrativoRepository.findAll();
        assertThat(attoAmministrativoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkOggettoIsRequired() throws Exception {
        int databaseSizeBeforeTest = attoAmministrativoRepository.findAll().size();
        // set the field null
        attoAmministrativo.setOggetto(null);

        // Create the AttoAmministrativo, which fails.

        restAttoAmministrativoMockMvc.perform(post("/api/atto-amministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attoAmministrativo)))
            .andExpect(status().isBadRequest());

        List<AttoAmministrativo> attoAmministrativoList = attoAmministrativoRepository.findAll();
        assertThat(attoAmministrativoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAttoAmministrativos() throws Exception {
        // Initialize the database
        attoAmministrativoRepository.saveAndFlush(attoAmministrativo);

        // Get all the attoAmministrativoList
        restAttoAmministrativoMockMvc.perform(get("/api/atto-amministrativos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attoAmministrativo.getId().intValue())))
            .andExpect(jsonPath("$.[*].numdetermina").value(hasItem(DEFAULT_NUMDETERMINA)))
            .andExpect(jsonPath("$.[*].datadetermina").value(hasItem(DEFAULT_DATADETERMINA.toString())))
            .andExpect(jsonPath("$.[*].oggetto").value(hasItem(DEFAULT_OGGETTO.toString())))
            .andExpect(jsonPath("$.[*].allegatoContentType").value(hasItem(DEFAULT_ALLEGATO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].allegato").value(hasItem(Base64Utils.encodeToString(DEFAULT_ALLEGATO))));
    }

    @Test
    @Transactional
    public void getAttoAmministrativo() throws Exception {
        // Initialize the database
        attoAmministrativoRepository.saveAndFlush(attoAmministrativo);

        // Get the attoAmministrativo
        restAttoAmministrativoMockMvc.perform(get("/api/atto-amministrativos/{id}", attoAmministrativo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attoAmministrativo.getId().intValue()))
            .andExpect(jsonPath("$.numdetermina").value(DEFAULT_NUMDETERMINA))
            .andExpect(jsonPath("$.datadetermina").value(DEFAULT_DATADETERMINA.toString()))
            .andExpect(jsonPath("$.oggetto").value(DEFAULT_OGGETTO.toString()))
            .andExpect(jsonPath("$.allegatoContentType").value(DEFAULT_ALLEGATO_CONTENT_TYPE))
            .andExpect(jsonPath("$.allegato").value(Base64Utils.encodeToString(DEFAULT_ALLEGATO)));
    }

    @Test
    @Transactional
    public void getNonExistingAttoAmministrativo() throws Exception {
        // Get the attoAmministrativo
        restAttoAmministrativoMockMvc.perform(get("/api/atto-amministrativos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttoAmministrativo() throws Exception {
        // Initialize the database
        attoAmministrativoService.save(attoAmministrativo);

        int databaseSizeBeforeUpdate = attoAmministrativoRepository.findAll().size();

        // Update the attoAmministrativo
        AttoAmministrativo updatedAttoAmministrativo = attoAmministrativoRepository.findOne(attoAmministrativo.getId());
        // Disconnect from session so that the updates on updatedAttoAmministrativo are not directly saved in db
        em.detach(updatedAttoAmministrativo);
        updatedAttoAmministrativo
            .numdetermina(UPDATED_NUMDETERMINA)
            .datadetermina(UPDATED_DATADETERMINA)
            .oggetto(UPDATED_OGGETTO)
            .allegato(UPDATED_ALLEGATO)
            .allegatoContentType(UPDATED_ALLEGATO_CONTENT_TYPE);

        restAttoAmministrativoMockMvc.perform(put("/api/atto-amministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttoAmministrativo)))
            .andExpect(status().isOk());

        // Validate the AttoAmministrativo in the database
        List<AttoAmministrativo> attoAmministrativoList = attoAmministrativoRepository.findAll();
        assertThat(attoAmministrativoList).hasSize(databaseSizeBeforeUpdate);
        AttoAmministrativo testAttoAmministrativo = attoAmministrativoList.get(attoAmministrativoList.size() - 1);
        assertThat(testAttoAmministrativo.getNumdetermina()).isEqualTo(UPDATED_NUMDETERMINA);
        assertThat(testAttoAmministrativo.getDatadetermina()).isEqualTo(UPDATED_DATADETERMINA);
        assertThat(testAttoAmministrativo.getOggetto()).isEqualTo(UPDATED_OGGETTO);
        assertThat(testAttoAmministrativo.getAllegato()).isEqualTo(UPDATED_ALLEGATO);
        assertThat(testAttoAmministrativo.getAllegatoContentType()).isEqualTo(UPDATED_ALLEGATO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAttoAmministrativo() throws Exception {
        int databaseSizeBeforeUpdate = attoAmministrativoRepository.findAll().size();

        // Create the AttoAmministrativo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttoAmministrativoMockMvc.perform(put("/api/atto-amministrativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attoAmministrativo)))
            .andExpect(status().isCreated());

        // Validate the AttoAmministrativo in the database
        List<AttoAmministrativo> attoAmministrativoList = attoAmministrativoRepository.findAll();
        assertThat(attoAmministrativoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAttoAmministrativo() throws Exception {
        // Initialize the database
        attoAmministrativoService.save(attoAmministrativo);

        int databaseSizeBeforeDelete = attoAmministrativoRepository.findAll().size();

        // Get the attoAmministrativo
        restAttoAmministrativoMockMvc.perform(delete("/api/atto-amministrativos/{id}", attoAmministrativo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AttoAmministrativo> attoAmministrativoList = attoAmministrativoRepository.findAll();
        assertThat(attoAmministrativoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttoAmministrativo.class);
        AttoAmministrativo attoAmministrativo1 = new AttoAmministrativo();
        attoAmministrativo1.setId(1L);
        AttoAmministrativo attoAmministrativo2 = new AttoAmministrativo();
        attoAmministrativo2.setId(attoAmministrativo1.getId());
        assertThat(attoAmministrativo1).isEqualTo(attoAmministrativo2);
        attoAmministrativo2.setId(2L);
        assertThat(attoAmministrativo1).isNotEqualTo(attoAmministrativo2);
        attoAmministrativo1.setId(null);
        assertThat(attoAmministrativo1).isNotEqualTo(attoAmministrativo2);
    }
}
