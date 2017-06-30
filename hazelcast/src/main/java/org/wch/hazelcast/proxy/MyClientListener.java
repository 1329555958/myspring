package org.wch.hazelcast.proxy;

import com.hazelcast.core.Client;
import com.hazelcast.core.ClientListener;

/**
 * @author weichunhe
 *         Created on 2017/4/19.
 * @version 1.0
 */
public class MyClientListener implements ClientListener {
    @Override
    public void clientConnected(Client client) {
        System.out.println(client.toString() + "-" + client.getSocketAddress().getAddress().toString());
    }

    @Override
    public void clientDisconnected(Client client) {
        System.out.println(client.toString() + "-" + client.getSocketAddress().getAddress().toString());
    }
}
