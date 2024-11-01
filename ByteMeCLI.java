import java.util.*;

// Main Class to Launch CLI
public class ByteMeCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        CustomerManager customerManager = new CustomerManager();

        // Adding initial menu items to the canteen
        initializeMenu(admin);

        while (true) {
            System.out.println(
                    "Welcome to Byte Me! Choose an option:\n1. Admin Interface\n2. Customer Interface\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> adminInterface(admin, scanner);
                case 2 -> customerInterface(customerManager, admin, scanner);
                case 3 -> {
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeMenu(Admin admin) {
        admin.addMenuItem(new FoodItem("Veg Sandwich", 30.0, "Snacks", true));
        admin.addMenuItem(new FoodItem("Cheese Burger", 50.0, "Snacks", true));
        admin.addMenuItem(new FoodItem("Pasta", 70.0, "Meals", true));
        admin.addMenuItem(new FoodItem("Fried Rice", 60.0, "Meals", true));
        admin.addMenuItem(new FoodItem("Veg Thali", 120.0, "Meals", true));
        admin.addMenuItem(new FoodItem("Grilled Chicken", 150.0, "Meals", true));
        admin.addMenuItem(new FoodItem("Paneer Tikka", 100.0, "Snacks", true));
        admin.addMenuItem(new FoodItem("Masala Dosa", 40.0, "Meals", true));
        admin.addMenuItem(new FoodItem("Cold Coffee", 30.0, "Beverages", true));
        admin.addMenuItem(new FoodItem("Chai", 10.0, "Beverages", true));
        admin.addMenuItem(new FoodItem("Lemonade", 20.0, "Beverages", true));
        admin.addMenuItem(new FoodItem("Chocolate Shake", 50.0, "Beverages", true));
        admin.addMenuItem(new FoodItem("Veg Momos", 35.0, "Snacks", true));
        admin.addMenuItem(new FoodItem("Chicken Wrap", 80.0, "Snacks", true));
        admin.addMenuItem(new FoodItem("Spring Roll", 45.0, "Snacks", true));
        System.out.println("Initial menu items have been added to the canteen.");
    }

    // ByteMeCLI Class: Add interface option for order status update in
    // adminInterface
    private static void adminInterface(Admin admin, Scanner scanner) {
        while (true) {
            System.out.println(
                    "\nAdmin Options:\n1. Manage Menu\n2. Process Orders\n3. Generate Sales Report\n4. Update Order Status\n5. View Feedback\n6. View Complaints\n7. View Refund Requests\n8. Go Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageMenu(admin, scanner);
                case 2 -> processOrders(admin);
                case 3 -> admin.generateDailySalesReport();
                case 4 -> admin.updateOrderStatus(scanner);
                case 5 -> admin.viewFeedback();
                case 6 -> admin.viewComplaints();
                case 7 -> admin.viewRefundRequests();
                case 8 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageMenu(Admin admin, Scanner scanner) {
        while (true) {
            System.out.println(
                    "\nMenu Management:\n1. Add Item\n2. Update Item\n3. Remove Item\n4. View Menu\n5. Go Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    admin.addMenuItem(new FoodItem(name, price, category, true));
                    System.out.println("Item added.");
                }
                case 2 -> {
                    System.out.print("Enter item name to update: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Is it available? (true/false): ");
                    boolean available = scanner.nextBoolean();
                    scanner.nextLine();
                    admin.updateMenuItem(itemName, price, available);
                }
                case 3 -> {
                    System.out.print("Enter item name to remove: ");
                    String itemName = scanner.nextLine();
                    admin.removeMenuItem(itemName);
                }
                case 4 -> admin.getMenu().printMenu();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void processOrders(Admin admin) {
        admin.processOrder();
    }
    private static void customerInterface(CustomerManager customerManager, Admin admin, Scanner scanner) {
        System.out.print("\nEnter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = customerManager.getCustomer(customerId);
        customer.initializeCart(admin.getMenu());
    
        while (true) {
            System.out.println("\nCustomer Options:" +
                    "\n1. View Menu" +
                    "\n2. Add to Cart" +
                    "\n3. Remove from Cart" +
                    "\n4. Show Cart" +
                    "\n5. Place Order" +
                    "\n6. View Order Status" +
                    "\n7. View Order History" +
                    "\n8. Become VIP" +
                    "\n9. Cancel Order" +
                    "\n10. Go Back" +
                    "\n11. Update Item Quantity in Cart");
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1 -> viewMenuOptions(admin.getMenu(), scanner);
                case 2 -> addToCart(customer, scanner);
                case 3 -> removeFromCart(customer, scanner);
                case 4 -> customer.getCart().showCart();
                case 5 -> placeOrder(customer, admin, scanner);
                case 6 -> customer.viewOrderStatus();
                case 7 -> customer.viewOrderHistory(customer.getOrderHistory());
                case 8 -> customerManager.upgradeToVIP(customer);
                case 9 -> cancelOrder(customer, admin, scanner);
                case 10 -> { return; }
                case 11 -> {
                    System.out.print("Enter item name to update quantity: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    customer.getCart().updateItemQuantity(itemName, quantity);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    private static void addToCart(Customer customer, Scanner scanner) {
        System.out.print("Enter item name to add: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        customer.getCart().addItem(itemName, quantity);
    }
    
    private static void removeFromCart(Customer customer, Scanner scanner) {
        System.out.print("Enter item name to remove: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter quantity to remove: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        customer.getCart().removeItem(itemName, quantity);
    }
    
    // Update the placeOrder method to include cart items and delivery information
    private static void placeOrder(Customer customer, Admin admin, Scanner scanner) {
        if (customer.getCart().isEmpty()) {
            System.out.println("Cart is empty. Please add items before placing an order.");
            return;
        }
    
        System.out.println("\nCurrent Cart:");
        customer.getCart().showCart();

        System.out.print("\nDo you have any special requests? If yes, please enter it now (or press Enter to skip): ");
        String specialRequest = scanner.nextLine();
        
        System.out.println("\nPlease enter delivery information:");
        System.out.print("Enter Room No./Address for delivery: ");
        String deliveryAddress = scanner.nextLine();
    
        Order order = new Order(
            UUID.randomUUID().toString(), 
            customer.getCustomerId(), 
            customer.isVip(),
            deliveryAddress,
            specialRequest
        );
    
        // Add items from cart to order
        Map<String, Integer> cartItems = customer.getCart().getItems();
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            order.addItem(entry.getKey(), entry.getValue());
        }
    
        admin.addOrder(order);
        customer.addOrderToHistory(order);
        customer.getCart().clearCart();
        System.out.println("Order placed successfully!");
    }
    
    private static void viewMenuOptions(Menu menu, Scanner scanner) {
        while (true) {
            System.out.println(
                    "\nMenu Options:\n1. View All\n2. Search by Name\n3. Filter by Category\n4. Sort by Price\n5. Go Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> menu.printMenu();
                case 2 -> {
                    System.out.print("Enter item name or keyword to search: ");
                    String keyword = scanner.nextLine();
                    menu.searchByName(keyword);
                }
                case 3 -> {
                    System.out.print("Enter category to filter by (Snacks, Meals, Beverages): ");
                    String category = scanner.nextLine();
                    menu.filterByCategory(category);
                }
                case 4 -> {
                    System.out.print("Sort by price (asc/desc): ");
                    String order = scanner.nextLine();
                    menu.sortByPrice(order.equalsIgnoreCase("asc"));
                }
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }


    private static void cancelOrder(Customer customer, Admin admin, Scanner scanner) {
        System.out.print("Enter Order ID to cancel: ");
        String orderId = scanner.nextLine();
        customer.cancelOrder(orderId, admin);
    }
}

// FoodItem Class
class FoodItem {
    private String name;
    private double price;
    private String category;
    private boolean available;
    private List<FoodItemReview> reviews = new ArrayList<>();

    public FoodItem(String name, double price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void addReview(FoodItemReview review) {
        reviews.add(review);
        System.out.println("Review added.");
    }

    public void viewReviews() {
        System.out.println("\nReviews for " + name + ":");
        for (FoodItemReview review : reviews) {
            System.out.println(review);
        }
    }
}

class FoodItemReview {
    private String customerId;
    private String reviewText;
    private int rating;

    public FoodItemReview(String customerId, String reviewText, int rating) {
        this.customerId = customerId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Rating: " + rating + "/5, Review: " + reviewText;
    }
}

// Menu Class with added functionalities
// Menu Class with added functionalities
class Menu {
    private Map<String, FoodItem> items = new TreeMap<>();

    public void addItem(FoodItem item) {
        items.put(item.getName(), item);
    }

    public Map<String, FoodItem> getItems() {
        return items;
    } // Add this method

    public void searchByName(String keyword) {
        System.out.println("\nSearch Results:");
        for (FoodItem item : items.values()) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                printItem(item);
            }
        }
    }

    public void filterByCategory(String category) {
        System.out.println("\n" + category + " Items:");
        for (FoodItem item : items.values()) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                printItem(item);
            }
        }
    }

    public void sortByPrice(boolean ascending) {
        System.out.println("\nMenu sorted by price:");
        items.values().stream()
                .sorted((a, b) -> ascending ? Double.compare(a.getPrice(), b.getPrice())
                        : Double.compare(b.getPrice(), a.getPrice()))
                .forEach(this::printItem);
    }

    public void printMenu() {
        System.out.println("\nMenu:");
        for (FoodItem item : items.values()) {
            printItem(item);
        }
    }

    private void printItem(FoodItem item) {
        System.out.printf("Name: %s, Price: %.2f, Category: %s, Available: %b%n",
                item.getName(), item.getPrice(), item.getCategory(), item.isAvailable());
    }
}
class Cart {
    private Map<String, Integer> items = new HashMap<>();
    private Menu menu;

    public Cart(Menu menu) {
        this.menu = menu;
    }

    public void addItem(String itemName, int quantity) {
        if (menu.getItems().containsKey(itemName)) {
            items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
            System.out.println(quantity + " " + itemName + "(s) added to cart.");
        } else {
            System.out.println("Item not found in menu.");
        }
    }

    public void updateItemQuantity(String itemName, int newQuantity) {
        if (items.containsKey(itemName)) {
            if (newQuantity > 0) {
                items.put(itemName, newQuantity);
                System.out.println(itemName + " quantity updated to " + newQuantity);
            } else {
                items.remove(itemName);
                System.out.println(itemName + " removed from cart.");
            }
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public void removeItem(String itemName, int quantity) {
        if (items.containsKey(itemName)) {
            int currentQty = items.get(itemName);
            if (currentQty <= quantity) {
                items.remove(itemName);
            } else {
                items.put(itemName, currentQty - quantity);
            }
            System.out.println(quantity + " " + itemName + "(s) removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\nCart Contents:");
        double total = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double price = menu.getItems().get(itemName).getPrice();
            double itemTotal = price * quantity;
            total += itemTotal;
            System.out.printf("%s x%d - ₹%.2f (₹%.2f each)%n", 
                itemName, quantity, itemTotal, price);
        }
        System.out.printf("\nTotal Bill: ₹%.2f%n", total);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<String, Integer> getItems() {
        return new HashMap<>(items);
    }

    public void clearCart() {
        items.clear();
    }
}

// Order Class
class Order {
    private String orderId;
    private String customerId;
    private boolean vip;
    private Map<String, Integer> items = new LinkedHashMap<>();
    private boolean canceled = false;
    private String status; // e.g., "Pending", "Canceled", "Delivered", etc.
    private String feedback; // Stores customer feedback
    private String complaint; // Stores customer complaints
    private String refundRequest; // Stores refund requests
    private boolean refundInitiated = false;
    private String deliveryAddress;
    private String specialRequest;

    public Order(String orderId, String customerId, boolean vip, String deliveryAddress,String specialRequest ) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.vip = vip;
        this.deliveryAddress = deliveryAddress;
        this.status = "Pending";
        this.specialRequest = specialRequest;
    }

    public String getOrderId() {
        return orderId;
    }

    public boolean isVip() {
        return vip;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getRefundRequest() {
        return refundRequest;
    }

    public boolean isRefundInitiated() {
        return refundInitiated;
    }

    public void addFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void addComplaint(String complaint) {
        this.complaint = complaint;
    }

    public void requestRefund(String refundRequest) {
        this.refundRequest = refundRequest;
    }
    public void cancelOrder() {
        // Assuming there’s a status field in Order to track its current state
        this.status = "Canceled"; // or use an enum for statuses if applicable
    }
    

    public void initiateRefund() {
        this.refundInitiated = true;
    }
    public void addItem(String itemName, int quantity) {
        // Assuming there’s a Map or List in Order to store items and quantities
        items.put(itemName, quantity); // or items.add(new Item(itemName, quantity));
    }
    
    public void updateStatus(String newStatus) {
        status = newStatus;
        System.out.println("Order status updated to: " + status);
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void printOrderDetails() {
        if (!canceled) {
            System.out.println("\nOrder ID: " + orderId +
                               "\nCustomer ID: " + customerId +
                               "\nVIP: " + vip +
                               "\nDelivery Address: " + deliveryAddress +
                               "\nSpecial Request: " + (specialRequest != null ? specialRequest : "None"));
            System.out.println("Status: " + status);
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                System.out.println(entry.getKey() + " x" + entry.getValue());
            }
        } else {
            System.out.println("Order " + orderId + " has been canceled.");
        }
    }
}
class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.isVip() && !o2.isVip()) return -1;
        if (!o1.isVip() && o2.isVip()) return 1;
        return 0; // Retain FCFS for same VIP status
    }
}
// Admin Class
class Admin {
    private Menu menu = new Menu();
    private PriorityQueue<Order> orderQueue = new PriorityQueue<>(new OrderComparator());
    private List<Order> activeOrders = new ArrayList<>(); // Track active orders
    private List<Order> salesReport = new ArrayList<>(); // Track delivered orders for the sales report
    // Add methods for viewing feedback, complaints, and refund requests

    public void viewFeedback() {
        System.out.println("Viewing feedback from customers:");
        for (Order order : salesReport) {
            if (order.getFeedback() != null) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Feedback: " + order.getFeedback());
            }
        }
    }

    public void viewComplaints() {
        System.out.println("Viewing complaints from customers:");
        for (Order order : salesReport) {
            if (order.getComplaint() != null) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Complaint: " + order.getComplaint());
            }
        }
    }

    public void viewRefundRequests() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Viewing refund requests from customers:");
        for (Order order : salesReport) {
            if (order.getRefundRequest() != null) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Refund Request: " + order.getRefundRequest());

                System.out.println("Would you like to initiate the refund for this order? (yes/no)");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    order.initiateRefund();
                    System.out.println("Refund initiated for Order ID: " + order.getOrderId());
                } else {
                    System.out.println("Refund not initiated for Order ID: " + order.getOrderId());
                }
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public void addMenuItem(FoodItem item) {
        menu.addItem(item);
    }

    public void updateMenuItem(String name, double price, boolean available) {
        FoodItem item = menu.getItems().get(name);
        if (item != null) {
            item.setPrice(price);
            item.setAvailable(available);
            System.out.println("Item updated.");
        } else {
            System.out.println("Item not found.");
        }
    }

    
    

    public void addOrder(Order order) {
        orderQueue.add(order);
        activeOrders.add(order); // Also add to active orders list
    }

    public boolean cancelOrder(String orderId) {
        for (Order order : activeOrders) {
            if (order.getOrderId().equals(orderId)) {
                order.cancelOrder();
                orderQueue.remove(order); // Remove from processing queue
                return true;
            }
        }
        return false;
    }

    public void processOrder() {
        if (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll(); // Get next order
            System.out.println("Processing order...");
            order.printOrderDetails();
        } else {
            System.out.println("No pending orders.");
        }
    }

    public void removeMenuItem(String name) {
        menu.getItems().remove(name);
        for (Order order : activeOrders) {
            if (order.getItems().containsKey(name)) {
                order.updateStatus("Denied");
            }
        }
        System.out.println("Item removed.");
    }

    // Updated updateOrderStatus method to check activeOrders
    public void updateOrderStatus(Scanner scanner) {
        System.out.print("Enter Order ID to update status: ");
        String orderId = scanner.nextLine();

        for (Order order : activeOrders) { // Search active orders
            if (order.getOrderId().equals(orderId)) {
                System.out.println("Choose new status:\n1. Prepared\n2. Packed\n3. Out for Delivery\n4. Delivered");
                int statusChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                String newStatus = switch (statusChoice) {
                    case 1 -> "Prepared";
                    case 2 -> "Packed";
                    case 3 -> "Out for Delivery";
                    case 4 -> "Delivered";
                    default -> "Unknown";
                };

                if (!newStatus.equals("Unknown")) {
                    order.updateStatus(newStatus);

                    // If delivered, remove from activeOrders and add to salesReport
                    if (newStatus.equals("Delivered")) {
                        activeOrders.remove(order);
                        salesReport.add(order);
                        System.out.println("Order marked as delivered and moved to sales report.");
                    }
                } else {
                    System.out.println("Invalid status choice.");
                }
                return;
            }
        }
        System.out.println("Order ID not found.");
    }

    // Generate daily sales report
    public void generateDailySalesReport() {
        System.out.println("Generating sales report...");
        System.out.println("Total orders delivered: " + salesReport.size());
        for (Order order : salesReport) {
            order.printOrderDetails();
        }
    }
}

// Customer Class
class Customer {
    private String customerId;
    private Admin admin;
    private boolean vip;
    private Cart cart;
    private List<Order> orderHistory = new ArrayList<>();

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public void viewOrderStatus() {
        System.out.println("Order status is being processed.");
    }
    public List<Order> getOrderHistory() {
        return this.orderHistory; // or however order history is stored in Customer
    }

    public void initializeCart(Menu menu) {
        this.cart = new Cart(menu);
    }

    public Cart getCart() {
        return cart;
    }
    

    public void viewOrderHistory(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your Order History:");

        for (Order order : orders) {
            order.printOrderDetails();
            if (order.getStatus().equals("Delivered")) {
                System.out.println(
                        "Options for delivered order:\n1. Give Feedback\n2. File Complaint\n3. Request Refund");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter your feedback: ");
                        String feedback = scanner.nextLine();
                        order.addFeedback(feedback);
                        System.out.println("Feedback submitted.");
                        break;
                    case 2:
                        System.out.print("Enter your complaint: ");
                        String complaint = scanner.nextLine();
                        order.addComplaint(complaint);
                        System.out.println("Complaint submitted.");
                        break;
                    case 3:
                        System.out.print("Enter your reason for refund request: ");
                        String refundRequest = scanner.nextLine();
                        order.requestRefund(refundRequest);
                        System.out.println("Refund request submitted.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }

    public void cancelOrder(String orderId, Admin admin) {
        for (Order order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                order.cancelOrder();
                admin.cancelOrder(orderId);
                return;
            }
        }
        System.out.println("Order ID not found.");
    }
}

// CustomerManager Class
class CustomerManager {
    private Map<String, Customer> customers = new HashMap<>();

    public Customer getCustomer(String customerId) {
        return customers.computeIfAbsent(customerId, Customer::new);
    }

    public void upgradeToVIP(Customer customer) {
        if (!customer.isVip()) {
            customer.setVip(true);
            System.out.println("Customer upgraded to VIP.");
        } else {
            System.out.println("Customer is already a VIP.");
        }
    }
}
