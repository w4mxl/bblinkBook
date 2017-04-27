package rml.service.impl;

import java.awt.Component.BaselineResizeBehavior;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.BookBaseDao;
import rml.model.Base;
import rml.model.BaseBook;
import rml.model.BookAuthor;
import rml.model.BookCommentItem;
import rml.model.BookCommentUserItem;
import rml.model.BookDetails;
import rml.model.BookImages;
import rml.model.BookRating;
import rml.model.BookTags;
import rml.model.CommentItem;
import rml.model.FavouriteList;
import rml.model.QueryVo;
import rml.model.UserBookFavourite;
import rml.model.UserBookState;
import rml.model.UserCommentResponse;
import rml.model.UserWeapp;
import rml.request.BaseUserBook;
import rml.request.UserComment;
import rml.service.BookBaseService;
import rml.util.Page;

@Service("bookBaseService")
public class BookBaseServiceIml implements BookBaseService {

	@Autowired
	private BookBaseDao bookBaseDao;
	/*@Override
	public List<BaseBook> queryBookBase() {
		// TODO Auto-generated method stub
		return bookBaseDao.queryBookBase();
	}
	@Override
	public int insertBookBase(BaseBook baseBook) {
		// TODO Auto-generated method stub
		return bookBaseDao.insertBookBase(baseBook);
	}
	@Override
	public Page<BaseBook> queryBookBasePage(QueryVo vo){
		Page<BaseBook> page = new Page<BaseBook>();
		//每页数
		page.setSize(vo.getSize());
		if(null!=vo){
			//判断当前页
			if(null != vo.getPage()){
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage()-1)*vo.getSize());
			}
			//总条数
			page.setTotal(bookBaseDao.queryBookBasePage(vo));
			//结果集
			page.setRows(bookBaseDao.queryBookBaseListByQueryVo(vo));
		}
		return page;
	}
	@Override
	public int insertBookDetails(BookDetails bookDetails) {

		return	bookBaseDao.insertBookDetails(bookDetails);
		
	}
	@Override
	public int insertBookAuthor(BookAuthor bookAuthor) {
		// TODO Auto-generated method stub
		return bookBaseDao.insertBookAuthor(bookAuthor);
	}
	@Override
	public int insertBookImages(BookImages bookImages) {
		// TODO Auto-generated method stub
		return bookBaseDao.insertBookImages(bookImages);
	}
	@Override
	public int insertBookTags(BookTags bookTags) {
		// TODO Auto-generated method stub
		return bookBaseDao.insertBookTags(bookTags);
	}
	@Override
	public int insertBookRating(BookRating bookRating) {
		// TODO Auto-generated method stub
		return bookBaseDao.insertBookRating(bookRating);
	}
	@Override
	public BookDetails selectBookDetails(String id) {
		// TODO Auto-generated method stub
		List list = new ArrayList<String>();
		BookDetails bookDetails = bookBaseDao.selectBookDetails(id);
		if(bookDetails!=null){
			List<BookAuthor>  bookAuthor  =  bookBaseDao.selectBookAuthor(id);
			list.add(bookAuthor.get(0).getAuthor());
			BookImages  bookImages = bookBaseDao.selectBookImages(id);
			List<BookTags>  bookTags = bookBaseDao.selectBookTag(id);
			bookDetails.setImages(bookImages);
			bookDetails.setTags(bookTags);
			bookDetails.setAuthor(list);
			return bookDetails;
		}
		return null;
		
	}
	@Override
	public int changeBookState(UserBookState userBookState) {
		// TODO Auto-generated method stub
		return bookBaseDao.changeBookState(userBookState)+bookBaseDao.changeBookStateToBase(userBookState);
	}
	@Override
	public int changeBookFavourite(UserBookFavourite userBookFavourite) {
		// TODO Auto-generated method stub	
		return bookBaseDao.changeBookFavourite(userBookFavourite)+bookBaseDao.changeBookFavouriteToBase(userBookFavourite);
	}
	@Override
	public String insertFavouriteList(UserBookFavourite userBookFavourite) {
		
		if(bookBaseDao.selectFavouriteList(userBookFavourite) >0){
			if	(bookBaseDao.deleteFavouriteList(userBookFavourite)>0){
			return "取消收藏成功";
			}
		}
		bookBaseDao.insertFavouriteList(userBookFavourite);
		return "收藏成功";
	}
	@Override
	public int changeUserBook(BaseUserBook baseUserBook) {
		int i =  bookBaseDao.updateUserBook(baseUserBook);
		if(i == 0 & baseUserBook.getNexusState() != 0 ){
			i = bookBaseDao.insertUserBook(baseUserBook);
		}
		
		return i;
	}

	public int selectUseBookCount(BaseUserBook baseUserBook){
		
		bookBaseDao.selectUseBookCount(baseUserBook);
		return bookBaseDao.selectUseBookCount(baseUserBook);
	}
	@Override
	public List<BookDetails> selectUserBookList(BaseUserBook baseUserBook) {
			List<BookDetails> bookList = new ArrayList<>(); 
		List<String> list = bookBaseDao.selectUserBookList(baseUserBook);
		for (String id : list) {
			
				bookList.add(bookBaseDao.selectBookDetails(id));
		}
		
		return bookList;
	}
	@Override
	public int insertUserComment(UserComment userComment) {
		return bookBaseDao.insertUserComment(userComment);
	}
	@Override
	public int selectUserCommentCount(String uid) {
		
	
		return	bookBaseDao.selectUserCommentCount(uid);
	}
	@Override
	public List<UserCommentResponse> serlectUserCommentList(String uid) {
		List<UserCommentResponse> listResponse = new ArrayList<>();
		List<UserComment> list = bookBaseDao.selectUserCommentList(uid);
		for (UserComment userComment : list) {
			UserCommentResponse userCommentResponse = new UserCommentResponse();
			BookDetails bookDetails = bookBaseDao.selectBookDetails(userComment.getId());
			userCommentResponse.setImage(bookDetails.getImage());
			userCommentResponse.setName(bookDetails.getTitle());
			userCommentResponse.setContent(userComment.getContent());
			userCommentResponse.setCommentTime(userComment.getCommentTime());
			listResponse.add(userCommentResponse);
		}
		return listResponse;
	}
	*/
//---------------------------重新整理------------------------------------
	@Override
	public Base inputBookDetails(BookDetails bookDetails) {
		Base base = new Base();
			try {
				BookDetails book =bookBaseDao.selectBookDetails(bookDetails.getId());
				
				if(book == null){
					bookBaseDao.insertBookDetails(bookDetails);
					String[] bookAuthorList = bookDetails.getAuthor();
					for (String author : bookAuthorList) {
						BookAuthor bookAuthor = new BookAuthor();
						bookAuthor.setId(bookDetails.getId());
						bookAuthor.setAuthor(author);
						bookBaseDao.insertBookAuthor(bookAuthor);
					}
					BookRating bookRating = bookDetails.getRating();
					bookRating.setId(bookDetails.getId());
					bookBaseDao.insertBookRating(bookRating);
					
					List<BookTags> bookTags = bookDetails.getTags();
					for (BookTags tag : bookTags) {
						tag.setId(bookDetails.getId());
						bookBaseDao.insertBookTags(tag);
					} 
					BookImages images = bookDetails.getImages();
					images.setId(bookDetails.getId());
					bookBaseDao.insertBookImages(images);	
					book = bookBaseDao.selectBookDetails(bookDetails.getId());
					book.setAuthor(bookBaseDao.selectBookAuthor(bookDetails.getId()));
					book.setRating(bookBaseDao.selectBookRating(bookDetails.getId()));
					book.setTags(bookBaseDao.selectBookTag(bookDetails.getId()));
					book.setImages(bookBaseDao.selectBookImages(bookDetails.getId()));
					base.setCode(0);
					base.setState("录入成功");
					base.setData(book);		
					return base;
			}
				base.setCode(1);
				base.setState("录入失败");
				book.setAuthor(bookBaseDao.selectBookAuthor(bookDetails.getId()));
				book.setRating(bookBaseDao.selectBookRating(bookDetails.getId()));
				book.setTags(bookBaseDao.selectBookTag(bookDetails.getId()));
				book.setImages(bookBaseDao.selectBookImages(bookDetails.getId()));
				base.setData(book);
			return base;
		
	}catch (Exception e) {
		base.setCode(2);
		base.setState(e.getMessage());
		base.setData(null);
		return base;
	}

	}

	@Override
	public Page<BookDetails> queryBookBasePage(QueryVo vo){
		Page<BookDetails> page = new Page<BookDetails>();
		//每页数
		page.setSize(vo.getSize());
		if(null!=vo){
			//判断当前页
			if(null != vo.getPage()){
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage()-1)*vo.getSize());
			}
			//总条数
			page.setTotal(bookBaseDao.queryBookBasePage(vo));
			//结果集
			List<BookDetails> list = bookBaseDao.queryBookBaseListByQueryVo(vo);
			
			for (BookDetails bookDetails : list) {
				BookImages images =  bookBaseDao.selectBookImages(bookDetails.getId());
				BookRating  bookRating = bookBaseDao.selectBookRating(bookDetails.getId());
				bookDetails.setImages(images);
				bookDetails.setRating(bookRating);
			}
			page.setRows(list);
		}
		return page;
	}

	@Override
	public Base updateUserBook(BaseUserBook baseUserBook) {
		Base base = new Base();
		try {
			if(baseUserBook.getId() == null ||
				baseUserBook.getUid() == null ||
				baseUserBook.getNexusState()<0||
				baseUserBook.getNexusState()>2){
				throw new Exception("参数异常");
			}
			
			int i =  bookBaseDao.updateUserBook(baseUserBook);
			if(i == 0 & baseUserBook.getNexusState() != 0 ){
				i = bookBaseDao.insertUserBook(baseUserBook);
				base.setCode(0);
				base.setState("成功");
				base.setData("关联成功");
				return base;
			}
			if(i == 0 & baseUserBook.getNexusState() == 0){
				base.setCode(3);
				base.setState("成功");
				base.setData("你没有借书");
				return base;
			}
			
			base.setCode(1);
			base.setState("成功");
			base.setData("状态更新成功");
			return base;
		} catch (Exception e) {
			base.setCode(2);
			base.setState(e.getMessage());
			base.setData("状态更新失败");
			return base;
		}
	}

	@Override
	public BookDetails selectBookDetails(String id) {
			
			BookDetails book = bookBaseDao.selectBookDetails(id);
			book.setAuthor(bookBaseDao.selectBookAuthor(id));
			book.setRating(bookBaseDao.selectBookRating(id));
			book.setTags(bookBaseDao.selectBookTag(id));
			book.setImages(bookBaseDao.selectBookImages(id));
			
			return book;
		
	}

	public int selectBookCount(BaseUserBook baseUserBook){
		
		return bookBaseDao.selectUserBookCount(baseUserBook);
	}

	@Override
	public List<BookDetails> selectUserBookList(BaseUserBook baseUserBook) {
			List<BookDetails> bookList = new ArrayList<>(); 
		List<String> list = bookBaseDao.selectUserBookList(baseUserBook);
		for (String id : list) {
			
				BookDetails bookDetails =selectBookDetails(id);			
				bookList.add(bookDetails);
		}
		
		return bookList;
	}
	
	@Override
	public int insertUserComment(UserComment userComment) {
		return bookBaseDao.insertUserComment(userComment);
	}

	@Override
	public int selectUserCommentCount(String uid) {
		return	bookBaseDao.selectUserCommentCount(uid);
	}

	@Override
	public List<CommentItem> selectUserCommentList(String uid) {
		List<CommentItem> commentList = new ArrayList<CommentItem>();
		List<UserComment> userCommetnList =bookBaseDao. selectUserCommentList(uid);
	
		for (UserComment userComment : userCommetnList) {
			CommentItem commentItem = new CommentItem();
			BookDetails bookDetails = selectBookDetails(userComment.getId());
			commentItem.setBookDetails(bookDetails);
			commentItem.setUserComment(userComment);
			commentList.add(commentItem);
		}		
		return commentList;
	}

	@Override
	public List<BookCommentItem> setlectUserCommentBookList(String id) {
		List<BookCommentItem> list = new ArrayList<>();
		List<UserComment> BookCommentList = bookBaseDao.selectUserCommentBookList(id);
		
		for (UserComment userComment : BookCommentList) {
			BookCommentItem item = new BookCommentItem();
			BookCommentUserItem user = bookBaseDao.selectUserWeappByBookId(userComment.getUid());
			item.setBookCommentUserItem(user);
			item.setUserComment(userComment);
			list.add(item);
		}
		
		return list;
	}
}
