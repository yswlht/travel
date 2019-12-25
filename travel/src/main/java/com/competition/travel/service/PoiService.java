package com.competition.travel.service;

import com.competition.travel.bean.Poi;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PoiService {

    List<Poi> searchPoi(String search, int limit, int offset);

    Poi findPoiById(int place_id);

    Boolean insertPoi(@RequestBody Poi poi);
}
