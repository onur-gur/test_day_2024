package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("create delivery")
    request {
        method POST()
        url("/api/v1/delivery/create")
        headers {
            contentType(applicationJson())
        }
        body("""
             {
                "price" : 10.10,
                "description" : "test description" ,
                "quantity" : 5,
                "deliveryType" : "SCHEDULED" ,
                "buyer" : {
                                "firstName" : "first_name",
                                "lastName" : "last_name",
                                "email" : "email@email.com",
                                "phoneNumber" : "123456",
                                "address" : "test address",
                                "location" : {
                                                "type": "Point",
                                                "coordinates": [
                                                    29.44011,
                                                    40.80126
                                                ]
                                             }
                },
                "storeId" : 74
           }
            """)
    }
    response {
        status CREATED()
    }
}
