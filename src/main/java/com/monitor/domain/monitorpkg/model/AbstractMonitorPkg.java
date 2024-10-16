package com.monitor.domain.monitorpkg.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractMonitorPkg {
    /**
     * name.
     */
    protected String name;

    /**
     * upstream.
     */
    protected String upstream;

    /**
     * backend.
     */
    protected String backend;

    /**
     * valid pkg.
     * @return boolean.
     */
    public abstract boolean validPkg();
}
