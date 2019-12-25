package com.competition.travel.service;

import com.competition.travel.bean.Poi;
import com.competition.travel.dao.PoiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {

    @Autowired
    PoiDao poiDao;

    @Override
    public List<Poi> searchPoi(String search, int limit, int offset) {
        search = "%" + search + "%";
        List<Poi> pois = poiDao.searchPoi(search, limit, offset);
        for(int i=0; i<pois.size(); i++){

        }
        return pois;
    }

    @Override
    public Poi findPoiById(int place_id) {
        Poi onepoi = poiDao.findPoiById(place_id);
        onepoi.setImagescroll(segValues(onepoi.getImage()));
        return onepoi;
    }

    @Override
    public Boolean insertPoi(Poi poi) {
        return poiDao.insertPoi(poi);
    }

    private List<String> segValues(String value){
        List<String> list = new ArrayList<String>();
        if(value != null) {
            String[] values = value.split("\\^");
            for (int i = 0; i < values.length; i++) {
                list.add(values[i]);
            }
        }
        return list;
    }
}
