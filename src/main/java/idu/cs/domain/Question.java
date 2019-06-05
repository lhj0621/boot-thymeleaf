package idu.cs.domain;

import java.time.LocalDateTime;

import idu.cs.entity.UserEntity;

public class Question { //domain object == dto, vo
		private Long id; 	
		private String title;
		private User writer;
		private String conntents;
		private LocalDateTime createTime;
		
		public Question() {}
		
		public Question(String title, User writer, String conntents) {
			super();
			this.title = title;
			this.writer = writer;
			this.conntents = conntents;
			this.createTime = LocalDateTime.now();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public User getWriter() {
			return writer;
		}

		public void setWriter(User writer) {
			this.writer = writer;
		}

		public String getConntents() {
			return conntents;
		}

		public void setConntents(String conntents) {
			this.conntents = conntents;
		}

		public LocalDateTime getCreateTime() {
			return createTime;
		}

		public void setCreateTime(LocalDateTime createTime) {
			this.createTime = createTime;
		}
}

