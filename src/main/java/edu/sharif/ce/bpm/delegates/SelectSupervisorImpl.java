package edu.sharif.ce.bpm.delegates;

import edu.sharif.ce.bpm.data.Student;
import edu.sharif.ce.bpm.data.StudentRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SelectSupervisorImpl implements JavaDelegate {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String initiator = (String) execution.getVariable("initiator");
        String newSupervisor = (String) execution.getVariable("newSupervisor");

        User newSupervisorUser = identityService.createUserQuery().userId(newSupervisor).singleResult();

        Optional<Student> student = studentRepository.findById(initiator);
        student.get().setSupervisor(newSupervisorUser);
        studentRepository.save(student.get());
    }
}
