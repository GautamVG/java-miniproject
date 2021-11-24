package TwoDo.db;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.*;

public class TaskData {
    public int id;
    public boolean done;
    public String title;
    public String desc;
    public LocalDateTime date;
    public int user;

    private DateTimeFormatter outputDatetimeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
    private DateTimeFormatter inputDatetimeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);

    public String getDateLong() {
        return date == null ? null : date.format(outputDatetimeFormat);
    }

    public String getDateShort() {
        return date == null ? null : date.format(inputDatetimeFormat);
    }

    public Timestamp getDateAsTimestamp() {
        return date == null ? null : Timestamp.valueOf(date);
    }

    public void setDate(String _date) {
        date = (_date == null || _date.isBlank()) ? null : LocalDateTime.parse(_date, inputDatetimeFormat);
    }

    public void setDate(Timestamp _date) {
        date = _date == null ? null : _date.toLocalDateTime();
    }
}