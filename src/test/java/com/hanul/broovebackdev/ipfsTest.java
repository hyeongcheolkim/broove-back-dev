package com.hanul.broovebackdev;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;

@SpringBootTest
@Slf4j
public class ipfsTest {

    private final String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mb3JtYXRpb24iOnsiaWQiOiJjZWQyMDA4NS03MWJhLTRiNDktOGQ1NS02ZjcxMGY1YjQzODIiLCJlbWFpbCI6Imh5ZW9uZ2NoZW9sMTc1M0BnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGluX3BvbGljeSI6eyJyZWdpb25zIjpbeyJpZCI6IkZSQTEiLCJkZXNpcmVkUmVwbGljYXRpb25Db3VudCI6MX0seyJpZCI6Ik5ZQzEiLCJkZXNpcmVkUmVwbGljYXRpb25Db3VudCI6MX1dLCJ2ZXJzaW9uIjoxfSwibWZhX2VuYWJsZWQiOmZhbHNlLCJzdGF0dXMiOiJBQ1RJVkUifSwiYXV0aGVudGljYXRpb25UeXBlIjoic2NvcGVkS2V5Iiwic2NvcGVkS2V5S2V5IjoiYjNlZTMxYWRjYTMxNTU2Y2ExMjYiLCJzY29wZWRLZXlTZWNyZXQiOiI4MjlhNTAwOGZlNDI0MmRlZGI0ODM5Mzc3MzMyYmUwNDU3MjMzNTliODRkOWRmMjI4M2E2MGMyYzExYTkwOTUxIiwiaWF0IjoxNjkxMjI0OTAyfQ.3HdF4FvaYY7T3WeCzDvxQafSDpOZN0Gov_2evbU77PY";

    @Test
    void pinataFileApiTest() {
        File file = new File("C:\\GItRepositories\\broove-back-dev\\src\\test\\java\\com\\hanul\\broovebackdev\\image.png");

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder
                .part("file", new FileSystemResource(file));


        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.pinata.cloud")
                .build();

        String res = webClient.post()
                .uri("/pinning/pinFileToIPFS")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header("authorization", "Bearer " + jwt)
                .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(res);

    }

    @Test
    void pinataJsonApiTest() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.pinata.cloud")
                .build();

        PinataJsonDto pinataJsonDto = PinataJsonDto.builder()
                .image("ipfs://QmZV6GWrYWwtmjR6CdKTMKa6UBVVXwAqqZPFM5iHZ749V2")
                .name("testName")
                .build();

        String res = webClient.post()
                .uri("/pinning/pinJSONToIPFS")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", "Bearer " + jwt)
                .bodyValue(pinataJsonDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(res);

    }

    @Data
    @Builder
    static class PinataJsonDto{
        private String image;
        private String name;
    }
}
