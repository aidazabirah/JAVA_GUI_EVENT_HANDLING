import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class AppData
{
	public static ArrayList<Student> studentList = new ArrayList<>();
	public static ArrayList<Book> bookList = new ArrayList<>();
}

class Student
{
	private String name;
	private String matric;
	
	public Student(String name, String matric)
	{
		this.name = name;
		this.matric = matric;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getMatric()
	{
		return matric;
	}
	
	public String toString()
	{
		return name + " (" + matric + " )";
	}
}

class Book
{
	private String title;
	private String author;
	
	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public String toString()
	{
		return title + " (" + author + " )";
	}
}

class AddStudentFrame extends JFrame
{
	private JTextField tfName, tfMatric;
	private JTextArea taStudents;
	
	public AddStudentFrame()
	{
		setTitle("Add Student Info");
		setSize(450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		JPanel pInput = new JPanel(new GridLayout (2, 2, 5, 5));
		pInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		pInput.add(new JLabel("Name:"));
        tfName = new JTextField();
        pInput.add(tfName);

        pInput.add(new JLabel("Matric:"));
        tfMatric = new JTextField();
        pInput.add(tfMatric);
        pane.add(pInput, BorderLayout.NORTH);

        JPanel pButtons = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnList = new JButton("List");
        pButtons.add(btnAdd);
        pButtons.add(btnList);
        pane.add(pButtons, BorderLayout.CENTER);
        
        JPanel pListArea = new JPanel(new BorderLayout());
        pListArea.setBorder(BorderFactory.createTitledBorder("Students:"));
        taStudents = new JTextArea(6, 10);
        taStudents.setEditable(false);
        pListArea.add(new JScrollPane(taStudents), BorderLayout.CENTER);
        pane.add(pListArea, BorderLayout.SOUTH);
        
        btnAdd.addActionListener(e -> 
        {
            String name = tfName.getText().trim();
            String matric = tfMatric.getText().trim();
            if (!name.isEmpty() && !matric.isEmpty()) 
            {
                AppData.studentList.add(new Student(name, matric));
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                tfName.setText("");
                tfMatric.setText("");
            } else 
            {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });

        btnList.addActionListener(e -> 
        {
            StringBuilder sb = new StringBuilder();
            for (Student s : AppData.studentList) 
            {
                sb.append("Name : ").append(s.getName()).append(" (").append(s.getMatric()).append(")\n");
            }
            taStudents.setText(sb.toString());
        });
	}
}

class AddBookFrame extends JFrame
{
    private JTextField tfTitle, tfAuthor;
    private JTextArea taBooks;

    public AddBookFrame()
    {
        setTitle("Add Book Info");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel pInput = new JPanel(new GridLayout(2, 2, 5, 5));
        pInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pInput.add(new JLabel("Book Title:"));
        tfTitle = new JTextField();
        pInput.add(tfTitle);

        pInput.add(new JLabel("Author:"));
        tfAuthor = new JTextField();
        pInput.add(tfAuthor);
        pane.add(pInput, BorderLayout.NORTH);

        JPanel pButtons = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnList = new JButton("List");
        pButtons.add(btnAdd);
        pButtons.add(btnList);
        pane.add(pButtons, BorderLayout.CENTER);

        JPanel pListArea = new JPanel(new BorderLayout());
        pListArea.setBorder(BorderFactory.createTitledBorder("Books:"));
        taBooks = new JTextArea(6, 10);
        taBooks.setEditable(false);
        pListArea.add(new JScrollPane(taBooks), BorderLayout.SOUTH);
        pane.add(pListArea, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> 
        {
            String title = tfTitle.getText().trim();
            String author = tfAuthor.getText().trim();
            if (!title.isEmpty() && !author.isEmpty()) 
            {
                AppData.bookList.add(new Book(title, author));
                JOptionPane.showMessageDialog(this, "Book added successfully!");
                tfTitle.setText("");
                tfAuthor.setText("");
            } else 
            {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });

        btnList.addActionListener(e -> 
        {
            StringBuilder sb = new StringBuilder();
            for (Book b : AppData.bookList) 
            {
                sb.append("Book title : ").append(b.getTitle()).append(" (Author : ").append(b.getAuthor()).append(")\n");
            }
            taBooks.setText(sb.toString());
        });
    }
}

class BorrowBookFrame extends JFrame 
{
    private JComboBox<Student> cbStudents;
    private JComboBox<Book> cbBooks;
    private JLabel lblBorrowInfo;

    public BorrowBookFrame() 
    {
        setTitle("Borrow Book");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel pCenter = new JPanel(new GridLayout(3, 2, 5, 5));
        pCenter.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        pCenter.add(new JLabel("Student Name & Matric:"));
        cbStudents = new JComboBox<>();
        for (Student s : AppData.studentList) 
        {
            cbStudents.addItem(s);
        }
        pCenter.add(cbStudents);

        pCenter.add(new JLabel("Book Title & Author:"));
        cbBooks = new JComboBox<>();
        for (Book b : AppData.bookList) 
        {
            cbBooks.addItem(b);
        }
        pCenter.add(cbBooks);

        JButton btnBorrow = new JButton("Borrow");
        pCenter.add(new JLabel("")); 
        pCenter.add(btnBorrow);

        pane.add(pCenter, BorderLayout.CENTER);

        JPanel pBottom = new JPanel(new BorderLayout());
        pBottom.setBorder(BorderFactory.createTitledBorder("Borrow Information:"));
        lblBorrowInfo = new JLabel("Select a student and book to borrow.");
        pBottom.add(lblBorrowInfo, BorderLayout.CENTER);
        pane.add(pBottom, BorderLayout.SOUTH);

        btnBorrow.addActionListener(e -> 
        {
            Student selectedStudent = (Student) cbStudents.getSelectedItem();
            Book selectedBook = (Book) cbBooks.getSelectedItem();

            if (selectedStudent != null && selectedBook != null) 
            {
                String info = selectedStudent.getName() + " with matric number " + selectedStudent.getMatric() +
                              " has borrow a book with title " + selectedBook.getTitle() + ".";
                lblBorrowInfo.setText(info);
            } else {
                JOptionPane.showMessageDialog(this, "Please ensure both a student and a book are selected.");
            }
        });
    }
}

public class BorrowBookSystem extends JFrame
{
	public BorrowBookSystem()
	{
		setTitle("Main Menu");
		setSize(350, 260);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Container pane = getContentPane();
        pane.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblTitle = new JLabel("~ BORROW BOOK SYSTEM ~", JLabel.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));

        JButton btnAddStudent = new JButton("Add Student Info");
        JButton btnAddBook = new JButton("Add Book Info");
        JButton btnBorrowBook = new JButton("Borrow Book");

        pane.add(lblTitle);
        pane.add(btnAddStudent);
        pane.add(btnAddBook);
        pane.add(btnBorrowBook);

        btnAddStudent.addActionListener(e -> new AddStudentFrame().setVisible(true));
        btnAddBook.addActionListener(e -> new AddBookFrame().setVisible(true));
        btnBorrowBook.addActionListener(e -> new BorrowBookFrame().setVisible(true));
	}
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(() -> {
            new BorrowBookSystem().setVisible(true);
        });
	}
}
