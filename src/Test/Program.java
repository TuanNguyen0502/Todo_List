package Test;

import Model.*;
import View.TodoListView;

import javax.swing.*;
import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException {
        TodoListModel todoListModel = new TodoListModel();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new TodoListView(todoListModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
