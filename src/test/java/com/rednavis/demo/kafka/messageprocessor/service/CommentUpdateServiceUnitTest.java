package com.rednavis.demo.kafka.messageprocessor.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rednavis.demo.kafka.messageprocessor.dto.ItemDto;

class CommentUpdateServiceUnitTest {

  CommentUpdateService uut;

  @BeforeEach
  void setUp() {
    uut = new CommentUpdateService();
  }

  @Test
  void updateCommentWithRarityTestRare() {
    ItemDto rareItem = new ItemDto();
    rareItem.setAmount(1);

    ItemDto itemDto = uut.updateCommentWithRarity(rareItem);

    assertThat(itemDto.getDescription()).endsWith("\nRare item");
  }

  @Test
  void updateCommentWithRarityTestUncommon() {
    ItemDto uncommonItem = new ItemDto();
    uncommonItem.setAmount(15);

    ItemDto itemDto = uut.updateCommentWithRarity(uncommonItem);

    assertThat(itemDto.getDescription()).endsWith("\nUncommon item");
  }

  @Test
  void updateCommentWithRarityTestCommon() {
    ItemDto commonItem = new ItemDto();
    commonItem.setAmount(101);

    ItemDto itemDto = uut.updateCommentWithRarity(commonItem);

    assertThat(itemDto.getDescription()).endsWith("\nCommon item");
  }

  @Test
  void updateCommentWithRarityTestInvalidWithoutSpring() {
    ItemDto invalidItem = new ItemDto();
    invalidItem.setAmount(0);

    ItemDto itemDto = uut.updateCommentWithRarity(invalidItem);

    assertThat(itemDto.getDescription()).isNullOrEmpty();
  }
}
