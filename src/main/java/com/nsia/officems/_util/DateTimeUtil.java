package com.nsia.officems._util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.github.mfathi91.time.PersianDate;

import org.springframework.stereotype.Component;


@Component
public class DateTimeUtil {

    ZoneId defaultZoneId = ZoneId.systemDefault();

    public LocalDateTime getCurrentDate() {
        Instant instant = (new Date()).toInstant();
        LocalDateTime dateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        return dateTime;
    }

    public LocalDateTime convertStringToLocalDateTime(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public LocalDateTime convertDateStringToLocalDateTime(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime dateTime = LocalDate.parse(dateStr, formatter).atStartOfDay();
            return dateTime;
        }
        return null;
    }

    public LocalDateTime convertStringToLocalDateTimeWithMilisec(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public LocalDateTime convertStringToLocalDateTimeSlashMDYHMS(String dateStr) {
        if(dateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            return dateTime;
        }
        return null;
    }

    public Timestamp convertStringToTimestamp(String dateStr) {
        if(dateStr != null) {
            LocalDateTime localDateTime = convertStringToLocalDateTime(dateStr);
            return Timestamp.valueOf(localDateTime);
        }
        return null;
    }

    public Timestamp convertStringToTimestampWithMilisec(String dateStr) {
        if(dateStr != null) {
            LocalDateTime localDateTime = convertStringToLocalDateTimeWithMilisec(dateStr);
            return Timestamp.valueOf(localDateTime);
        }
        return null;
    }

    public LocalDateTime convertDateToLocalDateTime(Date date) {
        if(date != null) {
            Instant instant = date.toInstant();
            LocalDateTime dateTime = instant.atZone(defaultZoneId).toLocalDateTime();
            return dateTime;
        }
        return null;
    }

    public String convertGregorianDateToPersianDate(String sDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate localDateTime = LocalDate.parse(sDate, formatter);
        // LocalDate localDate = localDateTime.toLocalDate();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PersianDate persianDate = PersianDate.fromGregorian(localDateTime);
        return persianDate.format(formatter);
    }

    public String convertGregorianDateToPersianDate(Date date) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        PersianDate persianDate = PersianDate.fromGregorian(localDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return persianDate.format(formatter);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
