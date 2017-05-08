package ua.pp.disik.tt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * Created by disik on 4/19/17.
 */

@Service
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@RequestScope
public class ParentTestService {
    private static int counter;

    private final int id = counter++;

    private ChildTestService childTestService;

    @Autowired
    public void setChildTestService (ChildTestService childTestService) {
        this.childTestService = childTestService;
    }

    public int getId() {
        return id;
    }

    public ChildTestService getChildTestService() {
        return childTestService;
    }
}
