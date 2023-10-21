package Model;

import java.util.Calendar;

public class TodoListModel {
    public LinkedList<Job> todoList = new LinkedList<Job>();
    public Node<String> current;

    public void addJob(Job job)
    {
        if (job.priority.equals("1"))
        {
            todoList.addHead(job);
        }
        else if (job.priority.equals("2"))
        {
            todoList.addAfter(job, todoList.getLastNode1());
        }
        else if (job.priority.equals("3"))
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

    public void changePriority(Job job, String doUuTien)
    {
        Node<Job> temp = todoList.findNode(job);

        if (temp == null) {
            System.out.println("Khong co cong viec ban muon thay doi.");
            return;
        }

        temp.data.setPriority(doUuTien);

//        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
//        {
//            if (p.next == temp)
//            {
//                p.next = temp.next;
//                if (temp.data.getPriority() == 1)
//                {
//                    temp.next = todoList.getHead();
//                    todoList.setHead(temp);
//                    return;
//                }
//                else if (temp.data.getPriority() == 3)
//                {
//                    todoList.getTail().next = temp;
//                    temp.next = null;
//                    todoList.setTail(temp);
//                    return;
//                }
//                else if (temp.data.getPriority() == 2)
//                {
//                    temp.next = todoList.getLastNode1().next;
//                    todoList.getLastNode1().next = temp;
//                    return;
//                }
//            }
//        }
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

        temp.data.setStatus(true);
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

    public void remind()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int mintue = calendar.get(Calendar.MINUTE);

        for (Node<Job> p = todoList.getHead(); p != null; p = p.next)
        {
            int d = Integer.parseInt(p.data.getDay());
            int m = Integer.parseInt(p.data.getMonth());
            int y = Integer.parseInt(p.data.getYear());
            int h = Integer.parseInt(p.data.getHour());
            int mi = Integer.parseInt(p.data.getMinute());
            if (y < year)
            {
                System.out.println(p.data.toString());
                System.out.println();
            }
            else
            {
                if (m < month)
                {
                    System.out.println(p.data.toString());
                    System.out.println();
                }
                else
                {
                    if (d < day)
                    {
                        System.out.println(p.data.toString());
                        System.out.println();
                    }
                    else if (d == day)
                    {
                        if (h < hour)
                        {
                            System.out.println(p.data.toString());
                            System.out.println();
                        }
                        else
                        {
                            if (mi <= mintue)
                            {
                                System.out.println(p.data.toString());
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkExist(Job job)
    {
        Node<Job> temp = todoList.findNode(job);
        return temp != null;
    }
}
