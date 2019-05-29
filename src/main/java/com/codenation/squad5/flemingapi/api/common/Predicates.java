package com.codenation.squad5.flemingapi.api.common;

import java.util.function.Predicate;

public class Predicates {
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }
}
