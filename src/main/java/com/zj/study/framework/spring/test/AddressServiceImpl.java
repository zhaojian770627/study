package com.zj.study.framework.spring.test;

import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public String getAddress(Long id) {
        return "河南省";
    }
}