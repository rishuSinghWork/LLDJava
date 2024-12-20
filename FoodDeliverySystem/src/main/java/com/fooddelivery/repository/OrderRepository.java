package com.fooddelivery.repository;

import com.fooddelivery.models.MenuItem;
import com.fooddelivery.models.Order;
import com.fooddelivery.models.Customer;
import com.fooddelivery.models.Restaurant;
import com.fooddelivery.models.DeliveryPartner;
import com.fooddelivery.server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
	// add order 
	
	private final CustomerRepository customerRepository;
	private final RestaurantRepository restaurantRepository;
	private final DeliveryPartnerRepository deliveryPartnerRepository;

	public OrderRepository(CustomerRepository customerRepository,
	                       RestaurantRepository restaurantRepository,
	                       DeliveryPartnerRepository deliveryPartnerRepository) {
	    this.customerRepository = customerRepository;
	    this.restaurantRepository = restaurantRepository;
	    this.deliveryPartnerRepository = deliveryPartnerRepository;
	}

	public void addOrder(Order order) throws SQLException {
	    String orderQuery = "INSERT INTO orders (customer_id, restaurant_id, delivery_partner_id, status) VALUES (?, ?, ?, ?)";
	    String orderItemQuery = "INSERT INTO order_items (order_id, menu_item_id, quantity) VALUES (?, ?, ?)";

	    try (Connection connection = DatabaseConnection.getConnection()) {
	        connection.setAutoCommit(false);

	        try (PreparedStatement orderStatement = connection.prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            orderStatement.setInt(1, order.getCustomer().getId());
	            orderStatement.setInt(2, order.getRestaurant().getId());
	            orderStatement.setObject(3, order.getDeliveryPartner() != null ? order.getDeliveryPartner().getId() : null);
	            orderStatement.setString(4, order.getStatus());
	            orderStatement.executeUpdate();

	            // Retrieve the generated order ID
	            int orderId = 0;
	            try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    orderId = generatedKeys.getInt(1);
	                }
	            }

	            // Insert order items
	            try (PreparedStatement itemStatement = connection.prepareStatement(orderItemQuery)) {
	                for (MenuItem item : order.getItems()) {
	                    itemStatement.setInt(1, orderId);
	                    itemStatement.setInt(2, item.getId());
	                    itemStatement.setInt(3, item.getQuantity());
	                    itemStatement.addBatch();
	                }
	                itemStatement.executeBatch();
	            }
	            connection.commit();
	        } catch (SQLException e) {
	            connection.rollback();
	            throw e;
	        }
	    }
	}

	// get order by id 
	public Order getOrderById(int orderId) throws SQLException {
		String orderQuery = "SELECT * FROM orders WHERE id = ?";
		String orderItemsQuery = "SELECT oi.menu_item_id, oi.quantity, mi.name, mi.price, mi.available " +
				"FROM order_items oi " + 
				"JOIN menu_items mi ON oi.menu_item_id = mi.id " +
				"WHERE oi.order_id = ?";
		try (Connection connection = DatabaseConnection.getConnection()){
			try (PreparedStatement orderStatement = connection.prepareStatement(orderQuery)){
				orderStatement.setInt(1, orderId);
				try (ResultSet orderResult = orderStatement.executeQuery()){
					if (orderResult.next()) {
						int customerId = orderResult.getInt("customer_id");
						int restaurantId = orderResult.getInt("restaurant_id");
						int deliveryPartnerId = orderResult.getInt("delivery_partner_id");
						
						Customer customer = customerRepository.getCustomerById(customerId);
						Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);
						DeliveryPartner deliveryPartner = deliveryPartnerRepository.getDeliveryPartnerById(deliveryPartnerId);
						
						List<MenuItem> items = new ArrayList<>();
						try (PreparedStatement orderItemsStatement = connection.prepareStatement(orderItemsQuery)){
							orderItemsStatement.setInt(1, orderId);
							try (ResultSet itemsResult = orderItemsStatement.executeQuery()){
								while (itemsResult.next()) {
									MenuItem item = new MenuItem(
											itemsResult.getInt("menu_item_id"),
											itemsResult.getString("name"),
											itemsResult.getDouble("price"),
											itemsResult.getBoolean("available"),
											itemsResult.getInt("quantity")
											);
									item.setQuantity(itemsResult.getInt("quantity"));
									items.add(item);
								}
							}
						}
						return new Order(
	                            orderResult.getInt("id"),
	                            customer,
	                            restaurant,
	                            deliveryPartner,
	                            items,
	                            orderResult.getString("status")
	                    );
					}
				}
			}
		}
		return null;
	}
	
	
	
	// get all orders 
	public List<Order> getAllOrders() throws SQLException {
        String query = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = getOrderById(resultSet.getInt("id"));
                if (order != null) {
                    orders.add(order);
                }
            }
        }
        return orders;
    }
	
	// update order status 
	public void updateOrderStatus(int orderId, String status) throws SQLException {
		String query = "UPDATE orders SET status = ? WHERE id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, status);
			statement.setInt(2, orderId);
			statement.executeUpdate(); 
		}
	}
}
