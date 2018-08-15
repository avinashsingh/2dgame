/**
 * 
 */
package com.avinash.puzzle.pojo;

import com.avinash.puzzle.listener.CommandExecutor;

/**
 * @author avinashsingh
 *
 */
public class MenuItem {
	
	private String name;
	private CommandExecutor commandExecutor;
	
	public MenuItem(String name, CommandExecutor commandExecutor) {
		this.name = name;
		this.commandExecutor = commandExecutor;
	}

	public String getName() {
		return name;
	}

	public CommandExecutor getCommandExecutor() {
		return commandExecutor;
	}

}
