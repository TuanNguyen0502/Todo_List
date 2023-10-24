package Model;

import View.TodoListView;

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
        todoList.deleteItem(job);
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
        Scanner sc = null;
        try
        {
            BufferedReader in = new BufferedReader(new FileReader("Job_List.txt"), 16*1024);
            sc = new Scanner(in);
            sc.useDelimiter(";");
            while (sc.hasNext())
            {
                Job job = new Job();
                job.setJobName(sc.next());
                job.setPriority(sc.next());
                job.setTypeOfWork(sc.next());
                job.setPlaceToWork(sc.next());
                job.setStatus(sc.next());
                job.setHour(sc.next());
                job.setMinute(sc.next());
                job.setDay(sc.next());
                job.setMonth(sc.next());
                job.setYear(sc.next());
                this.addJob(job);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            sc.close();
        }
    }

    public void writeIntoFile() throws IOException {
        BufferedWriter bufferedWriter = null;

        try {
            Writer writer = new FileWriter("Job_List.txt");
            bufferedWriter = new BufferedWriter(writer);

            for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
            {
                bufferedWriter.write(p.data.getJobName());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getPriority());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getTypeOfWork());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getPlaceToWork());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getStatus());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getHour());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getMinute());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getDay());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getMonth());
                bufferedWriter.write(";");
                bufferedWriter.write(p.data.getYear());
                bufferedWriter.write(";");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

    public void findJob(Job job)
    {

    }

}
