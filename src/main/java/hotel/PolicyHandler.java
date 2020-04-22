package hotel;

import hotel.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    ResultProcessingRepository resultProcessingRepository;

    ResultProcessing resultProcessing = new ResultProcessing();

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationComfirmed_ReservedPolicy(@Payload ReservationComfirmed reservationComfirmed){

        if(reservationComfirmed.isMe()){
            System.out.println("##### listener ReservedPolicy : " + reservationComfirmed.toJson());
            resultProcessing.setRegId(reservationComfirmed.getId()); // Co-Relation
            resultProcessing.setName(reservationComfirmed.getName());
            resultProcessing.setDate(reservationComfirmed.getDate());
            resultProcessing.setPhone(reservationComfirmed.getPhone());
            resultProcessing.setUserid(reservationComfirmed.getUserId());
            resultProcessing.setStatus("ReservationComfirmed ( OK )");
            resultProcessingRepository.save(resultProcessing);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverChanged_ChangedPolicy(@Payload Changed changed){

        if(changed.isMe()){
            System.out.println("##### listener ChangedPolicy : " + changed.toJson());
            resultProcessing.setRegId(changed.getId()); // Co-Relation
            resultProcessing.setName(changed.getName());
            resultProcessing.setDate(changed.getDate());
            resultProcessing.setPhone(changed.getPhone());
            resultProcessing.setUserid(changed.getUserid());
            resultProcessing.setStatus("changed( Change OK)");
            resultProcessingRepository.save(resultProcessing);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCanceled_CanceledPolicy(@Payload Canceled canceled){

        if(canceled.isMe()){
            resultProcessing.setRegId(canceled.getId()); // Co-Relation
            resultProcessing.setName(canceled.getName());
            resultProcessing.setDate(canceled.getDate());
            resultProcessing.setPhone(canceled.getPhone());
            resultProcessing.setUserid(canceled.getUserid());
            resultProcessing.setStatus("canceled (Cancel OK)");
            resultProcessingRepository.save(resultProcessing);
            System.out.println("##### listener CanceledPolicy : " + canceled.toJson());
        }
    }

}
