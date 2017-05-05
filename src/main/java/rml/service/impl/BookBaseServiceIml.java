package rml.service.impl;

import java.awt.Component.BaselineResizeBehavior;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.BookBaseDao;
import rml.model.*;
import rml.request.BaseUserBook;
import rml.request.UserComment;
import rml.service.BookBaseService;
import rml.util.BaseUtils;
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
						BookNexus<BookAuthor> bookAuthorNexus = new BookNexus<BookAuthor>();
						bookAuthorNexus.setT(bookAuthor);
						bookAuthorNexus.setId(bookDetails.getId());
						bookBaseDao.insertBookAuthor(bookAuthor);
					}
					BookRating bookRating = bookDetails.getRating();
					//bookRating.setId(bookDetails.getId());
					BookNexus<BookRating> bookRatingBookNexus= new BookNexus<BookRating>();
					bookRatingBookNexus.setId(bookDetails.getId());
				//	bookRatingBookNexus.setT(bookRating.getAverage());
					bookBaseDao.insertBookRating(bookDetails.getId(),bookRating.getAverage());
					
					List<BookTags> bookTags = bookDetails.getTags();
					for (BookTags tag : bookTags) {
						bookBaseDao.insertBookTags(bookDetails.getId(),tag.getCount(),
								tag.getName(),tag.getTitle());
					} 
					BookImages images = bookDetails.getImages();
					//images.setId(bookDetails.getId());
					bookBaseDao.insertBookImages(images.getSmall(),images.getLarge(),images.getMedium(),bookDetails.getId());
					book = bookBaseDao.selectBookDetails(bookDetails.getId());
					book.setAuthor(bookBaseDao.selectBookAuthor(bookDetails.getId()));
					book.setRating(bookBaseDao.selectBookRating(bookDetails.getId()));
					book.setTags(bookBaseDao.selectBookTag(bookDetails.getId()));
					book.setImages(bookBaseDao.selectBookImages(bookDetails.getId()));
					book.setBorrowState(getUserBookState(bookDetails.getId()));
					base.setCode(0);
					base.setState("录入成功");
					base.setData(book);		
					return base;
			}
				base.setCode(1);
				base.setState("已经录入");
				book.setAuthor(bookBaseDao.selectBookAuthor(bookDetails.getId()));
				book.setRating(bookBaseDao.selectBookRating(bookDetails.getId()));
				book.setTags(bookBaseDao.selectBookTag(bookDetails.getId()));
				book.setImages(bookBaseDao.selectBookImages(bookDetails.getId()));
				book.setBorrowState(getUserBookState(bookDetails.getId()));
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
				int state = getUserBookState(bookDetails.getId());
				bookDetails.setImages(images);
				bookDetails.setRating(bookRating);
				bookDetails.setBorrowState(state);
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
				baseUserBook.getUid() == null
				){
				throw new Exception("参数异常");
			}

				List<BaseUserBook> bookNexus =  bookBaseDao.selectuserBookNexus(baseUserBook.getId());
				for(BaseUserBook userBook : bookNexus){
					if(userBook.getNexusState() == 1){
						if(baseUserBook.getNexusState() ==1){
							//base.setData("这本书已经借出去了");
							base.setData("这本书已经被其他小伙伴借走了");
							base.setCode(0);
							base.setState("");
							return base;
						}
					}
				}
			BaseUserBook baseUserBookres = bookBaseDao.selectUserBookNexusByIdUid(baseUserBook);
			if(baseUserBookres == null) {

				if (baseUserBook.getNexusState() == 1) {
					int i =	bookBaseDao.insertUserBook(baseUserBook);
					if(i>0){
						base.setData("借阅成功");
					}else{
						throw  new Exception("借书异常");
					}
				}
				if (baseUserBook.getNexusState() == 2) {
					int i = bookBaseDao.insertUserBook(baseUserBook);
					if(i>0){

						base.setData("想读成功");
					}else{
						throw  new Exception("想读异常");
					}

				}
				if (baseUserBook.getNexusState() == -1) {
					base.setData("您都没有借，拿什么去还");
				}
				base.setCode(0);
				base.setState("成功");
				return  base;
				}

				if(baseUserBookres != null){
					if(baseUserBookres.getNexusState() ==1){//在读
						if(baseUserBook.getNexusState() ==1||baseUserBook.getNexusState() ==2){
							base.setData("你已经在读此书了");
						}
						if(baseUserBook.getNexusState() == -1){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){
								base.setData("归还成功");
							}else{
								throw  new Exception("归还失败");
							}
						}
					}

					if(baseUserBookres.getNexusState() == -1){
						if(baseUserBook.getNexusState() == -1){
							base.setData("这本书已经归还过了");
						}
						if(baseUserBook.getNexusState() == 1){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){

								base.setData("借阅成功");
							}else{
								throw  new Exception("借阅失败");

							}
						}
						if(baseUserBook.getNexusState() == 2){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){

								base.setData("添加想读成功");
							}else{
								throw  new Exception("添加想读失败");
							}
						}
					}

					if(baseUserBookres.getNexusState() == 2){
						if(baseUserBook.getNexusState() == 2){
							base.setData("这本书已经想读了");
						}
						if(baseUserBook.getNexusState() == -1){
							base.setData("您都没有借，拿什么去还");
						}
						if(baseUserBook.getNexusState() == 1){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){
								base.setData("想读到借书成功");
							}else{
								throw  new Exception("想读到借书失败");
							}
						}
					}
				}
			base.setCode(0);
			base.setState("成功");
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
			int state = getUserBookState(id);
			book.setBorrowState(state);
			
			return book;
		
	}

	private int getUserBookState(String id) {
		List<BaseUserBook> userNexus = bookBaseDao.selectuserBookNexus(id);
		int state = -1;
		if (userNexus.size() == 0){
			state = 0;
		}else{
			for (BaseUserBook userBookState : userNexus){
				if(userBookState.getNexusState() == 1){
					state = 1;
					break;
				}
				state = 0;
			}
		}
		return state;
	}

	public UserBookCount selectBookCount(BaseUserBook baseUserBook){
		//在读
		baseUserBook.setNexusState(1);
		int reading = bookBaseDao.selectUserBookCount(baseUserBook);
		//已读
		baseUserBook.setNexusState(0);
		int readed = bookBaseDao.selectUserBookCount(baseUserBook);
		//想读
		baseUserBook.setNexusState(2);
		 int wantRead = bookBaseDao.selectUserBookCount(baseUserBook);
		 UserBookCount userBookCount = new UserBookCount();

		 userBookCount.setReading(reading);
		 userBookCount.setReaded(readed);
		 userBookCount.setWantRead(wantRead);
		return userBookCount;
	}

	@Override
	public userBookListResponse selectUserBookList(BaseUserBook baseUserBook) {
			List<UserBookTime> readingList = new ArrayList<UserBookTime>();
			List<UserBookTime> wantReadList = new ArrayList<UserBookTime>();
			List<UserBookTime> readedList  = new ArrayList<UserBookTime>();

			//在读书的id
			baseUserBook.setNexusState(1);
		    //List<String> listReading = bookBaseDao.selectUserBookList(baseUserBook);
			List<selectUserBookTime> listReading = bookBaseDao.selectUserBookList(baseUserBook);
			//想读书的id
			baseUserBook.setNexusState(2);
			//List<String> listWantRead = bookBaseDao.selectUserBookList(baseUserBook);
			List<selectUserBookTime> listWantRead = bookBaseDao.selectUserBookList(baseUserBook);
			//还书的书的id
			baseUserBook.setNexusState(0);
			//List<String> listReaded = bookBaseDao.selectUserBookList(baseUserBook);
			List<selectUserBookTime> listReaded = bookBaseDao.selectUserBookList(baseUserBook);

		for (selectUserBookTime id : listReading) {
			UserBookTime userBookItem = new UserBookTime();
			/*	BookDetails bookDetails =selectBookDetails(id.getBookId());
				Long time  = id.getCreateTime();*/
				userBookItem.setBookDetails(selectBookDetails(id.getBookId()));
				userBookItem.setCreatTime(id.getCreateTime());
				readingList.add(userBookItem);
		}

		for (selectUserBookTime id: listWantRead){
			/*BookDetails bookDetails =selectBookDetails(id);
			wantReadList.add(bookDetails);*/
			UserBookTime userBookItem = new UserBookTime();
			userBookItem.setBookDetails(selectBookDetails(id.getBookId()));
			userBookItem.setCreatTime(id.getCreateTime());
			wantReadList.add(userBookItem);
		}

		for(selectUserBookTime id: listReaded){

			/*BookDetails bookDetails =selectBookDetails(id);
			readedList.add(bookDetails);*/

			UserBookTime userBookItem = new UserBookTime();
			userBookItem.setBookDetails(selectBookDetails(id.getBookId()));
			userBookItem.setCreatTime(id.getCreateTime());
			readedList.add(userBookItem);
		}
		userBookListResponse listResponse = new userBookListResponse();
		listResponse.setReading(readingList);
		listResponse.setReaded(readedList);
		listResponse.setWantRead(wantReadList);

		return listResponse;
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
	public Page<UserCommentResponse> selectUserCommentList(QueryUserComment vo) {
		Page<UserCommentResponse> page = new Page<UserCommentResponse>();
		page.setTotal(bookBaseDao.selectuserBookCountByUid(vo));
		List<UserCommentResponse> commentList = new ArrayList<UserCommentResponse>();
		List<UserComment> userCommetnList =bookBaseDao. selectUserCommentList(vo.getUid());
	
		for (UserComment userComment : userCommetnList) {

			UserCommentResponse userCommentResponse = new UserCommentResponse();
			BookDetails bookDetails = selectBookDetails(userComment.getId());
			userCommentResponse.setImage(bookDetails.getImages().getSmall());
			userCommentResponse.setName(bookDetails.getTitle());
			userCommentResponse.setContent(userComment.getContent());
			userCommentResponse.setCommentTime(userComment.getCommentTime());

			commentList.add(userCommentResponse);
		}
		page.setRows(commentList);
		return page;
	}

	@Override
	public List<BookCommentItem> setlectUserCommentBookList(String id) {
		List<BookCommentItem> list = new ArrayList<BookCommentItem>();
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

	@Override
	public Page<UserCommentResponse> setlectUserCommentBookList(QueryBookComment vo) {
			Page<UserCommentResponse> page = new Page<UserCommentResponse>();
			List<UserCommentResponse> list = new ArrayList<UserCommentResponse>();
			//每页数
			page.setSize(vo.getSize());
			if(vo.getPage()!= null){
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage()-1)*vo.getSize());
			}
			//总条数
			page.setTotal(bookBaseDao.selectuserBookCountByid(vo));
			//结果集
			//bookBaseDao.selectUserBookListById(vo);
		List<UserComment> BookCommentList = bookBaseDao.selectUserBookListById(vo);
		for (UserComment userComment : BookCommentList) {
			//BookCommentItem item = new BookCommentItem();
			BookCommentUserItem user = bookBaseDao.selectUserWeappByBookId(userComment.getUid());

			UserCommentResponse userCommentResponse = new UserCommentResponse();
			userCommentResponse.setContent(userComment.getContent());
			userCommentResponse.setCommentTime(userComment.getCommentTime());
			/*userCommentResponse.setImage(user.getHeadimgurl());
			userCommentResponse.setName(user.getNickName());
*/
			/*item.setBookCommentUserItem(user);
			item.setUserComment(userComment);*/
			list.add(userCommentResponse);
		}

		page.setRows(list);

		return page;
	}

	//查询
	@Override
 	public Page<SearchResultBean> search(String searchContent) {

		Page<SearchResultBean> page = new Page<SearchResultBean>();

		//isbn查询
		List<SearchResultBean> resultList = new ArrayList<SearchResultBean>();
		if (BaseUtils.isNumeric(searchContent) && searchContent.length() >= 10) {

			resultList.addAll(bookBaseDao.searchBookByISBN_dim(searchContent));

		} else {
			//作者和书名查询
			ArrayList<SearchResultBean> list = new ArrayList<SearchResultBean>();
			list.addAll(bookBaseDao.searchBookByBookName_dim(searchContent));
			List<String> bookIDList = bookBaseDao.searchBookIDbyAuthor_dim(searchContent);
			for (String bookID : bookIDList) {
				list.add(bookBaseDao.searchBookByID(bookID));
			}

			HashSet<SearchResultBean> setList = new HashSet<SearchResultBean>();
			setList.addAll(list);
			resultList.addAll(setList);
		}

		//根据ID得到书的作者和封面
		for (SearchResultBean bookDetails : resultList) {
			bookDetails.setAuthor(bookBaseDao.searchAuthorByID(bookDetails.getId()));
			bookDetails.setImages(bookBaseDao.searchImageByID(bookDetails.getId()));
		}

		page.setRows(resultList);
		page.setTotal(resultList.size());
		return page;

	}
}
