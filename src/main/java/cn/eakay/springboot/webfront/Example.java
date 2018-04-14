package cn.eakay.springboot.webfront;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by magic~ on 2018/2/23.
 */
@RestController
@EnableAutoConfiguration
public class Example
{
    @RequestMapping("/")
    String home()
    {
         return "Hello World!";
    }

}
