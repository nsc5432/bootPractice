package com.backend.sample.demo.vo.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogResponse implements Serializable {
    private long id;
    private LogType type;
    private String content;
}
