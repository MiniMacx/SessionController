package org.eric.sessionserver.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("nbi")
public class TestController {

    @RequestMapping("/deliverysession")
    public ResponseEntity deliverySession(Long id) throws InterruptedException {
        System.out.println("received: " + id);
        Thread.sleep(1000);
        return new ResponseEntity(HttpStatus.OK);
    }
}
