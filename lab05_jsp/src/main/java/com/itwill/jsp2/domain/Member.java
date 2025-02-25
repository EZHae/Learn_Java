package com.itwill.jsp2.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Member {
	private Integer id;
	private String userName;
	private String password;
	private String email;
	private Integer points;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	public Member(Integer id, String userName, String password, String email, Integer points, LocalDateTime createdTime,
			LocalDateTime modifiedTime) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.points = points;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public Integer getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public Integer getPoints() {
		return points;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return String.format(
				"Member [id=%s, userName=%s, password=%s, email=%s, points=%s, createdTime=%s, modifiedTime=%s]", 
				id, userName, password, email, points, createdTime, modifiedTime);
	}
	
	public static MemberBuilder builder() {
		return new MemberBuilder();
	}
	
	public static class MemberBuilder {
		private Integer id;
		private String userName;
		private String password;
		private String email;
		private Integer points;
		private LocalDateTime createdTime;
		private LocalDateTime modifiedTime;
		
		private MemberBuilder() {}
		
		public MemberBuilder id (Integer id) {
			this.id = id;
			return this;
		}
		public MemberBuilder userName (String userName) {
			this.userName = userName;
			return this;
		}
		public MemberBuilder password (String password) {
			this.password = password;
			return this;
		}
		public MemberBuilder email (String email) {
			this.email = email;
			return this;
		}
		public MemberBuilder points (Integer points) {
			this.points = points;
			return this;
		}
		public MemberBuilder createdTime (LocalDateTime createdTime) {
			this.createdTime = createdTime;
			return this;
		}
		public MemberBuilder createdTime (Timestamp createdTime) {
			this.createdTime = createdTime.toLocalDateTime();
			return this;
		}
		public MemberBuilder modifiedTime (LocalDateTime modifiedTime) {
			this.modifiedTime = modifiedTime;
			return this;
		}
		public MemberBuilder modifiedTime (Timestamp modifiedTime) {
			this.modifiedTime = modifiedTime.toLocalDateTime();
			return this;
		}
		public Member build() {
			return new Member(id, userName, password, email, points, createdTime, modifiedTime);
		}
	}
}
