/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import com.example.demo.domain.Supporter;

/**
 *
 * @author iset1enloc
 */
public interface ISupporterService {

    Supporter save(Supporter supporter);
    Supporter update(Integer staffId, Supporter supporter);
}