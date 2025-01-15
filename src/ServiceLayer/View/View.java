package ServiceLayer.View;

import BusinessLayer.Utils.callbacks.InputCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;

public abstract class View {
    public abstract void display(String msg);
    public abstract String getInput();
    public MessageCallback getCallback() // return a function that displays a message
    {
        return this::display;
    }
    public InputCallback getInputCallback() // return a function that returns the input
    {
        return this::getInput;
    }
}