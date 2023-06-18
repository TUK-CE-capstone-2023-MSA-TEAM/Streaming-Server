package com.barbel.streamingserver.domain.videoGroup.controller;

import com.barbel.streamingserver.domain.videoGroup.dto.VODRegistrationRequestDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODTitleUpdateRequestDto;
import com.barbel.streamingserver.domain.videoGroup.service.VODService;
import com.barbel.streamingserver.global.aws.dto.FinishUploadMultipartRequestDto;
import com.barbel.streamingserver.global.aws.dto.MultipartUploadRequestDto;
import com.barbel.streamingserver.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@RequestMapping(VODController.VOD_API_URI)
public class VODController {
  public static final String VOD_API_URI = "streaming/api/vod";
  private final VODService vodService;

  @PostMapping("/registration")
  @Operation(summary = "VOD 멀티파트 등록 요청(VOD 등록 요청)")
  public ResponseEntity<ResultResponse> registerVOD(
      @RequestPart("data")VODRegistrationRequestDto vodRegistrationRequestDto,
      @RequestPart("thumbnail") MultipartFile thumbnail
  ) {
    return vodService.initUploadMultipart(vodRegistrationRequestDto, thumbnail);
  }

  @PostMapping("/upload")
  @Operation(summary = "VOD 멀티파트 업로드")
  public ResponseEntity<ResultResponse> uploadVOD(
      @RequestPart("data") MultipartUploadRequestDto multipartUploadRequestDto,
      @RequestPart("file") MultipartFile file
  ) {
    return vodService.uploadMultipart(multipartUploadRequestDto, file);
  }

  @PostMapping("/completeUpload")
  @Operation(summary = "VOD 멀티파트 업로드 완료")
  public ResponseEntity<ResultResponse> completeUploadVOD(
      @RequestBody FinishUploadMultipartRequestDto finishUploadMultipartRequestDto
  ) {
    return vodService.finishUploadMultipart(finishUploadMultipartRequestDto);
  }

  @PostMapping("/update/VODTitle")
  @Operation(summary = "VOD 제목 변경")
  public ResponseEntity<ResultResponse> updateVODTitle(
      @RequestBody VODTitleUpdateRequestDto vodTitleUpdateRequestDto
  ) {
    return vodService.updateVODTitle(vodTitleUpdateRequestDto);
  }
}
