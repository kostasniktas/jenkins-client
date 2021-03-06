/*
 * Copyright (c) 2013 Rising Oak LLC.
 *
 * Distributed under the MIT license: http://opensource.org/licenses/MIT
 */

package com.offbytwo.jenkins.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.net.UrlEscapers;

public class ComputerWithDetails extends Computer {

    String displayName;
    List actions;
    List<Executor> executors;
    Boolean idle;
    Boolean jnlp;
    Boolean launchSupported;
    Boolean manualLaunchAllowed;
    Map monitorData;
    Integer numExecutors;
    Boolean offline;
    Object offlineCause;
    String offlineReason;
    List oneOffExecutors;
    Boolean temporarilyOffline;

    public String getDisplayName() {
        return displayName;
    }

    public List<Map> getActions() {
        return actions;
    }

    public List<Executor> getExecutors() {
        return executors;
    }

    public Boolean getIdle() {
        return idle;
    }

    public Boolean getJnlp() {
        return jnlp;
    }

    public Boolean getLaunchSupported() {
        return launchSupported;
    }

    /**
     * This will explicitly get the whole statistics for the given computer
     * (node) name.
     * 
     * @return {@link LoadStatistics}
     * @throws IOException
     */
    public LoadStatistics getLoadStatistics() throws IOException {
        // TODO: Think about the following handling, cause that has also being
        // done in Computer#details().
        String name;
        if ("master".equals(displayName)) {
            name = "(master)";
        } else {
            name = UrlEscapers.urlPathSegmentEscaper().escape(displayName);
        }

        //TODO: ?depth=2 good idea or could this being done better?
        return client.get("/computer/" + name + "/" + "loadStatistics/?depth=2", LoadStatistics.class);
    }

    public Boolean getManualLaunchAllowed() {
        return manualLaunchAllowed;
    }

    public Map<String, Map> getMonitorData() {
        return monitorData;
    }

    public Integer getNumExecutors() {
        return numExecutors;
    }

    public Boolean getOffline() {
        return offline;
    }

    public Object getOfflineCause() {
        return offlineCause;
    }

    public String getOfflineReason() {
        return offlineReason;
    }

    public List<Map> getOneOffExecutors() {
        return oneOffExecutors;
    }

    public Boolean getTemporarilyOffline() {
        return temporarilyOffline;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComputerWithDetails other = (ComputerWithDetails) obj;
        if (actions == null) {
            if (other.actions != null)
                return false;
        } else if (!actions.equals(other.actions))
            return false;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (executors == null) {
            if (other.executors != null)
                return false;
        } else if (!executors.equals(other.executors))
            return false;
        if (idle == null) {
            if (other.idle != null)
                return false;
        } else if (!idle.equals(other.idle))
            return false;
        if (jnlp == null) {
            if (other.jnlp != null)
                return false;
        } else if (!jnlp.equals(other.jnlp))
            return false;
        if (launchSupported == null) {
            if (other.launchSupported != null)
                return false;
        } else if (!launchSupported.equals(other.launchSupported))
            return false;
        if (manualLaunchAllowed == null) {
            if (other.manualLaunchAllowed != null)
                return false;
        } else if (!manualLaunchAllowed.equals(other.manualLaunchAllowed))
            return false;
        if (monitorData == null) {
            if (other.monitorData != null)
                return false;
        } else if (!monitorData.equals(other.monitorData))
            return false;
        if (numExecutors == null) {
            if (other.numExecutors != null)
                return false;
        } else if (!numExecutors.equals(other.numExecutors))
            return false;
        if (offline == null) {
            if (other.offline != null)
                return false;
        } else if (!offline.equals(other.offline))
            return false;
        if (offlineCause == null) {
            if (other.offlineCause != null)
                return false;
        } else if (!offlineCause.equals(other.offlineCause))
            return false;
        if (offlineReason == null) {
            if (other.offlineReason != null)
                return false;
        } else if (!offlineReason.equals(other.offlineReason))
            return false;
        if (oneOffExecutors == null) {
            if (other.oneOffExecutors != null)
                return false;
        } else if (!oneOffExecutors.equals(other.oneOffExecutors))
            return false;
        if (temporarilyOffline == null) {
            if (other.temporarilyOffline != null)
                return false;
        } else if (!temporarilyOffline.equals(other.temporarilyOffline))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((actions == null) ? 0 : actions.hashCode());
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((executors == null) ? 0 : executors.hashCode());
        result = prime * result + ((idle == null) ? 0 : idle.hashCode());
        result = prime * result + ((jnlp == null) ? 0 : jnlp.hashCode());
        result = prime * result + ((launchSupported == null) ? 0 : launchSupported.hashCode());
        result = prime * result + ((manualLaunchAllowed == null) ? 0 : manualLaunchAllowed.hashCode());
        result = prime * result + ((monitorData == null) ? 0 : monitorData.hashCode());
        result = prime * result + ((numExecutors == null) ? 0 : numExecutors.hashCode());
        result = prime * result + ((offline == null) ? 0 : offline.hashCode());
        result = prime * result + ((offlineCause == null) ? 0 : offlineCause.hashCode());
        result = prime * result + ((offlineReason == null) ? 0 : offlineReason.hashCode());
        result = prime * result + ((oneOffExecutors == null) ? 0 : oneOffExecutors.hashCode());
        result = prime * result + ((temporarilyOffline == null) ? 0 : temporarilyOffline.hashCode());
        return result;
    }

    private class ComputerWithClient implements Function<Computer, Computer> {
        @Override
        public Computer apply(Computer computer) {
            computer.setClient(client);
            return computer;
        }
    }
}