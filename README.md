# Concept

  * No bussiness Code
  * All Java Code can use PayPushServer
  * Another Language can use PayPushServer with using http & json

# Project List

  * BasicPom - Root Pom.xml
  * PayPushCommonModule - Client-Server Common Module
  * PayPushServer - Back-End Server for receipt validation and Push with IOS, Android 

# PayPushServer - Settings
  
  * Input your app information into com.myc0058.paypush.settings.PayPushGlobalConsts.java
  
# PayPushServer - Use Case

  * See UnitTests in the PayPushServer project (com.myc0058.paypush.Tests package)

# PayPushServer - How to run

  * Compile All
  mvn -f ./BasicPom/pom.xml compile
 
  * Run /PayPushServer
  mvn -f ./PayPushServer/pom.xml jetty:run 
  
  * Test
  Get http://127.0.0.1:2222/PayPushServer/test.do


# Q&A

  * myc0058@gmail.com
