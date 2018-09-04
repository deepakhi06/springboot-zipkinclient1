package com.sb.zipkinclient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZipkinApplication.class, args);

	}

	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

}
@RestController
class ZipkinController{
     
    @Autowired
    RestTemplate restTemplate;
 
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
 
   /* @Bean
    public AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }*/
 
    private static final Logger LOG = Logger.getLogger(ZipkinController.class.getName());
     
    @GetMapping(value="/zipkin")
    public String zipkinService1()
    {
        LOG.info("Inside zipkinService 1..");
         
         String response = (String) restTemplate.exchange("http://localhost:8282/zipkin2",
                        HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
        return "Hi...";
    }
}

