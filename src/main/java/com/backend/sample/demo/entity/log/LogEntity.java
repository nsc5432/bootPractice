package com.backend.sample.demo.entity.log;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.backend.sample.demo.vo.log.LogType;


@AllArgsConstructor
@Data
public class LogEntity {
    private long id;
    private LogType type;
    private String content;
}
