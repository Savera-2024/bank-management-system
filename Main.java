// Import necessary Swing components for GUI
import javax.swing.*;
// Import Swing border components for UI borders
import javax.swing.border.TitledBorder;
// Import table cell renderer for customizing table cells
import javax.swing.table.DefaultTableCellRenderer;
// Import table model for managing table data
import javax.swing.table.DefaultTableModel;
// Import table header for customizing table headers
import javax.swing.table.JTableHeader;
// Import AWT components for graphics and layout
import java.awt.*;
// Import mouse adapter for mouse events
import java.awt.event.MouseAdapter;
// Import mouse event class
import java.awt.event.MouseEvent;
// Import IO classes for file operations
import java.io.*;
// Import SimpleDateFormat for date formatting
import java.text.SimpleDateFormat;
// Import utility classes
import java.util.*;
// Import List interface
import java.util.List;

// ====================== PERSON CLASS ======================
// Person class representing a person with basic information
class Person implements Serializable {
    // Person's name
    private String name;
    // Person's CNIC (13-digit ID)
    private String CNIC;
    // Person's phone number
    private String phoneNo;

    // Default constructor
    public Person() {}

    // Parameterized constructor
    public Person(String name, String CNIC, String phoneNo) {
        // Assign name parameter to instance variable
        this.name = name;
        // Assign CNIC parameter to instance variable
        this.CNIC = CNIC;
        // Assign phone number parameter to instance variable
        this.phoneNo = phoneNo;
    }

    // Getter for name
    public String getName() { return name; }
    // Setter for name
    public void setName(String name) { this.name = name; }
    // Getter for CNIC
    public String getCNIC() { return CNIC; }
    // Setter for CNIC
    public void setCNIC(String CNIC) { this.CNIC = CNIC; }
    // Getter for phone number
    public String getPhoneNo() { return phoneNo; }
    // Setter for phone number
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    // String representation of Person object
    @Override
    public String toString() {
        // Return formatted string with name, CNIC, and phone
        return name + " (" + CNIC + ") - " + phoneNo;
    }
}

// ====================== ACCOUNT CLASS ======================
// Account class representing a bank account
class Account implements Serializable {
    // Account number
    private String number;
    // Account balance
    private float amount;
    // Account holder (Client object)
    private Client ACholder;
    // Account creation date
    private Date createdDate;

    // Default constructor
    public Account() {}

    // Parameterized constructor
    public Account(float amount, Client ACholder) {
        // Set initial amount
        this.amount = amount;
        // Set account holder
        this.ACholder = ACholder;
        // Set creation date to current date
        this.createdDate = new Date();
    }

    // Getter for account number
    public String getNumber() { return number; }
    // Setter for account number
    public void setNumber(String number) { this.number = number; }
    // Getter for account amount
    public float getAmount() { return amount; }
    // Setter for account amount
    public void setAmount(float amount) { this.amount = amount; }
    // Getter for account holder
    public Client getACholder() { return ACholder; }
    // Setter for account holder
    public void setACholder(Client ACholder) { this.ACholder = ACholder; }
    // Getter for creation date
    public Date getCreatedDate() { return createdDate; }
    // Setter for creation date
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    // Method to withdraw money from account
    public float withdraw(float amount) {
        // Check if amount is positive and less than or equal to balance
        if (amount > 0 && amount <= this.amount) {
            // Subtract amount from balance
            this.amount -= amount;
            // Return new balance
            return this.amount;
        }
        // Return -1 if withdrawal fails
        return -1;
    }

    // Method to deposit money to account
    public float deposit(float amount) {
        // Check if amount is positive
        if (amount > 0) {
            // Add amount to balance
            this.amount += amount;
            // Return new balance
            return this.amount;
        }
        // Return -1 if deposit fails
        return -1;
    }

    // String representation of Account object
    @Override
    public String toString() {
        // Return formatted account information
        return "Account Number: " + number +
                "\nAmount: PKR " + String.format("%,.2f", amount) +
                "\nAccount Holder: " + (ACholder != null ? ACholder.getPersonDetails().getName() : "N/A") +
                "\nCreated Date: " + new SimpleDateFormat("dd-MMM-yyyy").format(createdDate);
    }
}

// ====================== CLIENT CLASS ======================
// Client class representing a bank client
class Client implements Serializable {
    // Client ID
    private String id;
    // Personal details (Person object)
    private Person personDetails;
    // List of accounts owned by client
    private List<Account> AcList;
    // Username for login
    private String username;
    // Password for login
    private String password;

    // Default constructor
    public Client() {
        // Initialize empty account list
        this.AcList = new ArrayList<>();
    }

    // Parameterized constructor
    public Client(Person personDetails, String username, String password) {
        // Set personal details
        this.personDetails = personDetails;
        // Set username
        this.username = username;
        // Set password
        this.password = password;
        // Initialize empty account list
        this.AcList = new ArrayList<>();
    }

    // Getter for client ID
    public String getId() { return id; }
    // Setter for client ID
    public void setId(String id) { this.id = id; }
    // Getter for person details
    public Person getPersonDetails() { return personDetails; }
    // Setter for person details
    public void setPersonDetails(Person personDetails) { this.personDetails = personDetails; }
    // Getter for account list
    public List<Account> getAcList() { return AcList; }
    // Setter for account list
    public void setAcList(List<Account> AcList) { this.AcList = AcList; }
    // Getter for username
    public String getUsername() { return username; }
    // Setter for username
    public void setUsername(String username) { this.username = username; }
    // Getter for password
    public String getPassword() { return password; }
    // Setter for password
    public void setPassword(String password) { this.password = password; }

    // Calculate total amount across all accounts
    public float totalAmount() {
        // Initialize total to 0
        float total = 0;
        // Loop through all accounts
        for (Account account : AcList) {
            // Add each account's amount to total
            total += account.getAmount();
        }
        // Return total amount
        return total;
    }

    // Withdraw from specific account
    public float withdraw(float amount, String accNo) {
        // Loop through all accounts
        for (Account account : AcList) {
            // Check if account number matches
            if (account.getNumber().equals(accNo)) {
                // Call account's withdraw method
                return account.withdraw(amount);
            }
        }
        // Return -1 if account not found
        return -1;
    }

    // Deposit to specific account
    public float deposit(float amount, String accNo) {
        // Loop through all accounts
        for (Account account : AcList) {
            // Check if account number matches
            if (account.getNumber().equals(accNo)) {
                // Call account's deposit method
                return account.deposit(amount);
            }
        }
        // Return -1 if account not found
        return -1;
    }

    // Add account to client's account list
    public void addAccount(Account a) {
        // Check if account is not null
        if (a != null) {
            // Add account to list
            AcList.add(a);
        }
    }

    // String representation of Client object
    @Override
    public String toString() {
        // Create StringBuilder for building string
        StringBuilder sb = new StringBuilder();
        // Add client information header
        sb.append("=== CLIENT INFORMATION ===\n");
        // Add client ID
        sb.append("Client ID: ").append(id).append("\n");
        // Add personal details
        sb.append("Personal Details: ").append(personDetails).append("\n");
        // Add total amount
        sb.append("Total Amount: PKR ").append(String.format("%,.2f", totalAmount())).append("\n");
        // Add accounts header
        sb.append("\n=== ACCOUNTS ===\n");

        // Check if account list is empty
        if (AcList.isEmpty()) {
            // Add message for no accounts
            sb.append("No accounts found.\n");
        } else {
            // Loop through all accounts
            for (Account account : AcList) {
                // Add account information
                sb.append("Account ID: ").append(account.getNumber())
                        .append(", Amount: PKR ").append(String.format("%,.2f", account.getAmount()))
                        .append(", Created: ").append(new SimpleDateFormat("dd-MMM-yyyy").format(account.getCreatedDate()))
                        .append("\n");
            }
        }
        // Return built string
        return sb.toString();
    }
}

// ====================== BANK CLASS ======================
// Bank class representing the entire banking system
class Bank implements Serializable {
    // Bank name
    private String name;
    // List of all clients
    private List<Client> ClList;
    // List of all accounts
    private List<Account> AcList;
    // Next client ID to be assigned
    private int nextClientId = 101;
    // Next account ID to be assigned
    private int nextAccountId = 1001;

    // Default constructor
    public Bank() {
        // Initialize empty client list
        this.ClList = new ArrayList<>();
        // Initialize empty account list
        this.AcList = new ArrayList<>();
    }

    // Parameterized constructor with bank name
    public Bank(String name) {
        // Set bank name
        this.name = name;
        // Initialize empty client list
        this.ClList = new ArrayList<>();
        // Initialize empty account list
        this.AcList = new ArrayList<>();
    }

    // Getter for bank name
    public String getName() { return name; }
    // Setter for bank name
    public void setName(String name) { this.name = name; }
    // Getter for client list
    public List<Client> getClList() { return ClList; }
    // Setter for client list
    public void setClList(List<Client> ClList) { this.ClList = ClList; }
    // Getter for account list
    public List<Account> getAcList() { return AcList; }
    // Setter for account list
    public void setAcList(List<Account> AcList) { this.AcList = AcList; }
    // Getter for next client ID
    public int getNextClientId() { return nextClientId; }
    // Setter for next client ID
    public void setNextClientId(int nextClientId) { this.nextClientId = nextClientId; }
    // Getter for next account ID
    public int getNextAccountId() { return nextAccountId; }
    // Setter for next account ID
    public void setNextAccountId(int nextAccountId) { this.nextAccountId = nextAccountId; }

    // Method to add new client
    public Client addClient(Person p, String username, String password) {
        // Generate client ID with formatting
        String clientId = "CL" + String.format("%04d", nextClientId);
        // Create new client object
        Client newClient = new Client(p, username, password);
        // Set client ID
        newClient.setId(clientId);
        // Add client to list
        ClList.add(newClient);
        // Increment next client ID
        nextClientId++;
        // Return created client
        return newClient;
    }

    // Method to add new account for client
    public Account addAccount(Client client, float amount) {
        // Generate account number with formatting
        String accountNumber = "ACC" + String.format("%06d", nextAccountId);
        // Create new account object
        Account newAccount = new Account(amount, client);
        // Set account number
        newAccount.setNumber(accountNumber);
        // Add account to bank's account list
        AcList.add(newAccount);
        // Add account to client's account list
        client.addAccount(newAccount);
        // Increment next account ID
        nextAccountId++;
        // Return created account
        return newAccount;
    }

    // Method to search for account by ID
    public Account searchAccount(String id) {
        // Loop through all accounts
        for (Account account : AcList) {
            // Check if account number matches
            if (account.getNumber().equals(id)) {
                // Return found account
                return account;
            }
        }
        // Return null if not found
        return null;
    }

    // Method to remove client by ID
    public boolean removeClient(String id) {
        // Create iterator for client list
        Iterator<Client> clientIterator = ClList.iterator();
        // Loop through clients
        while (clientIterator.hasNext()) {
            // Get next client
            Client client = clientIterator.next();
            // Check if client ID matches
            if (client.getId().equals(id)) {
                // Create iterator for account list
                Iterator<Account> accountIterator = AcList.iterator();
                // Loop through accounts
                while (accountIterator.hasNext()) {
                    // Get next account
                    Account account = accountIterator.next();
                    // Check if account belongs to this client
                    if (account.getACholder().getId().equals(id)) {
                        // Remove account
                        accountIterator.remove();
                    }
                }
                // Remove client
                clientIterator.remove();
                // Return true for successful removal
                return true;
            }
        }
        // Return false if client not found
        return false;
    }

    // Method to calculate total bank amount
    public float totalAmount() {
        // Initialize total to 0
        float total = 0;
        // Loop through all accounts
        for (Account account : AcList) {
            // Add each account's amount to total
            total += account.getAmount();
        }
        // Return total amount
        return total;
    }

    // Method to search client by CNIC
    public Client searchCustomerDetail(String CNIC) {
        // Loop through all clients
        for (Client client : ClList) {
            // Check if client's CNIC matches
            if (client.getPersonDetails().getCNIC().equals(CNIC)) {
                // Return found client
                return client;
            }
        }
        // Return null if not found
        return null;
    }

    // Method to authenticate client login
    public Client loginClient(String username, String password) {
        // Loop through all clients
        for (Client client : ClList) {
            // Check if username and password match
            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                // Return authenticated client
                return client;
            }
        }
        // Return null if authentication fails
        return null;
    }

    // Method to check if username is already taken
    public boolean isUsernameTaken(String username) {
        // Loop through all clients
        for (Client client : ClList) {
            // Check if username matches
            if (client.getUsername().equals(username)) {
                // Return true if username exists
                return true;
            }
        }
        // Return false if username available
        return false;
    }

    // String representation of Bank object
    @Override
    public String toString() {
        // Create StringBuilder for building string
        StringBuilder sb = new StringBuilder();
        // Add bank information header
        sb.append("=== BANK INFORMATION ===\n");
        // Add bank name
        sb.append("Bank Name: ").append(name).append("\n");
        // Add total clients count
        sb.append("Total Clients: ").append(ClList.size()).append("\n");
        // Add total accounts count
        sb.append("Total Accounts: ").append(AcList.size()).append("\n");
        // Add total bank amount
        sb.append("Total Bank Amount: PKR ").append(String.format("%,.2f", totalAmount())).append("\n");
        // Return built string
        return sb.toString();
    }

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;
}

// ====================== FILE HANDLER ======================
// File handler class for saving/loading bank data
class BankFileHandler {
    // File name for bank data
    private static final String BANK_FILE = "bank_data.dat";
    // File name for counter data
    private static final String COUNTERS_FILE = "counters.dat";

    // Static initializer block
    static {
        // Create data directory if it doesn't exist
        new File("data").mkdirs();
    }

    // Method to save bank data to file
    public static void saveBankData(Bank bank) {
        // Try-with-resources for ObjectOutputStream
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("data/" + BANK_FILE))) {
            // Write bank object to file
            oos.writeObject(bank);

            // Save counters separately
            // Try-with-resources for counters output stream
            try (ObjectOutputStream countersOOS = new ObjectOutputStream(
                    new FileOutputStream("data/" + COUNTERS_FILE))) {
                // Write client counter to file
                countersOOS.writeInt(bank.getNextClientId());
                // Write account counter to file
                countersOOS.writeInt(bank.getNextAccountId());
            }
        } catch (IOException e) {
            // Show error message if save fails
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to load bank data from file
    public static Bank loadBankData(String bankName) {
        // Create file object for bank data
        File bankFile = new File("data/" + BANK_FILE);
        // Create file object for counters data
        File countersFile = new File("data/" + COUNTERS_FILE);

        // Check if bank file exists
        if (bankFile.exists()) {
            // Try-with-resources for ObjectInputStream
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(bankFile))) {
                // Read bank object from file
                Bank bank = (Bank) ois.readObject();

                // Load counters if available
                // Check if counters file exists
                if (countersFile.exists()) {
                    // Try-with-resources for counters input stream
                    try (ObjectInputStream countersOIS = new ObjectInputStream(
                            new FileInputStream(countersFile))) {
                        // Read client counter from file
                        int clientCounter = countersOIS.readInt();
                        // Read account counter from file
                        int accountCounter = countersOIS.readInt();
                        // Set counters in bank object
                        bank.setNextClientId(clientCounter);
                        bank.setNextAccountId(accountCounter);
                    }
                }

                // Return loaded bank
                return bank;
            } catch (IOException | ClassNotFoundException e) {
                // Show error message if load fails
                JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Return new bank if file doesn't exist
        return new Bank(bankName);
    }
}

// ====================== LOGIN FRAME ======================
// Login frame class for user authentication
class LoginFrame extends JFrame {
    // Bank object reference
    private Bank bank;
    // Username input field
    private JTextField usernameField;
    // Password input field
    private JPasswordField passwordField;
    // Radio button for user login
    private JRadioButton userRadio;
    // Radio button for admin login
    private JRadioButton adminRadio;

    // Constructor
    public LoginFrame() {
        // Load bank data from file
        bank = BankFileHandler.loadBankData("MSB");
        // Initialize UI components
        initializeUI();
    }

    // Method to initialize UI
    private void initializeUI() {
        // Set window title
        setTitle("MSB - Login");
        // Set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        setSize(400, 350);
        // Center window on screen
        setLocationRelativeTo(null);
        // Disable window resizing
        setResizable(false);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            // Override paintComponent for custom background
            @Override
            protected void paintComponent(Graphics g) {
                // Call parent's paintComponent
                super.paintComponent(g);
                // Cast Graphics to Graphics2D
                Graphics2D g2d = (Graphics2D) g;
                // Create gradient paint
                GradientPaint gp = new GradientPaint(0, 0, new Color(119, 205, 189),
                        0, getHeight(), new Color(119, 205, 189));
                // Set paint
                g2d.setPaint(gp);
                // Fill rectangle with gradient
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        // Set border for main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create title label
        JLabel titleLabel = new JLabel("MSB", SwingConstants.CENTER);
        // Set font for title
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        // Set text color
        titleLabel.setForeground(Color.BLACK);
        // Set border for title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Create login panel with GridBagLayout
        JPanel loginPanel = new JPanel(new GridBagLayout());
        // Make login panel transparent
        loginPanel.setOpaque(false);
        // Create GridBagConstraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        // Set insets (margins)
        gbc.insets = new Insets(10, 10, 10, 10);
        // Set fill behavior
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User type selection label
        gbc.gridx = 0;  // Column 0
        gbc.gridy = 0;  // Row 0
        gbc.gridwidth = 2;  // Span 2 columns
        JLabel typeLabel = new JLabel("Login as:");
        // Set font for label
        typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Set text color
        typeLabel.setForeground(Color.black);
        // Add label to panel
        loginPanel.add(typeLabel, gbc);

        // Create user radio button
        userRadio = new JRadioButton("User", true);
        // Create admin radio button
        adminRadio = new JRadioButton("Admin");
        // Set text color for user radio
        userRadio.setForeground(Color.black);
        // Set text color for admin radio
        adminRadio.setForeground(Color.black);
        // Make user radio transparent
        userRadio.setOpaque(false);
        // Make admin radio transparent
        adminRadio.setOpaque(false);
        // Set font for user radio
        userRadio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        // Set font for admin radio
        adminRadio.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Create button group for radio buttons
        ButtonGroup group = new ButtonGroup();
        // Add user radio to group
        group.add(userRadio);
        // Add admin radio to group
        group.add(adminRadio);

        // Create panel for radio buttons
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        // Make radio panel transparent
        radioPanel.setOpaque(false);
        // Add user radio to panel
        radioPanel.add(userRadio);
        // Add admin radio to panel
        radioPanel.add(adminRadio);

        gbc.gridy = 1;  // Row 1
        // Add radio panel to login panel
        loginPanel.add(radioPanel, gbc);

        // Username label
        gbc.gridwidth = 1;  // Reset to single column
        gbc.gridy = 2;  // Row 2
        gbc.gridx = 0;  // Column 0
        JLabel userLabel = new JLabel("Username:");
        // Set font for label
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Set text color
        userLabel.setForeground(Color.black);
        // Add label to panel
        loginPanel.add(userLabel, gbc);

        // Username input field
        gbc.gridx = 1;  // Column 1
        usernameField = new JTextField(15);
        // Set font for text field
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Add text field to panel
        loginPanel.add(usernameField, gbc);

        // Password label
        gbc.gridy = 3;  // Row 3
        gbc.gridx = 0;  // Column 0
        JLabel passLabel = new JLabel("Password:");
        // Set font for label
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Set text color
        passLabel.setForeground(Color.black);
        // Add label to panel
        loginPanel.add(passLabel, gbc);

        // Password input field
        gbc.gridx = 1;  // Column 1
        passwordField = new JPasswordField(15);
        // Set font for password field
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Add password field to panel
        loginPanel.add(passwordField, gbc);

        // Login button
        gbc.gridy = 4;  // Row 4
        gbc.gridx = 0;  // Column 0
        gbc.gridwidth = 1;  // Single column
        JButton loginButton = createStyledButton("Login", new Color(116, 76, 175));
        // Add action listener for login
        loginButton.addActionListener(e -> login());
        // Add button to panel
        loginPanel.add(loginButton, gbc);

        // Register button
        gbc.gridx = 1;  // Column 1
        JButton registerButton = createStyledButton("Register", new Color(243, 33, 138));
        // Add action listener for registration
        registerButton.addActionListener(e -> openRegistration());
        // Add button to panel
        loginPanel.add(registerButton, gbc);

        // Add title label to main panel (north position)
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Add login panel to main panel (center position)
        mainPanel.add(loginPanel, BorderLayout.CENTER);

//        // Admin credentials hint
//        JLabel hintLabel = new JLabel( SwingConstants.CENTER);
        // Set font for hint
//        hintLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
//        // Set text color
//        hintLabel.setForeground(Color.YELLOW);
//        // Add hint to main panel (south position)
//        mainPanel.add(hintLabel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Set default button for Enter key
        getRootPane().setDefaultButton(loginButton);
    }

    // Method to create styled button
    private JButton createStyledButton(String text, Color color) {
        // Create button with text
        JButton button = new JButton(text);
        // Set button font
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Set button background color
        button.setBackground(color);
        // Set button text color
        button.setForeground(Color.WHITE);
        // Disable focus painting
        button.setFocusPainted(false);
        // Disable border painting
        button.setBorderPainted(false);
        // Set cursor to hand cursor
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Set preferred size
        button.setPreferredSize(new Dimension(120, 35));

        // Add mouse listener for hover effects
        button.addMouseListener(new MouseAdapter() {
            // When mouse enters button
            @Override
            public void mouseEntered(MouseEvent e) {
                // Darken button color
                button.setBackground(color.darker());
            }

            // When mouse exits button
            @Override
            public void mouseExited(MouseEvent e) {
                // Restore original button color
                button.setBackground(color);
            }
        });

        // Return created button
        return button;
    }

    // Method to handle login
    private void login() {
        // Get username from field (trim whitespace)
        String username = usernameField.getText().trim();
        // Get password from field (trim whitespace)
        String password = new String(passwordField.getPassword()).trim();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            // Show error message
            JOptionPane.showMessageDialog(this, "Please enter username and password!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Exit method
            return;
        }

        // Check if admin radio is selected
        if (adminRadio.isSelected()) {
            // Check admin credentials
            if (username.equals("admin123") && password.equals("admin456")) {
                // Open admin frame
                new AdminFrame(bank).setVisible(true);
                // Close login frame
                dispose();
            } else {
                // Show invalid credentials message
                JOptionPane.showMessageDialog(this, "Invalid admin credentials!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Try to login as client
            Client client = bank.loginClient(username, password);
            // Check if client exists
            if (client != null) {
                // Open user frame
                new UserFrame(bank, client).setVisible(true);
                // Close login frame
                dispose();
            } else {
                // Show invalid credentials message
                JOptionPane.showMessageDialog(this, "Invalid username or password!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to open registration frame
    private void openRegistration() {
        // Create and show registration frame
        new RegistrationFrame(bank, this).setVisible(true);
    }
}

// ====================== REGISTRATION FRAME ======================
// Registration frame class for new user registration
class RegistrationFrame extends JFrame {
    // Bank object reference
    private Bank bank;
    // Login frame reference
    private LoginFrame loginFrame;

    // Constructor
    public RegistrationFrame(Bank bank, LoginFrame loginFrame) {
        // Initialize bank reference
        this.bank = bank;
        // Initialize login frame reference
        this.loginFrame = loginFrame;
        // Initialize UI components
        initializeUI();
    }

    // Method to initialize UI
    private void initializeUI() {
        // Set window title
        setTitle("User Registration");
        // Set window size
        setSize(400, 450);
        // Center window on screen
        setLocationRelativeTo(null);
        // Disable window resizing
        setResizable(false);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        // Set border for main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Set background color
        mainPanel.setBackground(new Color(133, 125, 125));

        // Create title label
        JLabel titleLabel = new JLabel("New User Registration", SwingConstants.CENTER);
        // Set font for title
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        // Set text color
        titleLabel.setForeground(new Color(4, 7, 35, 255));
        // Set border for title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Create form panel with grid layout
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        // Set background color
        formPanel.setBackground(new Color(133, 125, 125));

        // Create input fields
        JTextField nameField = new JTextField();
        JTextField cnicField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();

        // Add form labels and fields
        formPanel.add(createFormLabel("Full Name:"));
        formPanel.add(nameField);
        formPanel.add(createFormLabel("CNIC (13 digits):"));
        formPanel.add(cnicField);
        formPanel.add(createFormLabel("Phone Number:"));
        formPanel.add(phoneField);
        formPanel.add(createFormLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(createFormLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(createFormLabel("Confirm Password:"));
        formPanel.add(confirmPasswordField);

        // Create register button
        JButton registerButton = createStyledButton("Register", new Color(76, 175, 80));
        // Create cancel button
        JButton cancelButton = createStyledButton("Cancel", new Color(244, 67, 54));

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        // Set background color
        buttonPanel.setBackground(new Color(133, 125, 125));
        // Add buttons to panel
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        // Add action listener to register button
        registerButton.addActionListener(e -> {
            // Get input values (trim whitespace)
            String name = nameField.getText().trim();
            String cnic = cnicField.getText().trim();
            String phone = phoneField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            // Check if any field is empty
            if (name.isEmpty() || cnic.isEmpty() || phone.isEmpty() ||
                    username.isEmpty() || password.isEmpty()) {
                // Show error message
                JOptionPane.showMessageDialog(this, "All fields are required!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Validate CNIC format (13 digits)
            if (!cnic.matches("\\d{13}")) {
                // Show error message
                JOptionPane.showMessageDialog(this, "CNIC must be 13 digits!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Check if CNIC already registered
            if (bank.searchCustomerDetail(cnic) != null) {
                // Show error message
                JOptionPane.showMessageDialog(this, "CNIC already registered!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Check if username already taken
            if (bank.isUsernameTaken(username)) {
                // Show error message
                JOptionPane.showMessageDialog(this, "Username already taken!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                // Show error message
                JOptionPane.showMessageDialog(this, "Passwords do not match!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Check password length
            if (password.length() < 6) {
                // Show error message
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Create Person object
            Person person = new Person(name, cnic, phone);
            // Add client to bank
            Client client = bank.addClient(person, username, password);

            // Save bank data to file
            BankFileHandler.saveBankData(bank);

            // Show success message with client ID
            JOptionPane.showMessageDialog(this,
                    "Registration successful!\nYour Client ID: " + client.getId(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            // Close registration frame
            dispose();
        });

        // Add action listener to cancel button
        cancelButton.addActionListener(e -> dispose());

        // Add title to main panel (north position)
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Add form panel to main panel (center position)
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Add button panel to main panel (south position)
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    // Method to create form label
    private JLabel createFormLabel(String text) {
        // Create label with text
        JLabel label = new JLabel(text);
        // Set font for label
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        // Set text color
        label.setForeground(Color.BLACK);
        // Return created label
        return label;
    }

    // Method to create styled button
    private JButton createStyledButton(String text, Color color) {
        // Create button with text
        JButton button = new JButton(text);
        // Set button font
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Set button background color
        button.setBackground(color);
        // Set button text color
        button.setForeground(Color.WHITE);
        // Disable focus painting
        button.setFocusPainted(false);
        // Disable border painting
        button.setBorderPainted(false);
        // Set cursor to hand cursor
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Set preferred size
        button.setPreferredSize(new Dimension(120, 35));

        // Add mouse listener for hover effects
        button.addMouseListener(new MouseAdapter() {
            // When mouse enters button
            @Override
            public void mouseEntered(MouseEvent e) {
                // Darken button color
                button.setBackground(color.darker());
            }

            // When mouse exits button
            @Override
            public void mouseExited(MouseEvent e) {
                // Restore original button color
                button.setBackground(color);
            }
        });

        // Return created button
        return button;
    }
}

// ====================== USER FRAME ======================
// User frame class for client dashboard
class UserFrame extends JFrame {
    // Bank object reference
    private Bank bank;
    // Current client object
    private Client client;
    // Table for displaying accounts
    private JTable accountsTable;
    // Table model for accounts table
    private DefaultTableModel accountsTableModel;
    // Text area for displaying details
    private JTextArea displayArea;
    // Label for welcome message
    private JLabel welcomeLabel;
    // Label for total balance
    private JLabel totalBalanceLabel;

    // Constructor
    public UserFrame(Bank bank, Client client) {
        // Initialize bank reference
        this.bank = bank;
        // Initialize client reference
        this.client = client;
        // Initialize UI components
        initializeUI();
        // Refresh table data
        refreshTables();
    }

    // Method to initialize UI
    private void initializeUI() {
        // Set window title
        setTitle("MSB - User Dashboard");
        // Set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        setSize(900, 600);
        // Center window on screen
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        // Set border for main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Set background color
        mainPanel.setBackground(Color.WHITE);

        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        // Set background color
        headerPanel.setBackground(new Color(30, 60, 114));
        // Set border for header
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Create welcome label with client name
        welcomeLabel = new JLabel("Welcome, " + client.getPersonDetails().getName());
        // Set font for welcome label
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        // Set text color
        welcomeLabel.setForeground(Color.WHITE);

        // Create total balance label
        totalBalanceLabel = new JLabel("Total Balance: PKR 0.00");
        // Set font for balance label
        totalBalanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        // Set text color
        totalBalanceLabel.setForeground(Color.WHITE);

        // Create user info label
        JLabel userInfoLabel = new JLabel("Client ID: " + client.getId() + " | CNIC: " + client.getPersonDetails().getCNIC());
        // Set font for info label
        userInfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        // Set text color
        userInfoLabel.setForeground(new Color(200, 200, 200));

        // Create info panel
        JPanel infoPanel = new JPanel(new BorderLayout());
        // Make panel transparent
        infoPanel.setOpaque(false);
        // Add welcome label to north
        infoPanel.add(welcomeLabel, BorderLayout.NORTH);
        // Add user info label to south
        infoPanel.add(userInfoLabel, BorderLayout.SOUTH);

        // Add info panel to header (west position)
        headerPanel.add(infoPanel, BorderLayout.WEST);
        // Add balance label to header (east position)
        headerPanel.add(totalBalanceLabel, BorderLayout.EAST);

        // Create center panel
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        // Set background color
        centerPanel.setBackground(Color.WHITE);
        // Set border for center panel
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create table panel
        JPanel tablePanel = new JPanel(new BorderLayout());

        // Define table column names
        String[] accountColumns = {"Account Details", "Balance (PKR)"};
        // Create table model with columns
        accountsTableModel = new DefaultTableModel(accountColumns, 0) {
            // Override to make cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                // Return false to prevent editing
                return false;
            }
        };

        // Create table with model
        accountsTable = new JTable(accountsTableModel);

        // Get table header
        JTableHeader header = accountsTable.getTableHeader();
        // Set header font
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        // Set header background color
        header.setBackground(new Color(0, 0, 0));
        // Set header text color
        header.setForeground(Color.BLUE);
        // Set header preferred size
        header.setPreferredSize(new Dimension(header.getWidth(), 50));

        // Get header renderer
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        // Center align header text
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Set table row height
        accountsTable.setRowHeight(35);
        // Set table font
        accountsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Set grid color
        accountsTable.setGridColor(new Color(180, 180, 180));
        // Show grid
        accountsTable.setShowGrid(true);
        // Set intercell spacing
        accountsTable.setIntercellSpacing(new Dimension(1, 1));
        // Set selection background color
        accountsTable.setSelectionBackground(new Color(220, 240, 255));
        // Set selection text color
        accountsTable.setSelectionForeground(Color.BLACK);

        // Create cell renderer for centering
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        // Center align cell content
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        // Set default renderer for all cells
        accountsTable.setDefaultRenderer(Object.class, centerRenderer);

        // Create scroll pane for table
        JScrollPane tableScrollPane = new JScrollPane(accountsTable);
        // Set border with title for table
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                // Create line border
                BorderFactory.createLineBorder(Color.BLACK, 2),
                // Title text
                "VIEW ACCOUNTS",
                // Title position
                TitledBorder.CENTER,
                // Title placement
                TitledBorder.TOP,
                // Title font
                new Font("Segoe UI", Font.BOLD, 16),
                // Title color
                Color.BLACK
        ));

        // Create button panel with grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        // Set border for button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        // Set background color
        buttonPanel.setBackground(Color.WHITE);

        // Define button names
        String[] buttonNames = {"View Accounts", "Deposit", "Withdraw", "Personal Info", "Account Details", "Logout"};
        // Define button colors
        Color[] buttonColors = {
                new Color(39, 165, 176), new Color(39, 165, 176), new Color(39, 165, 176),
                new Color(39, 165, 176), new Color(39, 165, 176), new Color(39, 165, 176)
        };

        // Create and add buttons
        for (int i = 0; i < buttonNames.length; i++) {
            // Create styled button
            JButton button = createStyledButton(buttonNames[i], buttonColors[i]);
            // Store current index for lambda
            int finalI = i;
            // Add action listener to button
            button.addActionListener(e -> handleUserAction(buttonNames[finalI]));
            // Add button to panel
            buttonPanel.add(button);
        }

        // Create display text area
        displayArea = new JTextArea();
        // Set text area font
        displayArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        // Make text area read-only
        displayArea.setEditable(false);
        // Set background color
        displayArea.setBackground(new Color(250, 250, 250));
        // Set text color
        displayArea.setForeground(Color.BLACK);

        // Create scroll pane for display area
        JScrollPane displayScrollPane = new JScrollPane(displayArea);
        // Set border with title for display area
        displayScrollPane.setBorder(BorderFactory.createTitledBorder(
                // Create line border
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                // Title text
                "Account Details",
                // Title justification
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                // Title position
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                // Title font
                new Font("Segoe UI", Font.BOLD, 12),
                // Title color
                Color.BLACK
        ));

        // Create split pane for table and display area
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScrollPane, displayScrollPane);
        // Set divider location (300 pixels from top)
        splitPane.setDividerLocation(300);
        // Set background color
        splitPane.setBackground(Color.WHITE);

        // Add split pane to center panel
        centerPanel.add(splitPane, BorderLayout.CENTER);

        // Add header panel to main panel (north position)
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        // Add button panel to main panel (west position)
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        // Add center panel to main panel (center position)
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);
    }

    // Method to create styled button
    private JButton createStyledButton(String text, Color color) {
        // Create button with text
        JButton button = new JButton(text);
        // Set button font
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        // Set button background color
        button.setBackground(color);
        // Set button text color
        button.setForeground(Color.WHITE);
        // Disable focus painting
        button.setFocusPainted(false);
        // Disable border painting
        button.setBorderPainted(false);
        // Set cursor to hand cursor
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Set preferred size
        button.setPreferredSize(new Dimension(150, 40));

        // Add mouse listener for hover effects
        button.addMouseListener(new MouseAdapter() {
            // When mouse enters button
            @Override
            public void mouseEntered(MouseEvent e) {
                // Darken button color
                button.setBackground(color.darker());
            }

            // When mouse exits button
            @Override
            public void mouseExited(MouseEvent e) {
                // Restore original button color
                button.setBackground(color);
            }
        });

        // Return created button
        return button;
    }

    // Method to handle user actions
    private void handleUserAction(String action) {
        // Switch based on action name
        switch (action) {
            case "View Accounts":
                // Display all accounts in text area
                displayArea.setText(client.toString());
                break;
            case "Deposit":
                // Open deposit dialog
                deposit();
                break;
            case "Withdraw":
                // Open withdraw dialog
                withdraw();
                break;
            case "Personal Info":
                // Show personal information
                showPersonalInfo();
                break;
            case "Account Details":
                // Show details of selected account
                showAccountDetails();
                break;
            case "Logout":
                // Save bank data
                BankFileHandler.saveBankData(bank);
                // Open login frame
                new LoginFrame().setVisible(true);
                // Close current frame
                dispose();
                break;
        }
    }

    // Method to show account details
    private void showAccountDetails() {
        // Get selected row index
        int selectedRow = accountsTable.getSelectedRow();
        // Check if valid row is selected
        if (selectedRow >= 0 && selectedRow < client.getAcList().size()) {
            // Get account from selected row
            Account account = client.getAcList().get(selectedRow);
            // Create date formatter
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            // Build details string
            String details = "Account Number: " + account.getNumber() + "\n" +
                    "Balance: PKR " + String.format("%,.2f", account.getAmount()) + "\n" +
                    "Created Date: " + sdf.format(account.getCreatedDate()) + "\n" +
                    "Account Holder: " + client.getPersonDetails().getName() + "\n" +
                    "CNIC: " + client.getPersonDetails().getCNIC();
            // Display details in text area
            displayArea.setText(details);
        } else {
            // Show warning if no account selected
            JOptionPane.showMessageDialog(this, "Please select an account from the table.",
                    "No Account Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Method to handle deposit
    private void deposit() {
        // Get client's accounts
        List<Account> accounts = client.getAcList();
        // Check if client has any accounts
        if (accounts.isEmpty()) {
            // Show error message
            JOptionPane.showMessageDialog(this, "You don't have any accounts yet!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Exit method
            return;
        }

        // Create array for account options
        String[] accountOptions = new String[accounts.size()];
        // Populate options array
        for (int i = 0; i < accounts.size(); i++) {
            // Get account
            Account account = accounts.get(i);
            // Create option string
            accountOptions[i] = account.getNumber() + " - PKR " + String.format("%,.2f", account.getAmount());
        }

        // Show account selection dialog
        String selectedAccount = (String) JOptionPane.showInputDialog(this,
                "Select account to deposit to:", "Deposit", JOptionPane.QUESTION_MESSAGE,
                null, accountOptions, accountOptions[0]);

        // Check if account was selected
        if (selectedAccount != null) {
            // Extract account number from selection
            String accountNumber = selectedAccount.split(" - ")[0];
            // Show amount input dialog
            String amountStr = JOptionPane.showInputDialog(this,
                    "Enter deposit amount in PKR:");

            // Check if amount was entered
            if (amountStr != null && !amountStr.trim().isEmpty()) {
                try {
                    // Parse amount string to float
                    float amount = Float.parseFloat(amountStr);
                    // Check if amount is positive
                    if (amount > 0) {
                        // Attempt deposit
                        float result = client.deposit(amount, accountNumber);
                        // Check if deposit successful
                        if (result != -1) {
                            // Save bank data
                            BankFileHandler.saveBankData(bank);
                            // Show success message
                            JOptionPane.showMessageDialog(this,
                                    "Deposit successful!\nNew Balance: PKR " + String.format("%,.2f", result),
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                            // Refresh tables
                            refreshTables();
                        } else {
                            // Show error message
                            JOptionPane.showMessageDialog(this, "Deposit failed!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Show error for non-positive amount
                        JOptionPane.showMessageDialog(this, "Amount must be positive!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    // Show error for invalid amount format
                    JOptionPane.showMessageDialog(this, "Invalid amount! Please enter a valid number.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Method to handle withdrawal
    private void withdraw() {
        // Get client's accounts
        List<Account> accounts = client.getAcList();
        // Check if client has any accounts
        if (accounts.isEmpty()) {
            // Show error message
            JOptionPane.showMessageDialog(this, "You don't have any accounts yet!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Exit method
            return;
        }

        // Create array for account options
        String[] accountOptions = new String[accounts.size()];
        // Populate options array
        for (int i = 0; i < accounts.size(); i++) {
            // Get account
            Account account = accounts.get(i);
            // Create option string
            accountOptions[i] = account.getNumber() + " - PKR " + String.format("%,.2f", account.getAmount());
        }

        // Show account selection dialog
        String selectedAccount = (String) JOptionPane.showInputDialog(this,
                "Select account to withdraw from:", "Withdraw", JOptionPane.QUESTION_MESSAGE,
                null, accountOptions, accountOptions[0]);

        // Check if account was selected
        if (selectedAccount != null) {
            // Extract account number from selection
            String accountNumber = selectedAccount.split(" - ")[0];
            // Search for account in bank
            Account account = bank.searchAccount(accountNumber);

            // Check if account found
            if (account != null) {
                // Show amount input dialog with current balance
                String amountStr = JOptionPane.showInputDialog(this,
                        "Enter withdrawal amount in PKR (Available: PKR " +
                                String.format("%,.2f", account.getAmount()) + "):");

                // Check if amount was entered
                if (amountStr != null && !amountStr.trim().isEmpty()) {
                    try {
                        // Parse amount string to float
                        float amount = Float.parseFloat(amountStr);
                        // Check if amount is positive
                        if (amount > 0) {
                            // Attempt withdrawal
                            float result = client.withdraw(amount, accountNumber);
                            // Check if withdrawal successful
                            if (result != -1) {
                                // Save bank data
                                BankFileHandler.saveBankData(bank);
                                // Show success message
                                JOptionPane.showMessageDialog(this,
                                        "Withdrawal successful!\nRemaining Balance: PKR " + String.format("%,.2f", result),
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                // Refresh tables
                                refreshTables();
                            } else {
                                // Show error for insufficient balance
                                JOptionPane.showMessageDialog(this,
                                        "Withdrawal failed! Insufficient balance.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Show error for non-positive amount
                            JOptionPane.showMessageDialog(this, "Amount must be positive!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        // Show error for invalid amount format
                        JOptionPane.showMessageDialog(this, "Invalid amount!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    // Method to show personal information
    private void showPersonalInfo() {
        // Build personal information string
        String info = "=== PERSONAL INFORMATION ===\n" +
                "Name: " + client.getPersonDetails().getName() + "\n" +
                "CNIC: " + client.getPersonDetails().getCNIC() + "\n" +
                "Phone: " + client.getPersonDetails().getPhoneNo() + "\n" +
                "Client ID: " + client.getId() + "\n" +
                "Username: " + client.getUsername() + "\n" +
                "\n=== FINANCIAL SUMMARY ===\n" +
                "Total Accounts: " + client.getAcList().size() + "\n" +
                "Total Balance: PKR " + String.format("%,.2f", client.totalAmount());

        // Display information in text area
        displayArea.setText(info);
    }

    // Method to refresh tables
    private void refreshTables() {
        // Clear existing table rows
        accountsTableModel.setRowCount(0);

        // Create date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        // Loop through client's accounts
        for (Account account : client.getAcList()) {
            // Add account row to table
            accountsTableModel.addRow(new Object[]{
                    // Combine account number and creation date
                    account.getNumber() + " | Created: " + sdf.format(account.getCreatedDate()),
                    // Format balance with currency
                    "PKR " + String.format("%,.2f", account.getAmount())
            });
        }

        // Update total balance label
        totalBalanceLabel.setText("Total Balance: PKR " + String.format("%,.2f", client.totalAmount()));
    }
}

// ====================== ADMIN FRAME ======================
// Admin frame class for administrator dashboard
class AdminFrame extends JFrame {
    // Bank object reference
    private Bank bank;
    // Table for displaying clients
    private JTable clientsTable;
    // Table for displaying accounts
    private JTable accountsTable;
    // Table model for clients table
    private DefaultTableModel clientsTableModel;
    // Table model for accounts table
    private DefaultTableModel accountsTableModel;
    // Text area for displaying details
    private JTextArea displayArea;
    // Label for statistics
    private JLabel statsLabel;

    // Constructor
    public AdminFrame(Bank bank) {
        // Initialize bank reference
        this.bank = bank;
        // Initialize UI components
        initializeUI();
        // Refresh table data
        refreshTables();
    }

    // Method to initialize UI
    private void initializeUI() {
        // Set window title
        setTitle("MSB - Admin Dashboard");
        // Set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        setSize(1200, 700);
        // Center window on screen
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        // Set border for main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Set background color
        mainPanel.setBackground(Color.WHITE);

        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        // Set background color
        headerPanel.setBackground(new Color(30, 60, 114));
        // Set border for header
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Create title label
        JLabel titleLabel = new JLabel("MSB - Admin Dashboard");
        // Set font for title
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        // Set text color
        titleLabel.setForeground(Color.WHITE);

        // Create stats label
        statsLabel = new JLabel("Clients: 0 | Accounts: 0 | Totals: PKR 0.00");
        // Set font for stats
        statsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        // Set text color
        statsLabel.setForeground(Color.WHITE);

        // Add title to header (west position)
        headerPanel.add(titleLabel, BorderLayout.WEST);
        // Add stats to header (east position)
        headerPanel.add(statsLabel, BorderLayout.EAST);

        // Create button panel with grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(9, 1, 10, 10));
        // Set border for button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        // Set background color
        buttonPanel.setBackground(Color.WHITE);

        // Define button names
        String[] buttonNames = {
                "Add Client", "Add Account", "Search Account",
                "Search Client", "Remove Client", "Withdraw",
                "Deposit", "Display All", "Logout"
        };

        // Define button colors
        Color[] buttonColors = {
                new Color(31, 191, 178), new Color(31, 191, 178), new Color(31, 191, 178),
                new Color(31, 191, 178), new Color(31, 191, 178), new Color(31, 191, 178),
                new Color(31, 191, 178), new Color(31, 191, 178), new Color(31, 191, 178)
        };

        // Create and add buttons
        for (int i = 0; i < buttonNames.length; i++) {
            // Create styled button
            JButton button = createStyledButton(buttonNames[i], buttonColors[i]);
            // Store current index for lambda
            int finalI = i;
            // Add action listener to button
            button.addActionListener(e -> handleAdminAction(buttonNames[finalI]));
            // Add button to panel
            buttonPanel.add(button);
        }

        // Create tabbed pane for different views
        JTabbedPane tabbedPane = new JTabbedPane();
        // Set tab font
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 12));
        // Set background color
        tabbedPane.setBackground(Color.WHITE);

        // Create clients tab
        JPanel clientsTab = new JPanel(new BorderLayout());
        // Set border for clients tab
        clientsTab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Set background color
        clientsTab.setBackground(Color.WHITE);

        // Define client table columns
        String[] clientColumns = {"Client ID", "Name", "CNIC", "Phone", "Amount"};
        // Create table model for clients
        clientsTableModel = new DefaultTableModel(clientColumns, 0) {
            // Override to make cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                // Return false to prevent editing
                return false;
            }
            // Override to specify column class
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Return String class for all columns
                return String.class;
            }
        };

        // Create clients table with model
        clientsTable = new JTable(clientsTableModel);

        // Get clients table header
        JTableHeader clientHeader = clientsTable.getTableHeader();
        // Set header font
        clientHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
        // Set header background color
        clientHeader.setBackground(Color.BLACK);
        // Set header text color
        clientHeader.setForeground(Color.BLUE);
        // Set header preferred size
        clientHeader.setPreferredSize(new Dimension(clientHeader.getWidth(), 50));

        // Get header renderer
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) clientHeader.getDefaultRenderer();
        // Center align header text
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Set table row height
        clientsTable.setRowHeight(35);
        // Set table font
        clientsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Set grid color
        clientsTable.setGridColor(new Color(180, 180, 180));
        // Show grid
        clientsTable.setShowGrid(true);
        // Set selection background color
        clientsTable.setSelectionBackground(new Color(220, 240, 255));
        // Set selection text color
        clientsTable.setSelectionForeground(Color.BLACK);

        // Create cell renderer for centering
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        // Center align cell content
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        // Set default renderer for all cells
        clientsTable.setDefaultRenderer(Object.class, cellRenderer);

        // Set column widths
        clientsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        clientsTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        clientsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        clientsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        clientsTable.getColumnModel().getColumn(4).setPreferredWidth(120);

        // Create scroll pane for clients table
        JScrollPane clientsScrollPane = new JScrollPane(clientsTable);
        // Set border with title for clients table
        clientsScrollPane.setBorder(BorderFactory.createTitledBorder(
                // Create line border
                BorderFactory.createLineBorder(Color.BLACK, 2),
                // Title text
                "ALL CLIENTS",
                // Title position
                TitledBorder.CENTER,
                // Title placement
                TitledBorder.TOP,
                // Title font
                new Font("Segoe UI", Font.BOLD, 16),
                // Title color
                Color.BLACK
        ));
        // Add scroll pane to clients tab
        clientsTab.add(clientsScrollPane, BorderLayout.CENTER);

        // Create accounts tab
        JPanel accountsTab = new JPanel(new BorderLayout());
        // Set border for accounts tab
        accountsTab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Set background color
        accountsTab.setBackground(Color.WHITE);

        // Define account table columns
        String[] accountColumns = {"Account No", "Client Name", "Client ID", "Balance (PKR)"};
        // Create table model for accounts
        accountsTableModel = new DefaultTableModel(accountColumns, 0) {
            // Override to make cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                // Return false to prevent editing
                return false;
            }
        };

        // Create accounts table with model
        accountsTable = new JTable(accountsTableModel);

        // Get accounts table header
        JTableHeader accountHeader = accountsTable.getTableHeader();
        // Set header font
        accountHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
        // Set header background color
        accountHeader.setBackground(Color.BLACK);
        // Set header text color
        accountHeader.setForeground(Color.YELLOW);
        // Set header preferred size
        accountHeader.setPreferredSize(new Dimension(accountHeader.getWidth(), 50));

        // Get header renderer
        DefaultTableCellRenderer accHeaderRenderer = (DefaultTableCellRenderer) accountHeader.getDefaultRenderer();
        // Center align header text
        accHeaderRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Set table row height
        accountsTable.setRowHeight(35);
        // Set table font
        accountsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Set grid color
        accountsTable.setGridColor(new Color(180, 180, 180));
        // Show grid
        accountsTable.setShowGrid(true);
        // Set selection background color
        accountsTable.setSelectionBackground(new Color(220, 240, 255));
        // Set selection text color
        accountsTable.setSelectionForeground(Color.BLACK);

        // Set default renderer for all cells
        accountsTable.setDefaultRenderer(Object.class, cellRenderer);

        // Set column widths
        accountsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        accountsTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        accountsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        accountsTable.getColumnModel().getColumn(3).setPreferredWidth(120);

        // Create scroll pane for accounts table
        JScrollPane accountsScrollPane = new JScrollPane(accountsTable);
        // Set border with title for accounts table
        accountsScrollPane.setBorder(BorderFactory.createTitledBorder(
                // Create line border
                BorderFactory.createLineBorder(Color.BLACK, 2),
                // Title text
                "ALL ACCOUNTS",
                // Title position
                TitledBorder.CENTER,
                // Title placement
                TitledBorder.TOP,
                // Title font
                new Font("Segoe UI", Font.BOLD, 16),
                // Title color
                Color.BLACK
        ));
        // Add scroll pane to accounts tab
        accountsTab.add(accountsScrollPane, BorderLayout.CENTER);

        // Create display tab
        JPanel displayTab = new JPanel(new BorderLayout());
        // Set border for display tab
        displayTab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Set background color
        displayTab.setBackground(Color.WHITE);

        // Create display text area
        displayArea = new JTextArea();
        // Set text area font
        displayArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        // Make text area read-only
        displayArea.setEditable(false);
        // Set background color
        displayArea.setBackground(new Color(250, 250, 250));
        // Set text color
        displayArea.setForeground(Color.BLACK);
        // Set border for text area
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create scroll pane for display area
        JScrollPane displayScrollPane = new JScrollPane(displayArea);
        // Set border with title for display area
        displayScrollPane.setBorder(BorderFactory.createTitledBorder(
                // Create line border
                BorderFactory.createLineBorder(Color.BLACK, 1),
                // Title text
                "DETAILS VIEW",
                // Title position
                TitledBorder.CENTER,
                // Title placement
                TitledBorder.TOP,
                // Title font
                new Font("Segoe UI", Font.BOLD, 14),
                // Title color
                Color.BLACK
        ));
        // Add scroll pane to display tab
        displayTab.add(displayScrollPane, BorderLayout.CENTER);

        // Add tabs to tabbed pane
        tabbedPane.addTab("Clients", clientsTab);
        tabbedPane.addTab("Accounts", accountsTab);
        tabbedPane.addTab("Details", displayTab);

        // Add header panel to main panel (north position)
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        // Add button panel to main panel (west position)
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        // Add tabbed pane to main panel (center position)
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);
    }

    // Method to create styled button
    private JButton createStyledButton(String text, Color color) {
        // Create button with text
        JButton button = new JButton(text);
        // Set button font
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        // Set button background color
        button.setBackground(color);
        // Set button text color
        button.setForeground(Color.WHITE);
        // Disable focus painting
        button.setFocusPainted(false);
        // Disable border painting
        button.setBorderPainted(false);
        // Set cursor to hand cursor
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Set preferred size
        button.setPreferredSize(new Dimension(150, 40));

        // Add mouse listener for hover effects
        button.addMouseListener(new MouseAdapter() {
            // When mouse enters button
            @Override
            public void mouseEntered(MouseEvent e) {
                // Darken button color
                button.setBackground(color.darker());
            }

            // When mouse exits button
            @Override
            public void mouseExited(MouseEvent e) {
                // Restore original button color
                button.setBackground(color);
            }
        });

        // Return created button
        return button;
    }

    // Method to handle admin actions
    private void handleAdminAction(String action) {
        // Switch based on action name
        switch (action) {
            case "Add Client":
                // Open add client dialog
                addClient();
                break;
            case "Add Account":
                // Open add account dialog
                addAccount();
                break;
            case "Search Account":
                // Open search account dialog
                searchAccount();
                break;
            case "Search Client":
                // Open search client dialog
                searchClient();
                break;
            case "Remove Client":
                // Open remove client dialog
                removeClient();
                break;
            case "Withdraw":
                // Open withdraw dialog
                withdraw();
                break;
            case "Deposit":
                // Open deposit dialog
                deposit();
                break;
            case "Display All":
                // Display all bank information
                displayAll();
                break;
            case "Logout":
                // Save bank data
                BankFileHandler.saveBankData(bank);
                // Open login frame
                new LoginFrame().setVisible(true);
                // Close current frame
                dispose();
                break;
        }
    }

    // Method to refresh tables
    private void refreshTables() {
        // Update stats label
        statsLabel.setText("Clients: " + bank.getClList().size() +
                " | Accounts: " + bank.getAcList().size() +
                " | Totals: PKR " + String.format("%,.2f", bank.totalAmount()));

        // Clear existing client table rows
        clientsTableModel.setRowCount(0);
        // Loop through all clients
        for (Client client : bank.getClList()) {
            // Create row data
            Object[] row = {
                    client.getId(),  // Client ID
                    client.getPersonDetails().getName(),  // Name
                    client.getPersonDetails().getCNIC(),  // CNIC
                    client.getPersonDetails().getPhoneNo(),  // Phone
                    "PKR " + String.format("%,.2f", client.totalAmount())  // Amount
            };
            // Add row to table
            clientsTableModel.addRow(row);
        }

        // Clear existing account table rows
        accountsTableModel.setRowCount(0);
        // Loop through all accounts
        for (Account account : bank.getAcList()) {
            // Create row data
            Object[] row = {
                    account.getNumber(),  // Account number
                    account.getACholder().getPersonDetails().getName(),  // Client name
                    account.getACholder().getId(),  // Client ID
                    "PKR " + String.format("%,.2f", account.getAmount())  // Balance
            };
            // Add row to table
            accountsTableModel.addRow(row);
        }
    }

    // Method to add new client
    private void addClient() {
        // Create dialog for adding client
        JDialog dialog = new JDialog(this, "Add New Client", true);
        // Set dialog size
        dialog.setSize(400, 350);
        // Center dialog on parent
        dialog.setLocationRelativeTo(this);
        // Set dialog background
        dialog.getContentPane().setBackground(Color.WHITE);

        // Create dialog panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        // Set border for panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Set background color
        panel.setBackground(Color.WHITE);

        // Create title label
        JLabel titleLabel = new JLabel("Add New Client", SwingConstants.CENTER);
        // Set font for title
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        // Set text color
        titleLabel.setForeground(new Color(30, 60, 114));
        // Add title to panel (north position)
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        // Set background color
        formPanel.setBackground(Color.WHITE);

        // Create input fields
        JTextField nameField = new JTextField();
        JTextField cnicField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        // Add form labels and fields
        formPanel.add(createFormLabel("Full Name:"));
        formPanel.add(nameField);
        formPanel.add(createFormLabel("CNIC (13 digits):"));
        formPanel.add(cnicField);
        formPanel.add(createFormLabel("Phone Number:"));
        formPanel.add(phoneField);
        formPanel.add(createFormLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(createFormLabel("Password:"));
        formPanel.add(passwordField);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        // Set background color
        buttonPanel.setBackground(Color.WHITE);
        // Create add button
        JButton addButton = createStyledButton("Add Client", new Color(76, 175, 80));
        // Create cancel button
        JButton cancelButton = createStyledButton("Cancel", new Color(244, 67, 54));
        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Add action listener to add button
        addButton.addActionListener(e -> {
            // Get input values (trim whitespace)
            String name = nameField.getText().trim();
            String cnic = cnicField.getText().trim();
            String phone = phoneField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Check if any field is empty
            if (name.isEmpty() || cnic.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
                // Show error message
                JOptionPane.showMessageDialog(dialog, "All fields are required!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Validate CNIC format (13 digits)
            if (!cnic.matches("\\d{13}")) {
                // Show error message
                JOptionPane.showMessageDialog(dialog, "CNIC must be 13 digits!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Check if CNIC already registered
            if (bank.searchCustomerDetail(cnic) != null) {
                // Show error message
                JOptionPane.showMessageDialog(dialog, "CNIC already registered!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Check if username already taken
            if (bank.isUsernameTaken(username)) {
                // Show error message
                JOptionPane.showMessageDialog(dialog, "Username already taken!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                // Exit method
                return;
            }

            // Create Person object
            Person person = new Person(name, cnic, phone);
            // Add client to bank
            Client client = bank.addClient(person, username, password);
            // Save bank data
            BankFileHandler.saveBankData(bank);

            // Show success message with client ID
            JOptionPane.showMessageDialog(dialog,
                    "Client added successfully!\nClient ID: " + client.getId(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh tables
            refreshTables();
            // Close dialog
            dialog.dispose();
        });

        // Add action listener to cancel button
        cancelButton.addActionListener(e -> dialog.dispose());

        // Add form panel to dialog (center position)
        panel.add(formPanel, BorderLayout.CENTER);
        // Add button panel to dialog (south position)
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Add panel to dialog
        dialog.add(panel);
        // Make dialog visible
        dialog.setVisible(true);
    }

    // Method to create form label
    private JLabel createFormLabel(String text) {
        // Create label with text
        JLabel label = new JLabel(text);
        // Set font for label
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        // Set text color
        label.setForeground(Color.BLACK);
        // Return created label
        return label;
    }

    // Method to add new account
    private void addAccount() {
        // Check if there are any clients
        if (bank.getClList().isEmpty()) {
            // Show error message
            JOptionPane.showMessageDialog(this, "No clients found. Add a client first.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Exit method
            return;
        }

        // Create array for client options
        String[] clientOptions = new String[bank.getClList().size()];
        // Populate options array
        for (int i = 0; i < bank.getClList().size(); i++) {
            // Get client
            Client client = bank.getClList().get(i);
            // Create option string
            clientOptions[i] = client.getId() + " - " + client.getPersonDetails().getName();
        }

        // Show client selection dialog
        String selectedClient = (String) JOptionPane.showInputDialog(this,
                "Select client:", "Add Account", JOptionPane.QUESTION_MESSAGE,
                null, clientOptions, clientOptions[0]);

        // Check if client was selected
        if (selectedClient != null) {
            // Extract client ID from selection
            String clientId = selectedClient.split(" - ")[0];
            // Initialize selected client object
            Client selectedClientObj = null;

            // Find the selected client
            for (Client client : bank.getClList()) {
                // Check if client ID matches
                if (client.getId().equals(clientId)) {
                    // Store found client
                    selectedClientObj = client;
                    // Exit loop
                    break;
                }
            }

            // Check if client found
            if (selectedClientObj != null) {
                // Show amount input dialog
                String amountStr = JOptionPane.showInputDialog(this,
                        "Enter initial deposit amount in PKR for " + selectedClientObj.getPersonDetails().getName() + ":");

                // Check if amount was entered
                if (amountStr != null && !amountStr.trim().isEmpty()) {
                    try {
                        // Parse amount string to float
                        float amount = Float.parseFloat(amountStr);
                        // Check if amount is non-negative
                        if (amount >= 0) {
                            // Create account for selected client
                            Account account = bank.addAccount(selectedClientObj, amount);
                            // Save bank data
                            BankFileHandler.saveBankData(bank);

                            // Show success message
                            JOptionPane.showMessageDialog(this,
                                    "Account created successfully!\n" +
                                            "Account Number: " + account.getNumber() + "\n" +
                                            "Initial Balance: PKR " + String.format("%,.2f", amount) + "\n" +
                                            "Account Holder: " + selectedClientObj.getPersonDetails().getName(),
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                            // Refresh tables
                            refreshTables();
                        } else {
                            // Show error for negative amount
                            JOptionPane.showMessageDialog(this, "Amount must be positive!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        // Show error for invalid amount format
                        JOptionPane.showMessageDialog(this, "Invalid amount!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    // Method to search account
    private void searchAccount() {
        // Show account number input dialog
        String accountNumber = JOptionPane.showInputDialog(this, "Enter account number to search:");

        // Check if account number was entered
        if (accountNumber != null && !accountNumber.trim().isEmpty()) {
            // Search for account in bank
            Account account = bank.searchAccount(accountNumber.trim());
            // Check if account found
            if (account != null) {
                // Display account details
                displayArea.setText(account.toString());
                // Switch to details tab
                ((JTabbedPane)((JPanel)getContentPane().getComponent(0)).getComponent(2)).setSelectedIndex(2);
            } else {
                // Show error message
                JOptionPane.showMessageDialog(this, "Account not found!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to search client
    private void searchClient() {
        // Show CNIC input dialog
        String cnic = JOptionPane.showInputDialog(this, "Enter CNIC to search (13 digits):");

        // Check if CNIC was entered
        if (cnic != null && !cnic.trim().isEmpty()) {
            // Search for client by CNIC
            Client client = bank.searchCustomerDetail(cnic.trim());
            // Check if client found
            if (client != null) {
                // Display client details
                displayArea.setText(client.toString());
                // Switch to details tab
                ((JTabbedPane)((JPanel)getContentPane().getComponent(0)).getComponent(2)).setSelectedIndex(2);
            } else {
                // Show error message
                JOptionPane.showMessageDialog(this, "Client not found!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to remove client
    private void removeClient() {
        // Check if there are any clients
        if (bank.getClList().isEmpty()) {
            // Show error message
            JOptionPane.showMessageDialog(this, "No clients found.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Exit method
            return;
        }

        // Create array for client options
        String[] clientOptions = new String[bank.getClList().size()];
        // Populate options array
        for (int i = 0; i < bank.getClList().size(); i++) {
            // Get client
            Client client = bank.getClList().get(i);
            // Create option string
            clientOptions[i] = client.getId() + " - " + client.getPersonDetails().getName();
        }

        // Show client selection dialog
        String selectedClient = (String) JOptionPane.showInputDialog(this,
                "Select client to remove:", "Remove Client", JOptionPane.QUESTION_MESSAGE,
                null, clientOptions, clientOptions[0]);

        // Check if client was selected
        if (selectedClient != null) {
            // Extract client ID from selection
            String clientId = selectedClient.split(" - ")[0];

            // Show confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove this client?\n" +
                            "All accounts of this client will also be removed.",
                    "Confirm Removal", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            // Check if user confirmed
            if (confirm == JOptionPane.YES_OPTION) {
                // Attempt to remove client
                boolean removed = bank.removeClient(clientId);
                // Check if removal successful
                if (removed) {
                    // Save bank data
                    BankFileHandler.saveBankData(bank);
                    // Show success message
                    JOptionPane.showMessageDialog(this, "Client removed successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Refresh tables
                    refreshTables();
                } else {
                    // Show error message
                    JOptionPane.showMessageDialog(this, "Failed to remove client.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Method to handle withdrawal
    private void withdraw() {
        // Check if there are any accounts
        if (bank.getAcList().isEmpty()) {
            // Show error message
            JOptionPane.showMessageDialog(this, "No accounts found.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Exit method
            return;
        }

        // Create array for account options
        String[] accountOptions = new String[bank.getAcList().size()];
        // Populate options array
        for (int i = 0; i < bank.getAcList().size(); i++) {
            // Get account
            Account account = bank.getAcList().get(i);
            // Create option string
            accountOptions[i] = account.getNumber() + " - " +
                    account.getACholder().getPersonDetails().getName() +
                    " (PKR " + String.format("%,.2f", account.getAmount()) + ")";
        }

        // Show account selection dialog
        String selectedAccount = (String) JOptionPane.showInputDialog(this,
                "Select account to withdraw from:", "Withdraw Money", JOptionPane.QUESTION_MESSAGE,
                null, accountOptions, accountOptions[0]);

        // Check if account was selected
        if (selectedAccount != null) {
            // Extract account number from selection
            String accountNumber = selectedAccount.split(" - ")[0];
            // Search for account in bank
            Account account = bank.searchAccount(accountNumber);

            // Check if account found
            if (account != null) {
                // Show amount input dialog with current balance
                String amountStr = JOptionPane.showInputDialog(this,
                        "Enter withdrawal amount in PKR (Available: PKR " +
                                String.format("%,.2f", account.getAmount()) + "):");

                // Check if amount was entered
                if (amountStr != null && !amountStr.trim().isEmpty()) {
                    try {
                        // Parse amount string to float
                        float amount = Float.parseFloat(amountStr);
                        // Check if amount is positive
                        if (amount > 0) {
                            // Attempt withdrawal
                            float result = account.withdraw(amount);
                            // Check if withdrawal successful
                            if (result != -1) {
                                // Save bank data
                                BankFileHandler.saveBankData(bank);
                                // Show success message
                                JOptionPane.showMessageDialog(this,
                                        "Withdrawal successful!\n" +
                                                "Remaining Balance: PKR " + String.format("%,.2f", result),
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                // Refresh tables
                                refreshTables();
                            } else {
                                // Show error for insufficient balance
                                JOptionPane.showMessageDialog(this,
                                        "Withdrawal failed! Check account balance.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Show error for non-positive amount
                            JOptionPane.showMessageDialog(this, "Amount must be positive!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        // Show error for invalid amount format
                        JOptionPane.showMessageDialog(this, "Invalid amount!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    // Method to handle deposit
    private void deposit() {
        // Check if there are any accounts
        if (bank.getAcList().isEmpty()) {
            // Show error message
            JOptionPane.showMessageDialog(this, "No accounts found.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Exit method
            return;
        }

        // Create array for account options
        String[] accountOptions = new String[bank.getAcList().size()];
        // Populate options array
        for (int i = 0; i < bank.getAcList().size(); i++) {
            // Get account
            Account account = bank.getAcList().get(i);
            // Create option string
            accountOptions[i] = account.getNumber() + " - " +
                    account.getACholder().getPersonDetails().getName() +
                    " (PKR " + String.format("%,.2f", account.getAmount()) + ")";
        }

        // Show account selection dialog
        String selectedAccount = (String) JOptionPane.showInputDialog(this,
                "Select account to deposit to:", "Deposit Money", JOptionPane.QUESTION_MESSAGE,
                null, accountOptions, accountOptions[0]);

        // Check if account was selected
        if (selectedAccount != null) {
            // Extract account number from selection
            String accountNumber = selectedAccount.split(" - ")[0];
            // Search for account in bank
            Account account = bank.searchAccount(accountNumber);

            // Check if account found
            if (account != null) {
                // Show amount input dialog
                String amountStr = JOptionPane.showInputDialog(this,
                        "Enter deposit amount in PKR:");

                // Check if amount was entered
                if (amountStr != null && !amountStr.trim().isEmpty()) {
                    try {
                        // Parse amount string to float
                        float amount = Float.parseFloat(amountStr);
                        // Check if amount is positive
                        if (amount > 0) {
                            // Attempt deposit
                            float result = account.deposit(amount);
                            // Check if deposit successful
                            if (result != -1) {
                                // Save bank data
                                BankFileHandler.saveBankData(bank);
                                // Show success message
                                JOptionPane.showMessageDialog(this,
                                        "Deposit successful!\n" +
                                                "New Balance: PKR " + String.format("%,.2f", result),
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                // Refresh tables
                                refreshTables();
                            } else {
                                // Show error message
                                JOptionPane.showMessageDialog(this, "Deposit failed!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Show error for non-positive amount
                            JOptionPane.showMessageDialog(this, "Amount must be positive!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        // Show error for invalid amount format
                        JOptionPane.showMessageDialog(this, "Invalid amount!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    // Method to display all bank information
    private void displayAll() {
        // Create StringBuilder for building string
        StringBuilder sb = new StringBuilder();
        // Add bank overview header
        sb.append("=== BANK OVERVIEW ===\n");
        // Add bank information
        sb.append(bank.toString()).append("\n\n");

        // Add all clients header
        sb.append("=== ALL CLIENTS ===\n");
        // Loop through all clients
        for (Client client : bank.getClList()) {
            // Add client information
            sb.append(client.toString()).append("\n");
        }

        // Display information in text area
        displayArea.setText(sb.toString());
        // Get main panel
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        // Get tabbed pane
        JTabbedPane tabbedPane = (JTabbedPane) mainPanel.getComponent(2);
        // Switch to details tab
        tabbedPane.setSelectedIndex(2);
    }
}

// ====================== MAIN CLASS ======================
// Main class to start the application
public class Main {
    // Main method - entry point of the application
    public static void main(String[] args) {
        // Try to set system look and feel
        try {
            // Set system look and feel for native appearance
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Set default UI properties for better visibility
            // Set font for option pane messages
            UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 12));
            // Set font for option pane buttons
            UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
            // Set font for text fields
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 12));
            // Set font for labels
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 12));
            // Set font for buttons
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12));

        } catch (Exception e) {
            // Print stack trace if look and feel setting fails
            e.printStackTrace();
        }

        // Start the application with login screen
        // Use SwingUtilities.invokeLater for thread safety
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}