package com.minhle.leeservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    String message;
    LocalDateTime time;
}
