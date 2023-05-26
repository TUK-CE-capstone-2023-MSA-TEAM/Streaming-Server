package com.barbel.streamingserver.domain.videoGroup.dto;

import lombok.Data;

@Data
public class VODRegistrationRequestDto {
    private String title;
    private String description;
    private String ownerId;
}
