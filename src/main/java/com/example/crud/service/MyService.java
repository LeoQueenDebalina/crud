package com.example.crud.service;

import com.example.crud.entity.MyEntity;
import com.example.crud.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyService {
    @Autowired
    private MyRepository myRepository;
    public List<MyEntity> getAll(){
        return this.myRepository.findAll();
    }
    public MyEntity getById(int id){
        Optional<MyEntity> of = this.myRepository.findById(id);
        if (of.isPresent()){
            return of.get();
        }else {
            return null;
        }
    }
    public MyEntity saveAllData(MyEntity myEntity){
        this.myRepository.save(myEntity);
        return myEntity;
    }
    public MyEntity updateData(MyEntity myEntity){
        this.myRepository.save(myEntity);
        return myEntity;
    }
    public void deleteById(int id){
        this.myRepository.deleteById(id);
    }
}
