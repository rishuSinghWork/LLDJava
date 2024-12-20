package com.hotelbooking.server;

import com.hotelbooking.models.Customer;
import com.hotelbooking.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HotelServerTest {
	private HotelService mockHotelService;
	private HotelServer hotelServer;
	@BeforeEach
	public void setUp() {
		mockHotelService = mock(HotelService.class);
		hotelServer = new HotelServer(mockHotelService);
	}
	
	@Test
	public void testAddCustomer() {
		String input = "1\nAlice\nalice@example.com\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		hotelServer.addCustomer(new Scanner(System.in));
		
		ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
		verify(mockHotelService, times(1)).addCustomer(captor.capture());
		
		Customer capturedCustomer = captor.getValue();
        assertEquals("Alice", capturedCustomer.getName());
        assertEquals("alice@example.com", capturedCustomer.getEmail());
        assertEquals(1, capturedCustomer.getCustomerId());
	}
}
