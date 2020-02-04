package com.rednavis.demo.kafka.messageprocessor.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import com.rednavis.demo.kafka.messageprocessor.dto.ItemDto;

@Service
@EnableBinding(Processor.class)
@MessageEndpoint
public class CommentUpdateService {

  @StreamListener(Processor.INPUT)
  @SendTo(Processor.OUTPUT)
  public ItemDto updateCommentWithRarity(ItemDto toUpdate) {
    String description = toUpdate.getDescription();
    if (toUpdate.getAmount() > 0 && toUpdate.getAmount() <= 10) {
      toUpdate.setDescription(description + "\nRare item");
    }
    if (toUpdate.getAmount() > 10 && toUpdate.getAmount() <= 100) {
      toUpdate.setDescription(description + "\nUncommon item");
    }
    if (toUpdate.getAmount() > 100) {
      toUpdate.setDescription(description + "\nCommon item");
    }
    return toUpdate;
  }
}
