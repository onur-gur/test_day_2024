**For Pact Broker** 

 * docker-compose up -d

**Consumer Test And Publish Contract**

 * cd delivery
 * mvn -Dtest=StoreApiHttpClientConsumer test
 * mvn pact:publish

**Provider Verification**
 * cd store
 * mvn pact:verify

and visit http://localhost/80 -> pact broker