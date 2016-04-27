# Concept

  * IOS와 Android(Google Play) 앱을 개발할때 꼭 필요한 결제 유효성 검사와 푸쉬를 보내는 기능을 담고 있습니다.
  
  * 앱을 개발할때 마다 이 기능은 필요하고 그떄마다 서버를 만드는게 불필요하다 생각하여 만들게 되었습니다.
  
  * PayPushGlobalConsts.java 파일에 해당 앱에 대한 정보를 입력하고 서버를 빌드하면 어떤 앱에서든지 손쉽게 사용할수 있습니다.
  
# Source Code Point

  * 개발 시간과 편의를 위해 모든 Controller의 함수들의 파라미터는 동일하게 data 하나만 받도록 하였습니다. 호출할 API마다 PayPushCommonModule 프로젝트의 com.myc0058.paypush.Params의 Model들을 Json으로 Serialization해서 넣습니다.
	
  * 반대로 Response를 받을때도 마찬가지 입니다. 이 과정은 com.myc0058.paypush.Tests 패키지의 Unit Test들을 보시면 이해하기 쉽습니다.
  
  * 결제와 푸쉬 관련한 코드를 작성할때 앱에 대한 정보와 PayPushGlobalConsts.java의 상수를 매칭할때 헷갈리수 있는데 이 떄문에 샘플을 넣어두었습니다. 앱정보와 샘플 정보를 비교하면서 보시면 쉽게 찾으실수 있을겁니다.
  
  * HTTP와 Json을 이용할 수 있으면 어떤 서버던지 통신할수 있기 때문에 언어와 프레임웍 무관하게 이용할 수 있습니다.
  
# Project List

  * BasicPom - 최상위 pom.xml이 들어있습니다.
  * PayPushCommonModule - PayPushServer와 통신하기 위해 필요한 Model이 들어있습니다.
  * PayPushServer - 결제 유효성 검사와 푸쉬 보내기 기능이 들어있습니다.

# PayPushServer - Settings

  * 앱의 정보를 다음 파일에 입력해주세요. com.myc0058.paypush.settings.PayPushGlobalConsts.java

# PayPushServer - Use Case

  * 유즈케이스는 다음 패키지를 하세요. com.myc0058.paypush.Tests

# PayPushServer - How to run

Compile All
 * mvn -f ./BasicPom/pom.xml compile
 
Run /PayPushServer
 * mvn -f ./PayPushServer/pom.xml jetty:run 

Test
 * wget http://127.0.0.1:2222/PayPushServer/test.do

# Q&A

  * myc0058@gmail.com
