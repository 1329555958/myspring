package org.wch.logagent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author weichunhe
 *         Created on 2016/10/24.
 * @version 1.0
 */
class MyHttpConnect extends HttpURLConnection {

    /**
     * Constructor for the HttpURLConnection.
     *
     * @param u the URL
     */
    protected MyHttpConnect(URL u) {
        super(u);
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {

    }

}