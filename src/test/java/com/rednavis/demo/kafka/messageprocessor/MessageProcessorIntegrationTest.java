package com.rednavis.demo.kafka.messageprocessor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

import java.util.concurrent.BlockingQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.messaging.support.MessageBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rednavis.demo.kafka.messageprocessor.dto.ItemDto;

@SpringBootTest
class MessageProcessorIntegrationTest {

  @Autowired
  private Processor processor;
  @Autowired
  private MessageCollector messageCollector;

  @Test
  void receiveItemValid() throws Exception {
    ItemDto validItem = new ItemDto();
    validItem.setName("Not empty");
    validItem.setAmount(15);
    validItem.setPrice(10D);
    validItem.setPublic(false);
    validItem.setDescription("");

    processor.input().send(MessageBuilder.withPayload(validItem).build());

    validItem.setDescription("\nUncommon item");
    String itemRS = new ObjectMapper().writeValueAsString(validItem);
    BlockingQueue<Message<?>> messages = messageCollector.forChannel(processor.output());

    assertThat(messages, receivesPayloadThat(is(itemRS)));
  }

  @Test
  void receiveItemInvalid() {
    ItemDto invalidItem = new ItemDto();
    invalidItem.setName("Not empty");
    invalidItem.setAmount(15);
    invalidItem.setPrice(0D);
    invalidItem.setPublic(false);

    assertThrows(MethodArgumentNotValidException.class, () -> processor.input().send(MessageBuilder.withPayload(invalidItem).build()));
  }

}
