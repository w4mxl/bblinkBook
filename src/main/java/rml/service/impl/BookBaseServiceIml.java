package rml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rml.dao.BookBaseDao;
import rml.model.*;
import rml.request.BaseNexus;
import rml.request.BaseUserBook;
import rml.request.UserComment;
import rml.service.BookBaseService;
import rml.util.BaseUtils;
import rml.util.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Transactional
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
							Thread.sleep(1000);
							base.setData("这本书已经被其他小伙伴借走了");
							base.setCode(1);
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
						base.setCode(0);
						base.setState("成功");

					}else{
						throw  new Exception("借书异常");
					}
				}
				if (baseUserBook.getNexusState() == 2) {
					int i = bookBaseDao.insertUserBook(baseUserBook);
					if(i>0){

						base.setData("添加成功");
						base.setCode(0);
						base.setState("成功");

					}else{
						throw  new Exception("想读异常");
					}

				}
				if (baseUserBook.getNexusState() == -1) {
					base.setData("您都没有借，没法还哦");
					base.setCode(1);
					base.setState("成功");

				}
				/*base.setCode(0);
				base.setState("成功");
				return  base;*/
				}

				if(baseUserBookres != null){
					if(baseUserBookres.getNexusState() ==1){//在读
						if(baseUserBook.getNexusState() ==1||baseUserBook.getNexusState() ==2){
							base.setData("你之前已经借走此书了");
							base.setCode(1);
							base.setState("成功");

						}
						if(baseUserBook.getNexusState() == -1){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){
								base.setData("归还成功");
								base.setCode(0);
								base.setState("成功");

							}else{
								throw  new Exception("归还失败");
							}
						}
					}

					if(baseUserBookres.getNexusState() == -1){
						if(baseUserBook.getNexusState() == -1){
							base.setData("您都没有借，没法还哦");
							base.setCode(1);
							base.setState("成功");

						}
						if(baseUserBook.getNexusState() == 1){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){

								base.setData("借阅成功");
								base.setCode(0);
								base.setState("成功");

							}else{
								throw  new Exception("借阅失败");

							}
						}
						if(baseUserBook.getNexusState() == 2){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){

								base.setData("添加成功");
								base.setCode(0);
								base.setState("成功");

							}else{
								throw  new Exception("添加想读失败");
							}
						}
					}

					if(baseUserBookres.getNexusState() == 2){
						if(baseUserBook.getNexusState() == 2){
							base.setData("这本书标记过想读了");
							base.setCode(1);
							base.setState("成功");

						}
						if(baseUserBook.getNexusState() == -1){
							base.setData("您都没有借，没法还哦");
							base.setCode(1);
							base.setState("成功");

						}
						if(baseUserBook.getNexusState() == 1){
							int i =  bookBaseDao.updateUserBook(baseUserBook);
							if(i>0){
								base.setData("借阅成功");
								base.setCode(0);
								base.setState("成功");

							}else{
								throw  new Exception("想读到借书失败");
							}
						}
					}
				}
			/*base.setCode(0);
			base.setState("成功");
			return base;*/
		} catch (Exception e) {
			base.setCode(2);
			base.setState(e.getMessage());
			base.setData("状态更新失败");

		}
		return base;
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
	public Page<userBookListResponse> selectUserBookList(BaseUserBook baseUserBook) {
			List<userBookListResponse> readList = new ArrayList<userBookListResponse>();

			Page<userBookListResponse> page = new Page<userBookListResponse>();
			page.setTotal(bookBaseDao.selectUserBookCount(baseUserBook));
		if(baseUserBook.getPage()!= null){
			page.setPage(baseUserBook.getPage());
			baseUserBook.setStartRow((baseUserBook.getPage()-1)*baseUserBook.getSize());
		}
			List<selectUserBookTime> list =bookBaseDao.selectUserBookList(baseUserBook);
			if(list != null){
				for (selectUserBookTime id : list) {
					userBookListResponse userbookListResponse = new userBookListResponse();
					BookDetails bookDetails = selectBookDetails(id.getBookId());
					userbookListResponse.setTitle(bookDetails.getTitle());
					userbookListResponse.setAuthor(bookDetails.getAuthor());
					userbookListResponse.setPublisher(bookDetails.getPublisher());
					userbookListResponse.setPubdate(bookDetails.getPubdate());
					userbookListResponse.setRating(bookDetails.getRating().getAverage());
					userbookListResponse.setCreateTime(id.getCreateTime());
					userbookListResponse.setImages(bookDetails.getImages());
					userbookListResponse.setId(id.getBookId());
					readList.add(userbookListResponse);
				}
				page.setRows(readList);
			}

		return page;
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
		if(vo.getPage()!= null){
			page.setPage(vo.getPage());
			vo.setStartRow((vo.getPage()-1)*vo.getSize());
		}

		List<UserComment> userCommetnList =bookBaseDao. selectUserCommentList(vo);


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
			System.out.print("++++"+user.toString());

			UserCommentResponse userCommentResponse = new UserCommentResponse();
			userCommentResponse.setName(user.getNickName());
			userCommentResponse.setImage(user.getHeadimgurl());
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
 	public Page<SearchResultBean> search(QueryContent queryContent) {

		Page<SearchResultBean> page = new Page<SearchResultBean>();

		//每页数
		page.setSize(queryContent.getSize());
		if(queryContent.getPage()!= null){
			page.setPage(queryContent.getPage());
			queryContent.setStartRow((queryContent.getPage()-1)*queryContent.getSize());
		}
		//总条数
		//page.setTotal(bookBaseDao.selectuBookCountBy(queryContent));
		//isbn查询
		List<SearchResultBean> resultList = new ArrayList<SearchResultBean>();
		if (BaseUtils.isNumeric(queryContent.getSearchContent()) && queryContent.getSearchContent().length() >= 10) {

			resultList.addAll(bookBaseDao.searchBookByISBN_dim(queryContent.getSearchContent()));

		} else {
			//作者和书名查询
			ArrayList<SearchResultBean> list = new ArrayList<SearchResultBean>();
			list.addAll(bookBaseDao.searchBookByBookName_dim(queryContent.getSearchContent()));
			List<String> bookIDList = bookBaseDao.searchBookIDbyAuthor_dim(queryContent.getSearchContent());
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

	@Override
	public BookDetails selectBook(String isbn13) {
		return bookBaseDao.selectbook(isbn13);
	}

	@Override
	public BaseNexusResponse selectUserBookNexus(BaseNexus baseNexus) {
		return bookBaseDao.selectuserBookNexusbyIdTodetail(baseNexus);
	}
}
