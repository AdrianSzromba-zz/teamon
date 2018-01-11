package pl.teamon.manager.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "trening")
public class Trening {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;
	
	@Future
	private Date date;
	
	@NotEmpty
	private String topic;
	
	@NotEmpty
	@Size(min = 15, max = 200)
	private String description;
	
	@OneToOne(cascade =CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usergroup")
	private UserGroup usergroup;
	
	@OneToMany (cascade =CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_user")
	private List <User> user = new ArrayList<>();

	public String getName() {
		return this.date + " " + this.topic + " " + this.usergroup.getGroupName();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserGroup getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	
}
