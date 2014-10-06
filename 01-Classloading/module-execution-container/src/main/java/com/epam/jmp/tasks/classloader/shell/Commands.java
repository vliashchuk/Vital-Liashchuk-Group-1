package com.epam.jmp.tasks.classloader.shell;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.classloader.App;

public class Commands {

	public static final Logger LOGGER =Logger.getLogger(Commands.class);
	private static final String DEFAULT_COMMAND = "help";
	
	Map<String, Command> commands = new HashMap<String, Command>();
	
	public Commands(final App app){
		
		commands.put("help", new Command() {
			
			@Override
			public void usage() {
				LOGGER.info("Command help usage:");
				LOGGER.info("'help'");
			}
			
			@Override
			public void name() {
				LOGGER.info("help");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 0:
					LOGGER.info("Help for Module Execution App.");
					LOGGER.info("Here is list of commans you can use");
					for(Command commnad:commands.values()){
						commnad.name();
					}
					LOGGER.info("Use command 'usage <command name>' to know details.");
					break;
					
				default:
					LOGGER.error("Error: help command does not have any parameter.");
					usage();
					break;
			}
			}
		});
		
		commands.put("usage", new Command() {
			
			@Override
			public void usage() {
				LOGGER.info("Command usage usage:");
				LOGGER.info("'usage <command name>'");
			}
			
			@Override
			public void name() {
				LOGGER.info("usage");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 1:
					commands.get(parameters[0]).usage();
					break;
					
				default:
					LOGGER.error("Error: usage command can have only one parameter.");
					usage();
					break;
			}
			}
		});
		
		commands.put("exit", new Command() {
			
			@Override
			public void usage() {
				LOGGER.info("Command exit usage:");
				LOGGER.info("'exit'");
			}
			
			@Override
			public void name() {
				LOGGER.info("exit");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 0:
					LOGGER.info("bye");
					System.exit(0);
					break;
					
				default:
					LOGGER.error("Error: exit does not have any parameter.");
					usage();
					break;
			}
			}
		});
		
		commands.put("deploy", new Command() {
			
			@Override
			public void usage() {
				LOGGER.info("Command deploy usage:");
				LOGGER.info("'deploy <jar name> <module name>'");
				LOGGER.info("'deploy <jar name>' - <jar name> is used as <module name>");
			}
			
			@Override
			public void name() {
				LOGGER.info("deploy");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 1:
					app.deploy(parameters[0], parameters[0]);
					break;
				case 2:
					app.deploy(parameters[0], parameters[1]);
					break;
					
				default:
					LOGGER.error("Error: deploy command can have only one or two parameters.");
					usage();
					break;
				}
			}
		});
		
		commands.put("undeploy", new Command() {
			
			@Override
			public void usage() {
				LOGGER.info("Command undeploy usage:");
				LOGGER.info("'undeploy <module name>'");
			}
			
			@Override
			public void name() {
				LOGGER.info("undeploy");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 1:
					app.undeploy(parameters[0]);
					break;
					
				default:
					LOGGER.error("Error: undeploy command can have only one parameter.");
					usage();
					break;
			}
			}
		});
		
		commands.put("execute", new Command() {
			
			@Override
			public void usage() {
				LOGGER.info("Command execute usage:");
				LOGGER.info("'execute <module name>'");
			}
			
			@Override
			public void name() {
				LOGGER.info("execute");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 1:
					app.execute(parameters[0]);
					break;
					
				default:
					LOGGER.error("Error: execute command can have only one parameter.");
					usage();
					break;
			}
			}
		});
		
		commands.put("list", new Command() {
			
			@Override
			public void usage() {
				LOGGER.info("Command list usage:");
				LOGGER.info("'list'");
			}
			
			@Override
			public void name() {
				LOGGER.info("list");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 0:
					app.list();
					break;
					
				default:
					LOGGER.error("Error: list command does not have any parameter.");
					usage();
					break;
			}
			}
		});
	}
	
	public Command getCommand(String name){
		if(commands.containsKey(name.toLowerCase())){
			return commands.get(name.toLowerCase());
		} else {
			LOGGER.error("Error: Command '" + name + "' is not supported!\n");
			return commands.get(DEFAULT_COMMAND);
		}
		
	}
}
