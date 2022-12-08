package com.spring.app.allokal.logging;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging{

    public void info(String className){
        Logger logger = LoggerFactory.getLogger("["+className+"]");

        //controller
        logger.info("로그가 실행되었습니다.");
    }

}