package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 8683507939615921782L;

	//simple types
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    @Transient
    private String password;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(nullable = false)
    private String hash;
    
    
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;
    
    public enum Type {
        INVESTIGATOR, UAS_ANALYST, SYSADMIN
    }
    
    @Enumerated(EnumType.STRING)
    private Type type;

    

    //Relationships
    //turn into a map later for pi and co-pi 
    //current functionality is for just a pi
//    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "user")
    private List<Proposal> proposals;
    
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
    
    @OneToOne
    @JoinColumn(name = "college_id")
    private College college;
    
	public User(){}
   


	public User(String username, String password, String hash, String lastName, String firstName,
			String phoneNumber, String email, Type type, List<Proposal> proposals, Department department,
			College college) {
		super();
		this.username = username;
		this.password = password;
		this.hash = hash;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.type = type;  
		this.proposals = proposals;
		this.department = department;
		this.college = college;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public void addToProposals(Proposal proposal) {
		List<Proposal> proposals = getProposals();
		proposals.add(proposal);
		setProposals(proposals);
	}


	public String getHash() {
		return hash;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}
	
	

}
