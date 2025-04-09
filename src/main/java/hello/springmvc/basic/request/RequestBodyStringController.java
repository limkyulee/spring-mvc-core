package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);

        responseWriter.write("ok");
    }

    /**
     * @HttpEntity
     * HTTP header, body 정보를 편리하게 조회
     * 메세지 바디 정보를 직접 조회
     * 요청 파라미터를 조회하는 기능과 관계 없음. @RequestParam X, @ModelAttribute X

     * HttpEntity 는 응답에도 사용 가능
     * 메세지 바디 정보 직접 반환.
     * 헤더 정보 포함 가능.
     * view 조회 안함.
     * @param httpEntity
     * @return
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){
        String messageBody = httpEntity.getBody();

        log.info("messageBody = {}", messageBody);

       return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody
     * HTTP 메시지 바디 정보를 편리하기 조회할 수 있음.
     * 헤더 정보가 필요할 경우 HttpEntity 를 사용하거나 @RequestHeader 를 사용하면 됨.
     * @param messageBody
     * @return
     *
     * @ResponseBody
     * 응답결과를 Http 에 직접 담아서 전달할 수 있음.
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){
        log.info("messageBody = {}", messageBody);

        return "ok";
    }
}
