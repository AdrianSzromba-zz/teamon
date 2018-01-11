package pl.teamon.manager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;

	@NotEmpty
	@Size(min = 2, max = 15)
	private String username;
	
	@NotEmpty
	@Size(min = 2, max = 30)
	private String userlastname;

	@NotEmpty
	private String password;
	
	@NotNull
	private boolean enabled;

	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;
	
	@ManyToMany (cascade =CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserGroup> userGroup = new ArrayList<>();
	
	public String getFullName() {
		return this.username + "   " + this.userlastname+ "   " + this.email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		this.password = BCrypt.hashpw(password, BCrypt.gensalt()); // szyfrowanie hasła za pomocą BCrypt
	}
	
	public boolean isPasswordCorrect(String pwd) { // sprawdzenie czy podane hasło jest ok
		return BCrypt.checkpw(pwd, this.password);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserlastname() {
		return userlastname;
	}

	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public List<UserGroup> getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(List<UserGroup> userGroup) {
		this.userGroup = userGroup;
	}

}
