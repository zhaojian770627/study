package com.zj.study.framework.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private AddressService addressService;

    @Override
    public String getAge(Long id) {
        return id + addressService.getAddress(id) + ConfigUtil.getPath();
    }
}