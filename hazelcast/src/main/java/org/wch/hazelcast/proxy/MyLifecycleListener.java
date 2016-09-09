package org.wch.hazelcast.proxy;

import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

/**
 * @author weichunhe
 *         Created on 2016/9/9.
 * @version 1.0
 */
public class MyLifecycleListener implements LifecycleListener {

    @Override
    public void stateChanged(LifecycleEvent event) {
        System.out.println(event.getState().name());
    }
}
