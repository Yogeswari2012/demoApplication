//package org.demo.demoapplication;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
//import lombok.extern.slf4j.XSlf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.UiService.LOGGER;
//
//@XSlf4j
//public class DynamoDBInitialization implements ApplicationListener<ApplicationReadyEvent>{
//    @Autowired
//    private DynamoDBMapper dynamoDBMapper;
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//
//        // Alternatively, you can scan your model package for the DynamoDBTable annotation
//        List<Class> modelClasses = new ArrayList<>();
//        modelClasses.add(Layer.class);
//        modelClasses.add(Marker.class);
//
//        for (Class cls : modelClasses) {
//            LOGGER.info("Creating DynamoDB table for " + cls.getSimpleName());
//            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(cls);
//            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
//
//            boolean created = TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
//
//            if (created) {
//                LOGGER.info("Created DynamoDB table for " + cls.getSimpleName());
//            } else {
//                LOGGER.info("Table already exists for " + cls.getSimpleName());
//            }
//        }
//
//        ListTablesResult tablesResult = amazonDynamoDB.listTables();
//
//        LOGGER.info("Current DynamoDB tables are: ");
//        for (String name : tablesResult.getTableNames()) {
//            LOGGER.info("\t" + name);
//        }
//    }
//}
