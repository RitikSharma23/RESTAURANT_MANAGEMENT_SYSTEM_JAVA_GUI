import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.sql.*; 



class App extends JFrame implements ActionListener{

    JLabel nameLabel, typeLabel, phoneLabel, passwordLabel;
    JTextField nameTextField, typeTextField, phoneTextField,foodtype;
    JButton registerButton, resetButton,loginButton;
    JLabel  foodLabel, userLabel;
    JButton insertFoodButton, deleteFoodButton, inv,updateFoodButton, newUserButton, editUserButton, deleteUserButton, displayMenuButton, displayUsersButton;
    JLabel titleLabel, fnameLabel, ftypeLabel, priceLabel,idLabel;
    JTextField fnameField, priceField;
    JComboBox<String> ftypeComboBox;
    JButton insertButton, cancelButton;
    JTextField idField;
    JButton deleteButton;
    JTextField  nameField, typeField;
    JButton updateButton;
    JTextField phoneField, passwordField;
    JTable menuTable;
    JScrollPane scrollPane;
    JTable userTable;
    private JPasswordField passwordTextField;

    public void register() {
        setTitle("Registration Form");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        add(nameLabel);
        
        nameTextField = new JTextField();
        nameTextField.setBounds(150, 50, 200, 30);
        add(nameTextField);
        
        typeLabel = new JLabel("Type:");
        typeLabel.setBounds(50, 100, 100, 30);
        add(typeLabel);
        
        typeTextField = new JTextField();
        typeTextField.setBounds(150, 100, 200, 30);
        add(typeTextField);
        
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 150, 100, 30);
        add(phoneLabel);
        
        phoneTextField = new JTextField();
        phoneTextField.setBounds(150, 150, 200, 30);
        add(phoneTextField);
        
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 200, 100, 30);
        add(passwordLabel);
        
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(150, 200, 200, 30);
        add(passwordTextField);
        
        registerButton = new JButton("Register");
        registerButton.setBounds(150, 250, 100, 30);
        registerButton.addActionListener(this);
        add(registerButton);
        
        resetButton = new JButton("Cancel");
        resetButton.setBounds(250, 250, 100, 30);
        add(resetButton);
        setVisible(true);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql:///restaurant","root","");  
                    String query = "INSERT INTO user (name, type, phone, password) VALUES (?, ?, ?, ?)";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, nameTextField.getText());
                    statement.setString(2, typeTextField.getText());
                    statement.setString(3, phoneTextField.getText());
                    statement.setString(4, passwordTextField.getText());
                    int x=statement.executeUpdate(); 
                    if(x==1){
                        App m=new App();
                        System.out.println("register successful");
                        m.DashboardForm();
                        setVisible(false);
                    }else{
                        System.out.println("Error In Inserting Data..");
                    }
                    con.close(); 
                    }catch(Exception ex){ System.out.println(ex.getMessage());} 
            }
        });
    }

    public void login(String x) {
        setTitle("Login Form");
        setBounds(100, 100, 400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 50, 100, 30);
        add(phoneLabel);
        
        phoneTextField = new JTextField();
        phoneTextField.setBounds(150, 50, 200, 30);
        add(phoneTextField);
        
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        add(passwordLabel);
        
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(150, 100, 200, 30);
        add(passwordTextField);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        add(loginButton);
        
        resetButton = new JButton("Cancel");
        resetButton.setBounds(250, 150, 100, 30);
        add(resetButton);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phone=phoneTextField.getText();
                String password=new String(passwordTextField.getPassword());
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql:///restaurant","root","");
                    PreparedStatement stmt = con.prepareStatement(
                            "SELECT * FROM user WHERE phone=? AND password=?");
                    stmt.setString(1, phone);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String t=(rs.getString("type"));

                        if(x.equals("insertuser") && t.equals("manager")){
                            setVisible(false);
                            App m = new App();
                            m.register();
                        }else if(x.equals("insertfood") && t.equals("manager")){
                            setVisible(false);
                            App m = new App();
                            m.InsertFoodForm();
                        }else if(x.equals("updateuser") && t.equals("manager")){
                            setVisible(false);
                            App m = new App();
                            m.EditUserForm();
                        }else if(x.equals("updatefood") && t.equals("manager")){
                            setVisible(false);
                            App m = new App();
                            m.UpdateFoodForm();
                        }else if(x.equals("deletefood") && t.equals("manager")){
                            setVisible(false);
                            App m = new App();
                            m.DeleteFoodForm();
                        }else if(x.equals("deleteuser") && t.equals("manager")){
                            setVisible(false);
                            App m = new App();
                            m.DeleteUserForm();
                        }else{
                        JOptionPane.showMessageDialog(null, "Tere Aaukat Nahi Hai Be \n Jaa Ke Kaam Kar \n aur manager ko bol ye sab karne ko");
                        }
            
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid phone number or password.");
                    }
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                            setVisible(false);
                            App m = new App();
                            m.DashboardForm();
            }
        });
    }

    public void DashboardForm() {
        setTitle("Restaurant Dashboard");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        titleLabel = new JLabel("Restaurant Dashboard");
        titleLabel.setBounds(200, 20, 200, 30);
        add(titleLabel);
        
        foodLabel = new JLabel("Food");
        foodLabel.setBounds(100, 70, 100, 30);
        add(foodLabel);
        
        insertFoodButton = new JButton("Insert Food");
        insertFoodButton.setBounds(50, 100, 150, 30);
        insertFoodButton.addActionListener(this);
        add(insertFoodButton);
        
        deleteFoodButton = new JButton("Delete Food");
        deleteFoodButton.setBounds(250, 100, 150, 30);
        add(deleteFoodButton);
        
        updateFoodButton = new JButton("Update Food");
        updateFoodButton.setBounds(450, 100, 150, 30);
        add(updateFoodButton);
        
        userLabel = new JLabel("User");
        userLabel.setBounds(100, 170, 100, 30);
        add(userLabel);
        
        newUserButton = new JButton("New User");
        newUserButton.setBounds(50, 200, 150, 30);
        add(newUserButton);
        
        editUserButton = new JButton("Edit User");
        editUserButton.setBounds(250, 200, 150, 30);
        add(editUserButton);
        
        deleteUserButton = new JButton("Delete User");
        deleteUserButton.setBounds(450, 200, 150, 30);
        add(deleteUserButton);
        
        displayMenuButton = new JButton("Display Menu");
        displayMenuButton.setBounds(50, 270, 150, 30);
        add(displayMenuButton);
        
        displayUsersButton = new JButton("Display Users");
        displayUsersButton.setBounds(250, 270, 150, 30);
        add(displayUsersButton);
        setVisible(true);
        
        inv = new JButton("Generate Invoice");
        inv.setBounds(450, 270, 150, 30);
        add(inv);
        setVisible(true);

        insertFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.login("insertfood");
                setVisible(false);
            }
        });
        deleteFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DeleteFoodForm();
                setVisible(false);
            }
        });
        updateFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.UpdateFoodForm();
                setVisible(false);
            }
        });
        newUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.login("insertuser");
                setVisible(false);
            }
        });
        editUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.EditUserForm();
                setVisible(false);
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DeleteUserForm();
                setVisible(false);
            }
        });
        displayMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DisplayMenuForm();
                setVisible(false);
            }
        });
        displayUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DisplayUserForm();
                setVisible(false);
            }
        });
        inv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.generateInvoice();
                setVisible(false);
            }
        });
    }

    public void InsertFoodForm() {
        setTitle("Insert Food");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        titleLabel = new JLabel("Insert Food");
        titleLabel.setBounds(150, 20, 100, 30);
        add(titleLabel);
        
        fnameLabel = new JLabel("Name:");
        fnameLabel.setBounds(50, 70, 100, 30);
        add(fnameLabel);
        
        fnameField = new JTextField();
        fnameField.setBounds(150, 70, 200, 30);
        add(fnameField);
        
        ftypeLabel = new JLabel("Type:");
        ftypeLabel.setBounds(50, 110, 100, 30);
        add(ftypeLabel);

        foodtype = new JTextField();
        foodtype.setBounds(150, 110, 200, 30);
        add(foodtype);
        
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 150, 100, 30);
        add(priceLabel);
        
        priceField = new JTextField();
        priceField.setBounds(150, 150, 200, 30);
        add(priceField);
        
        insertButton = new JButton("Insert");
        insertButton.setBounds(100, 200, 100, 30);
        insertButton.addActionListener(this);
        add(insertButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 200, 100, 30);
        add(cancelButton);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql:///restaurant","root","");  
                    String query = "INSERT INTO food (fname, ftype, price) VALUES (?, ?, ?)";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, fnameField.getText());
                    statement.setString(2, foodtype.getText());
                    statement.setInt(3, Integer.parseInt(priceField.getText()));
                    int x=statement.executeUpdate(); 
                    if(x==1){
                        App m=new App();
                        System.out.println("register successful");
                        m.DashboardForm();
                        setVisible(false);
                    }else{
                        System.out.println("Error In Inserting Data..");
                    }
                    con.close(); 
                    }catch(Exception ex){ System.out.println(ex.getMessage());} 
            }
        });

        

    
    }

    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() == registerButton) {
        //     // Handle registration logic here
        //     String name = nameTextField.getText();
        //     String type = typeTextField.getText();
        //     String phone = phoneTextField.getText();
        //     String password = passwordTextField.getText();
            
            
        //     JOptionPane.showMessageDialog(this, "Registration successful!");
        // } else if (e.getSource() == resetButton) {
        //     // Clear form fields
        //     nameTextField.setText("");
        //     typeTextField.setText("");
        //     phoneTextField.setText("");
        //     passwordTextField.setText("");
        // }

        // if (e.getSource() == loginButton) {
        //     // Handle login logic here
        //     String name = nameTextField.getText();
        //     String password = passwordTextField.getText();
            
        //     // Query database to check if username and password match
            
        //     if (name.equals("admin") && password.equals("admin")) { // for example purposes only
        //         JOptionPane.showMessageDialog(this, "Login successful!");
        //     } else {
        //         JOptionPane.showMessageDialog(this, "Invalid username or password.");
        //     }
        // } else if (e.getSource() == resetButton) {
        //     // Clear form fields
        //     nameTextField.setText("");
        //     passwordTextField.setText("");
        // }
    }

    public void generateInvoice() {
        JFrame frame = new JFrame("Invoice");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
    
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel customerLabel = new JLabel("Customer Name:");
        JTextField customerTextField = new JTextField(20);
        headerPanel.add(customerLabel);
        headerPanel.add(customerTextField);
        frame.add(headerPanel, BorderLayout.NORTH);
    
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Sr. No.", "Food Name", "Quantity", "Price", "Total"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(tablePanel, BorderLayout.CENTER);
    
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JComboBox<String> foodComboBox = new JComboBox<String>();
   
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
    
            String query = "SELECT * FROM food";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                foodComboBox.addItem(rs.getString("fname"));
            }
            con.close();
    
        } catch (Exception e) {
            System.out.println(e);
        }

        int id[]=new int[1];

        foodComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
                    String query = "SELECT * FROM food WHERE fname = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, foodComboBox.getSelectedItem().toString());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        id[0]=Integer.parseInt(rs.getString("id"));
                        System.out.println(id[0]);
                    }
                    con.close();
    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        JTextField quantityTextField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JLabel displaytotal=new JLabel("");
        JButton generateinv = new JButton("Generate Invoice");

        footerPanel.add(foodComboBox);
        footerPanel.add(quantityTextField);
        footerPanel.add(addButton);
        footerPanel.add(displaytotal);
        footerPanel.add(generateinv);

        frame.add(footerPanel, BorderLayout.SOUTH);
        double totalfood[]=new double[1];
        totalfood[0]=0.00;
    
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
                    String query = "SELECT * FROM food WHERE id = '"+id[0]+"'";
                    PreparedStatement stmt = con.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String foodName = rs.getString("fname");
                        int quantity = Integer.parseInt(quantityTextField.getText());
                        double price = Integer.parseInt(rs.getString("price"));
                        double total = quantity * price;
                        totalfood[0]+=total;
                        model.addRow(new Object[] {model.getRowCount()+1, foodName, quantity, price, total});
                        System.out.println(totalfood[0]);
                        displaytotal.setText("Total : "+totalfood[0]+"");
                    }
                    con.close();
    
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });
    
        generateinv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Land le le .|. ");
            }
        });
    
        frame.setVisible(true);
    }
    
    public void DeleteFoodForm() {
        setTitle("Delete Food");
        setBounds(100, 100, 400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        titleLabel = new JLabel("Delete Food");
        titleLabel.setBounds(150, 20, 100, 30);
        add(titleLabel);
        
        idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 70, 100, 30);
        add(idLabel);
        
        JComboBox<String> userComboBox = new JComboBox<String>();
        userComboBox.setBounds(150, 60, 200, 30);
        add(userComboBox);
        int id[]=new int[1];
    
        try {
            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
    
            // Retrieve the list of users from the database and add them to the JComboBox
            String query = "SELECT * FROM food";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                userComboBox.addItem(rs.getString("fname"));
            }
            con.close();
    
        } catch (Exception e) {
            System.out.println(e);
        }
        
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 120, 100, 30);
        add(deleteButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 120, 100, 30);
        add(cancelButton);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });

        userComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
                    String query = "SELECT * FROM food WHERE fname = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, userComboBox.getSelectedItem().toString());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        id[0]=Integer.parseInt(rs.getString("id"));
                    }
                    con.close();
    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{  
                    System.out.println(id[0]);
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql:///restaurant","root","");  
                    String query = "delete from food  where id=?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, id[0]+"");
                    int x=statement.executeUpdate(); 
                    if(x==1){
                        App m=new App();
                        System.out.println("User Deleted successful");
                        m.DashboardForm();
                        setVisible(false);
                    }else{
                        System.out.println("Error In Inserting Data..");
                    }
                    con.close(); 
                    }catch(Exception ex){ System.out.println(ex.getMessage());} 
            }
        });


    }

    public void UpdateFoodForm() {
        setTitle("Update Food");
        setBounds(100, 100, 400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        titleLabel = new JLabel("Update Food");
        titleLabel.setBounds(150, 20, 100, 30);
        add(titleLabel);
        
        JLabel selectUserLabel = new JLabel("Select User:");
        selectUserLabel.setBounds(50, 60, 100, 30);
        add(selectUserLabel);
    
        JComboBox<String> userComboBox = new JComboBox<String>();
        userComboBox.setBounds(150, 60, 200, 30);
        add(userComboBox);
    
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
    
            String query = "SELECT * FROM food";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                userComboBox.addItem(rs.getString("fname"));
            }
            con.close();
    
        } catch (Exception e) {
            System.out.println(e);
        }
        
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 90, 100, 30);
        add(nameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(150, 90, 200, 30);
        add(nameField);
        
        typeLabel = new JLabel("Type:");
        typeLabel.setBounds(50, 120, 100, 30);
        add(typeLabel);
        
        typeField = new JTextField();
        typeField.setBounds(150, 120, 200, 30);
        add(typeField);
        
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 150, 100, 30);
        add(priceLabel);
        
        priceField = new JTextField();
        priceField.setBounds(150, 150, 200, 30);
        add(priceField);
        
        updateButton = new JButton("Update");
        updateButton.setBounds(100, 190, 100, 30);
        updateButton.addActionListener(this);
        add(updateButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 190, 100, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });

        int [] id=new int[1];
    
        userComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
                    String query = "SELECT * FROM food WHERE fname = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, userComboBox.getSelectedItem().toString());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        // idField.setText(rs.getString("id"));
                        id[0]=Integer.parseInt(rs.getString("id"));
                        nameField.setText(rs.getString("fname"));
                        typeField.setText(rs.getString("ftype"));
                        priceField.setText(rs.getInt("price")+"");
                    }
                    con.close();
    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });


        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{  
                    System.out.println(id[0]);
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql:///restaurant","root","");  
                    String query = "update food set fname=?, ftype=?, price=? where id=?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, nameField.getText());
                    statement.setString(2, typeField.getText());
                    statement.setInt(3, Integer.parseInt(priceField.getText()));
                    statement.setString(4, id[0]+"");
                    int x=statement.executeUpdate(); 
                    if(x==1){
                        App m=new App();
                        System.out.println("Update successful");
                        m.DashboardForm();
                        setVisible(false);
                    }else{
                        System.out.println("Error In Inserting Data..");
                    }
                    con.close(); 
                    }catch(Exception ex){ System.out.println(ex.getMessage());} 
            }
        });
    
        
    }
    
    public void DeleteUserForm() {
        setTitle("Delete User");
        setBounds(100, 100, 400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        titleLabel = new JLabel("Delete User");
        titleLabel.setBounds(150, 20, 100, 30);
        add(titleLabel);
        
        JLabel selectUserLabel = new JLabel("Select User:");
        selectUserLabel.setBounds(50, 60, 100, 30);
        add(selectUserLabel);
    
        JComboBox<String> userComboBox = new JComboBox<String>();
        userComboBox.setBounds(150, 60, 200, 30);
        add(userComboBox);
        int id[]=new int[1];
    
        try {
            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
    
            // Retrieve the list of users from the database and add them to the JComboBox
            String query = "SELECT * FROM user";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                userComboBox.addItem(rs.getString("name"));
            }
            con.close();
    
        } catch (Exception e) {
            System.out.println(e);
        }

        
        idField = new JTextField();
        idField.setBounds(150, 60, 200, 30);
        add(idField);
        
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 120, 100, 30);
        add(deleteButton);

        userComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
                    String query = "SELECT * FROM user WHERE name = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, userComboBox.getSelectedItem().toString());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        id[0]=Integer.parseInt(rs.getString("id"));
                    }
                    con.close();
    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{  
                    System.out.println(id[0]);
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql:///restaurant","root","");  
                    String query = "delete from user  where id=?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, id[0]+"");
                    int x=statement.executeUpdate(); 
                    if(x==1){
                        App m=new App();
                        System.out.println("User Deleted successful");
                        m.DashboardForm();
                        setVisible(false);
                    }else{
                        System.out.println("Error In Inserting Data..");
                    }
                    con.close(); 
                    }catch(Exception ex){ System.out.println(ex.getMessage());} 
            }
        });
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 120, 100, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });

    }
   
    public void EditUserForm() {
        setTitle("Edit User");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
    
        titleLabel = new JLabel("Edit User");
        titleLabel.setBounds(150, 20, 100, 30);
        add(titleLabel);
    
        JLabel selectUserLabel = new JLabel("Select User:");
        selectUserLabel.setBounds(50, 60, 100, 30);
        add(selectUserLabel);
    
        JComboBox<String> userComboBox = new JComboBox<String>();
        userComboBox.setBounds(150, 60, 200, 30);
        add(userComboBox);
    
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
    
            String query = "SELECT * FROM user";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                userComboBox.addItem(rs.getString("name"));
            }
            con.close();
    
        } catch (Exception e) {
            System.out.println(e);
        }

    
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 90, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 90, 200, 30);
        add(nameField);

        typeLabel = new JLabel("Type:");
        typeLabel.setBounds(50, 120, 100, 30);
        add(typeLabel);
    
        typeField = new JTextField();
        typeField.setBounds(150, 120, 200, 30);
        add(typeField);
    
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 150, 100, 30);
        add(phoneLabel);
    
        phoneField = new JTextField();
        phoneField.setBounds(150, 150, 200, 30);
        add(phoneField);
    
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 180, 100, 30);
        add(passwordLabel);
    
        passwordField = new JTextField();
        passwordField.setBounds(150, 180, 200, 30);
        add(passwordField);
    
        updateButton = new JButton("Update");
        updateButton.setBounds(100, 220, 100, 30);
        add(updateButton);
    
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 220, 100, 30);
        add(cancelButton);
        setVisible(true);
        int [] id=new int[1];
    
        userComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
                    String query = "SELECT * FROM user WHERE name = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, userComboBox.getSelectedItem().toString());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        // idField.setText(rs.getString("id"));
                        id[0]=Integer.parseInt(rs.getString("id"));
                        nameField.setText(rs.getString("name"));
                        typeField.setText(rs.getString("type"));
                        phoneField.setText(rs.getString("phone"));
                        passwordField.setText(rs.getString("password"));
                    }
                    con.close();
    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{  
                    System.out.println(id[0]);
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql:///restaurant","root","");  
                    String query = "update user set name=?, type=?, phone=?, password=? where id=?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, nameField.getText());
                    statement.setString(2, typeField.getText());
                    statement.setString(3, phoneField.getText());
                    statement.setString(4, passwordField.getText());
                    statement.setString(5, id[0]+"");
                    int x=statement.executeUpdate(); 
                    if(x==1){
                        App m=new App();
                        System.out.println("Update successful");
                        m.DashboardForm();
                        setVisible(false);
                    }else{
                        System.out.println("Error In Inserting Data..");
                    }
                    con.close(); 
                    }catch(Exception ex){ System.out.println(ex.getMessage());} 
            }
        });
    
    }

    public void DisplayMenuForm() {
        setTitle("Menu");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
    
        String[] columnNames = {"ID", "Name", "Type", "Price"};
    
        // Create a new model for the table
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Disable edit option
            }
        };
    
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM food";
            ResultSet rs = stmt.executeQuery(query);
            
            // Iterate over the result set and add each row to the table model
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("fname");
                String type = rs.getString("ftype");
                double price = rs.getDouble("price");
                Object[] row = {id, name, type, price};
                model.addRow(row);
            }
            
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        menuTable = new JTable(model);
        scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    
        setVisible(true);
    
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });
    } 

    public void DisplayUserForm() {
        setTitle("Menu");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
    
        String[] columnNames = {"ID", "Name", "Type", "phone"};
    
        // Create a new model for the table
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Disable edit option
            }
        };
    
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(query);
            
            // Iterate over the result set and add each row to the table model
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String phone = rs.getString("phone");
                Object[] row = {id, name, type, phone};
                model.addRow(row);
            }
            
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        menuTable = new JTable(model);
        scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    
        setVisible(true);
    
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App a=new App();
                a.DashboardForm();
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        App m = new App();
        m.DashboardForm();
    }

}