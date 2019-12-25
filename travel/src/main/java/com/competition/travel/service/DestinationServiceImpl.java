package com.competition.travel.service;

import com.competition.travel.bean.Destination;
import com.competition.travel.dao.DestinationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationDao destinationDao;

    @Override
    public List<Destination> fuzzySearch(String title, int limit, int offset) {
        title = "%" + title + "%";
        List<Destination> des = destinationDao.fuzzySearch(title, limit, offset);
        des =  setImage(des);
        return des;
    }

    @Override
    public Destination findOneDestination(int destination_id) {
        destinationDao.addDread(destination_id);
        Destination des = destinationDao.findOneDestination(destination_id);
        des.setPicture(segValues(des.getPictures()));
        return des;
    }

    @Override
    public List<Destination> findDestination(String region, String type, int limit, int offset) {
        List<Destination> des = destinationDao.findDestination(region, type, limit, offset);
        des = setImage(des);
        return des;
    }

    @Override
    public boolean insertDestination(Destination destination) {
        return destinationDao.insertDestination(destination);
    }

    @Override
    public List<Destination> findDestinationByType(String type) {
        List<Destination> des = destinationDao.findDestinationByType(type);
        des = setImage(des);
        return des;
    }

    @Override
    public List<Destination> findDestinationByRegion(String region) {
        List<Destination> des = destinationDao.findDestinationByRegion(region);
        des = setImage(des);
        return des;
    }

    @Override
    public List<Destination> findAllDestination() {
        List<Destination> des = destinationDao.findAllDestination();
        des = setImage(des);
        return des;
    }

    @Override
    public boolean updateDestination(Destination destination) {
        Destination des = destinationDao.findOneDestination(destination.getDestination_id());

        return destinationDao.updateDestination(destination);
    }

    @Override
    public boolean deleteDestination(int destination_id) {
        return destinationDao.deleteDestination(destination_id);
    }

    @Override
    public List<Destination> findPhotos(String search, String type, int limit, int offset) {
        search = "%" + search + "%";
        List<Destination> des = destinationDao.findPhotos(search, type, limit, offset);
        des = setImage(des);
        return des;
    }

    @Override
    public Destination findOnePhotos(int destination_id) {
        Destination des = destinationDao.findOnePhotos(destination_id);
        des.setPicture(segValues(des.getPictures()));
        destinationDao.addDread(destination_id);
        return des;
    }

    @Override
    public List<Destination> searchDestination(String search) {
        search = "%" + search + "%";
        List<Destination> des = destinationDao.searchDestination(search);
        des = setImage(des);
        return des;
    }

    @Override
    public boolean deletePicture(int destination_id, int picture_id) {

        return false;
    }

    @Override
    public List<Destination> findDestinationByTyRe(String region, String type) {
        List<Destination> des = destinationDao.findDestinationByTyRe(region, type);
        des = setImage(des);
        return des;
    }

    @Override
    public List<Destination> findMyDestination(String editer) {
        List<Destination> des = destinationDao.findMyDestination(editer);
        des = setImage(des);
        return des;
    }

    public List<Destination> setImage(List<Destination> des){
        for(int i=0; i<des.size(); i++){
            des.get(i).setPicture(segValues(des.get(i).getPictures()));
        }
        return des;
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
