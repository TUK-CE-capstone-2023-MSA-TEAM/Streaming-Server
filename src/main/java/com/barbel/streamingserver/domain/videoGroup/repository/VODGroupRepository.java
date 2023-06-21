package com.barbel.streamingserver.domain.videoGroup.repository;

import com.barbel.streamingserver.domain.videoGroup.document.VODGroup;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VODGroupRepository extends MongoRepository<VODGroup, String> {
  List<VODGroup> findAllByOwnerId(String ownerId);
  List<VODGroup> findAllByKeyword(String keyword);
}
