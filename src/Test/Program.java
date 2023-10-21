package Test;

import Model.*;
import View.TodoListView;

import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        Job job1 = new Job("Ăn cơm", "1", "Family", "At Home",
                false, "20", "10", "2023", "7", "0");
        Job job2 = new Job("Đi học", "2", "School", "At School",
                false, "25", "10", "2023", "7", "0");
        Job job3 = new Job("Đi dạy", "1", "Work", "At Office",
                false, "16", "10", "2023", "18", "30");
        Job job4 = new Job("Đi ngủ", "3", "Personal", "At Home",
                false, "20", "10", "2023", "23", "30");
        Job job5 = new Job("Đi choi", "2", "Personal", "Outside",
                false, "30", "10", "2023", "19", "30");

        TodoListModel todoListModel = new TodoListModel();

        todoListModel.addJob(job1);
        todoListModel.addJob(job2);
        todoListModel.addJob(job3);
        todoListModel.addJob(job4);
        todoListModel.addJob(job5);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new TodoListView(todoListModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
