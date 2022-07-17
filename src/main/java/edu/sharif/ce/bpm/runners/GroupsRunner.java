package edu.sharif.ce.bpm.runners;

import edu.sharif.ce.bpm.config.BpmProps;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GroupsRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(GroupsRunner.class);

    @Autowired
    BpmProps props;

    @Autowired
    private IdentityService identityService;

    @Override
    public void run(String... args) {
        if (props.getGroups() == null)
            return;

        for (BpmProps.GroupDefinition item : props.getGroups()) {
            if (identityService.createGroupQuery().groupId(item.getId()).singleResult() != null)
                continue;

            logger.info("Creating group " + item.getId());

            Group group = identityService.newGroup(item.getId());
            group.setName(item.getName());

            identityService.saveGroup(group);
        }
    }

}
