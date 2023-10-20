package Test;

import Model.*;
import View.TodoListView;

import java.util.Calendar;

public class Program {
    public static void main(String[] args) {
        Job job1 = new Job("Ăn cơm", "", 1, Place.At_Home, TypeOfWork.Family,
                false, 20, 10, 2023, 7, 0);
        Job job2 = new Job("Đi học", "", 2, Place.At_School, TypeOfWork.School,
                false, 17, 10, 2023, 7, 0);
        Job job3 = new Job("Đi dạy", "", 1, Place.At_Office, TypeOfWork.Work,
                false, 16, 10, 2023, 18, 30);
        Job job4 = new Job("Đi ngủ", "", 3, Place.At_Home, TypeOfWork.Personal,
                false, 20, 10, 2023, 23, 30);
        Job job5 = new Job("Đi choi", "", 2, Place.At_Home, TypeOfWork.Personal,
                false, 20, 10, 2023, 19, 30);

        TodoListModel todoListModel = new TodoListModel();

        todoListModel.addJob(job1);
        todoListModel.addJob(job2);
        todoListModel.addJob(job3);
        todoListModel.addJob(job4);
        todoListModel.addJob(job5);

//        todoListModel.deleteJob(job3);

        todoListModel.markJobAsDone(job2);

        todoListModel.changeJobName(job1, "Com");

        todoListModel.printListByDate(20);

//        todoListModel.todoList.printList();
    }
}
