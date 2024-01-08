package com.api.movieapp.batch;

import com.api.movieapp.model.Movie;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;

import java.util.StringTokenizer;

public class CustomLineMapper implements LineMapper<Movie> {

    private final LineTokenizer tokenizer;
    private final FieldSetMapper<Movie> fieldSetMapper;

    public CustomLineMapper(LineTokenizer tokenizer, FieldSetMapper<Movie> fieldSetMapper) {
        this.tokenizer = tokenizer;
        this.fieldSetMapper = fieldSetMapper;
    }

    @Override
    public Movie mapLine(String line, int lineNumber) throws Exception {
        StringTokenizer tokens = new StringTokenizer(line, "\t"); // Adjust the delimiter
        return fieldSetMapper.mapFieldSet(tokenizer.tokenize(String.valueOf(tokens)));
    }
}
