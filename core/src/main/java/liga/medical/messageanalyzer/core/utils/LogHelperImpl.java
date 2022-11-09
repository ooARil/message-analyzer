package liga.medical.messageanalyzer.core.utils;

import liga.medical.messageanalyzer.core.utils.api.LogHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LogHelperImpl implements LogHelper {

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
