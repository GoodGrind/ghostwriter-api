package io.ghostwriter;

public class NoopTracerProvider implements TracerProvider<NoopTracer> {

    private NoopTracer tracer = new NoopTracer();

    @Override
    public NoopTracer getTracer() {
        return tracer;
    }

}
