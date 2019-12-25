package com.competition.travel.service;

import com.competition.travel.bean.SelectRegion;
import com.competition.travel.dao.SelectRegionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectRegionServiceImpl implements SelectRegionService {

    @Autowired
    SelectRegionDao selectRegionDao;

    @Override
    public List<SelectRegion> getRegion(int pid) {
        return selectRegionDao.getRegion(pid);
    }
}
