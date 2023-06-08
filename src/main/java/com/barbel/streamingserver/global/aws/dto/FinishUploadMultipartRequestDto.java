package com.barbel.streamingserver.global.aws.dto;

import com.amazonaws.services.s3.model.PartETag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class FinishUploadMultipartRequestDto {
    private String uploadId;
    private String VODGroupId;
    private int vodIndex;
    private List<MultipartFinishETagRequestDto> partETagList;

    public List<PartETag> deserializePartEtagToS3PartETagClass() {
      return partETagList.stream().map(partETag -> new PartETag(partETag.getPartIndex(), partETag.getEtag())).collect(
          Collectors.toList());
    }
}
