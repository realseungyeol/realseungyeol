웹 프로젝트

## 01. 프로젝트 기본 디렉토리

project/
  src/
    main/             <-프로그램 소스 파일을 두는 폴더
      java/           <-자바소스파일을 두는폴더
                        예) .java
      resources/      <- 프로그램을 실행할 때 사용하는 설정 파일을 두는 폴더.
                        예) .properties, .xml, .txt 등
      webapp/         <- 웹 자원을 두는 폴더. 이클립스 웹프로젝트의 WebContent 폴더와 같다
                        예) .html, .css, .js, .jsp
        WEB-INF       
          classes
    test/             <- 코드를 테스트하는 소스 파일을 두는 폴더
      java/           <- 단위 테스트 관련 자바 소스 파일을 두는 폴더
                         예) .java
      resources/      <- 단위 테스트 ㅏㄹ 때 사용할 설정 파일을 두는 폴더
                         예) .propertiesm, .xml, .txt 등
                         
                         
#3 02 gradle 설정 파일을 준비

1) gradle 빌드 도구를 실행할 떄 필요한 설정 파일을 준비한다.

프로젝트 폴더 > gradle init
build.gradle 등이 생성된다

##12 로그인 기능 추가하기
- 로그인 폼 추가
 - /webapp/auth/login.html
- 로그인 처리 서블릿 추가
 - Servlet.LoginServlet.java
- 이메일과 암호로 사용자 정보 조회하는 메서드 추가
 - MemverDao.selectOneByEmailPassword(String email, String password)