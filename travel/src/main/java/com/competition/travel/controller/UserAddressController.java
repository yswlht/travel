package com.competition.travel.controller;

import com.competition.travel.bean.UserAddress;
import com.competition.travel.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAddressController {

    @Autowired
    UserAddressService userAddressService;

    @PostMapping(value = "/wx/insertAddress")
    public boolean insertAddress(@RequestBody UserAddress userAddress){
        return userAddressService.insertAddress(userAddress);
    }

    @DeleteMapping(value = "/wx/deleteAddress")
    public boolean deleteAddress(@RequestParam(value = "id") int id){
        return userAddressService.deleteAddress(id);
    }

    @GetMapping(value = "/wx/getAddress")
    public List<UserAddress> getAddress(@RequestParam(value = "user_id") int user_id){
        return userAddressService.getAddress(user_id);
    }

    @GetMapping(value = "/wx/getDefaultAddress")
    public UserAddress getDefaultAddress(@RequestParam(value = "user_id") int user_id){
        return userAddressService.getDefaultAddress(user_id);
    }

    @PutMapping(value = "/wx/updateAddress")
    public boolean updateAddress(@RequestBody UserAddress userAddress){
        return userAddressService.updateAddress(userAddress);
    }

    @PutMapping(value = "/wx/updateDefaultAddress")
    public boolean updateDefaultAddress(@RequestParam(value = "user_id") int user_id,
                                        @RequestParam(value = "id") int id){
        return userAddressService.updateDefaultAddress(user_id, id);
    }

    @GetMapping(value = "/wx/getOneAddress")
    public UserAddress getOneAddress(@RequestParam(value = "id") int id){
        return userAddressService.getOneAddress(id);
    }
}
