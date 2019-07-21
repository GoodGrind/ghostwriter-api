package io.ghostwriter;

/**
 * @author ghostwriter.io
 *
 *         The implementations of {@link Tracer} are responsible to produce the output of a tracing
 *         event. The default implementation is NOOP.
 */
public interface Tracer {

    /**
     * Creates an entering trace message.
     *
     * @param source The object triggering the tracing event.
     * @param method The method name, where the tracing event happened.
     * @param params Parameters of the method in question. They are passed as {name, value} pairs in sequence.
     */
    void entering(Object source, String method, Object... params);

    /**
     * Creates an exiting trace message. Used in case of methods with {@link Void} return value.
     *
     * @param source The object triggering the tracing event.
     * @param method The method name, where the tracing event happened.
     */
    void exiting(Object source, String method);

    /**
     * Creates a value change trace message immediately after a successful update.
     *
     * @param source   The object triggering the event.
     * @param method   The method name, where the tracing event happened.
     * @param variable The name of the variable that had a new value assigned
     * @param value    The value that was assigned to the variable
     */
    void valueChange(Object source, String method, String variable, Object value);

    /**
     *
     * @param source The object triggering the event
     * @param method The method name, where the tracing event happened
     * @param returnValue The value that the current method returns
     * @param <T> Type of the return value
     */
    <T> void returning(Object source, String method, T returnValue);

    /**
     * Triggered when an unhandled exception occurs. The primary use case is for runtime implementations,
     * by triggering state saving only in cases where the context details are valuable.
     *
     * @param source The object triggering the event.
     * @param method The method name, where the tracing event happened.
     * @param error  The capture exception object.
     */
    void onError(Object source, String method, Throwable error);

}
