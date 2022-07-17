package com.microservice.demo.reactive.elastic.query.service.repository;

import com.microservice.demo.elastic.model.index.impl.EmployeeModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCrudRepository<EmployeeModel, Long> {
    Flux<EmployeeModel> findByName(String name);
}
