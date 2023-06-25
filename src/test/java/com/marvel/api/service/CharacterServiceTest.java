package com.marvel.api.service;

import com.marvel.api.model.entity.QueryLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * La clase CharacterServiceTest Se probó antes de hacer QueryLog una Entidad de JPA por lo tanto
 * ah quedado deprecada. No dediqué tiempo a corregir ya que quise terminar los endpoints de la BD
 */
public class CharacterServiceTest {

    private CharacterService characterService;
    private DriverManagerDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL","sa","sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/query-log.sql"));

        characterService = new CharacterService();
    }

    @Test
    public void load_all_characters() throws SQLException {

        List<QueryLog> queryLogs = characterService.GetAllQueryLog();

        assertThat( queryLogs, is(Arrays.asList(
                new QueryLog(2L,"Hulk", 1009351)
        )));
    }

    @Test
    public void insert_a_query_log() {

        QueryLog queryLog = new QueryLog(3L,"Groot", 1010743);

        characterService.addQueryCharacter(queryLog);

        QueryLog queryLogFromDb = characterService.getQueryLogById(1010743);

        assertThat( queryLogFromDb, is(queryLog));
    }

    @After
    public void tearDown() throws Exception {
        //Remove H2 files
        //Para borrar los datos de la bd después de cada test para evitar que se dupliquen los datos
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files"); //"shutdown" is also enough for men db
    }
}