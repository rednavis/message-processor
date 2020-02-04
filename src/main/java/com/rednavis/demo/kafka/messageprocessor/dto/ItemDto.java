package com.rednavis.demo.kafka.messageprocessor.dto;

import lombok.Data;

@Data
public class ItemDto {

  private String name;
  private int amount;
  private double price;
  private String description;
  private boolean isPublic;
}
