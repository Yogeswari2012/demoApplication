package org.demo.demoapplication;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@Getter
@Setter
@DynamoDBTable(tableName="Student")
public class Student {

    @DynamoDBHashKey
    private String id;
    @DynamoDBRangeKey
    private int joinedDate;
    private String firstName;
    private String lastName;
    private String email;

    public Student() {

    }
    public Student(String id) {
        this.id = id;
    }
    public Student(String id, int date) {
        this.id = id;
        this.joinedDate = date;
    }
    // partition key
//
//    @DynamoDBHashKey(attributeName = "id") // Partition key
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @DynamoDBRangeKey(attributeName = "joinedDate") // Sort key
//    public int getJoinedDate() {
//        return joinedDate;
//    }
//
//    public void setJoinedDate(int joinedDate) {
//        this.joinedDate = joinedDate;
//    }
//
//    @DynamoDBAttribute(attributeName = "firstName")
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @DynamoDBAttribute(attributeName = "lastName")
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @DynamoDBAttribute(attributeName = "email")
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}

