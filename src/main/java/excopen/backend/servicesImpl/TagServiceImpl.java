package excopen.backend.servicesImpl;

import excopen.backend.entities.Tag;
import excopen.backend.iservices.ITagService;
import excopen.backend.repositories.TagRepository;
import excopen.backend.utills.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Service
public class TagServiceImpl implements ITagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Optional<Tag> getTagById(Long tagId) {
        return tagRepository.findById(tagId);
    }

    @Override
    public Tag updateTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }



    public long getTagCount() {
        return (int) tagRepository.count();
    }
}
