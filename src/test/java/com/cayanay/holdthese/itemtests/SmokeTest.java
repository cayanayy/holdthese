package com.cayanay.holdthese.itemtests;

import com.cayanay.holdthese.controllers.FileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private FileController fileController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(fileController).isNotNull();
    }
}