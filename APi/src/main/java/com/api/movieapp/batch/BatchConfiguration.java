package com.api.movieapp.batch;


import com.api.movieapp.model.Movie;
import com.api.movieapp.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;


@AllArgsConstructor
@Configuration
public class BatchConfiguration {
    private final MovieRepository movieRepository;

    @Bean
    public FlatFileItemReader<Movie> reader() {
        FlatFileItemReader<Movie> itemReader = new FlatFileItemReader<Movie>();
        itemReader.setName("csvReader");
        itemReader.setResource(new FileSystemResource("src/main/resources/data.tsv"));
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Movie> lineMapper() {
        DefaultLineMapper<com.api.movieapp.model.Movie> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setNames("tconst", "titleType", "primaryTitle", "originalTitle", "isAdult", "startYear", "endYear", "runtimeMinutes", "genres");
        lineTokenizer.setDelimiter("\t");
        lineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<Movie> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Movie.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new MovieFieldSetMapper());
        return lineMapper;

    }


    @Bean
    public RepositoryItemWriter<com.api.movieapp.model.Movie> writer() {
        return new RepositoryItemWriterBuilder<com.api.movieapp.model.Movie>()
                .repository(movieRepository)
                .methodName("save")
                .build();
    }

    @Bean
    public MovieItemProcessor processor() {
        return new MovieItemProcessor();
    }


    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<Movie, Movie>chunk(1000, transactionManager)
                .reader(reader())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new JobBuilder("import", jobRepository)
                .flow(step1(jobRepository,transactionManager)).end().build();
    }

    // we 1M line so we need to use multi thread
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
        taskExecutor.setConcurrencyLimit(10);
        taskExecutor.setThreadGroupName("spring_batch");
        taskExecutor.setThreadNamePrefix("spring_batch");
        taskExecutor.setThreadPriority(Thread.MAX_PRIORITY);
        return taskExecutor;
    }



}