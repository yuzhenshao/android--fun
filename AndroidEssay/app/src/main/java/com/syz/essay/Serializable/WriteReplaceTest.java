package com.syz.essay.Serializable;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class WriteReplaceTest implements Serializable {

    @Test
    public void testOut03() throws NotSerializableException, IOException {
        People p = new People(2, "小白");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/Users/joy/temp01.txt"));
        out.writeObject(p);
        out.flush();
        out.close();
    }

    @Test
    public void testIn03() throws NotSerializableException, IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("/Users/joy/temp01.txt"));
        Kong k = (Kong) in.readObject();
        in.close();
        System.out.println(k.s);
    }


     class People implements Serializable {
        private static final long serialVersionUID = 2659082826995480601L;
        private int age;
        private String name;

        People(int age, String name) {
            this.age = age;
            this.name = name;
        }

        private void writeObject(ObjectOutputStream out) {
            System.out.println("是否调用了我？");
        }

        private void readObject(ObjectInputStream in) {
            System.out.println("是否调用了我？");
        }

        private Object writeReplace() {
            return new Kong("路人");
        }

    }


     class Kong implements Serializable {
        private static final long serialVersionUID = -7144694309484327560L;

        public String s;

        Kong(String s) {
            this.s = s;
        }

        private void writeObject(ObjectOutputStream out) throws IOException,NotSerializableException {
            out.defaultWriteObject();
            System.out.println("出");
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException,NotSerializableException {
            in.defaultReadObject();
            System.out.println("入");
        }
    }
}
