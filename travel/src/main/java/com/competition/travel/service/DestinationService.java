package com.competition.travel.service;

import com.competition.travel.bean.Destination;

import java.util.List;

public interface DestinationService {

    Destination findOneDestination(int destination_id);

    List<Destination> fuzzySearch(String title, int limit, int offset);

    List<Destination> findDestination(String region, String type, int limit, int offset);

    boolean insertDestination(Destination destination);

    List<Destination> findDestinationByType(String type);

    List<Destination> findDestinationByRegion(String region);

    List<Destination> findAllDestination();

    boolean updateDestination(Destination destination);

    boolean deleteDestination(int destination_id);

    List<Destination> findPhotos(String search, String type, int limit, int offset);

    Destination findOnePhotos(int destination_id);

    List<Destination> searchDestination(String search);

    boolean deletePicture(int destination_id, int picture_id);

    List<Destination> findDestinationByTyRe(String region, String type);

    List<Destination> findMyDestination(String editer);
}
