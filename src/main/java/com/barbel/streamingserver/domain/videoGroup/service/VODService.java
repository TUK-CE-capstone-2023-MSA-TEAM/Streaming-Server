package com.barbel.streamingserver.domain.videoGroup.service;

import com.barbel.streamingserver.domain.videoGroup.dto.VODRegistrationRequestDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODTitleUpdateRequestDto;
import com.barbel.streamingserver.domain.videoGroup.repository.VODRepository;
import com.barbel.streamingserver.global.result.ResultResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class VODService {
    private final VODRepository vodRepository;

    /**
     * @param vodRegistrationRequestDto, thumbnail
     * @return ResultResponse
     * @description VOD를 등록하는 서비스
     * @since 2023. 05. 26.
     */
    public ResultResponse registerVOD(
            VODRegistrationRequestDto vodRegistrationRequestDto,
            MultipartFile thumbnail
    ) {
        return null;
    }

    /**
     * @param vodTitleUpdateRequestDto
     * @return ResultResponse
     * @description VOD제목을 수정하는 서비스
     * @since 2023. 05. 26.
     */
    public ResultResponse updateVODTitle(
            VODTitleUpdateRequestDto vodTitleUpdateRequestDto
    ) {
        return null;
    }


}
