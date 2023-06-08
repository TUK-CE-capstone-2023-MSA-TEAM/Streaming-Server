package com.barbel.streamingserver.domain.videoGroup.controller;

import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupNameUpdateRequestDto;
import com.barbel.streamingserver.domain.videoGroup.dto.VODGroupRegistrationRequestDto;
import com.barbel.streamingserver.domain.videoGroup.service.VODGroupService;
import com.barbel.streamingserver.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@RequestMapping(VODGroupController.VODGROUP_API_URI)
public class VODGroupController {
  public static final String VODGROUP_API_URI = "streaming/api/vodGroup";
  private final VODGroupService vodGroupService;

  @GetMapping("/List")
  @Operation(summary = "VOD 그룹 전체 리스트 조회")
  public ResponseEntity<ResultResponse> getVODGroupList() {
    return vodGroupService.getAllVODGroups();
  }

  @GetMapping("/list/detail/{vodGroupId}")
  @Operation(summary = "특정 VOD 그룹 상세 정보 조회")
  public ResponseEntity<ResultResponse> getVODGroupDetail(
    @PathVariable("vodGroupId") String vodGroupId
  ) {
    return vodGroupService.getVODGroupDetailById(vodGroupId);
  }

  @GetMapping("/list/{mentorId}")
  @Operation(summary = "특정 멘토의 VOD 그룹 리스트 조회")
  public ResponseEntity<ResultResponse> getVODGroupListByMentorId(
    @PathVariable("mentorId") String mentorId
  ) {
    return vodGroupService.getVODGroupOfUser(mentorId);
  }

  @PostMapping("/registration")
  @Operation(summary = "VOD 그룹 생성")
  public ResponseEntity<ResultResponse> createVODGroup(
      @RequestPart("data") VODGroupRegistrationRequestDto vodGroupRegistrationRequestDto,
      @RequestPart("thumbnail") MultipartFile thumbnail
  ) {
    return vodGroupService.makeNewVODGroup(vodGroupRegistrationRequestDto, thumbnail);
  }

  @PostMapping("/update/vodGroupTitle")
  @Operation(summary = "VOD 그룹 제목 변경")
  public ResponseEntity<ResultResponse> updateVODGroupTitle(
      @RequestBody VODGroupNameUpdateRequestDto vodGroupNameUpdateRequestDto
  ) {
    return vodGroupService.updateVODGroupTitle(vodGroupNameUpdateRequestDto);
  }

}
