package com.codeflixjava.infrastructure.castmember;

import com.codeflixjava.MySQLGatewayTest;
import com.codeflixjava.infrastructure.castmember.persistence.CastMemberMySQLGateway;
import com.codeflixjava.infrastructure.castmember.persistence.CastMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class CastMemberMySQLGatewayTest {
    @Autowired
    private CastMemberMySQLGateway castMemberGateway;
    @Autowired
    private CastMemberRepository castMemberRepository;
    @Test
    public void testDependencies() {
        Assertions.assertNotNull(castMemberGateway);
        Assertions.assertNotNull(castMemberRepository);
    }
}
