package com.competition.travel.service;

import com.competition.travel.bean.ComUser;

import java.util.List;

public interface ComUserService {

    boolean comRegister(ComUser comUser);

    boolean comDelete(String username);

    boolean comAudit(String username);

    ComUser findByUsername(String username);

    List<ComUser> findAll();

    boolean updateInformation(ComUser comUser);

    int statisticalCom();
}
