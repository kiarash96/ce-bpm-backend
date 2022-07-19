package edu.sharif.ce.bpm.listeners;

import edu.sharif.ce.bpm.data.Student;
import edu.sharif.ce.bpm.data.StudentRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProcessStartListener implements ExecutionListener {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String initiator = (String) execution.getVariable("initiator");

        Optional<Student> student = studentRepository.findById(initiator);
        if (student.isPresent()) {
            execution.setVariable("student.firstName", student.get().getAccount().getFirstName());
            execution.setVariable("student.lastName", student.get().getAccount().getLastName());
            execution.setVariable("student.branch", student.get().getBranch());
            execution.setVariable("student.id", student.get().getId());

            User supervisor = student.get().getSupervisor();
            if (supervisor != null)
                execution.setVariable("supervisor", supervisor.getId());

            String group = student.get().getBranch().toString() + "-group-head";
            User groupHead = identityService.createUserQuery().memberOfGroup(group).singleResult();
            execution.setVariable("groupHead", groupHead.getId());
        }

    }

}
