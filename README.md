# intern-account-server
계정 관리 서버 - 인턴 프로젝트

## market인트라넷 서비스

## 개요

  
## 개발사항 정리

### Spec 정리
1. java 11
2. Spring boot 2.2.1
3. Build : Maven
4. DB : MS-SQL
5. Git : 2.23.0
6. WAS : Embedded Tomcat 9.0.27
7. Test : JUnit
8. Log : SLF4J(Log4j2)

### 설치 프로그램
1. VSCODE : https://code.visualstudio.com/
2. GIT-SCM : https://git-scm.com/download


### VSCODE 설치 플러그인
1. 한글팩
   1. Korean Language Pack for Visual Studio Code
2. 깃 연동
   1. Git History
   2. Git History Diff
3. 기타 지원기능
   1. Auto Close Tag
   2. Beautify
   3. ESLint
   4. Getter/Setter Generator
4. 웹사이트 즉시 로딩
   1. Open int browser
5. 스프링/JAVA 연동
   1. Spring Boot Tools
   2. Spring Initializer Java Support
   3. Spirng Boot Dashboard
   4. Spring Boot Extention Pack
   5. Java Dependency Viewer
   6. Java Extension Pack
   7. Java Test Runner
   8. Maven for Java
   9. Debugger for java
6.  소스 자동 완성
    1.  Visaul Studio IntelliCode 
7.  검은색 테마
    1.  Night Owl

### VSCODE 조작방법 가이드

https://demun.github.io/vscode-tutorial/

## API Request

|번호|URL|설명|
|---|---|---|
|1|http://localhost:8080/account/findAll|사용자 계정 전체조회|


## Status 정리

|번호|Code|설명|
|---|---|---|
|1|100|Success / 성공|
|2|101|Success : No Data / 성공(배치돌았으나 값이없음)|
|3|102|Success : Running Batch / 성공(서비스 시작후 배치가 도는중)|
|4|200|Fail : DB Access / 실패(DB연결 문제)|
|5|300|Fail : No Running Batch / 실패(배치 돌지 않음)|
|6|301|Fail : Batch / 실패(배치 진행중 에러발생)|
|7|400|Fail : No Data / 실패(데이터 없음)|
|8|401|Fail : No Before Date / 실패(이전데이터 없음)|
|9|402|Fail : Data Processing / 실패(데이터 처리중 에러발생)|
|10|500|Fail : Config Error / 실패(컨피그 오류)|
    
## 초반 가이드
GIT을 통해 파일을 가져오면 pom.xml에 정의 되어있는 의존성을 자동으로 가져온다  
좌측하단 로딩 표시 가 멈추면 끝
실행 파일은 vscode에서 자동으로 생성해주는데 F5 키를 눌러야함  
-> vscode에서 스프링부트 실행파일을 검색하여 만들어주는데 실행 파일이 여러개일 경우  
그중 선택 가능한 화면이 나옴 선택하면 vscode용 실행명령어 .vscode\launch.json 파일이 생성됨

생성된 이후 F5 키를 누르면 서버가 실행됨

> pom.xml H2의존성 추가와 application.properties에 설정  
의존 경로 : /pom.xml  
설정 경로 : /src/main/resources/application.properties  

## 폴더 구조

|번호|폴더경로|설명|
|---|---|---|
|1|/logs|로그 파일 경로 일별 로테이션 압축보관|
|2|/src/main/asciidoc|API 테스트 문서화 템플릿|
|3|/src/main/java/.../admonitor/cache|캐시 파일|
|4|/src/main/java/.../admonitor/config|프로퍼티 환경설정|
|5|/src/main/java/.../admonitor/controller|Get Post등 API 구현|
|6|/src/main/java/.../admonitor/entity|JPA 테이블과 매칭하는 객체|
|7|/src/main/java/.../admonitor/entity/dto|QueryDSL 커스텀객체|
|8|/src/main/java/.../admonitor/entity/id|JPA 테이블 복합키|
|9|/src/main/java/.../admonitor/entity/object|JPA 테이블 하위 객체|
|10|/src/main/java/.../admonitor/etc/exception|예외처리|
|11|/src/main/java/.../admonitor/model|API 연동 모델객체|
|12|/src/main/java/.../admonitor/model|API 연동 모델 하위 객체|
|13|/src/main/java/.../admonitor/repository|JPA DB 연동|
|14|/src/main/java/.../admonitor/service|구글 페북 연동 서비스 및 배치|
|15|/src/main/java/.../admonitor/util|유틸 기능|
|16|/src/main/resources|properties모음|
|17|/src/test/java/.../adminitor|테스트 실행파일|
|18|/src/test/resources/.../templete|API 테스트 요청 템플릿|
|19|/target|빌드된 파일 모음|
|20|/target/classes|클래스화된 파일모음|
|21|/target/generated-docs|API 테스트 결과 문서|
|22|/target/generated-snippets|API 테스트 결과|
|23|/target/generated-sources|QueryDSL 플러그인 생성객체|


## 기능 설명

> 스프링부트는 레고 블럭과 같이 의존성을 추가하고 그기능들을 활용하는데  
> properties로 설정을 하고 @어노테이션으로 기능을 이용한다.  
> 참고 - 의존성을 추가하면 기본설정은 암묵적으로 정의 되어있다

## properties

|번호|파일경로|설명|
|---|---|---|
|1|/src/main/resources/application.properties|프로퍼티 설정 파일|
|2|/src/main/.../admonitor/Application.java|프로퍼티 경로 주입|
|3|/src/main/.../admonitor/common/GlobalValue.java|@Value 주입|
|4|/src/main/.../admonitor/config/GoogleProperties.java|@ConfigurationProperties 주입|
|5|/src/main/resources/log4j2.properties|프로퍼티 내부 경로 주입|

### 1. 프로퍼티 설정 파일
외부 설정정보를 밖으로 빼서 내부에서 활용하며 프로퍼티 파일은 다음과 같다.
```
# 사용자정의 설정
global.facebook.view_log=false
global.facebook.insight_field=impressions,spend,clicks...
