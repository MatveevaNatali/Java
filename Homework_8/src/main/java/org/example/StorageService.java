package org.example;

import java.io.*;

public class StorageService<T> {
    private String fileName;

    public StorageService(String fileName) {
        this.fileName = fileName;
    }

    public void serialize(T t) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(t);
        }
    }

    public T deserialize() throws IOException {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) is.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
