/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Supporter;
import com.example.demo.dto.supporter.registerSupporterRequest;
import com.example.demo.dto.user.UserRoleDTO;
import com.example.demo.repository.SupporterRepository;
import com.example.demo.service.ISupporterService;
import com.example.demo.service.IUserService;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author iset1enloc
 */
@Service
@RequiredArgsConstructor
public class SupporterServiceImpl implements ISupporterService {

    private final SupporterRepository supporterRepository;
    private final IUserService iUserService;

    @Override
    public Supporter save(Supporter supporter) {
        return this.supporterRepository.save(supporter);
    }

    @Override
    public List<registerSupporterRequest> getAllSupporter() {
        List<Supporter> dsSP = supporterRepository.findAll();
        List<registerSupporterRequest> listSP = new ArrayList<>();
        List<UserRoleDTO> userRoles = iUserService.getAllUsers();
        for (Supporter sp : dsSP) {
            List<String> roles = userRoles.stream()
                    .filter(r -> r.getUser_id() == sp.getId())
                    .flatMap(r -> r.getRole().stream())
                    .collect(Collectors.toList());

            registerSupporterRequest requestSP = new registerSupporterRequest(sp.getId(), sp.getUsername(), sp.getFullName(),
                    sp.getEmail(),
                    sp.getPhone(), sp.getPassword(), sp.getTitle(), roles);
            listSP.add(requestSP);
        }
        return listSP;
    }
}
