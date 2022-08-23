package org.cource.advanced.serialization;

import org.cource.advanced.serialization.beans.Person;

import java.io.IOException;

public class Runner {

    public static void main(String args[]) throws IOException {
        Person mark = new Person("Mark", 19);
        // Show 1: ObjectOutputStream
        // Show 2: gson.toJson(mark)
        // Show 3: java records
    }
}
