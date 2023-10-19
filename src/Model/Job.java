package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Job {
    String jobName; // Tiêu đề công việc
    String jobDiscription; // Mô tả công việc
    int priority;           // Độ ưu tiên
    Place placeToWork; // Địa điểm làm việc
    TypeOfWork typeOfWork; // Phân loại công việc
    boolean status = false; // Trạng thái công việc

    int day, month, year;   // Ngày tháng năm deadline
    int hour, minute;       // Giờ phút deadline

    public Job(String jobName, String jobDiscription, int priority, Place placeToWork,
               TypeOfWork typeOfWork, boolean status, int day, int month, int year, int hour, int minute) {
        this.jobName = jobName;
        this.jobDiscription = jobDiscription;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDiscrip() {
        return jobDiscription;
    }

    public void setJobDiscrip(String jobDiscrip) {
        this.jobDiscription = jobDiscrip;
    }

    public Place getPlaceToWork() {
        return placeToWork;
    }

    public void setPlaceToWork(Place placeToWork) {
        this.placeToWork = placeToWork;
    }

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobName='" + jobName + '\'' +
                ", jobDiscription='" + jobDiscription + '\'' +
                ", priority=" + priority +
                ", placeToWork=" + placeToWork +
                ", typeOfWork=" + typeOfWork +
                ", status=" + status +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
