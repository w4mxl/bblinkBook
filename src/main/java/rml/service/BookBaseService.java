package rml.service;

import java.util.List;

import rml.model.Base;
import rml.model.BaseBook;
import rml.model.BookAuthor;
import rml.model.BookCommentItem;
import rml.model.BookDetails;
import rml.model.BookImages;
import rml.model.BookRating;
import rml.model.BookTags;
import rml.model.CommentItem;
import rml.model.FavouriteList;
import rml.model.QueryVo;
import rml.model.User;
import rml.model.UserBookFavourite;
import rml.model.UserBookState;
import rml.model.UserCommentResponse;
import rml.model.UserWeapp;
import rml.request.BaseUserBook;
import rml.request.UserComment;
import rml.util.Page;

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
	
	int selectBookCount(BaseUserBook baseUserBook);
	
	List<BookDetails> selectUserBookList(BaseUserBook baseUserBook);
	
	int insertUserComment(UserComment userComment);
	
	int selectUserCommentCount(String uid);
	
	List<CommentItem> selectUserCommentList(String uid);
	
	List <BookCommentItem> setlectUserCommentBookList(String id);
}
