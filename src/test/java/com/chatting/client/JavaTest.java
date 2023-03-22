package com.chatting.client;

import org.junit.jupiter.api.Test;

public class JavaTest {


    @Test
    void test() {
        ConstructTest constructTest2 = new ConstructTest("test");
        ConstructTest constructTest3 = new ConstructTest("55", "test");
    }


    public static class ConstructTest{

        public ConstructTest(String a){

        }

        public ConstructTest(String a, String b){

        }

    }
}
