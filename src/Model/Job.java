package Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Job implements Serializable {
    private String jobName; // Tiêu đề công việc
    private String priority;           // Độ ưu tiên
    private String placeToWork; // Địa điểm làm việc
    private String typeOfWork; // Phân loại công việc
    private String status = "Not done"; // Trạng thái công việc
    private String day, month, year;   // Ngày tháng năm deadline
    private String hour, minute;       // Giờ phút deadline


    public Job(String jobName, String priority, String placeToWork, String typeOfWork,
               String status, String day, String month, String year, String hour, String minute) {
        this.jobName = jobName;
        this.priority = priority;
        this.placeToWork = placeToWork;
        this.typeOfWork = typeOfWork;
        this.status = status;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public Job() {
        this.jobName = null;
        this.priority = null;
        this.placeToWork = null;
        this.typeOfWork = null;
        this.status = "Not done";
        this.day = null;
        this.month = null;
        this.year = null;
        this.hour = null;
        this.minute = null;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPlaceToWork() {
        return placeToWork;
    }

    public void setPlaceToWork(String placeToWork) {
        this.placeToWork = placeToWork;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return jobName + ", \t" + priority + ", \t" + typeOfWork + ", \t" +placeToWork + status + ", \t" +
                hour + ", \t" + minute + ", \t" + day + ", \t" + month + ", \t" + year;
    }
}
