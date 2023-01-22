package de.cronn.cryptobookapp.db.converter;

import androidx.room.TypeConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Converters {

    @TypeConverter
    public static String fromBigDecimal(BigDecimal bigDecimal){
        return bigDecimal.toString();
    }

    @TypeConverter
    public static BigDecimal toBigDecimal(String string){
        return new BigDecimal(string);
    }

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.toString();
    }

    @TypeConverter
    public static LocalDateTime toLocalDateTime(String string){
        return LocalDateTime.parse(string);
    }
}
