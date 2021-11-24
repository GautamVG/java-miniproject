package TwoDo.db;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.*;

public class TaskData {
    public int id;
    public String title;
    public String desc;
    public LocalDateTime date;

    private DateTimeFormatter outputDatetimeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
    private DateTimeFormatter inputDatetimeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);

    public boolean isPastDueDate() {
        if (date == null) {
            return false;
        }
        return LocalDateTime.now().compareTo(date) > 0 ? true : false;
    }

    public String getDateLong() {
        return date == null ? null : date.format(outputDatetimeFormat);
    }

    public String getDateShort() {
        return date == null ? null : date.format(inputDatetimeFormat).split(", ")[0];
    }

    public String getTimeShort() {
        return date == null ? null : date.format(inputDatetimeFormat).split(", ")[1];
    }

    public Timestamp getDateAsTimestamp() {
        return date == null ? null : Timestamp.valueOf(date);
    }

    public boolean setDate(String _date) {
        try {
            date = (_date == null || _date.isBlank()) ? null : LocalDateTime.parse(_date, inputDatetimeFormat);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public void setDate(Timestamp _date) {
        date = _date == null ? null : _date.toLocalDateTime();
    }
}