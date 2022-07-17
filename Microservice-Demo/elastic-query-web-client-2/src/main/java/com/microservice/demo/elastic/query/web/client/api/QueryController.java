package com.microservice.demo.elastic.query.web.client.api;//package com.microservice.demo.elastic.query.web.client.api;
//
//import com.microservice.demo.elastic.query.web.client.model.ElasticQueryWebClientRequestModel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
////@RequestMapping(value = "/client/", produces = "application/vnd.api.v1+json")
//public class QueryController {
//
//    private static final Logger LOG = LoggerFactory.getLogger(QueryController.class);
//
////    private final ElasticQueryWebClient elasticQueryWebClient;
////
////    public QueryController(ElasticQueryWebClient webClient) {
////        this.elasticQueryWebClient = webClient;
////    }
//
//    @GetMapping("")
//    public String index() {
//        return "index";
//    }
//
////    @GetMapping("/error")
////    public String error() {
////        return "error";
////    }
////
////    @GetMapping("/home")
////    public String home(Model model) {
////        model.addAttribute("elasticQueryWebClientRequestModel",
////                ElasticQueryWebClientRequestModel.builder().build());
////        return "home";
////    }
////
//    @PostMapping("/query-by-text")
//    public String queryByText(@RequestBody @Valid ElasticQueryWebClientRequestModel requestModel) {
//        LOG.info("Querying with text {}", requestModel.getText());
////        List<ElasticQueryWebClientResponseModel> responseModels = elasticQueryWebClient.getDataByText(requestModel);
////        model.addAttribute("elasticQueryWebClientResponseModels", responseModels);
////        model.addAttribute("searchText", requestModel.getText());
////        model.addAttribute("elasticQueryWebClientRequestModel",
////                ElasticQueryWebClientRequestModel.builder().build());
//        return "home";
//    }
//
//}
