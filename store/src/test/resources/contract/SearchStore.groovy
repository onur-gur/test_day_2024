package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return stores response"
    request {
        method GET()
        url("/api/v1/store?x=1.0&y=2.0")
    }
    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body("""
                            {
                                "stores": [
                                  {
                                    "id": 1,
                                    "name": "Sample Entity",
                                    "status": "ACTIVE",
                                    "workingStatus": "OPEN",
                                    "email": "example@example.com",
                                    "phone": "123-456-7890",
                                    "logoUrl": "http://example.com/logo.png",
                                    "address": "123 Main St, City, Country",
                                    "deliveryType": "SCHEDULED",
                                    "deliveryArea": [
                                      {
                                        "x": 1.0,
                                        "y": 2.0
                                      },
                                      {
                                        "x": 3.0,
                                        "y": 4.0
                                      }
                                    ]
                                  }
                                ]
"                           }
             """)
    }
}