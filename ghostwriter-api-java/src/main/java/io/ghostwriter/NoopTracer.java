package io.ghostwriter;

public class NoopTracer implements Tracer {

    @Override
    public void entering(Object source, String method, Object... params) {
        // NOOP
    }

    @Override
    public void exiting(Object source, String method) {
        // NOOP
    }

    @Override
    public void valueChange(Object source, String method, String variable, Object newValue) {
        // NOOP
    }

    @Override
    public <T> void returning(Object source, String method, T returnValue) {
        // NOOP
    }

    @Override
    public void onError(Object source, String method, Throwable error) {
        // NOOP
    }

    @Override
    public void timeout(Object source, String method, long timeoutThreshold, long timeout) {
        // NOOP
    }

}
