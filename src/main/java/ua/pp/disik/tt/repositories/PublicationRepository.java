package ua.pp.disik.tt.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.pp.disik.tt.entities.Publication;
import ua.pp.disik.tt.entities.Topic;

import java.util.List;

/**
 * Created by disik on 4/6/17.
 */
public interface PublicationRepository extends MongoRepository<Publication, String> {
    Page<Publication> findByUserId(String userId, Pageable pageble);
    Page<Publication> findByUserIdAndTopic(String userId, Topic topic, Pageable pageble);
    Page<Publication> findByTopic(Topic topic, Pageable pageble);
    Page<Publication> findByUserIdInAndTopic(List<String> userIds, Topic topic, Pageable pageble);
    Page<Publication> findByUserIdIn(List<String> userIds, Pageable pageble);
}
