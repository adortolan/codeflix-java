package com.codeflixjava.infrastructure.api.controller;

import com.codeflixjava.infrastructure.api.CastMemberAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CastMemberController implements CastMemberAPI {
    @Override
    public ResponseEntity<?> create(final Object input) {
        return null;
    }
}
