Welcome to Byte Me!, a CLI-based food ordering system developed specifically for college canteens. This project enables students to conveniently order food from their hostel rooms while allowing canteen staff to efficiently manage orders, menu items, and sales reports.

Table of Contents
Introduction
Features
Admin Interface
Customer Interface
Data Management
User Flow
Admin Operations
Customer Operations
Technologies Used
Getting Started
Introduction
As part of this project, we implemented Byte Me!, a command-line food ordering system tailored for our college canteen. This project aims to:

Allow students to browse the canteen menu, place orders, and track deliveries without leaving their rooms.
Enable canteen staff to manage menu items and efficiently process orders.
Maintain order histories to help students recall their late-night meal choices.
Utilize Java collections to organize and sort data for optimized performance.
Features
Admin Interface
Menu Management

Add new items: Add food items to the menu with details like price, category, and availability.
Update existing items: Modify details (price, availability) of existing menu items.
Remove items: Discontinue items from the menu. Pending orders containing removed items will be updated to 'denied'.
Order Management

View pending orders: Orders are handled in the order they are received, ensuring fair processing.
Update order status: Track and update the status of orders (e.g., preparing, out for delivery, completed).
Process refunds: Issue refunds for canceled or problematic orders.
Handle special requests: Customers can specify requests (e.g., "extra spicy" or "no onions").
Order Priority: VIP customer orders are processed with higher priority over regular orders.
Report Generation

Daily sales report: Generate a daily report covering total sales, most popular items, and total orders.
Customer Interface
Customer Types

VIP: VIP orders receive priority processing. VIP status can be purchased for a specified amount.
Regular: Orders are processed in sequence, but only after all VIP orders.
Browse Menu

View all items: Display a full menu with item prices and availability.
Search functionality: Search menu items by name or keyword.
Filter by category: View items based on categories (e.g., snacks, beverages, meals).
Sort by price: Sort items by price in ascending or descending order.
Cart Operations

Add items: Add multiple items to the cart with specified quantities.
Modify quantities: Adjust quantities of items in the cart.
Remove items: Remove unwanted items from the cart.
View total: View the total cost of items before checkout.
Checkout process: Complete the order with payment and delivery details.
Order Tracking

View order status: Track real-time order status from 'order received' to 'delivered' or 'denied'.
Cancel order: Cancel orders before they are processed.
Order history: View past orders and re-order previously ordered meals.
Item Reviews

Provide review: Leave reviews for ordered items.
View reviews: See reviews for items left by other customers.
Data Management
The project leverages Java collections to manage various data types:

Menu of Food Items: Collection to store and manage the menu items.
Current Orders: Collection for tracking and processing active orders.
Order History: Collection for tracking each customer's past orders.
Collections such as ArrayList, TreeMap, TreeSet, and PriorityQueue are utilized to ensure efficient data handling.

User Flow
Admin Operations
Menu Management: Add, update, or remove items.
Order Management: View and process pending orders, handle special requests, and prioritize VIP orders.
Report Generation: Generate daily sales reports.
Customer Operations
Menu Browsing: View, search, filter, and sort items.
Cart Management: Add, adjust quantities, remove items, and proceed to checkout.
Order Tracking: Track status, cancel orders, and view order history.
Reviewing Items: Leave reviews for items and view reviews from others.
Technologies Used
Java: Core language for implementing CLI and managing collections.
Java Collections Framework: Utilized for efficient data storage and sorting.
