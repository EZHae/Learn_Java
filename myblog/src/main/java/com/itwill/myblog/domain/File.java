package com.itwill.myblog.domain;

public class File {
	private Integer id;
	private Integer postId;
	private String fileName;
	private String filePath;
	
	public File() {}
	public File(Integer id, Integer postId, String fileName, String filePath) {
		this.id = id;
		this.postId = postId;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	public Integer getId() {
		return id;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return String.format("File [id=%s, postId=%s, fileName=%s, filePath=%s]", id, postId, fileName, filePath);
	}
	
	public static FileBuilder builder() {
		return new FileBuilder();
	}
	
	public static class FileBuilder {
		private Integer id;
		private Integer postId;
		private String fileName;
		private String filePath;
		
		private FileBuilder() {}
		
		public FileBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		public FileBuilder postId(Integer postId) {
			this.postId = postId;
			return this;
		}
		public FileBuilder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}
		public FileBuilder filePath(String filePath) {
			this.filePath = filePath;
			return this;
		}
		public File build() {
			return new File(id, postId, fileName, filePath);
		}
	}
}
