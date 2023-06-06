package com.barbel.streamingserver.domain.videoGroup.exception;

import com.barbel.streamingserver.global.error.ErrorCode;
import com.barbel.streamingserver.global.error.exception.BusinessException;

public class VODNotFoundException extends BusinessException {
    public VODNotFoundException() {
        super(ErrorCode.VOD_NOT_FOUND);
    }
}
