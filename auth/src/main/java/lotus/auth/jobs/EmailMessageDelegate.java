package lotus.auth.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by flamen on 16-9-26.
 */
@Component("auth.emailMessageListener")
public class EmailMessageDelegate  {

    public void handleMessage(String message){
        Object obj = redisSerializer.deserialize(message.getBytes());
        if(obj != null && obj instanceof EmailJob) {
            EmailJob ej = (EmailJob)obj;
            logger.info("send email to {}: {}", ej.getTo(), ej.getTitle());

        }else{
            logger.info("bad message: {}", message);
        }


    }
    private final static Logger logger = LoggerFactory.getLogger(EmailMessageDelegate.class);

    @Resource
    RedisSerializer redisSerializer;

}
