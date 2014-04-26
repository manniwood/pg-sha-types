package com.manniwood.basicproj.test.factory;

import org.testng.annotations.Factory;

import com.manniwood.pgtypes.test.MD5HashTest;

public class TestNGTestFactory {
    @Factory
    public Object[] allTests() {
        return new Object[] {
                new MD5HashTest()
        };
    }

}
