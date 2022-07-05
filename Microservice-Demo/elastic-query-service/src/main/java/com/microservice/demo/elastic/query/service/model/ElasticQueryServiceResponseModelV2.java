package com.microservice.demo.elastic.query.service.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ElasticQueryServiceResponseModelV2 extends RepresentationModel<ElasticQueryServiceResponseModelV2> {
    //we changed Long from String=>This requires change at client level also.
    //So we need to create new api version(v2)
    private Long id;
    private Long userId;
    private String text;
    private String text2;
}
