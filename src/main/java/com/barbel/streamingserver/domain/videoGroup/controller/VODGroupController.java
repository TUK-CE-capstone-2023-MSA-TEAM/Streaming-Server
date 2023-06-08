package com.barbel.streamingserver.domain.videoGroup.controller;

import com.barbel.streamingserver.domain.videoGroup.service.VODGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@RequestMapping(VODGroupController.VODGROUP_API_URI)
public class VODGroupController {
  public static final String VODGROUP_API_URI = "streaming/api/vodGroup";
  private final VODGroupService vodGroupService;

}
