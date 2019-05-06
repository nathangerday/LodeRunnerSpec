package utils;

import java.util.Scanner;

import data.Command;
import services.Engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class CommandManager extends Thread{
	private Scanner scanner = new Scanner(System.in);
	private Thread mainThread;
	private Command currentCommand;

	public CommandManager(Thread mainThread){
		this.mainThread = mainThread;
		this.currentCommand = Command.NONE;
		JTextField component = new JTextField();
		component.addKeyListener(new MyKeyListener());

		JFrame f = new JFrame();

		f.add(component);
		f.setSize(300, 300);
	    f.setVisible(true);
		this.start();
	}

	public Command receiveCurrentCommand(){
		Command res = this.currentCommand;
		this.currentCommand = Command.NONE;
		return res;
    }
    
    public Command peekCurrentCommand(){
        return this.currentCommand;
    }

	public void run(){
	
	}

    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent evt) {
            // if (evt.getKeyChar() == 'a') {
            //     System.out.println("Check for key characters: " + evt.getKeyChar());
            // }
            switch(evt.getKeyChar()){
                case 'q': 
                    currentCommand = Command.MOVEL;
                    break;
                case 'd':
                    currentCommand =  Command.MOVER;
                    break;
                case 'z':
                    currentCommand =  Command.MOVEU;
                    break;
                case 's':
                    currentCommand =  Command.MOVED;
                    break;
                case 'a':
                    currentCommand =  Command.DIGL;
                    break;
                case 'e':
                    currentCommand =  Command.DIGR;
					break;
				case ' ':
					currentCommand = Command.USEITEM;
					break;
            }
        }
    }
}
