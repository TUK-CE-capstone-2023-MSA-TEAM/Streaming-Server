package com.barbel.streamingserver.domain.videoGroup.dto;

import lombok.Data;

@Data
public class VODTitleUpdateRequestDto {
    private String vodGroupId;
    private int vodIndex;
    private String newTitle;
}
