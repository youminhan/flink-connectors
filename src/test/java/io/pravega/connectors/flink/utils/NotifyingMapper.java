/**
 * Copyright (c) 2017 Dell Inc., or its subsidiaries.
 */
package io.pravega.connectors.flink.utils;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.runtime.state.CheckpointListener;

import java.util.concurrent.atomic.AtomicReference;

/**
 * An identity MapFunction that calls an interface once it receives a notification
 * that a checkpoint has been completed.
 */
public class NotifyingMapper<T> implements MapFunction<T, T>, CheckpointListener {

    public static final AtomicReference<Runnable> TO_CALL_ON_COMPLETION = new AtomicReference<>();

    @Override
    public T map(T element) throws Exception {
        return element;
    }

    @Override
    public void notifyCheckpointComplete(long l) throws Exception {
        TO_CALL_ON_COMPLETION.get().run();
    }
}