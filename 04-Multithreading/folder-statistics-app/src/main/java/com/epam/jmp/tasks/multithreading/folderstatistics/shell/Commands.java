package com.epam.jmp.tasks.multithreading.folderstatistics.shell;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderScannerApp;

public class Commands {

	
	
	public static final Logger LOGGER =Logger.getLogger(Commands.class);
	private static final String DEFAULT_COMMAND = "help";
	
	Map<String, CommandImpl> commands = new HashMap<String, CommandImpl>();
	
	public Commands(final IFolderScannerApp app){
		
		commands.put("help", new CommandImpl() {
			
			@Override
			public void usage() {
				LOGGER.info("Command help usage:");
				LOGGER.info("'help'");
				LOGGER.info("'help <command name>'");
			}
			
			@Override
			public void name() {
				LOGGER.info("help");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 0:
					LOGGER.info("Help for " + app.getName());
					LOGGER.info("Here is list of commans you can use");
					for(CommandImpl commnad:commands.values()){
						commnad.name();
					}
					LOGGER.info("Use command 'help <command name>' to know details.");
					break;
					
				case 1:
					doGetCommand(parameters[0]).usage();
					break;
					
				default:
					LOGGER.error("Error: help command can have zero or one parameter.");
					usage();
					break;
			}
			}
		});
		
//		commands.put("usage", new CommandImpl() {
//			
//			@Override
//			public void usage() {
//				LOGGER.info("Command usage usage:");
//				LOGGER.info("'usage <command name>'");
//			}
//			
//			@Override
//			public void name() {
//				LOGGER.info("usage");
//			}
//			
//			@Override
//			public void execute(String[] parameters) {
//				switch (parameters.length) {
//				case 1:
//					commands.get(parameters[0]).usage();
//					break;
//					
//				default:
//					LOGGER.error("Error: usage command can have only one parameter.");
//					usage();
//					break;
//			}
//			}
//		});
		
		commands.put("exit", new CommandImpl() {
			
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
					try {
						app.exit();
					} catch (InterruptedException e) {
						LOGGER.error("Error while exiting app!", e);
					} finally {
						LOGGER.info("bye");
						System.exit(0);
					}
					break;
					
				default:
					LOGGER.error("Error: exit does not have any parameter.");
					usage();
					break;
			}
			}
		});
		
		commands.put("scan", new CommandImpl() {
			
			@Override
			public void usage() {
				LOGGER.info("Scans given path to calculate size, file and dir count.");
				LOGGER.info("Command scan usage:");
				LOGGER.info("'scan <folder path>'");
			}
			
			@Override
			public void name() {
				LOGGER.info("scan");
			}
			
			@Override
			public void execute(String[] parameters) {
//				throw new RuntimeException("Unexpected error");
				switch (parameters.length) {
				case 1:
					app.scan(parameters[0]);
					break;
					
				default:
					LOGGER.error("Error: deploy command can have only one parameter.");
					usage();
					break;
				}
			}
		});
		
		commands.put("cancel", new CommandImpl() {
			
			@Override
			public void usage() {
				LOGGER.info("Stops scanning given folder.");
				LOGGER.info("Command cancel usage:");
				LOGGER.info("'cancel <folder path>'");
			}
			
			@Override
			public void name() {
				LOGGER.info("cancel");
			}
			
			@Override
			public void execute(String[] parameters) {
				switch (parameters.length) {
				case 1:
					app.stopScanning(parameters[0]);
					break;
					
				default:
					LOGGER.error("Error: undeploy command can have only one parameter.");
					usage();
					break;
			}
			}
		});
	}
	
	private CommandImpl doGetCommand(String name){
		if(commands.containsKey(name.toLowerCase())){
			return commands.get(name.toLowerCase());
		} else {
			LOGGER.error("Error: Command '" + name + "' is not supported!\n");
			return commands.get(DEFAULT_COMMAND);
		}
	}
	
	public Command getCommand(String name){
		return doGetCommand(name);
	}
	
	private abstract class CommandImpl implements Command{

		public abstract void name();
		@Override
		public abstract void execute(String[] parameters);
		public abstract void usage();
		}
}
