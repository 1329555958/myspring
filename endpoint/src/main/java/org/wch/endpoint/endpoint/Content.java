package org.wch.endpoint.endpoint;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weichunhe
 *         Created on 2016/8/12.
 */
@Component
public class Content {
    private List<String> services = new ArrayList<>();

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
