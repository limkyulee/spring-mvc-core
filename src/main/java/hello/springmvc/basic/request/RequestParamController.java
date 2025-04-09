package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.valueOf(request.getParameter("age"));
        log.info("username = {}", username);
        log.info("age = {}", age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age
    ) {
        log.info("username = {}/{}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
           @RequestParam String username,
           @RequestParam int age
    ) {
        log.info("username = {}/{}", username, age);

        return "ok";
    }

//  PLUS : String, int, Integer 등의 단순 타입이라면 @RequestParam 도 생략 가능.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
           String username,
           int age
    ) {
        log.info("username = {}/{}", username, age);

        return "ok";
    }

    /**
     * @RequestParam.required 파라미터 필수 여부
     * 기본값이 true
     * int > null 불가 => Integer 사용 권장.
     * @param username
     * @param age
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam String username,
            @RequestParam(required = false) Integer age
    ) {
        log.info("username = {}/{}", username, age);

        return "ok";
    }

//  PLUS : defaultValue 는 빈문자인 경우에도 적용됨.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") Integer age
    ) {
        log.info("username = {}/{}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Objects> paramMap
    ) {
        log.info("username = {}/{}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    /**
     * @ModelAttribute
     * 1. HelloData 객체 생성
     * 2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾음.
     * 3. 해당 프로퍼티의 setter 를 호출해서 파라미터의 값을 입력(바인딩)함.
     * 4. ex ) 이름이 username 이면 setUsername 메서드를 찾아서 호출하면서 값을 바인딩함.
     * @param helloData
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username/age = {}/{}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username/age = {}/{}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
}
