package View;

import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TodoListView extends JFrame {
    TodoListModel todoListModel;

    public TodoListView(TodoListModel todoListModel) throws HeadlessException {
        this.todoListModel = todoListModel;
        this.setTitle("Todo List");
        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        tableJobList = new TableJobList(todoListModel);
        table_JobList.setModel(tableJobList);
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Job job = new Job();
                job.setJobName(textField_setName.getText());
                job.setPriority((String) comboBox_setPriority.getSelectedItem());
                job.setStatus(false);
                job.setTypeOfWork((String) comboBox_setType.getSelectedItem());
                job.setPlaceToWork((String) comboBox_setPlace.getSelectedItem());
                job.setMinute((String) comboBox_setMinute.getSelectedItem());
                job.setHour((String) comboBox_setHour.getSelectedItem());
                job.setDay((String) comboBox_setDay.getSelectedItem());
                job.setMonth((String) comboBox_setMonth.getSelectedItem());
                job.setYear(textField_setYear.getText());

                todoListModel.addJob(job);
                tableJobList.createJobs(todoListModel);
                tableJobList.fireTableDataChanged();
                clearAndReset();
            }
        });
        table_JobList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!table_JobList.getSelectionModel().isSelectionEmpty())
                {
                    selectedIndex = table_JobList.convertRowIndexToModel(table_JobList.getSelectedRow());
                    selectedJob = tableJobList.jobs.get(selectedIndex);

                    if (selectedJob != null)
                    {
                        textField_setName.setText(selectedJob.getJobName());
                        comboBox_setPriority.setSelectedItem((String) selectedJob.getPriority());
                        comboBox_setType.setSelectedItem((String) selectedJob.getTypeOfWork());
                        comboBox_setPlace.setSelectedItem((String) selectedJob.getPlaceToWork());
                        comboBox_setMinute.setSelectedItem((String) selectedJob.getMinute());
                        comboBox_setHour.setSelectedItem((String) selectedJob.getHour());
                        comboBox_setDay.setSelectedItem((String) selectedJob.getDay());
                        comboBox_setMonth.setSelectedItem((String) selectedJob.getMonth());
                        textField_setYear.setText(selectedJob.getYear() + "");
                    }
                }
            }
        });

        button_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedJob.setJobName(textField_setName.getText());
                selectedJob.setPriority((String) comboBox_setPriority.getSelectedItem());
                selectedJob.setStatus(false);
                selectedJob.setTypeOfWork((String) comboBox_setType.getSelectedItem());
                selectedJob.setPlaceToWork((String) comboBox_setPlace.getSelectedItem());
                selectedJob.setMinute((String) comboBox_setMinute.getSelectedItem());
                selectedJob.setHour((String) comboBox_setHour.getSelectedItem());
                selectedJob.setDay((String) comboBox_setDay.getSelectedItem());
                selectedJob.setMonth((String) comboBox_setMonth.getSelectedItem());
                selectedJob.setYear(textField_setYear.getText());

                tableJobList.jobs.set(selectedIndex, selectedJob);
                tableJobList.fireTableDataChanged();
                clearAndReset();
            }
        });

        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.setContentPane(general);
        this.setVisible(true);
    }

    private void clearAndReset()
    {
        textField_setName.setText("");
        table_JobList.clearSelection();
        textField_setYear.setText("");
        selectedIndex = -1;
        selectedJob = null;
    }

    private JPanel general;
    private JPanel command;
    private JPanel selectJob;
    private JLabel jLabel_getName;
    private JTextField textField_getName;
    private JLabel jLabel_getPriority;
    private JComboBox comboBox_getPriority;
    private JLabel jLabel_getPlace;
    private JComboBox comboBox_getPlace;
    private JLabel jLabel_getStatus;
    private JComboBox comboBox_getStatus;
    private JLabel jLabel_getDay;
    private JTextField textField_getDay;
    private JLabel jLabal_getMonth;
    private JTextField textField_getMonth;
    private JTextField textField_getYear;
    private JLabel jLabel_getYear;
    private JTextField textField_getHour;
    private JTextField textField_getMinute;
    private JLabel jLabel_getHour;
    private JLabel jLabel_getMinute;
    private JPanel display;
    private JTextField textField_setName;
    private JLabel jLabel_setName;
    private JLabel jLabel_setPriority;
    private JLabel jLabel_setType;
    private JLabel jLabel_getType;
    private JComboBox comboBox_setPriority;
    private JComboBox comboBox_setType;
    private JLabel jLabel_setPlace;
    private JComboBox comboBox_getType;
    private JComboBox comboBox_setPlace;
    private JLabel jLabel_setMinute;
    private JLabel jLabel_setHour;
    private JLabel jLabel_setDay;
    private JLabel jLabel_setMonth;
    private JLabel jLabel_setYear;
    private JTextField textField_setYear;
    private JButton button_Print;
    private JButton button_add;
    private JButton button_delete;
    private JButton button_edit;
    private JTable table_JobList;
    private JComboBox comboBox_setMonth;
    private JComboBox comboBox_setDay;
    private JComboBox comboBox_setMinute;
    private JComboBox comboBox_setHour;
    TableJobList tableJobList;
    private Job selectedJob;
    private int selectedIndex;

    private static class TableJobList extends AbstractTableModel
    {
        private final String[] COLUMNS = {"Name", "Priority", "Type", "Place", "Status",
                "Minute", "Hour", "Day", "Month", "Year"};
        private TodoListModel todoListModel;
        private MyArrayList<Job> jobs;

        public TableJobList(TodoListModel todoListModel) {
            this.todoListModel = todoListModel;
            jobs = new MyArrayList<Job>(todoListModel.todoList.getSize() + 1);
            for (Node<Job> p = todoListModel.todoList.getHead(); p != null; p = p.next)
            {
                jobs.add(p.data);
            }
        }

        public void createJobs(TodoListModel todoListModel)
        {
            MyArrayList<Job> temp = new MyArrayList<>(todoListModel.todoList.getSize() + 1);
            for (Node<Job> p = todoListModel.todoList.getHead(); p != null; p = p.next)
            {
                temp.add(p.data);
            }
            jobs = temp;
        }

        @Override
        public int getRowCount() {
            return jobs.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> jobs.get(rowIndex).getJobName();
                case 1 -> jobs.get(rowIndex).getPriority();
                case 2 -> jobs.get(rowIndex).getTypeOfWork();
                case 3 -> jobs.get(rowIndex).getPlaceToWork();
                case 4 -> jobs.get(rowIndex).getStatus();
                case 5 -> jobs.get(rowIndex).getMinute();
                case 6 -> jobs.get(rowIndex).getHour();
                case 7 -> jobs.get(rowIndex).getDay();
                case 8 -> jobs.get(rowIndex).getMonth();
                case 9 -> jobs.get(rowIndex).getYear();
                default -> "-";
            };
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null)
            {
                return getValueAt(0, columnIndex).getClass();
            }
            else {
                return Object.class;
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }
    }
}
