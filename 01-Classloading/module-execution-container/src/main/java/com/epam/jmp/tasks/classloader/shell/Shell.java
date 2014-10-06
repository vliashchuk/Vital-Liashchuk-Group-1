package com.epam.jmp.tasks.classloader.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.jmp.tasks.classloader.App;

public class Shell {
    
	public static void main(String[] args) {
		App app;
		if(args.length==1){
			app = new App(args[0]);
		} else {
			app = new App();
		}
		 
		Commands commands = new Commands(app);
		commands.getCommand("help").execute(new String[0]);
		execCommandLoop(commands);
	}

    private static void execCommandLoop(final Commands commands)
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String commandLine;
			try {
				commandLine = br.readLine();
			} catch (IOException e) {
				System.out.print("Error: Error while reading console input.");
				break;
			}
            Scanner scanner = new Scanner(commandLine);

            if (scanner.hasNext())
            {
            	final String commandName = scanner.next();
            	List<String> parameters = new ArrayList<>();
            	while(scanner.hasNext()){
            		parameters.add(scanner.next());
            	}
            	commands.getCommand(commandName).execute(parameters.toArray(new String[parameters.size()]));
             }
            scanner.close();
        }
    }
	
}
