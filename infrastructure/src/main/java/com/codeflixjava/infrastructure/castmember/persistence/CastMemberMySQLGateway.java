package com.codeflixjava.infrastructure.castmember.persistence;

import com.codeflixjava.domain.castmember.CastMember;
import com.codeflixjava.domain.castmember.CastMemberGateway;
import com.codeflixjava.domain.castmember.CastMemberID;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.pagination.SearchQuery;
import com.codeflixjava.infrastructure.castmember.CastMemberJpaEntity;
import com.codeflixjava.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.Optional;

@Component
public class CastMemberMySQLGateway implements CastMemberGateway {
    private final CastMemberRepository castMemberRepository;
    public CastMemberMySQLGateway(final CastMemberRepository castMemberRepository) {
        this.castMemberRepository = Objects.requireNonNull(castMemberRepository);
    }
    @Override
    public CastMember create(final CastMember aCastMember) {
        return save(aCastMember);
    }
    @Override
    public void deleteById(final CastMemberID aMemberId) {
        final var anId = aMemberId.getValue();
        if (this.castMemberRepository.existsById(anId)) {
            this.castMemberRepository.deleteById(anId);
        }
    }
    @Override
    public Optional<CastMember> findById(final CastMemberID anId) {
        return this.castMemberRepository.findById(anId.getValue())
                .map(CastMemberJpaEntity::toAggregate);
    }
    @Override
    public CastMember update(final CastMember aCastMember) {
        return save(aCastMember);
    }
    @Override
    public Pagination<CastMember> findAll(final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );
        final var where = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);
        final var pageResult =
                this.castMemberRepository.findAll(where, page);
        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CastMemberJpaEntity::toAggregate).toList()
        );
    }

    private CastMember save(final CastMember aCastMember) {
        return this.castMemberRepository.save(CastMemberJpaEntity.from(aCastMember))
                .toAggregate();
    }

    private Specification<CastMemberJpaEntity> assembleSpecification(final String terms) {
        return SpecificationUtils.like("name", terms);
    }
}
