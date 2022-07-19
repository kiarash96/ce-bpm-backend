package edu.sharif.ce.bpm.data;

import org.camunda.bpm.engine.identity.User;

import javax.persistence.*;

@Entity
@Table(name = "bpm_student")
public class Student {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Branch branch;

    private User account;
    private User supervisor;

    public Student() {

    }

    public Student(String id, Branch branch, User account, User supervisor) {
        this.id = id;
        this.branch = branch;
        this.account = account;
        this.supervisor = supervisor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }
}
