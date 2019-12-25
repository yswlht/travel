package com.competition.travel.controller;

import com.competition.travel.bean.SelectRegion;
import com.competition.travel.service.SelectRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelectRegionController {

    @Autowired
    SelectRegionService selectRegionService;

    @GetMapping(value = "/wx/getRegion")
    public List<SelectRegion> getRegion(@RequestParam(value = "pid") int pid){
        return selectRegionService.getRegion(pid);
    }
}
