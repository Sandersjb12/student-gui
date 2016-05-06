import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;



public class Menu extends JFrame
{

	private JDesktopPane theDesktop;

	public Menu()
	{

		
		super("Show Menus");

		theDesktop = new JDesktopPane();

		JMenuBar bar = new JMenuBar();
		
		JMenu addMenu = new JMenu("Add");
		JMenuItem addCustomerItem = new JMenuItem("Add Customer");
		JMenuItem addCourseItem = new JMenuItem("Add Course");

		
		addCustomerItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
	
				
				JInternalFrame frame = new JInternalFrame("Add CUstomer",true,true,true,true);
				

				CustomerPanel cp = new CustomerPanel();
				
				
				


				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			
				
				frame.add(cp);
				frame.pack();
				theDesktop.add(frame);
				frame.setVisible(true);

			}




		});
		//add course actionlistener will go here
		addCourseItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JInternalFrame frame = new JInternalFrame("Add Course", true,true,true,true);
			

				CoursePanel cop = new CoursePanel();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			
				
				frame.add(cop);
				frame.pack();
				theDesktop.add(frame);
				frame.setVisible(true);
			}
		});



		addMenu.add(addCustomerItem);
		addMenu.add(addCourseItem);

		JMenu exitMenu = new JMenu("Exit");
		JMenuItem exitProgramItem = new JMenuItem("Exit Program");
		JMenuItem writeFilesItem = new JMenuItem("Write Files");

		//actionlisteners will go here

		exitMenu.add(exitProgramItem);

		exitProgramItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);

			}


		});

	

		
		exitMenu.add(exitProgramItem);
		exitMenu.add(writeFilesItem);

		
		


		

		



	
		bar.add(addMenu);
		bar.add(exitMenu);


		
		add(theDesktop);


		setJMenuBar(bar);


		
		

	}





	public static void main(String args[])
	{
	
	Menu m = new Menu();

	m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	m.setSize(700,700);
	m.setLocationRelativeTo(null);
	m.setVisible(true);

	

	}
}

class CustomerPanel extends JPanel
{
	private JLabel nameLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel zipLabel;
    private JLabel accountNumberLabel;
    private JLabel customerTypeLabel;

	private JTextField nameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField accountNumberField;

	 private JLabel studentLabel;
    private JLabel facultyLabel;
    private JLabel governmentLabel;
    private JLabel courseLabel;
    private JLabel submitLabel;
    private JLabel finishLabel;

   

    private JPanel customerTypePanel;
    private ButtonGroup customerTypeGroup;
    private JRadioButton studentButton;
    private JRadioButton facultyButton;
    private JRadioButton governmentButton;




    private JButton submitButton;
    private JButton finishButton;
	private ArrayList<Customer> customerList=new ArrayList<Customer>();
    private boolean exists;
    private Customer tempCustomer;
    private int customerIndex;
    
	public CustomerPanel()
	{

	nameLabel=new JLabel("Enter Name");
		nameField=new JTextField(20);
        	streetLabel=new JLabel("Enter Street");
		streetField=new JTextField(20);
        	cityLabel=new JLabel("Enter City");
		cityField=new JTextField(20);
        	stateLabel=new JLabel("Enter State");
		stateField=new JTextField(20);
        	zipLabel=new JLabel("Enter Zip Code");
		zipField=new JTextField(20);
        	accountNumberLabel=new JLabel("Enter Account Number");
		accountNumberField=new JTextField(20);
        	customerTypeLabel=new JLabel("Select Type of Customer");
        	studentLabel=new JLabel("Student");
        	facultyLabel=new JLabel("Faculty");
        	governmentLabel=new JLabel("Government");
        	
		courseLabel=new JLabel("Select a Course");
        	submitLabel=new JLabel("Click Submit When Done");
        	finishLabel=new JLabel("Click When Finished");

		submitButton=new JButton("Submit");
		finishButton=new JButton("Finish");

        	
        	
        	customerTypePanel=new JPanel();
        	customerTypeGroup=new ButtonGroup();
        	studentButton=new JRadioButton();
        	facultyButton=new JRadioButton();
        	governmentButton=new JRadioButton();
        	customerTypeGroup.add(studentButton);
        	customerTypeGroup.add(facultyButton);
        	customerTypeGroup.add(governmentButton);
        	customerTypePanel.add(studentLabel);
        	customerTypePanel.add(studentButton);
        	customerTypePanel.add(facultyLabel);
        	customerTypePanel.add(facultyButton);
        	customerTypePanel.add(governmentLabel);
        	customerTypePanel.add(governmentButton);

		



		add(nameLabel);
        	add(nameField);
        	add(streetLabel);
        	add(streetField);
        	add(cityLabel);
        	add(cityField);
        	add(stateLabel);
        	add(stateField);
        	add(zipLabel);
        	add(zipField);
        	add(accountNumberLabel);
        	add(accountNumberField);
        	add(customerTypeLabel);
        	add(customerTypePanel);
        	//add(courseLabel);
       		//add(courseBox);
        	add(submitLabel);
        	add(submitButton);
        	add(finishLabel);
        	add(finishButton);
	
	
	submitButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			addCustomer(nameField.getText(),new Address(streetField.getText(),cityField.getText(),stateField.getText(),Integer.parseInt(zipField.getText())),Integer.parseInt(accountNumberField.getText()));	
			clearAll();
		}

	});

	finishButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String invoices="";

				for(Customer c:customerList)
				{
					System.out.println(c);
					System.out.println();
					invoices+=c.createInvoice();
				}

				JOptionPane.showMessageDialog(null,invoices);
				System.exit(0);
			}
		});
		


	}
	public void addCustomer(String name,Address address, int accountNumber )
    	{
        	tempCustomer=new Customer(name,address,accountNumber);
        	if (studentButton.isSelected())
            		tempCustomer.setCType(Customer.CustomerType.STUDENT);
        	else if (facultyButton.isSelected())
            		tempCustomer.setCType(Customer.CustomerType.FACULTY);
       		 else if (governmentButton.isSelected())
            		tempCustomer.setCType(Customer.CustomerType.GOVERNMENT);
 

        	exists=false;
        	for(Customer c:customerList)
           		if(c.getAccountNumber()==tempCustomer.getAccountNumber())
            		{
                		exists=true;
                		customerIndex=customerList.indexOf(c);
           		 }

        	if (!exists)
        	{
            		customerIndex=customerList.size();
           	 	customerList.add(tempCustomer);
        	}
   	 }

	public void clearAll()
	{
		nameField.setText("");
        	streetField.setText("");
        	cityField.setText("");
        	stateField.setText("");
        	zipField.setText("");
        	accountNumberField.setText("");
        	customerTypeGroup.clearSelection();
        	
        	nameField.requestFocus();
	}
}

	
class CoursePanel extends JPanel
{

	private JLabel nameLabel;
	private JTextField nameField;

	private ArrayList<Course> courseList=new ArrayList<Course>();
    	private String[] courses;
    	private JComboBox<String> courseBox;
    	private int index;

	private ArrayList<Customer> customerList=new ArrayList<Customer>();
   	 private boolean exists;
    	private Customer tempCustomer;
    	private int customerIndex;


	private JLabel courseLabel;
	private JLabel submitLabel;
	private JLabel finishLabel;
    	private JButton submitButton;
	private JButton finishButton;

	public CoursePanel()
	{
		
		nameLabel=new JLabel("Enter Name");
		nameField=new JTextField(20);

		submitButton=new JButton("Submit");
		finishButton=new JButton("Finish");

		courseLabel=new JLabel("Select a Course");
        	submitLabel=new JLabel("Click Submit When Done");
        	finishLabel=new JLabel("Click When Finished");

		courseList = readCourses("courses.txt");
		courses = new String[(courseList.size())];
		int index = 0;
		for(Course c:courseList)
		{
			courses[index]=c.getTitle();
			index++;
		}
	
		courseBox=new JComboBox<String>(courses);
		courseBox.setMaximumRowCount(3);

	
	submitButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			addCourse(nameField.getText(),courseList.get(courseBox.getSelectedIndex()));	
			clearAll();
		}

	});

	finishButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				System.exit(0);
			}
		});



		
		add(nameLabel);
        	add(nameField);
		add(submitLabel);
        	add(submitButton);
        	add(finishLabel);
        	add(finishButton);
		add(courseLabel);
       		add(courseBox);
		add(courseLabel);
	}

	public static ArrayList<Course> readCourses(String fileName)
	{
		ArrayList<Course> tempCourseList= new ArrayList<Course>();
		Scanner input;
		Course tempCourse;
		String line;
		String values[];

		try
		{
			input=new Scanner(new File(fileName));

			while(input.hasNext())
			{
				line=input.nextLine();
				values=line.split(";");
			
				if ( values[0].equals("Online"))
				{
					tempCourse= new OnlineCourse(values[1],values[2],Double.parseDouble(values[3]), new Date(Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6])),new Date(Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9])),values[11], Boolean.parseBoolean(values[12]),Integer.parseInt(values[13]));
				}
				else if (values[0].equals("InClass"))
				{
					tempCourse = new InClassCourse(values[1],values[2],Double.parseDouble(values[3]), new Date(Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6])),new Date(Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9])),values[11], new Time(Integer.parseInt(values[12]),Integer.parseInt(values[13])), new Time(Integer.parseInt(values[14]), Integer.parseInt(values[15])));
				}
				else
				{
					tempCourse = new Course();
				}
				
				switch (values[10])
				{
					case "programming":
						tempCourse.setCType(Course.CourseType.PROGRAMMING);
						break;
					case "mathematics":
						tempCourse.setCType(Course.CourseType.MATHEMATICS);
						break;
					case "photography":
						tempCourse.setCType(Course.CourseType.PHOTOGRAPHY);
						break;
					case "music":
						tempCourse.setCType(Course.CourseType.MUSIC);
						break;
					case "painting":
						tempCourse.setCType(Course.CourseType.PAINTING);
						break;
					default:
						tempCourse.setCType(Course.CourseType.MISC);
				}
				tempCourseList.add(tempCourse);
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		return tempCourseList;	
	}


	public void addCourse(String name,Course ce)
	{
		if (confirm(ce))
			customerList.get(customerIndex).addCourse(ce);
	}

	public void clearAll()
	{
		nameField.setText("");
        	
        	courseBox.setSelectedIndex(0);
        	nameField.requestFocus();
	}
	public String getCourse()
	{
		return courses[courseBox.getSelectedIndex()];
	}

	
	public boolean confirm(Course ce)
	{
		JFrame frame=new JFrame();
		int result;
		String message;
		message=String.format("%s\n",ce);

		result=JOptionPane.showConfirmDialog(frame,message);

		if (result==JOptionPane.YES_OPTION)
			return true;

		return false;
	}
}


	




