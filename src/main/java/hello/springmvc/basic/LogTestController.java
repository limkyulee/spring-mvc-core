package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // lombok | logger 선언 안해도 됨.
@RestController
public class LogTestController {

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";
//      PLUS : log 남길 때 "+" 사용 시, 추가 연산이 들어가기 때문에, "{}/," 방식을 사용하는 것을 권장.
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
