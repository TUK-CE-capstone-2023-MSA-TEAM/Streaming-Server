package com.barbel.streamingserver.domain.videoGroup.service;

import com.barbel.streamingserver.domain.videoGroup.document.VOD;
import com.barbel.streamingserver.domain.videoGroup.document.VODGroup;
import com.barbel.streamingserver.domain.videoGroup.exception.VODNotFoundException;
import com.barbel.streamingserver.global.aws.dto.FinishUploadMultipartRequestDto;
import com.barbel.streamingserver.global.aws.dto.MultipartInitResponseDto;
import com.barbel.streamingserver.global.aws.dto.MultipartUploadRequestDto;
import com.barbel.streamingserver.global.aws.dto.MultipartUploadResponseDto;
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
    private final String S3_DIR_NAME = "streaming/vod/";

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
        String thumbURL = s3Uploader.uploadImage(thumbnail, S3_DIR_NAME+vodRegistrationRequestDto.getVODGroupId()+"/thumbnail");
        String key = S3_DIR_NAME+vodRegistrationRequestDto.getVODGroupId()+"/"+vodRegistrationRequestDto.getTitle();
        MultipartInitResponseDto multipartInitResponseDto = s3Uploader.initMultipartUpload(key);
        String[] vodInfo = vodRegistrationRequestDto.getTitle().split(".");
        VOD vod = new VOD(
                vodRegistrationRequestDto.getVodIndex(),
                vodRegistrationRequestDto.getTitle(),
//                vodInfo[0],
//                vodInfo[1],
                vodRegistrationRequestDto.getDescription(),
                multipartInitResponseDto.getURL(),
                thumbURL,
                key

        );
        VODGroup vodGroup = vodGroupRepository.findById(vodRegistrationRequestDto.getVODGroupId()).orElseThrow(VODGroupNotFoundException::new);
        List<VOD> vodList = vodGroup.getVODList();
        vodList.add(vod);
        vodGroup.setVODList(vodList);
        vodGroup.setVodCount(vodGroup.getVodCount()+1);
        vodGroupRepository.save(vodGroup);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.VOD_REGISTRATION_INIT_SUCCESS, multipartInitResponseDto.getUploadId()));
    }

    /**
     * @param multipartUploadRequestDto, file
     * @return ResponseEntity<ResultResponse>
     * @description Multipart 업로드 서비스
     * @since 2023. 05. 26.
     */
    public ResponseEntity<ResultResponse> uploadMultipart(
            MultipartUploadRequestDto multipartUploadRequestDto,
            MultipartFile file
    ) {
        VOD vod = getVOD(multipartUploadRequestDto.getVodGroupId(), multipartUploadRequestDto.getVodIndex());
        MultipartUploadResponseDto response = s3Uploader.multipartUpload(multipartUploadRequestDto, vod.getKey(), file);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.VOD_MULTIPART_UPLOAD_SUCCESS, response));
    }

    /**
     * @param finishUploadMultipartRequestDto
     * @return ResponseEntity<ResultResponse>
     * @description Multipart 업로드 완료 서비스
     * @since 2023. 05. 26.
     */
    public ResponseEntity<ResultResponse> finishUploadMultipart(
            FinishUploadMultipartRequestDto finishUploadMultipartRequestDto
    ) {
        VOD vod = getVOD(finishUploadMultipartRequestDto.getVODGroupId(), finishUploadMultipartRequestDto.getVodIndex());
        s3Uploader.finishMultipartUpload(finishUploadMultipartRequestDto, vod.getKey());

        return ResponseEntity.ok(ResultResponse.of(ResultCode.VOD_UPLOAD_FINISH_SUCCESS));
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
        VOD vod = getVOD(vodTitleUpdateRequestDto.getVodGroupId(), vodTitleUpdateRequestDto.getVodIndex());
        vod.setTitle(vodTitleUpdateRequestDto.getNewTitle());
        return ResponseEntity.ok(ResultResponse.of(ResultCode.VODGROUP_UPDATE_SUCCESS));
    }

    private VOD getVOD(String vodGroupId, int vodIndex) {
        VODGroup vodGroup = vodGroupRepository.findById(vodGroupId).orElseThrow(VODGroupNotFoundException::new);
        List<VOD> vodList = vodGroup.getVODList();
        return vodList.stream().filter(v -> v.getIdx() == vodIndex)
            .findFirst().orElseThrow(VODNotFoundException::new);
    }

}
