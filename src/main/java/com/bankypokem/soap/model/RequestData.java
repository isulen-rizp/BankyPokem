package com.bankypokem.soap.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
public class RequestData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ipOrigin;
    private LocalDateTime requestDate;
    private String methodExecuted;

    @Lob
    private String soapResponse;

    @Lob
    private String soapRequest;

    private Long totalTimeMs;
}
