package com.payroll.message.function;

import com.payroll.message.dto.LeaveMsgDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import org.slf4j.Logger;

@Configuration
public class LeaveMsg {

    private static final Logger logger = LoggerFactory.getLogger(LeaveMsg.class);


    @Bean
    public Function<LeaveMsgDto, LeaveMsgDto> sms(){
        return (leaveMsgDto) ->{
            sendSMS(leaveMsgDto.mobileNumber(), getMessage(leaveMsgDto));
            logger.info("Sending SMS with details - {}");
            return leaveMsgDto;
        };

    }
    public String getMessage(LeaveMsgDto leaveMsgDto){
        StringBuilder sb = new StringBuilder();
        if(leaveMsgDto.leaveStatus().equals("PENDING")){
            sb.append("Employee with employeeId: ").append(leaveMsgDto.employeeId()).append(" has requested leave from ")
                    .append(leaveMsgDto.startDate()).append(" to ").append(leaveMsgDto.endDate());
        }
        else{
            sb.append("Your leave from ").append(leaveMsgDto.startDate()).append(" to ").append(leaveMsgDto.endDate())
                    .append(" has been ").append( leaveMsgDto.leaveStatus()).append(" TAKE GOOD CARE BYE");
        }
        return sb.toString();
    }
    public String sendSMS(String mobileNumber, String message) {
        String SID = "AC7fd055f7d2d07ae84af5fa64c86b9ad7";
        String TOKEN = "6c31d08532c44e89729d84d5c7fe4db6";
        Twilio.init(SID, TOKEN);
        System.out.println("I am in the twilio send sms function");
        Message msg = Message.creator(new PhoneNumber("+91"+mobileNumber),
                new PhoneNumber("+16366925654"), message).create();

        return "DONE";
    }
}
