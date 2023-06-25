package com.marvel.api.service;

import com.google.gson.Gson;
import com.marvel.api.model.entity.CharacterDataWrapper;
import com.marvel.api.model.entity.QueryLog;
import com.marvel.api.repository.QueryLogRepository;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterService {

    private final QueryLogRepository queryLogRepository;

    @Value("${urlApi.url}")
    private String URL;

    @Value("${urlApi.apiKey}")
    private String API_KEY;

    @Value("${urlApi.ts}")
    private String TS;

    @Value("${urlApi.hash}")
    private String HASH;


    public CharacterDataWrapper findAll() throws IOException {

        String url = URL + "?apikey=" + API_KEY + "&ts=" + TS + "&hash=" + HASH;

        return httpGet(url);
    }

    public CharacterDataWrapper findById(int characterId) throws IOException {

        String url = URL + "/" + characterId + "?apikey=" + API_KEY + "&ts=" + TS + "&hash=" + HASH;

        return httpGet(url);

    }

    public void addQueryCharacter(QueryLog queryLog) {

        this.queryLogRepository.save(queryLog);

    }

    public List<QueryLog> GetAllQueryLog() {

        return queryLogRepository.findAll();

    }
    public QueryLog getQueryLogById(Long characterId) {

        return queryLogRepository.findById(characterId).orElseThrow();
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
                rs.getLong("id"),
                rs.getString("characterName"),
                rs.getInt("characterId")
            );
}
