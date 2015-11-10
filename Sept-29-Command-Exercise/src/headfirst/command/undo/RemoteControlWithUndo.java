package headfirst.command.undo;

import java.util.*;

//
// This is the invoker
//
public class RemoteControlWithUndo {
  private Command[] onCommands;
  private Command[] offCommands;
  private Stack<Command> undoCommands;

  public RemoteControlWithUndo() {
    onCommands = new Command[7];
    offCommands = new Command[7];
    undoCommands = new Stack<Command>();
    Command noCommand = new NoCommand();
    for (int i = 0; i < 7; i++) {
      onCommands[i] = noCommand;
      offCommands[i] = noCommand;
    }
    //undoCommand = noCommand;
  }

  public void setCommand(int slot, Command onCommand, Command offCommand) {
    onCommands[slot] = onCommand;
    offCommands[slot] = offCommand;
  }

  public void onButtonWasPushed(int slot) {
    onCommands[slot].execute();
    undoCommands.push(onCommands[slot]);
  }

  public void offButtonWasPushed(int slot) {
    offCommands[slot].execute();
    undoCommands.push(offCommands[slot]);
  }

  public void undoButtonWasPushed() {
	  if(!undoCommands.empty()){
		  undoCommands.pop().undo();
	  }
  }

  public String toString() {
    StringBuffer stringBuff = new StringBuffer();
    stringBuff.append("\n------ Remote Control -------\n");
    for (int i = 0; i < onCommands.length; i++) {
      stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName() + "    "
                        + offCommands[i].getClass().getName() + "\n");
    }
    Stack<Command> temp = new Stack<Command>();
    while(!undoCommands.empty()){
    	Command undo = undoCommands.pop();
    	temp.push(undo);
    	stringBuff.append("[undo] " + undo.getClass().getName() + "\n");
    }
    while(!temp.empty()){
    	undoCommands.push(temp.pop());
    }
    return stringBuff.toString();
  }
}
