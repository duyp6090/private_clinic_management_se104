/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Supporter;
import com.example.demo.dto.supporter.registerSupporterRequest;

/**
 *
 * @author iset1enloc
 */
public interface ISupporterService {

    Supporter save(Supporter supporter);
    List<registerSupporterRequest> getAllSupporter();

}