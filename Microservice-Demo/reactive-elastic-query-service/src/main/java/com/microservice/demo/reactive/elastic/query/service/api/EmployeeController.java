package com.microservice.demo.reactive.elastic.query.service.api;

import com.microservice.demo.elastic.model.index.impl.EmployeeModel;
import com.microservice.demo.reactive.elastic.query.service.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    @PostMapping("")
    public Mono<EmployeeModel> add(@RequestBody EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }

    @GetMapping("/{name}")
    public Flux<EmployeeModel> findByName(@PathVariable String name){
        return employeeRepository.findByName(name);
    }

    @GetMapping("")
    public Flux<EmployeeModel> findAll(){
        return employeeRepository.findAll();
    }
}
