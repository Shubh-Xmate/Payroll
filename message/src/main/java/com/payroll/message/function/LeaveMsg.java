package com.payroll.message.function;

import com.payroll.message.dto.LeaveMsgDto;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LeaveMsg {

    private static final Logger logger = LoggerFactory.getLogger(LeaveMsg.class);

    @Bean
    public Function<LeaveMsgDto, LeaveMsgDto> sms(){
        return (leaveMsgDto) ->{
            logger.info("Sending SMS with details - {}");
            return leaveMsgDto;
        };

    }
}
