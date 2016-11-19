package io.ghostwriter;

public interface TracerProvider<T extends Tracer> {

    /**
     * Provides the instance to which the events will be delegated.
     * Implementation should take into consideration that this function is called for each event that will be delegated.
     * In other word... constructing a new instance each time is a bad idea.
     * In the future the static component of GhostWriter might cache the result of this method.
     *
     * @return a tracer implementation to use by the runtime components.
     */
    T getTracer();

}
