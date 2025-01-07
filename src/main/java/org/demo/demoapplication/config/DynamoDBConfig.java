package org.demo.demoapplication.config;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@Slf4j
@EnableDynamoDBRepositories(basePackages = "org.demo.demoapplication")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndPoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB amazonDynamoDB =  AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndPoint,"us-east-1")).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAccessKey,amazonSecretKey))).build();
        return amazonDynamoDB;
    }
}
