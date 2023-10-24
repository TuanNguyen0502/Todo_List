package View;

import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TodoListView extends JFrame {
    TodoListModel todoListModel;

    public TodoListView(TodoListModel todoListModel) throws HeadlessException {
        this.todoListModel = todoListModel;
        this.setTitle("Todo List");
        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jMenuItem_save = new JMenuItem("Save");
        jMenuItem_save.setFont(new Font("Arial", Font.PLAIN, 15));

        jMenuItem_close = new JMenuItem("Exit");
        jMenuItem_close.setFont(new Font("Arial", Font.PLAIN, 15));

        jMenu_file = new JMenu("File");
        jMenu_file.setFont(new Font("Arial", Font.BOLD, 15));
        jMenu_file.add(jMenuItem_save);
        jMenu_file.add(jMenuItem_close);

        jMenuItem_Infor = new JMenuItem("Contact Information");
        jMenuItem_Infor.setFont(new Font("Arial", Font.BOLD, 15));

        jMenu_Help = new JMenu("Help");
        jMenu_Help.setFont(new Font("Arial", Font.BOLD, 15));
        jMenu_Help.add(jMenuItem_Infor);

        jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu_file);
        jMenuBar.add(jMenu_Help);

        jMenuItem_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    todoListModel.writeIntoFile();
                    JOptionPane.showMessageDialog(general, "Save successfully !!!");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jMenuItem_Infor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(general, "Student Email: 22110260@student.hcmute.edu.vn\n" +
                        "Personal Email: nguyenhahongtuan@gmail.com\n" +
                        "Phone: 0705488458");
            }
        });
        jMenuItem_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        tableJobList = new TableJobList(todoListModel);
        table_JobList.setModel(tableJobList);

        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField_setName.getText().isEmpty() ||
                        comboBox_setPriority.getSelectedIndex() == -1 ||
                        comboBox_setType.getSelectedIndex() == -1 ||
                        comboBox_setPlace.getSelectedIndex() == -1 ||
                        comboBox_setMinute.getSelectedIndex() == -1 ||
                        comboBox_setHour.getSelectedIndex() == -1 ||
                        comboBox_setDay.getSelectedIndex() == -1 ||
                        comboBox_setMonth.getSelectedIndex() == -1 ||
                        textField_setYear.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(general, "Must enter all information field !!!");
                } else {
                    Job job = new Job();
                    job.setJobName(textField_setName.getText());
                    job.setPriority((String) comboBox_setPriority.getSelectedItem());
                    job.setStatus((String) comboBox_setStatus.getSelectedItem());
                    job.setTypeOfWork((String) comboBox_setType.getSelectedItem());
                    job.setPlaceToWork((String) comboBox_setPlace.getSelectedItem());
                    job.setMinute((String) comboBox_setMinute.getSelectedItem());
                    job.setHour((String) comboBox_setHour.getSelectedItem());
                    job.setDay((String) comboBox_setDay.getSelectedItem());
                    job.setMonth((String) comboBox_setMonth.getSelectedItem());
                    job.setYear(textField_setYear.getText());

                    todoListModel.addJob(job);
                    tableJobList.fireTableDataChanged();
                    clearAndReset();
                    JOptionPane.showMessageDialog(general, "Add successfully !!!");
                }
            }
        });

        table_JobList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!table_JobList.getSelectionModel().isSelectionEmpty())
                {
                    selectedIndex = table_JobList.convertRowIndexToModel(table_JobList.getSelectedRow());
                    selectedJob = tableJobList.todoListModel.todoList.get(selectedIndex);

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
                if (table_JobList.getSelectedRowCount() == 1) {
                    Job tempJob = selectedJob;
                    selectedJob.setJobName(textField_setName.getText());
                    selectedJob.setPriority((String) comboBox_setPriority.getSelectedItem());
                    selectedJob.setStatus((String) comboBox_setStatus.getSelectedItem());
                    selectedJob.setTypeOfWork((String) comboBox_setType.getSelectedItem());
                    selectedJob.setPlaceToWork((String) comboBox_setPlace.getSelectedItem());
                    selectedJob.setMinute((String) comboBox_setMinute.getSelectedItem());
                    selectedJob.setHour((String) comboBox_setHour.getSelectedItem());
                    selectedJob.setDay((String) comboBox_setDay.getSelectedItem());
                    selectedJob.setMonth((String) comboBox_setMonth.getSelectedItem());
                    selectedJob.setYear(textField_setYear.getText());

                    todoListModel.changeJob(tempJob, selectedJob);
                    tableJobList.fireTableDataChanged();
                    clearAndReset();
                    JOptionPane.showMessageDialog(general, "Edit successfully !!!");
                }
                else
                {
                    if (table_JobList.getRowCount() == 0)
                        JOptionPane.showMessageDialog(general, "Table is empty !!!");
                    else
                    {
                        JOptionPane.showMessageDialog(general, "Please select single row !!!");
                    }
                }
            }
        });

        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table_JobList.getSelectedRowCount() == 1) {
                    todoListModel.deleteJob(selectedJob);
//                tableJobList.todoListModel.todoList.set(selectedIndex, selectedJob);
                    tableJobList.fireTableDataChanged();
                    clearAndReset();
                    JOptionPane.showMessageDialog(general, "Delete successfully !!!");
                }
                else
                {
                    if (table_JobList.getRowCount() == 0)
                        JOptionPane.showMessageDialog(general, "Table is empty !!!");
                    else
                    {
                        JOptionPane.showMessageDialog(general, "Please select single row !!!");
                    }
                }
            }
        });

        button_Print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField_getName.getText().isEmpty() &&
                        comboBox_getPriority.getSelectedIndex() == -1 &&
                        comboBox_getType.getSelectedIndex() == -1 &&
                        comboBox_getPlace.getSelectedIndex() == -1 &&
                        comboBox_getMinute.getSelectedIndex() == -1 &&
                        comboBox_getHour.getSelectedIndex() == -1 &&
                        comboBox_getDay.getSelectedIndex() == -1 &&
                        comboBox_getMonth.getSelectedIndex() == -1 &&
                        textField_getYear.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(general, "Must enter at least 1 information field !!!");
                } else {
//                    String[] temp = {textField_getName.getText(), (String) comboBox_getPriority.getSelectedItem(),
//                            (String) comboBox_getType.getSelectedItem(), (String) comboBox_getPlace.getSelectedItem(),
//                            (String) comboBox_getHour.getSelectedItem(), (String) comboBox_getMinute.getSelectedItem(),
//                            (String) comboBox_getDay.getSelectedItem(), (String) comboBox_getMonth.getSelectedItem(),
//                            textField_getYear.getText()};

                    Job job = new Job();
                    job.setJobName(textField_getName.getText());
                    job.setPriority((String) comboBox_getPriority.getSelectedItem());
                    job.setStatus((String) comboBox_getStatus.getSelectedItem());
                    job.setTypeOfWork((String) comboBox_getType.getSelectedItem());
                    job.setPlaceToWork((String) comboBox_getPlace.getSelectedItem());
                    job.setMinute((String) comboBox_getMinute.getSelectedItem());
                    job.setHour((String) comboBox_getHour.getSelectedItem());
                    job.setDay((String) comboBox_getDay.getSelectedItem());
                    job.setMonth((String) comboBox_getMonth.getSelectedItem());
                    job.setYear(textField_getYear.getText());

                    JOptionPane.showMessageDialog(general, "Print successfully !!!");
                }
            }
        });

        this.setJMenuBar(jMenuBar);
        this.setContentPane(general);
        this.setVisible(true);
        JOptionPane.showMessageDialog(general, "You have " + todoListModel.countJobNotDone() +
                " Jobs need to be done !!! \nAnd " + todoListModel.remind() + " Jobs are over Deadline !!!");
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
    private JLabel jLabal_getMonth;
    private JTextField textField_getYear;
    private JLabel jLabel_getYear;
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
    private JLabel jLabel_setStatus;
    private JComboBox comboBox_setStatus;
    private JComboBox comboBox_getMonth;
    private JComboBox comboBox_getDay;
    private JComboBox comboBox_getHour;
    private JComboBox comboBox_getMinute;
    TableJobList tableJobList;
    private Job selectedJob;
    private int selectedIndex;

    private JMenuBar jMenuBar;
    private JMenu jMenu_file;
    private JMenuItem jMenuItem_save;
    private JMenuItem jMenuItem_close;
    private JMenu jMenu_Help;
    private JMenuItem jMenuItem_Infor;

    private static class TableJobList extends AbstractTableModel
    {
        private final String[] COLUMNS = {"Name", "Priority", "Type", "Place", "Status",
                "Hour", "Minute", "Day", "Month", "Year"};
        private TodoListModel todoListModel;
//        private MyArrayList<Job> jobs;

        public TableJobList(TodoListModel todoListModel) {
            this.todoListModel = todoListModel;
        }

        @Override
        public int getRowCount() {
            return todoListModel.todoList.getSize();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> todoListModel.todoList.get(rowIndex).getJobName();
                case 1 -> todoListModel.todoList.get(rowIndex).getPriority();
                case 2 -> todoListModel.todoList.get(rowIndex).getTypeOfWork();
                case 3 -> todoListModel.todoList.get(rowIndex).getPlaceToWork();
                case 4 -> todoListModel.todoList.get(rowIndex).getStatus();
                case 5 -> todoListModel.todoList.get(rowIndex).getHour();
                case 6 -> todoListModel.todoList.get(rowIndex).getMinute();
                case 7 -> todoListModel.todoList.get(rowIndex).getDay();
                case 8 -> todoListModel.todoList.get(rowIndex).getMonth();
                case 9 -> todoListModel.todoList.get(rowIndex).getYear();
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
