package com.example.crud.controller;

import com.example.crud.entity.MyEntity;
import com.example.crud.service.MyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/crud/v3")
@Api(value = "this is my controller")
public class MyController {
    @Autowired
    private MyService myService;
    @ApiOperation(value = "Get all data from database", notes = "Get all data from database")
    @GetMapping("/crud")
    public List<MyEntity> getAll(){
        return this.myService.getAll();
    }
    @ApiOperation(value = "Get data by id", notes = "Get data by id")
    @GetMapping("/crud/{id}")
    public MyEntity getById(@PathVariable int id){
        return this.myService.getById(id);
    }
    @ApiOperation(value = "Save data", notes = "Save data")
    @PostMapping("/crud")
    public MyEntity saveData(@Valid @RequestBody MyEntity myEntity){
        return this.myService.saveAllData(myEntity);
    }
    @ApiOperation(value = "Update data", notes = "Update data")
    @PutMapping("/crud")
    public MyEntity updateData(@Valid @RequestBody MyEntity myEntity){
        return this.myService.updateData(myEntity);
    }
    @ApiOperation(value = "Delete data by id", notes = "Delete data by id")
    @DeleteMapping("/crud/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id){
        try {
            this.myService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
