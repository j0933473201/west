package com.comment.model;

import java.util.List;

public interface CommentsDAO_interface {
	public void insert(CommentsVO commentsVO);
    public void update(CommentsVO commentsVO);
    public void delete(String cmnt_no);
    public CommentsVO findByPrimaryKey(String cmnt_no);
   
    public List<CommentsVO> getAll();
	public List<CommentsVO> getVendor(String vendor_no);
//	public List<CommentsVO>findByRate(String vendor_no);
}
