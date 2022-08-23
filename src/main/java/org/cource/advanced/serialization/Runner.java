package org.cource.advanced.serialization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.cource.advanced.serialization.beans.Person;
import org.cource.advanced.serialization.beans.PersonRecord;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Runner {

    private static final String FILE_NAME = "somefile.txt";

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        var mark = new Person("Mark", 19);
        // Show 1: ObjectOutputStream
        showDefaultSerialization(mark);

        // Show 2: gson.toJson(mark)
        System.out.println("\n\n\n\n\n\n");
        showGsonSerialization(mark);

        // Show 3: java records
        System.out.println("\n\n\n\n\n\n\n");
        showRecords();
    }

    private static void showDefaultSerialization(Person mark) throws IOException, ClassNotFoundException {
        System.out.println("Starting serialization...");
//         writing
        try (var fileOutputStream = new FileOutputStream(FILE_NAME);
             var objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(mark);
            objectOutputStream.flush();
        }

        System.out.println("Starting deserialization...");
        // reading
        try (var fileInputStream = new FileInputStream(FILE_NAME);
             var objectInputStream = new ObjectInputStream(fileInputStream)) {

            var readPerson = (Person) objectInputStream.readObject(); // ClassCastException
            System.out.println("Person name: " + readPerson.getName()
                    + " person age: " + readPerson.getAge());
        }
    }

    private static void showGsonSerialization(Person mark) {
        var gson = new Gson();
        var json = gson.toJson(mark);
        System.out.println(json);

        var readPerson = gson.fromJson(json, Person.class);
        System.out.println("Person name: " + readPerson.getName()
                + " person age: " + readPerson.getAge());


        System.out.println("=====");
        var people = Arrays.asList(new Person("James", 93),
                new Person("Viktor", 41));

        var jsonList = gson.toJson(people);
        System.out.println(jsonList);
        Type myType = new TypeToken<List<Person>>(){}.getType();
        var list = gson.fromJson(jsonList, myType);
        System.out.println(list);
    }

    private static void showRecords() {
        var record = new PersonRecord("Record", 22);
        var gson = new Gson();
        var json = gson.toJson(record);
        System.out.println(json);
        var readPerson = gson.fromJson(json, PersonRecord.class);
        System.out.println("Person name: " + readPerson.name()
                + " person age: " + readPerson.age());
    }
}
