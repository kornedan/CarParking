package com.example.demo.service.Impl;

import com.example.demo.service.IDataHash;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;


@Component
public class DataHashImpl implements IDataHash {
    public String hashPassword(String password){
        return DigestUtils.md5Hex(password);
    }
}
