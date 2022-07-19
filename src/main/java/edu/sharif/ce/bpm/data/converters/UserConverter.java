package edu.sharif.ce.bpm.data.converters;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class UserConverter implements AttributeConverter<User, String> {

    private static IdentityService identityService;

    @Autowired
    public void setIdentityService(IdentityService identityService) {
        UserConverter.identityService = identityService;
    }

    @Override
    public String convertToDatabaseColumn(User user) {
        return user.getId();
    }

    @Override
    public User convertToEntityAttribute(String id) {
        if (id == null)
            return null;
        return identityService.createUserQuery().userId(id).singleResult();
    }
}
