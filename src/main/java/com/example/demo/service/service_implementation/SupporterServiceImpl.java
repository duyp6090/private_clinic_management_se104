/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import com.example.demo.domain.Supporter;
import com.example.demo.repository.SupporterRepository;
import com.example.demo.service.ISupporterService;

/**
 *
 * @author iset1enloc
 */
public class SupporterServiceImpl implements ISupporterService {

    private final SupporterRepository supporterRepository;

    public SupporterServiceImpl(SupporterRepository supporterRepository){
        this.supporterRepository = supporterRepository;
    }
    @Override
    public Supporter save(Supporter supporter) {
        return this.save(supporter);
    }

    @Override
    public Supporter update(Integer staffId, Supporter supporter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
