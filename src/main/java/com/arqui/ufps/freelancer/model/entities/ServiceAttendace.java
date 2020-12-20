package com.arqui.ufps.freelancer.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the service_attendaces database table.
 */
@Entity
@Table(name = "service_attendaces")
@NamedQuery(name = "ServiceAttendace.findAll", query = "SELECT s FROM ServiceAttendace s")
public class ServiceAttendace implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "service_details")
    private String serviceDetails;

    private Double state;

    //bi-directional many-to-one association to File
    @JsonManagedReference
    @OneToMany(mappedBy = "serviceAttendace")
    private List<File> files;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "attendanceStudent")
    private User studentId;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    @JsonBackReference(value = "attendanceContractor")
    private User contractorId;

    public ServiceAttendace() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceDetails() {
        return this.serviceDetails;
    }

    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public Double getState() {
        return this.state;
    }

    public void setState(Double state) {
        this.state = state;
    }

    public List<File> getFiles() {
        return this.files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public File addFile(File file) {
        getFiles().add(file);
        file.setServiceAttendace(this);

        return file;
    }

    public File removeFile(File file) {
        getFiles().remove(file);
        file.setServiceAttendace(null);

        return file;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    public User getContractorId() {
        return contractorId;
    }

    public void setContractorId(User contractorId) {
        this.contractorId = contractorId;
    }
}