package liga.medical.messageanalyzer.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.model.RabbitMessageDTO;

public interface MessageAnalyzerSenderService {

    void sendMessage(RabbitMessageDTO rabbitMessageDTO, String queue) throws JsonProcessingException;
}
