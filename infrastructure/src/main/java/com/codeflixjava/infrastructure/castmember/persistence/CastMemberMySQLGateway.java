package com.codeflixjava.infrastructure.castmember.persistence;

import com.codeflixjava.domain.castmember.CastMember;
import com.codeflixjava.domain.castmember.CastMemberGateway;
import com.codeflixjava.domain.castmember.CastMemberID;
import com.codeflixjava.domain.pagination.Pagination;
import com.codeflixjava.domain.pagination.SearchQuery;
import com.codeflixjava.infrastructure.castmember.CastMemberJpaEntity;
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
    public void deleteById(final CastMemberID anId) {
    }
    @Override
    public Optional<CastMember> findById(final CastMemberID anId) {
        return Optional.empty();
    }
    @Override
    public CastMember update(final CastMember aCastMember) {
        return null;
    }
    @Override
    public Pagination<CastMember> findAll(final SearchQuery aQuery) {
        return null;
    }

    private CastMember save(final CastMember aCastMember) {
        return this.castMemberRepository.save(CastMemberJpaEntity.from(aCastMember))
                .toAggregate();
    }
}
