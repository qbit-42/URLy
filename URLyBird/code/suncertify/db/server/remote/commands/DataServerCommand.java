package suncertify.db.server.remote.commands;


public abstract class DataServerCommand<T> {
    private DB
    abstract T execute() throws CommandExecutionException;
}
