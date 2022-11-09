package liga.medical.messageanalyzer.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.messageanalyzer.core.annotation.DbLog;
import liga.medical.messageanalyzer.core.api.MessageAnalyzerSenderService;
import liga.medical.model.dto.QueueNames;
import liga.medical.model.dto.RabbitMessageDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class MessageAnalyzerController {

    private final MessageAnalyzerSenderService messageAnalyzerSenderService;

    public MessageAnalyzerController(MessageAnalyzerSenderService messageAnalyzerSenderService) {
        this.messageAnalyzerSenderService = messageAnalyzerSenderService;
    }

    @DbLog
    @RequestMapping("/send")
    public void sendMessage(@RequestBody RabbitMessageDTO message) throws JsonProcessingException {
        messageAnalyzerSenderService.sendMessage(message, QueueNames.ROUTER_QUEUE_NAME);
    }
}
