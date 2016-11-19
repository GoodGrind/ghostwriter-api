package io.ghostwriter;

import java.util.ServiceLoader;

/**
 * @author ghostwriter.io
 */
public enum GhostWriter {

    INSTANCE;

    private static io.ghostwriter.TracerProvider<? extends Tracer> tracerProvider = initialize();

    public static void setTracerProvider(TracerProvider<? extends Tracer> tp) {
        if (tp == null) {
            throw new IllegalArgumentException("Must provide a valid instance! Got: " + String.valueOf(tp));
        }
        Tracer tracer = tp.getTracer();
        INSTANCE.tracerProvider = tp;
        // FIXME (snorbi07): this should be enabled using a DEBUG environmental variable or something
        System.out.println("GWRT: Initialized with Tracer implementation: " + tracer.getClass().getCanonicalName());
    }

    public static void entering(Object source, String method, Object... params) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.entering(source, method, params);
    }

    public static Object returning(Object source, String method, Object returnValue) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.returning(source, method, returnValue);
        return returnValue;
    }

    public static void exiting(Object source, String method) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.exiting(source, method);
    }

    public static void valueChange(Object source, String method, String variable, Object value) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.valueChange(source, method, variable, value);
    }

    public static void onError(Object source, String method, Throwable error) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.onError(source, method, error);
    }

    public static void timeout(Object source, String method, long timeoutThreshold, long timeout) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.timeout(source, method, timeoutThreshold, timeout);
    }

    @SuppressWarnings({"rawtypes"})
    private static TracerProvider<?> initialize() {
        TracerProvider foundProv = null;
        ServiceLoader<TracerProvider> serviceLoader = ServiceLoader.load(TracerProvider.class);
        for (TracerProvider tracerProvider : serviceLoader) {
            Tracer tracer = tracerProvider.getTracer();
            if (foundProv == null
                    && !(tracerProvider instanceof NoopTracerProvider)
                    && tracer != null) {

                foundProv = tracerProvider;
//                FIXME (snorbi07): this should be enabled using a DEBUG environmental variable or something
                System.out.println("GWRT: Initialized with Tracer implementation: " + tracer.getClass().getCanonicalName());

            } else if (tracer != null) {
//                FIXME (snorbi07): this should be enabled using a DEBUG environmental variable or something
                System.out.println("GWRT: Found other tracer implementation: "
                        + tracer.getClass().getCanonicalName());

            }
        }

        if (foundProv != null) {
            return foundProv;
        }

        System.out.println("GWRT: Using the default no-op implementation!");
        return new NoopTracerProvider();
    }

}
