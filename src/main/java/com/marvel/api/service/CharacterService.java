package com.marvel.api.service;

import com.google.gson.Gson;
import com.marvel.api.model.entity.CharacterDataWrapper;
import com.marvel.api.model.entity.QueryLog;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterService {

    @Value("${urlApi.url}")
    private String URL;

    @Value("${urlApi.apiKey}")
    private String API_KEY;

    @Value("${urlApi.ts}")
    private String TS;

    @Value("${urlApi.hash}")
    private String HASH;

    DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL","sa","sa");

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


    public CharacterDataWrapper findAll() throws IOException {

        String url = URL + "?apikey=" + API_KEY + "&ts=" + TS + "&hash=" + HASH;

        return httpGet(url);
    }

    public CharacterDataWrapper findById(int characterId) throws IOException {

        String url = URL + "/" + characterId + "?apikey=" + API_KEY + "&ts=" + TS + "&hash=" + HASH;

        return httpGet(url);

    }

    public void addQueryCharacter(QueryLog queryLog) {

        jdbcTemplate.update("insert into QUERY_LOG (characterName, characterId) values (?, ?)",
                queryLog.getCharacterName(), queryLog.getCharacterId()
                );

    }

    public List<QueryLog> GetAllQueryLog() {

        return jdbcTemplate.query("select * from QUERY_LOG", movieMapper);

    }
    public QueryLog getQueryLogById(int characterId) {

        Object[] args = { characterId };
        return jdbcTemplate.queryForObject("select * from QUERY_LOG where characterId = ?", args, movieMapper);
    }

    private CharacterDataWrapper httpGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Gson gson = new Gson();

        CharacterDataWrapper characterDataWrapper = gson.fromJson(json, CharacterDataWrapper.class);

        return characterDataWrapper;
    }

    private static RowMapper<QueryLog> movieMapper = (rs, rowNum) ->
            new QueryLog(
                rs.getString("characterName"),
                rs.getInt("characterId")
            );
}
