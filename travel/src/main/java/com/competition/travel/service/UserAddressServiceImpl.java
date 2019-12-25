package com.competition.travel.service;

import com.competition.travel.bean.UserAddress;
import com.competition.travel.dao.UserAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    UserAddressDao userAddressDao;

    @Override
    public boolean insertAddress(UserAddress userAddress) {
        boolean flag = false;
        try {
            userAddressDao.insertAddress(userAddress);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteAddress(int id) {
        boolean flag = false;
        try {
            userAddressDao.deleteAddress(id);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<UserAddress> getAddress(int user_id) {
        List<UserAddress> uas = userAddressDao.getAddress(user_id);
        UserAddress ua;
        for(int i=0; i<uas.size(); i++){
        }
        return uas;
    }

    @Override
    public UserAddress getDefaultAddress(int user_id) {
        UserAddress uas = userAddressDao.getDefaultAddress(user_id);
        return uas;
    }

    @Override
    public boolean updateAddress(UserAddress userAddress) {
        boolean flag = false;
        try {
            userAddressDao.updateAddress(userAddress);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateDefaultAddress(int user_id, int id) {
        boolean flag = false;
        try {
            userAddressDao.deleteDefaultAddress(user_id);
            userAddressDao.addDefaultAddress(id);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public UserAddress getOneAddress(int id) {
        UserAddress uas = userAddressDao.getOneAddress(id);
        return uas;
    }
}
