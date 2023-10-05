package com.web.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.login.entity.UserEntity;
import com.web.login.exception.ResourceNotFoundException;
import com.web.login.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository oUserRepository;

    public UserEntity get(Long id){
        return oUserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    public UserEntity create(UserEntity oUserEntity){
        oUserEntity.setId(null);
        return oUserRepository.save(oUserEntity);
    }

    public UserEntity update(UserEntity oUserEntity){
        UserEntity oUserEntityAux = oUserRepository.findById(oUserEntity.getId()).orElseThrow(()-> new ResourceNotFoundException("User not Found"));
        oUserEntityAux.setName(oUserEntity.getName());
        oUserEntityAux.setSurname(oUserEntity.getSurname());
        oUserEntityAux.setLastname(oUserEntity.getLastname());
        oUserEntityAux.setEmail(oUserEntity.getEmail());
        oUserEntityAux.setUsername(oUserEntity.getUsername());
        //oUserEntity.setPassword(oUserEntity.getPassword());
        oUserEntityAux.setRole(oUserEntity.getRole());
        return oUserRepository.save(oUserEntityAux);
    }

    public UserEntity delete(Long id) {
        UserEntity oUserEntityAux = oUserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        oUserRepository.deleteById(id);
        return oUserEntityAux;
    }

    public Page<UserEntity> getPage(Pageable oPageable) {
        return oUserRepository.findAll(oPageable);
    }

    public Long populate(Integer amount){
        for(int i = 0; i < amount; i++){
            oUserRepository.save(new UserEntity("name" + i, "surname" + i, "lastname" + i, "email" + i, "username" + i,
            "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", true));
        }

        return oUserRepository.count();
    }


}
