package GAJI;

import GAJI.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CheckStatusViewHandler {


    @Autowired
    private CheckStatusRepository checkStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRegistered_then_CREATE_1 (@Payload Registered registered) {
        try {
            if (registered.isMe()) {
                // view 객체 생성
                CheckStatus checkStatus = new CheckStatus();
                // view 객체에 이벤트의 Value 를 set 함
                checkStatus.setProductId(registered.getId());
                checkStatus.setProductName(registered.getProductName());
                // view 레파지 토리에 save
                checkStatusRepository.save(checkStatus);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    @Transactional
    public void whenCheckConfirmed_then_DELETE_1(@Payload CheckConfirmed checkConfirmed) {
        try {
            if (checkConfirmed.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                checkStatusRepository.deleteByProductId(checkConfirmed.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    @Transactional
    public void whenDeleted_then_DELETE_2(@Payload Deleted deleted) {
        try {
            if (deleted.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                checkStatusRepository.deleteByProductId(deleted.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}