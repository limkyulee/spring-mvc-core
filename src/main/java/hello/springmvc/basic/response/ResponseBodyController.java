package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController // @Controller + @ResponseBody
public class ResponseBodyController {

//  서블릿을 직접 다룸.
    @GetMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello World");
    }

//  ResponseEntity 사용.
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyStringV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//  ResponseBody 사용. | HTTP 메세지 컨버터를 통하여 HTTP 메세지 직접 입력 가능.
//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyStringV3() {
        return "ok";
    }

//  JSON 처리
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("kyuleelim");
        helloData.setAge(18);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

//  실무 사용.
//  @ResponseStatus | 상태코드 설정 가능. (동적인 상태코드 설정은 불가함, 동적으로 바꿔야할 경우 ResponseEntity 사용.)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("kyuleelim");
        helloData.setAge(18);
        return helloData;
    }
}
