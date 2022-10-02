package com.hcmute.yourtours.factories.rule_home_categories;

import com.hcmute.yourtours.commands.RuleHomeCategoriesCommand;
import com.hcmute.yourtours.exceptions.YourToursErrorCode;
import com.hcmute.yourtours.libs.exceptions.InvalidException;
import com.hcmute.yourtours.libs.factory.BasePersistDataFactory;
import com.hcmute.yourtours.models.rule_home_categories.RuleHomeCategoriesDetail;
import com.hcmute.yourtours.models.rule_home_categories.RuleHomeCategoriesInfo;
import com.hcmute.yourtours.repositories.RuleHomeCategoriesRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class RuleHomeCategoriesFactory
        extends BasePersistDataFactory<UUID, RuleHomeCategoriesInfo, RuleHomeCategoriesDetail, Long, RuleHomeCategoriesCommand>
        implements IRuleHomeCategoriesFactory {

    private final RuleHomeCategoriesRepository ruleHomeCategoriesRepository;

    protected RuleHomeCategoriesFactory(RuleHomeCategoriesRepository repository) {
        super(repository);
        this.ruleHomeCategoriesRepository = repository;
    }

    @Override
    @NonNull
    protected Class<RuleHomeCategoriesDetail> getDetailClass() {
        return RuleHomeCategoriesDetail.class;
    }

    @Override
    public RuleHomeCategoriesCommand createConvertToEntity(RuleHomeCategoriesDetail detail) {
        if (detail == null) {
            return null;
        }
        return RuleHomeCategoriesCommand.builder()
                .name(detail.getName())
                .description(detail.getDescription())
                .status(detail.getStatus())
                .build();
    }

    @Override
    public void updateConvertToEntity(RuleHomeCategoriesCommand entity, RuleHomeCategoriesDetail detail) {
        entity.setName(detail.getName());
        entity.setDescription(detail.getDescription());
        entity.setStatus(detail.getStatus());
    }

    @Override
    public RuleHomeCategoriesDetail convertToDetail(RuleHomeCategoriesCommand entity) {
        return (RuleHomeCategoriesDetail) convertToInfo(entity);
    }

    @Override
    public RuleHomeCategoriesInfo convertToInfo(RuleHomeCategoriesCommand entity) {
        if (entity == null) {
            return null;
        }
        return RuleHomeCategoriesInfo.builder()
                .id(entity.getRuleCategoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }

    @Override
    protected Long convertId(UUID id) throws InvalidException {
        Optional<RuleHomeCategoriesCommand> optional = ruleHomeCategoriesRepository.findByRuleCategoryId(id);
        if (optional.isEmpty()) {
            throw new InvalidException(YourToursErrorCode.NOT_FOUND_RULE_CATEGORIES);
        }
        return optional.get().getId();
    }
}
