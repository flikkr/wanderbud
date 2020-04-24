package com.kazymir.tripweaver.util

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class TypeConverters {

    companion object {
//        @TypeConverter
//        public fun toCalendar(timestamp: Long): Calendar {
//            val c = Calendar.getInstance();
//            c.timeInMillis = timestamp;
//            return c;
//        }

        @TypeConverter
        fun dateFormatterFromCalendar(cal: Calendar): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            return "${sdf.format(date.time)}"
        }
    }

}