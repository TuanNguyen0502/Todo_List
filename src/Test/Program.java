package Test;

import Model.*;
import View.TodoListView;

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

        TodoListModel todoListModel = new TodoListModel();
        todoListModel.todoList.addTail(job1);
        todoListModel.todoList.addTail(job2);
        todoListModel.todoList.addTail(job3);
        todoListModel.todoList.addTail(job4);

//        todoListModel.todoList.deleteHead();
//        todoListModel.todoList.deleteTail();
        todoListModel.todoList.deleteItem(job3);

        todoListModel.todoList.printList();
    }
}
