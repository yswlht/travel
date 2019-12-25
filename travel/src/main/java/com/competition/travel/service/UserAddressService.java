package com.competition.travel.service;

import com.competition.travel.bean.UserAddress;

import java.util.List;

public interface UserAddressService {

    boolean insertAddress(UserAddress userAddress);

    boolean deleteAddress(int id);

    List<UserAddress> getAddress(int user_id);

    UserAddress getDefaultAddress(int user_id);

    boolean updateAddress(UserAddress userAddress);

    boolean updateDefaultAddress(int user_id, int id);

    UserAddress getOneAddress(int id);
}
