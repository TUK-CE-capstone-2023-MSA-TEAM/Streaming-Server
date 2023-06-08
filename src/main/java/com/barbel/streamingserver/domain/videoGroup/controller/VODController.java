package com.barbel.streamingserver.domain.videoGroup.controller;

import com.barbel.streamingserver.domain.videoGroup.service.VODService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@RequestMapping(VODController.VOD_API_URI)
public class VODController {
  public static final String VOD_API_URI = "streaming/api/vod";
  private final VODService vodService;
}
