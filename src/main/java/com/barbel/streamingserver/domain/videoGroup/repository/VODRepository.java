package com.barbel.streamingserver.domain.videoGroup.repository;

import com.barbel.streamingserver.domain.videoGroup.document.VOD;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VODRepository extends MongoRepository<VOD, String> {
}
