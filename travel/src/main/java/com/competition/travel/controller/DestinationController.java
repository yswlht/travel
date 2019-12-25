package com.competition.travel.controller;

import com.competition.travel.bean.Destination;
import com.competition.travel.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    /**根据id查找攻略*/
    @GetMapping(value = "/wx/findOneDestination")
    public Destination findOneDestination(@RequestParam(value = "destination_id") int destination_id){
        return destinationService.findOneDestination(destination_id);
    }

    /**模糊搜索*/
    @GetMapping(value = "/wx/fuzzySearch")
    public List<Destination> fuzzySearch(@RequestParam(value = "title") String title,
                                         @RequestParam(value = "limit") int limit,
                                         @RequestParam(value = "offset") int offset){
        return destinationService.fuzzySearch(title, limit, offset);
    }

    /**分页搜索*/
    @GetMapping(value = "/wx/findDestination")
    public List<Destination> findDestiantion(@RequestParam(value = "region") String region,
                                             @RequestParam(value = "type") String type,
                                             @RequestParam(value = "limit") int limit,
                                             @RequestParam(value = "offset") int offset){
        return destinationService.findDestination(region, type, limit, offset);
    }

    /**插入数据*/
    @PostMapping(value = "/web/insertDestination")
    public String insertDestination(@RequestBody Destination destination){
        destination.setTime(new Date());
        System.out.println(destination.toString());
        if(destinationService.insertDestination(destination)){
            return "成功";
        }else {
            return "失败";
        }
    }

    /**根据类型找攻略*/
    @GetMapping(value = "/web/findDestinationByType")
    public List<Destination> findDestinationByType(@RequestParam(value = "type") String type){
        return destinationService.findDestinationByType(type);
    }

    /**根据地域找攻略*/
    @GetMapping(value = "/web/findDestinationByRegion")
    public List<Destination> findDestinationByRegion(@RequestParam(value = "region") String region){
        return destinationService.findDestinationByRegion(region);
    }

    /**查找所有攻略*/
    @GetMapping(value = "/web/findAllDestination")
    public List<Destination> findAllDestination(){
        return destinationService.findAllDestination();
    }

    /**修改数据*/
    @PutMapping(value = "/web/updateDestination")
    public String updateDestination(@RequestBody  Destination destination){
        Destination destinations = destinationService.findOneDestination(destination.getDestination_id());
        if(destinations != null) {
            destination.setTime(new Date());
            if (destinationService.updateDestination(destination)) {
                return "修改成功";
            } else {
                return "修改失败";
            }
        }else{
            return "该文章已被删除";
        }
    }

    /**删除数据*/
    @DeleteMapping(value = "/web/deleteDestination")
    public String deleteDestination(@RequestParam(value = "destination_id") int destination_id){
        Destination destination = destinationService.findOneDestination(destination_id);
        if(destination != null) {
            if (destinationService.deleteDestination(destination_id)) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }else{
            return "该文章不存在";
        }
    }

    /**搜索攻略*/
    @GetMapping(value = "/web/searchDestination")
    public List<Destination> searchDestination(@RequestParam(value = "search") String search){
        return destinationService.searchDestination(search);
    }

    /**删除图片*/
    @PutMapping(value = "/web/deletePicture")
    public String deletePicture(@RequestParam(value = "destination_id") int destination_id,
                                @RequestParam(value = "picture_id") int picture_id){
        if(destinationService.deletePicture(destination_id, picture_id)){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    /**查找游记*/
    @GetMapping(value = "/wx/findMyDestination")
    public List<Destination> findMyDestination(@RequestParam(value = "editer") String editer){
        return destinationService.findMyDestination(editer);
    }
}
