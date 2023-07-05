package com.chatting.client;


import org.junit.jupiter.api.Test;

public class JavaTest {

    private NullTestSample test1;
    private NullTestSample test2;

    @Test
    void test() {
        System.out.println("널 테스트 시작");
        System.out.println(test1);
        System.out.println(test2);

        test1 = new NullTestSample();
        test2 = new NullTestSample();

        System.out.println(test1);
        System.out.println(test2);

        test1.setA("test");
        test1.setB("TEST");
        test2.setA(null);
        test2.setB(null);

        System.out.println(test1.getA());
        System.out.println(test1.getB());

        System.out.println(test2.getA());
        System.out.println(test2.getB());

        System.out.println(test1.lengthByA());
        System.out.println(test2.lengthByA());



    }


}
