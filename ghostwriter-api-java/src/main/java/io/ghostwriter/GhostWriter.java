package io.ghostwriter;

import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ghostwriter.io
 */
public enum GhostWriter {

    INSTANCE;

    private static Logger LOG = Logger.getLogger(GhostWriter.class.getName());

    private static io.ghostwriter.TracerProvider<? extends Tracer> tracerProvider;

    static {
        final String ENV_GHOSTWRITER_VERBOSE = "GHOSTWRITER_VERBOSE";
        final String verboseEnv = System.getenv(ENV_GHOSTWRITER_VERBOSE);
        final boolean isVerbose = Boolean.parseBoolean(verboseEnv);

        if (!isVerbose) {
            LOG.setLevel(Level.OFF);
        }

        tracerProvider = initialize();
    }

    public static void setTracerProvider(TracerProvider<? extends Tracer> tp) {
        if (tp == null) {
            throw new IllegalArgumentException("Must provide a valid instance! Got: " + String.valueOf(tp));
        }
        Tracer tracer = tp.getTracer();
        INSTANCE.tracerProvider = tp;
        LOG.info(GhostWriter.class.getName() + " - initialized with implementation: " + tracer.getClass().getCanonicalName());
    }

    static void entering(Object source, String method, Object... params) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.entering(source, method, params);
    }

    static void returning(Object source, String method, Object returnValue) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.returning(source, method, returnValue);
    }

    static void exiting(Object source, String method) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.exiting(source, method);
    }

    static void valueChange(Object source, String method, String variable, Object value) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.valueChange(source, method, variable, value);
    }

    static void onError(Object source, String method, Throwable error) {
        Tracer tracer = INSTANCE.tracerProvider.getTracer();
        tracer.onError(source, method, error);
    }

    @SuppressWarnings({"rawtypes"})
    private static TracerProvider<?> initialize() {
        TracerProvider foundProv = null;
        ServiceLoader<TracerProvider> serviceLoader = ServiceLoader.load(TracerProvider.class, GhostWriter.class.getClassLoader());
        for (TracerProvider tracerProvider : serviceLoader) {
            Tracer tracer = tracerProvider.getTracer();
            if (foundProv == null
                    && !(tracerProvider instanceof NoopTracerProvider)
                    && tracer != null) {

                foundProv = tracerProvider;
                LOG.info(GhostWriter.class.getName() + " - initialized with implementation: "
                        + tracer.getClass().getCanonicalName());

            } else if (tracer != null) {
                LOG.info(GhostWriter.class.getName() + " - initialized with implementation: "
                        + tracer.getClass().getCanonicalName());
            }
        }

        if (foundProv != null) {
            return foundProv;
        }

        LOG.info(GhostWriter.class.getName() + " - using the default no-op implementation!");
        return new NoopTracerProvider();
    }

}
