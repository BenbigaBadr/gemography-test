package com.gemography.trendingrepos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private DateUtil()
    {

    }

    public static Date parse(String date) throws Exception
    {
        Date parsedDate = null;
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        try{
            parsedDate = parser.parse(date);
        }
        catch(ParseException e)
        {
            throw new Exception("The date passed doesn't respect the format");
        }
        return parsedDate;
    }
}
