package rml.service;

import rml.model.*;
import rml.request.BaseUserBook;
import rml.request.UserComment;
import rml.util.Page;

import java.util.List;

public interface BookBaseService {

/*	public List<BaseBook> queryBookBase();

	int insertBookBase(BaseBook baseBook);
	public Page<BaseBook> queryBookBasePage(QueryVo vo);
	
	int insertBookDetails(BookDetails bookDetails);

	int insertBookAuthor(BookAuthor bookAuthor);
	
	int insertBookImages(BookImages bookImages);
	
	int insertBookTags(BookTags bookTags);
	
	int insertBookRating(BookRating bookRating);
	
	BookDetails selectBookDetails(String id);
	
	int changeBookState(UserBookState userBookState);
	
	int changeBookFavourite(UserBookFavourite userBookFavourite);
	
	String insertFavouriteList(UserBookFavourite userBookFavourite);
	
	int changeUserBook(BaseUserBook baseUserBook);
	
	int selectUseBookCount(BaseUserBook baseUserBook);
	
	List<BookDetails> selectUserBookList(BaseUserBook baseUserBook);
	
	int insertUserComment(UserComment userComment);
	
	int selectUserCommentCount(String uid);
	
	List<UserCommentResponse> serlectUserCommentList(String uid);*/
	
	//---------------------重新整理-------------------------------
	Base inputBookDetails (BookDetails bookDetails);
	
	Page<BookDetails> queryBookBasePage(QueryVo vo);
	
	Base updateUserBook(BaseUserBook baseUserBook);
	
	BookDetails selectBookDetails(String id);
	
	UserBookCount selectBookCount(BaseUserBook baseUserBook);
	
	List<BookDetails> selectUserBookList(BaseUserBook baseUserBook);
	
	int insertUserComment(UserComment userComment);
	
	int selectUserCommentCount(String uid);
	
	List<CommentItem> selectUserCommentList(String uid);
	
	List <BookCommentItem> setlectUserCommentBookList(String id);
}
