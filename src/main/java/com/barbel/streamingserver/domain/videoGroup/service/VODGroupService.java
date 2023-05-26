package com.barbel.streamingserver.domain.videoGroup.service;

import com.barbel.streamingserver.domain.videoGroup.document.VOD;
import com.barbel.streamingserver.domain.videoGroup.document.VODGroup;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupDetailResponseDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupInfoResponseDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupNameUpdateRequestDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupRegistrationRequestDto;
import com.barbel.streamingserver.domain.videoGroup.exception.VODGroupNotFoundException;
import com.barbel.streamingserver.domain.videoGroup.repository.VODGroupRepository;
import com.barbel.streamingserver.global.result.ResultCode;
import com.barbel.streamingserver.global.result.ResultResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class VODGroupService {
  private final VODGroupRepository vodGroupRepository;

  /**
   *
   * @param vodGroupRegistrationRequestDto, thumbnail
   * @return ResultResponse
   * @description VOD 그룹을 생성하는 서비스
   * @since 2023. 05. 24.
   */
  public ResultResponse makeNewVODGroup(
    VODGroupRegistrationRequestDto vodGroupRegistrationRequestDto,
    MultipartFile thumbnail
  ) {
    //TODO: 썸네일 이미지 받아서 업로드 후 URL 받아 저장하는 코드 추가
    VODGroup vodGroup = VODGroup.builder()
        .vodGroupName(vodGroupRegistrationRequestDto.getVodGroupName())
        .ownerId(vodGroupRegistrationRequestDto.getOwnerId())
        .vodCount(0)
        .VODList(new ArrayList<>())
        .build();
    vodGroupRepository.save(vodGroup);
    return ResultResponse.of(ResultCode.VODGROUP_REGISTRATION_SUCCESS);
  }

  /**
   *
   * @param vodGroupNameUpdateRequestDto
   * @return ResultResponse
   * @description VOD 그룹 이름을 변경하는 서비스
   * @since 2023. 05. 24.
   */
  public ResultResponse updateVODGroupName(
      VODGroupNameUpdateRequestDto vodGroupNameUpdateRequestDto
  ) {
    VODGroup vodGroup = vodGroupRepository.findById(vodGroupNameUpdateRequestDto.getVodGroupId())
        .orElseThrow(VODGroupNotFoundException::new);
    vodGroup.setVodGroupName(vodGroupNameUpdateRequestDto.getVodGroupName());
    vodGroupRepository.save(vodGroup);
    return ResultResponse.of(ResultCode.VODGROUP_UPDATE_SUCCESS);
  }

  /**
   *
   * @param OwnerId
   * @return ResultResponse
   * @description 소유자 ID를 통해 VOD 그룹 리스트를 가져오는 서비스
   * @since 2023. 05. 24.
   */
  public ResultResponse getVODGroupOfUser(
      String OwnerId
  ) {
    List<VODGroupInfoResponseDto> vodGroupList = vodGroupRepository.findAllByOwnerId(OwnerId).stream().map(
        vodGroup -> VODGroupInfoResponseDto.builder()
            .id(vodGroup.get_id())
            .vodGroupName(vodGroup.getVodGroupName())
            .thumbnailURL(vodGroup.getThumbnailURL())
            .vodCount(vodGroup.getVodCount())
            .build()
    ).collect(Collectors.toList());

    return ResultResponse.of(ResultCode.VODGROUP_SEARCH_SUCCESS, vodGroupList);
  }

  /**
   *
   * @param vodGroupId
   * @return ResultResponse
   * @description VOD 그룹 ID를 통해 VOD 그룹 디테일을 가져오는 서비스
   * @since 2023. 05. 24.
   */
  public ResultResponse getVODGroupById(
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
        .build();
    return ResultResponse.of(ResultCode.VODGROUP_SEARCH_SUCCESS, result);
  }
}
