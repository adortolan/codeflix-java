package com.codeflixjava.domain.exceptions;

import com.codeflixjava.domain.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException {
   private final List<Error> errors;
   private DomainException(List<Error> anErrors) {
      super("",null,false,false);
      this.errors = anErrors;
   }

   public static DomainException with(List<Error> anErrors) {
      return new DomainException(anErrors);
   }

   public List<Error> getErrors() {
      return errors;
   }
}
