package com.itwill.myblog.domain;

public class Image {
	private Integer id;
	private Integer postId;
	private String imageName;
	private String imagePath;
	
	public Image() {}
	public Image(Integer id, Integer postId, String imageName, String imagePath) {
		this.id = id;
		this.postId = postId;
		this.imageName = imageName;
		this.imagePath = imagePath;
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
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return String.format("Image [id=%s, postId=%s, imageName=%s, imagePath=%s]", id, postId, imageName, imagePath);
	}
	
	public static ImageBuilder builder() {
		return new ImageBuilder();
	}
	
	public static class ImageBuilder {
		private Integer id;
		private Integer postId;
		private String imageName;
		private String imagePath;
		
		private ImageBuilder() {}
		
		public ImageBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		public ImageBuilder postId(Integer postId) {
			this.postId = postId;
			return this;
		}
		public ImageBuilder imageName(String imageName) {
			this.imageName = imageName;
			return this;
		}
		public ImageBuilder imagePath(String imagePath) {
			this.imagePath = imagePath;
			return this;
		}
		public Image build() {
			return new Image(id, postId, imageName, imagePath);
		}
	}
}
