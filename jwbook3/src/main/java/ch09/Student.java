package ch09;

import java.sql.Date;

public class Student {
	private int id;
	private String username;
	private String univ;
	private Date birth;
	private String email;
	private String tel;

	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", univ=" + univ + ", birth=" + birth + ", email="
				+ email + ", tel=" + tel + ", getId()=" + getId() + ", getUsername()=" + getUsername() + ", getUniv()="
				+ getUniv() + ", getBirth()=" + getBirth() + ", getEmail()=" + getEmail() + ", getTel()=" + getTel()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUniv() {
		return univ;
	}

	public void setUniv(String univ) {
		this.univ = univ;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
