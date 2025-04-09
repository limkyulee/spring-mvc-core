package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username: "hello", "age": 20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody : {}", messageBody);
        HelloData hd = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username : {}, age : {}", hd.getUsername(), hd.getAge());

        response.getWriter().write("ok");
    }

    /**
     * @RequestBody 요청
     * - JSON 요청 -> HTTP 메세지 컨버터 -> 객체
     * @ResponseBody 응답
     * - 객체 -> HTTP 메세지 컨버터 -> JSON 응답
     */

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody : {}", messageBody);
        HelloData hd = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username : {}, age : {}", hd.getUsername(), hd.getAge());

        return "ok";
    }

//  @RequestBody 를 생략하면 @ModelAttribute 가 돼서 생략하면 안됨.
//    > 요청 파라미터를 찾는 것으로 처리하게됨.
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData data) {
        log.info("username : {}, age : {}", data.getUsername(), data.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {
        HelloData data = httpEntity.getBody();
        log.info("username : {}, age : {}", data.getUsername(), data.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("username : {}, age : {}", data.getUsername(), data.getAge());

        return data;
    }

}
