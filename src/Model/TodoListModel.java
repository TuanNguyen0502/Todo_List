package Model;

import java.util.Calendar;

public class TodoListModel {
    public LinkedList<Job> todoList = new LinkedList<Job>();
    public Node<String> current;

    public void addJob(Job job)
    {
        if (job.priority == 1)
        {
            todoList.addHead(job);
        }
        else if (job.priority == 2)
        {
            todoList.addAfter(job, todoList.getLastNode1());
        }
        else if (job.priority == 3)
        {
            todoList.addTail(job);
        }
    }

    public void deleteJob(Job job)
    {
        todoList.deleteItem(job);
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

    public void changeDiscription(Job job, String discrip)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setJobDiscrip(discrip);
    }

    public void changePriority(Job job, int doUuTien)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setPriority(doUuTien);
    }

    public void changePlace(Job job, Place place)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setPlaceToWork(place);
    }

    public void changeTypeOfWork(Job job, TypeOfWork typeOfWork)
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

        temp.data.setStatus(true);
    }

    public void printListByDate(int day, int month, int year)
    {
        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
        {
            if (p.data.getDay() == day && p.data.getMonth() == month && p.data.getYear() == year)
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
            if (p.data.getDay() == day)
                if (p.data.getMonth() == month)
                    if (p.data.getYear() == year) {
                        System.out.println(p.data.toString());
                        System.out.println();
                    }
        }
    }
}
