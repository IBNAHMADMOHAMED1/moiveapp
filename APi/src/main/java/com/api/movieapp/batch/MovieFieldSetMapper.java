package com.api.movieapp.batch;

import com.api.movieapp.model.Movie;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class MovieFieldSetMapper implements org.springframework.batch.item.file.mapping.FieldSetMapper<Movie> {
        @Override
        public Movie mapFieldSet(FieldSet fieldSet) throws BindException {
            return new Movie(
                    fieldSet.readString("tconst"),
                    fieldSet.readString("titleType"),
                    fieldSet.readString("primaryTitle"),
                    fieldSet.readString("originalTitle"),
                    fieldSet.readBoolean("isAdult"),
                    fieldSet.readString("startYear"),
                    fieldSet.readString("endYear"),
                    fieldSet.readString("runtimeMinutes"),
                    fieldSet.readString("genres")
            );

        }
}
