package View;

import Model.TodoListModel;

import javax.swing.*;
import java.awt.*;

public class TodoListView extends JFrame {
    TodoListModel todoListModel;
    JButton jButton_add = new JButton("Add job");
    JButton jButton_remove = new JButton("Remove job");

    public TodoListView(TodoListModel todoListModel) throws HeadlessException {
        this.todoListModel = todoListModel;
        this.init();
        this.setVisible(true);
    }
    public void init()
    {
        this.setTitle("TODO LIST");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }
}
