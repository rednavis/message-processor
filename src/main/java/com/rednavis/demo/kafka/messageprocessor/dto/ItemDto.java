package com.rednavis.demo.kafka.messageprocessor.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ItemDto {

  @NotEmpty(message = "Name is required")
  private String name;
  @Min(value = 1, message = "Not less than 1 item")
  private int amount;
  @DecimalMin(value = "0.1", message = "Not less than 0.1")
  private double price;
  private String description;
  private boolean isPublic;
}
