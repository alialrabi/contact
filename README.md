
* technologies 

  spring Initializr
  java 11
  Spring Boot 2.5.0
  Gradle
  h2 database


-Run project
 
   ./gradlew bootRun
     
-Run test   

    ./gradlew test
      
     
*Rest API

 1-save new Contact

 post servic
 http://localhost:8080/api/contacts

  {
      "fullName":"ali",
      "dateOfBirth": "2021-03-17T18:58:06.510256+02:00",
      "address": {
         "city":"Cairo",
         "postalCode":"A5000"
      }
  }

 2-Get all Contacts
 http://localhost:8080/api/contacts

 3-Get contact By postalcode
 http://localhost:8080/api/contact/{postalcode}
 
 
 -Test report
 
    contact/build/reports/tests/test/index.html
