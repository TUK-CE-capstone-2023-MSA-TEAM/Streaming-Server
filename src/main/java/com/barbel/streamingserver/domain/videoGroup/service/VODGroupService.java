package com.barbel.streamingserver.domain.videoGroup.service;

import com.barbel.streamingserver.domain.videoGroup.document.VOD;
import com.barbel.streamingserver.domain.videoGroup.document.VODGroup;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupDetailResponseDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupInfoResponseDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupNameUpdateRequestDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupRegistrationRequestDto;
import com.barbel.streamingserver.domain.videoGroup.exception.VODGroupNotFoundException;
import com.barbel.streamingserver.domain.videoGroup.repository.VODGroupRepository;
import com.barbel.streamingserver.global.aws.S3Uploader;
import com.barbel.streamingserver.global.result.ResultCode;
import com.barbel.streamingserver.global.result.ResultResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class VODGroupService {
  private final VODGroupRepository vodGroupRepository;
  private final S3Uploader s3Uploader;

  private final String S3_DIR_NAME = "streaming/vodGroup/thumbnail";

  /**
   * @param vodGroupRegistrationRequestDto, thumbnail
   * @return ResponseEntity<ResultResponse>
   * @description VOD 그룹을 생성하는 서비스
   * @since 2023. 05. 24.
   */
  public ResponseEntity<ResultResponse> makeNewVODGroup(
    VODGroupRegistrationRequestDto vodGroupRegistrationRequestDto,
    MultipartFile thumbnail
  ) {
    String thumbnailURL = s3Uploader.uploadImage(thumbnail, S3_DIR_NAME);
    VODGroup vodGroup = VODGroup.builder()
        .vodGroupName(vodGroupRegistrationRequestDto.getVodGroupName())
        .ownerId(vodGroupRegistrationRequestDto.getOwnerId())
        .vodCount(0)
        .VODList(new ArrayList<>())
        .thumbnailURL(thumbnailURL)
        .keyword(vodGroupRegistrationRequestDto.getKeyword())
        .build();
    vodGroupRepository.save(vodGroup);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.VODGROUP_REGISTRATION_SUCCESS));
  }

  /**
   * @param vodGroupNameUpdateRequestDto
   * @return ResponseEntity<ResultResponse>
   * @description VOD 그룹 이름을 변경하는 서비스
   * @since 2023. 05. 24.
   */
  public ResponseEntity<ResultResponse> updateVODGroupTitle(
      VODGroupNameUpdateRequestDto vodGroupNameUpdateRequestDto
  ) {
    VODGroup vodGroup = vodGroupRepository.findById(vodGroupNameUpdateRequestDto.getVodGroupId())
        .orElseThrow(VODGroupNotFoundException::new);
    vodGroup.setVodGroupName(vodGroupNameUpdateRequestDto.getVodGroupName());
    vodGroupRepository.save(vodGroup);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.VODGROUP_UPDATE_SUCCESS));
  }

  /**
   * @param OwnerId
   * @return ResponseEntity<ResultResponse>
   * @description 소유자 ID를 통해 VOD 그룹 리스트를 가져오는 서비스
   * @since 2023. 05. 24.
   */
  public ResponseEntity<ResultResponse> getVODGroupOfUser(
      String OwnerId
  ) {
    List<VODGroupInfoResponseDto> vodGroupList = vodGroupRepository.findAllByOwnerId(OwnerId).stream().map(
        vodGroup -> VODGroupInfoResponseDto.builder()
            .id(vodGroup.get_id())
            .vodGroupName(vodGroup.getVodGroupName())
            .thumbnailURL(vodGroup.getThumbnailURL())
            .vodCount(vodGroup.getVodCount())
            .keyword(vodGroup.getKeyword())
            .build()
    ).collect(Collectors.toList());

    return ResponseEntity.ok(ResultResponse.of(ResultCode.VODGROUP_SEARCH_SUCCESS, vodGroupList));
  }

  /**
   * @param vodGroupId
   * @return ResponseEntity<ResultResponse>
   * @description VOD 그룹 ID를 통해 VOD 그룹 디테일을 가져오는 서비스
   * @since 2023. 05. 24.
   */
  public ResponseEntity<ResultResponse> getVODGroupDetailById(
      String vodGroupId
  ) {
    VODGroup vodGroup = vodGroupRepository.findById(vodGroupId)
        .orElseThrow(VODGroupNotFoundException::new);
    VODGroupDetailResponseDto result = VODGroupDetailResponseDto.builder()
        .id(vodGroup.get_id())
        .vodGroupName(vodGroup.getVodGroupName())
        .thumbnailURL(vodGroup.getThumbnailURL())
        .vodCount(vodGroup.getVodCount())
        .VODList(vodGroup.getVODList())
        .keyword(vodGroup.getKeyword())
        .build();
    return ResponseEntity.ok(ResultResponse.of(ResultCode.VODGROUP_SEARCH_SUCCESS, result));
  }

  /**
   * @param
   * @return ResponseEntity<ResultResponse>
   * @description 모든 VOD 그룹 리스트를 가져오는 서비스
   * @since 2023. 06. 08.
   */
  public ResponseEntity<ResultResponse> getAllVODGroups() {
    List<VODGroupInfoResponseDto> vodGroupList = vodGroupRepository.findAll().stream().map(
        vodGroup -> VODGroupInfoResponseDto.builder()
            .id(vodGroup.get_id())
            .vodGroupName(vodGroup.getVodGroupName())
            .thumbnailURL(vodGroup.getThumbnailURL())
            .vodCount(vodGroup.getVodCount())
            .keyword(vodGroup.getKeyword())
            .build()
    ).collect(Collectors.toList());

    return ResponseEntity.ok(ResultResponse.of(ResultCode.VODGROUP_SEARCH_SUCCESS, vodGroupList));
  }

  /**
   * @param keyword
   * @return ResponseEntity<ResultResponse>
   * @description VOD 그룹 키워드 검색 서비스
   * @since 2023. 06. 21.
   */
  public ResponseEntity<ResultResponse> searchVODGroupByKeyword(
      String keyword
  ) {
    List<VODGroupInfoResponseDto> vodGroupList = vodGroupRepository.findAllByKeyword(keyword).stream().map(
        vodGroup -> VODGroupInfoResponseDto.builder()
            .id(vodGroup.get_id())
            .vodGroupName(vodGroup.getVodGroupName())
            .thumbnailURL(vodGroup.getThumbnailURL())
            .vodCount(vodGroup.getVodCount())
            .keyword(vodGroup.getKeyword())
            .build()
    ).collect(Collectors.toList());

    return ResponseEntity.ok(ResultResponse.of(ResultCode.VODGROUP_SEARCH_SUCCESS, vodGroupList));
  }
}
