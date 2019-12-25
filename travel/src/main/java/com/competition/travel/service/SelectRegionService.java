package com.competition.travel.service;

import com.competition.travel.bean.SelectRegion;

import java.util.List;

public interface SelectRegionService {

    List<SelectRegion> getRegion(int pid);
}
