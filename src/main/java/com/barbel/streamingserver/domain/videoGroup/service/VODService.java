package com.barbel.streamingserver.domain.videoGroup.service;

import com.amazonaws.Response;
import com.barbel.streamingserver.domain.videoGroup.document.VOD;
import com.barbel.streamingserver.domain.videoGroup.document.VODGroup;
import com.barbel.streamingserver.domain.videoGroup.dto.MultipartInitResponseDto;
import com.barbel.streamingserver.domain.videoGroup.dto.MultipartUploadResponseDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODRegistrationRequestDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODTitleUpdateRequestDto;
import com.barbel.streamingserver.domain.videoGroup.exception.VODGroupNotFoundException;
import com.barbel.streamingserver.domain.videoGroup.repository.VODGroupRepository;
import com.barbel.streamingserver.global.aws.S3Uploader;
import com.barbel.streamingserver.global.result.ResultCode;
import com.barbel.streamingserver.global.result.ResultResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class VODService {
    private final VODGroupRepository vodGroupRepository;
    private final S3Uploader s3Uploader;
    private final String S3_DIR_NAME = "/vod/";

    /**
     * @param vodRegistrationRequestDto, thumbnail
     * @return ResponseEntity<ResultResponse>
     * @description Multipart 업로드 초기화 서비스
     * @since 2023. 05. 26.
     */
    @Transactional
    public ResponseEntity<ResultResponse> initUploadMultipart(
            VODRegistrationRequestDto vodRegistrationRequestDto,
            MultipartFile thumbnail
    ) {
        log.info("동영상 파일 업로드 초기화 시작");
        String thumbURL = s3Uploader.uploadImage(thumbnail, S3_DIR_NAME+vodRegistrationRequestDto.getVODGroupId());
        MultipartInitResponseDto multipartInitResponseDto = s3Uploader.initMultipartUpload(S3_DIR_NAME+vodRegistrationRequestDto.getVODGroupId());
        String[] vodInfo = vodRegistrationRequestDto.getTitle().split(".");
        VOD vod = new VOD(
                vodRegistrationRequestDto.getVodIndex(),
                vodInfo[0],
                vodInfo[1],
                vodRegistrationRequestDto.getDescription(),
                multipartInitResponseDto.getURL(),
                thumbURL

        );
        VODGroup vodGroup = vodGroupRepository.findById(vodRegistrationRequestDto.getVODGroupId()).orElseThrow(VODGroupNotFoundException::new);
        List<VOD> vodList = vodGroup.getVODList();
        vodList.add(vod);
        vodGroup.setVODList(vodList);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.VOD_REGISTRATION_INIT_SUCCESS, multipartInitResponseDto.getUploadId()));
    }

    public ResponseEntity<ResultResponse> uploadMultipart(
            String uploadId,
            int partNumber,
            MultipartFile file
    ) {
        log.info("동영상 파일 업로드 시작");
        //TODO : 멀티파트 업로드 구현
//        MultipartUploadResponseDto response = s3Uploader.multipartUpload(uploadId, partNumber, file);
//        return ResponseEntity.ok(ResultResponse.of(ResultCode.VOD_REGISTRATION_UPLOAD_SUCCESS, response));

        return null;
    }

    /**
     * @param vodTitleUpdateRequestDto
     * @return ResponseEntity<ResultResponse>
     * @description VOD제목을 수정하는 서비스
     * @since 2023. 05. 26.
     */
    public ResponseEntity<ResultResponse> updateVODTitle(
            VODTitleUpdateRequestDto vodTitleUpdateRequestDto
    ) {
        return null;
    }


}
