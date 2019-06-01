package com.xdlr.webflux.util;

import com.xdlr.webflux.exceptions.CheckException;

import java.util.stream.Stream;

public class CheckUtil {

    private static final String[] INVALID_NAMES = {"admin", "guanliyuan"};

    // 校验名字，不成功的时候抛出异常
    public static void checkName(String value) {
        Stream.of(INVALID_NAMES)
                .filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
                    throw new CheckException("name", value);
                }
        );
    }
}
