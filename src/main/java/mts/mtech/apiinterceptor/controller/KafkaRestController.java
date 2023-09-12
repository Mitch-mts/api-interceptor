package mts.mtech.apiinterceptor.controller;

import mts.mtech.apiinterceptor.kafka.KafkaSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaRestController {
    private final KafkaSender kafkaSender;

    public KafkaRestController(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message){
        kafkaSender.send(message);
        return "Message sent to the kafka topic";
    }
}
