package com.shopping.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingConsole extends JFrame{
	private final JTextArea textArea;
	private final JTextField inputField;
	private final JButton submitButton;
	private String userInput = null;
	
	
	public SwingConsole(String title) {
		super(title);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		
		inputField = new JTextField();
		submitButton = new JButton("Submit");
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(inputField, BorderLayout.CENTER);
		inputPanel.add(submitButton, BorderLayout.EAST);
		add(inputPanel, BorderLayout.SOUTH);
		
		ActionListener submitAction = e -> {
			synchronized (SwingConsole.this) {
				userInput = inputField.getText();
				textArea.append(">> " + userInput + "\n");
				inputField.setText("");
				autoScrollToBottom();
				SwingConsole.this.notifyAll();
			}
		};
		
		submitButton.addActionListener(submitAction);
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submitAction.actionPerformed(null);
                }
            }
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {}
		});
		
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void println(String message) {
		textArea.append(message + "\n");
		autoScrollToBottom();
	}
	
	public String getInput() {
		synchronized (this) {
			while (userInput == null) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String input = userInput;
			userInput = null;
			return input; 
		}
	}
	
	public void printAsciiArt(String art) {
        textArea.append(art + "\n");
        autoScrollToBottom();
    }

    public void printSeparator() {
        textArea.append("---------------------------------------------------------\n");
        autoScrollToBottom();
    }
	
	public void clear() {
		textArea.setText("");
	}
	
	private void autoScrollToBottom() {
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}
