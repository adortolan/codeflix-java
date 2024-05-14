package com.codeflixjava.application.category.create;

public record CreateCategoryCommand(
        String name,
        String description,
        Boolean isActive
) {
    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription,
            final Boolean isActive
    ) {
        return new CreateCategoryCommand(aName, aDescription, isActive);
    }
}
