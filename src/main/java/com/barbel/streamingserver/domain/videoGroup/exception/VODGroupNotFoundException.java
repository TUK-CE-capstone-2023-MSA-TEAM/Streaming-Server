package com.barbel.streamingserver.domain.videoGroup.exception;

import com.barbel.streamingserver.global.error.ErrorCode;
import com.barbel.streamingserver.global.error.exception.BusinessException;

public class VODGroupNotFoundException extends BusinessException {
    public VODGroupNotFoundException() {
        super(ErrorCode.VODGROUP_NOT_FOUND);
    }
}
