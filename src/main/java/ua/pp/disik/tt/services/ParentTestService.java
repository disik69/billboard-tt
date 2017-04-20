package ua.pp.disik.tt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Created by disik on 4/19/17.
 */

@Service
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@RequestScope
public class ParentTestService {
    private static int COUNTER;

    private final int id = COUNTER++;

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
