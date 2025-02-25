package excopen.backend.iservices;

import excopen.backend.entities.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Vector;


public interface ITagService {
    Tag createTag(Tag tag);
    Optional<Tag> getTagById(Long tagId);
    Tag updateTag(Tag tag);
    void deleteTag(Long tagId);
    List<Tag> getAllTags();
 //   List<Tag> findTagsByVector(Vector vectorRepresentation);
    public long getTagCount();
}
