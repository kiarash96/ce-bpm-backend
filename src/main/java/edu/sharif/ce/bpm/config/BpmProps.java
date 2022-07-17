package edu.sharif.ce.bpm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "bpm")
public class BpmProps {

    public static class GroupDefinition {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<GroupDefinition> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDefinition> groups) {
        this.groups = groups;
    }

    private List<GroupDefinition> groups;

}
