package com.orchestration.delivery.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class AbstractTestIT {

    @Autowired
    public TestRestTemplate testRestTemplate;

    @LocalServerPort
    public Integer port;

    protected static final MySQLContainer mySql = new MySQLContainer<>("mysql:8.0.26").withReuse(true);

    protected static final MockServerContainer mockServer =
            new MockServerContainer(DockerImageName.parse("jamesdbloom/mockserver:mockserver-5.13.2"));

    protected static MockServerClient mockServerClient;

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        Startables.deepStart(mySql, mockServer).join();

        registry.add("spring.datasource.url", mySql::getJdbcUrl);
        registry.add("spring.datasource.username", mySql::getUsername);
        registry.add("spring.datasource.password", mySql::getPassword);
        registry.add("store-api.baseUrl", mockServer::getEndpoint);

        mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());
    }

    @BeforeEach
    public void setUp() {
        testRestTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList(((request, body, execution) -> execution.execute(request, body))));
    }

    protected static void mockStoreSearch() {
        mockServerClient.when(
                        request().withMethod("GET").withPath(".*/store/search.*"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(new Header("Content-Type", "application/json;"))
                                .withBody(
                                        """ 
                                                {
                                                  "stores": [
                                                    {
                                                      "id": 110,
                                                      "name": "store 1",
                                                      "status": "ACTIVE",
                                                      "workingStatus": "OPEN",
                                                      "email": "email1@email.com",
                                                      "phone": "909009009090",
                                                      "logoUrl": "logo_url",
                                                      "address": "address1",
                                                      "deliveryType": "SCHEDULED",
                                                      "deliveryArea": [
                                                        {
                                                          "x": 34.57668533480603,
                                                          "y": 36.77238305755924
                                                        },
                                                        {
                                                          "x": 34.575869222881174,
                                                          "y": 36.773140722704895
                                                        },
                                                        {
                                                          "x": 34.57485303747497,
                                                          "y": 36.7741834997352
                                                        },
                                                        {
                                                          "x": 34.57668533480603,
                                                          "y": 36.77238305755924
                                                        }
                                                      ]
                                                    },
                                                    {
                                                      "id": 112,
                                                      "name": "store 2",
                                                      "status": "INACTIVE",
                                                      "workingStatus": "CLOSED",
                                                      "email": "email2@email.com",
                                                      "phone": "909009009090",
                                                      "logoUrl": "logo_url",
                                                      "address": "address2",
                                                      "deliveryType": "SCHEDULED",
                                                      "deliveryArea": [
                                                        {
                                                          "x": 34.57668533480603,
                                                          "y": 36.77238305755924
                                                        },
                                                        {
                                                          "x": 34.575869222881174,
                                                          "y": 36.773140722704895
                                                        },
                                                        {
                                                          "x": 34.57485303747497,
                                                          "y": 36.7741834997352
                                                        },
                                                        {
                                                          "x": 34.57668533480603,
                                                          "y": 36.77238305755924
                                                        }
                                                      ]
                                                    },
                                                    {
                                                      "id": 113,
                                                      "name": "store 3",
                                                      "status": "ACTIVE",
                                                      "workingStatus": "OPEN",
                                                      "email": "email3@email.com",
                                                      "phone": "909009009090",
                                                      "logoUrl": "logo_url",
                                                      "address": "address1",
                                                      "deliveryType": "INSTANT",
                                                      "deliveryArea": [
                                                        {
                                                          "x": 34.57668533480603,
                                                          "y": 36.77238305755924
                                                        },
                                                        {
                                                          "x": 34.575869222881174,
                                                          "y": 36.773140722704895
                                                        },
                                                        {
                                                          "x": 34.57485303747497,
                                                          "y": 36.7741834997352
                                                        },
                                                        {
                                                          "x": 34.57668533480603,
                                                          "y": 36.77238305755924
                                                        }
                                                      ]
                                                    }
                                                  ]
                                                }
                                                
                                                """
                                )
                );
    }
}
