package com.bigstomach.tongjihealthcare.common.exception;

import com.bigstomach.tongjihealthcare.common.response.IErrorCode;

public class Asserts
{
    public static void fail(String message)
    {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode)
    {
        throw new ApiException(errorCode);
    }
}
