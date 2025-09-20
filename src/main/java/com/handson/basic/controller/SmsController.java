package com.handson.basic.controller;

import com.handson.basic.model.MessageAndPhones;
import com.handson.basic.util.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SmsController {

    @Autowired
    SmsService smsService;

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ResponseEntity<?> smsAll(@RequestBody MessageAndPhones messageAndPhones)
    {
        new Thread(()-> {
            messageAndPhones.getPhones()
                    .parallelStream()
                    .forEach(phone -> smsService.send(messageAndPhones.getMessage(), phone));

        }).start();
        return new ResponseEntity<>("SENDING", HttpStatus.OK);
    }
}