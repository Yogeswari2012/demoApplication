package org.demo.demoapplication;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class PublicRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public  <T> String getTableName(T t) {
        if (t.getClass().isAnnotationPresent(DynamoDBTable.class)) {
            DynamoDBTable annotation = t.getClass().getAnnotation(DynamoDBTable.class);
            System.out.println(annotation.tableName());
            return annotation.tableName();
        }
        throw new IllegalArgumentException("Class " + t.getClass().getName() + " is not annotated with @DynamoDBTable");
    }

    public <T, V> List< DynamoDBMapperFieldModel<T, V>> getDynamoDBKeys(T t) {
        DynamoDBMapperTableModel<T> tableModel = (DynamoDBMapperTableModel<T>) dynamoDBMapper.getTableModel(t.getClass());

        DynamoDBMapperFieldModel<T, V> partitionKey = tableModel.hashKey();
        DynamoDBMapperFieldModel<T, V> sortKeyField = tableModel.rangeKey();
        System.out.println(partitionKey);
        System.out.println(sortKeyField);
        List<DynamoDBMapperFieldModel<T, V>> li = new ArrayList<>();
        li.add(partitionKey);
        if(sortKeyField != null) {
            li.add(sortKeyField);
        }
        return li;
        }

    public <T> T save(T t) {
        dynamoDBMapper.save(t);
        return t;
    }

//    returns only one item from table
    public <T, V>  T findByProperty(T t) {
        List<DynamoDBMapperFieldModel<T, V>>  li =   this.getDynamoDBKeys(t);
        return (T) dynamoDBMapper.load(t.getClass(),(Object) li.get(0).get(t), (Object) li.get(1).get(t));
    }

    public <T> List<T> query(T t, Class<T> tClass) {
        DynamoDBQueryExpression<T> expression = new DynamoDBQueryExpression<T>().withHashKeyValues(t).withLimit(10);
        return dynamoDBMapper.query(tClass, expression);
    }

    public <T,H, R> void deleteByProperty(T t, Class<T> tClass) {
        DynamoDBTableMapper<T, H, R> mapper = dynamoDBMapper.newTableMapper(tClass);
         mapper.deleteIfExists(t);
//        List<DynamoDBMapperFieldModel<T, V>>  li =   this.getDynamoDBKeys(t);
//
//        HashMap<String, AttributeValue> keyToGet = new HashMap<>();
//        keyToGet.put(li.get(0).name(), AttributeValue.(li.get(0).hashCode()).build());
//        if(li.size() > 1) {
//            keyToGet.put(li.get(1).name(), AttributeValue.builder().s(li.get(1).hashCode()).build());
//        }
//
//        DeleteItemRequest deleteReq = DeleteItemRequest.builder()
//                .tableName(this.getTableName(t))
//                .key(keyToGet)
////                .build();
//        try {
//            dynamoDBMapper.de(deleteReq);
//        } catch (DynamoDbException e) {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//        DeleteItemRequest dir = new DeleteItemRequest().withTableName(this.getTableName(t)).withKey(keyToGet);


    }
}
