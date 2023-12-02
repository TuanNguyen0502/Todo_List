package Model;

import java.io.*;
import java.util.Calendar;
import java.util.Scanner;

public class TodoListModel {
    public LinkedList<Job> todoList = new LinkedList<Job>();
    public Node<String> current;

    public TodoListModel()
    {
        this.readFromFile();
    }

    public void addJob(Job job)
    {
        switch (job.getPriority()) {
            case "1" -> todoList.addHead(job);
            case "2" -> todoList.addAfter(job, todoList.getLastNode1());
            case "3" -> todoList.addTail(job);
        }
    }

    public void deleteJob(Job job)
    {
        todoList.delete(job);
    }

    public void changeJob(Job oldJob, Job newJob)
    {
        Node<Job> temp = todoList.findNode(oldJob);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setJobName(newJob.getJobName());
        temp.data.setPriority(newJob.getPriority());
        temp.data.setStatus(newJob.getStatus());
        temp.data.setTypeOfWork(newJob.getTypeOfWork());
        temp.data.setPlaceToWork(newJob.getPlaceToWork());
        temp.data.setMinute(newJob.getMinute());
        temp.data.setHour(newJob.getHour());
        temp.data.setDay(newJob.getDay());
        temp.data.setMonth(newJob.getMonth());
        temp.data.setYear(newJob.getYear());

//        changeLocationInList(temp);
    }

    public void changeLocationInList(Node<Job> temp) {
        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
        {
            if (p.next == temp)
            {
                p.next = temp.next;

                if (temp.data.getPriority().equals("1"))
                {
                    temp.next = todoList.getHead();
                    todoList.setHead(temp);
                }
                else if (temp.data.getPriority().equals("3"))
                {
                    todoList.getTail().next = temp;
                    todoList.setTail(temp);
                    todoList.getTail().next = null;
                }
                else if (temp.data.getPriority().equals("2"))
                {
                    temp.next = todoList.getLastNode1().next;
                    todoList.getLastNode1().next = temp;
                }
            }
        }
    }

    public void changeJobName(Job job, String title)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setJobName(title);
    }

    public void changePriority(Job job, String doUuTien)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setPriority(doUuTien);

        changeLocationInList(temp);
    }

    public void changePlace(Job job, String place)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setPlaceToWork(place);
    }

    public void changeTypeOfWork(Job job, String typeOfWork)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setTypeOfWork(typeOfWork);
    }

    public void markJobAsDone(Job job)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null)
            return;

        temp.data.setStatus("true");
    }

    public void changeDeadline(Job job, String minute, String hour)
    {
        Node<Job> temp = todoList.findNode(job);
        temp.data.setMinute(minute);
        temp.data.setHour(hour);
    }
    public void changeDeadline(Job job, String minute, String hour, String day, String month, String year)
    {
        Node<Job> temp = todoList.findNode(job);
        temp.data.setMinute(minute);
        temp.data.setHour(hour);
        temp.data.setDay(day);
        temp.data.setMonth(month);
        temp.data.setYear(year);
    }

    public void printListByDate(int day, int month, int year)
    {
        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
        {
            int d = Integer.parseInt(p.data.getDay());
            int m = Integer.parseInt(p.data.getMonth());
            int y = Integer.parseInt(p.data.getYear());
            if (d == day && m == month && y == year)
            {
                System.out.println(p.data.toString());
                System.out.println();
            }
        }
    }

    public void printListByDate(int day)
    {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
        {
            int d = Integer.parseInt(p.data.getDay());
            int m = Integer.parseInt(p.data.getMonth());
            int y = Integer.parseInt(p.data.getYear());
            if (d == day)
                if (m == month)
                    if (y == year) {
                        System.out.println(p.data.toString());
                        System.out.println();
                    }
        }
    }

    public int remind()
    {
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int mintue = calendar.get(Calendar.MINUTE);

        for (Node<Job> p = todoList.getHead(); p != null; p = p.next) {
            if (p.data.getStatus().equals("Not done")) {
                int d = Integer.parseInt(p.data.getDay());
                int m = Integer.parseInt(p.data.getMonth());
                int y = Integer.parseInt(p.data.getYear());
                int h = Integer.parseInt(p.data.getHour());
                int mi = Integer.parseInt(p.data.getMinute());
                if (y < year) {
                    count++;
                } else {
                    if (m < month) {
                        count++;
                    } else {
                        if (d < day) {
                            count++;
                        } else if (d == day) {
                            if (h < hour) {
                                count++;
                            } else {
                                if (mi <= mintue) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public int countJobNotDone()
    {
        int count = 0;
        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
        {
            if (p.data.getStatus().equals("Not done"))
                count++;
        }
        return count;
    }

    public boolean checkExist(Job job)
    {
        Node<Job> temp = todoList.findNode(job);
        return temp != null;
    }

    public void readFromFile()
    {
        try {
            FileInputStream fis = new FileInputStream("Job_List");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Job job = null;
            while((job = (Job) ois.readObject()) != null) {
                this.addJob(job);
            }
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeIntoFile() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("Job_List");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
            {
                oos.writeObject(p.data);
            }
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findJob(Job job)
    {

    }

    public MyArrayList<Job> findListJob(String[] infor) {
        MyArrayList<Job> temp = new MyArrayList<>();
        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
        {
            for (int i = 0; i < infor.length; i++)
            {
                if (i == 0)
                {
                    if (!p.data.getJobName().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 1)
                {
                    if (!p.data.getPriority().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 2)
                {
                    if (!p.data.getTypeOfWork().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 3)
                {
                    if (!p.data.getPlaceToWork().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 4)
                {
                    if (!p.data.getHour().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 5)
                {
                    if (!p.data.getMinute().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 6)
                {
                    if (!p.data.getDay().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 7)
                {
                    if (!p.data.getMonth().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 8)
                {
                    if (!p.data.getYear().equals(infor[i]))
                    {
                        break;
                    }
                }
                else if (i == 9)
                {
                    if (!p.data.getStatus().equals(infor[i]))
                    {
                        break;
                    }
                }
                temp.add(p.data);
            }
        }
        return temp;
    }
}
