package com.competition.travel.controller;

import com.competition.travel.bean.Poi;
import com.competition.travel.service.PoiService;
import com.competition.travel.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PoiController {

    @Autowired
    PoiService poiService;

    /**关键字搜索*/
    @GetMapping("/wx/searchPoi")
    public List<Poi> searchPoi(@RequestParam(value = "search") String search,
                               @RequestParam(value = "limit") int limit,
                               @RequestParam(value = "offset") int offset){
        List<Poi> list = poiService.searchPoi(search, limit, offset);
        for(int i = 0; i < list.size(); i++){
            list.get(i).setImagescroll(ProductServiceImpl.segValues(list.get(i).getImage()));
        }
        return list;
    }

    /**根据ID查找景点*/
    @GetMapping("/wx/findPoiById")
    public Poi findPoiById(@RequestParam(value = "place_id") int place_id){
        Poi poi = poiService.findPoiById(place_id);
        poi.setImagescroll(ProductServiceImpl.segValues(poi.getImage()));
        return poiService.findPoiById(place_id);
    }

    @PostMapping("/web/insertPoi")
    public Boolean insertPoi(@RequestBody Poi poi){
        return poiService.insertPoi(poi);
    }
}
