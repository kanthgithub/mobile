package com.reji.mobile.util;

import java.math.*;

public class MathHelper {


    public static Boolean isValidAmount(BigDecimal amount) {
        return (amount.compareTo(BigDecimal.ZERO) >= 0);
    }

}
