package rml.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.model.*;
import rml.request.BaseUserBook;
import rml.request.UserComment;
import rml.service.BookBaseService;
import rml.util.Page;

import java.util.List;

/*
 * 书目管理
*/
@Controller
public class BookBaseController {
	@Autowired
	private BookBaseService bookBaseService;
	
	/*@RequestMapping(value = "/bookbase/list")
	@ResponseBody
	public Object listBookBase(HttpServletRequest request){
		List<BaseBook> list = bookBaseService.queryBookBase();
		return list;
	}
	@RequestMapping(value = "/bookbase/insert")
	@ResponseBody
	public String insertBookBase(){
		for (int i=0;i<5;i++) {
			BaseBook book1 =new BaseBook();
			book1.setBookId("00000"+i);
			book1.setBookName("bookname00"+i);
			book1.setBookRate("好书，好书"+i);
			book1.setImageUrl("www.baidu00"+i);
			bookBaseService.insertBookBase(book1);
		}
		return "OK";
	}
	@RequestMapping(value = "/books")
	@ResponseBody
	public Object queryBookBasePage(QueryVo vo){
		List<BaseBook> baseBookPage = bookBaseService.queryBookBasePage(vo).getRows();
		Base base = new Base();
		base.setCode(0);
		base.setMessage("成功");
		base.setState("成功");
		base.setData(baseBookPage);
	return	base;
		
	}
	@RequestMapping(value = "/insertBook" , method = RequestMethod.POST)
	@ResponseBody
	public Object insertBookDetails(@RequestBody BookDetails bookDetails){
		BookDetails book =bookBaseService.selectBookDetails(bookDetails.getId());
		if(book !=null){
			Base base = new Base();
			base.setCode(0);
			base.setMessage("图书库已经收录");
			base.setState("加入失败");
			base.setData(book);
			return base; 
		}
			//插入书的详情页表
			bookBaseService.insertBookDetails(bookDetails);
			//插入书的作者表
			BookAuthor bookAuthor =new BookAuthor();
			bookAuthor.setId(bookDetails.getId());
			bookAuthor.setAuthor(bookDetails.getAuthor().get(0));
			bookBaseService.insertBookAuthor(bookAuthor);
			//插入书的封面表
			BookImages bookImages = bookDetails.getImages();
			bookImages.setId(bookDetails.getId());
			bookBaseService.insertBookImages(bookImages); 
			//插入书的标签表
			List<BookTags> tags = bookDetails.getTags();
			for (BookTags bookTags : tags) {
				bookTags.setId(bookDetails.getId());
				bookBaseService.insertBookTags(bookTags);
			}
			//插入书的豆瓣评分表
			BookRating bookRating = bookDetails.getRating();
			bookRating.setId(bookDetails.getId());
			bookBaseService.insertBookRating(bookRating);
			//插入书的基本表
			BaseBook bookBase = new BaseBook();
			bookBase.setBookId(bookDetails.getId());
			bookBase.setBookName(bookDetails.getTitle());
			bookBase.setImageUrl(bookDetails.getImage());
			bookBase.setState(bookDetails.getState());
			bookBase.setFavourite(bookDetails.getFavourite());
			bookBaseService.insertBookBase(bookBase);
			//返回书的详细表
			Base base = new Base();
			base.setCode(0);
			base.setMessage("插入图书表成功");
			base.setState("成功");
			base.setData(bookBaseService.selectBookDetails(bookDetails.getId()));
			return base;
	}
	//通过ID进入详情页
	@RequestMapping(value ="/selectBook")
	@ResponseBody
	public Object selectBookDetailsById(String id){
		Base base = new Base();
		base.setCode(0);
		base.setMessage("成功");
		base.setState("成功");
		base.setData(bookBaseService.selectBookDetails(id)==null? "没有收录这本书":bookBaseService.selectBookDetails(id));
		return base;
	}
	//借书，还书
	@RequestMapping(value = "/bookState")
	@ResponseBody
	public Object changeBookState(UserBookState userBookState){
		System.out.println("111111"+userBookState.toString());
		Base base = new Base();
		base.setCode(0);
		base.setMessage("成功");
		base.setState("成功");
		base.setData(bookBaseService.changeBookState(userBookState)>0? "更新状态成功":"更新状态失败");
		return base;
	}
	//收藏接口
	@RequestMapping(value = "/bookFavourite")
	@ResponseBody
	public Object changeBookFavourite(UserBookFavourite userBookFavourite){
		Base base = new Base();
		base.setCode(0);
		base.setMessage("成功");
		base.setState("成功");
		base.setData(bookBaseService.changeBookFavourite(userBookFavourite)>0 ?"收藏成功":"取消收藏");
		return base;
	}
	//收藏列表
	@RequestMapping(value = "/favourite/list")
	@ResponseBody
	public Object selectFavouriteList(UserBookFavourite userBookFavourite){
		
		bookBaseService.insertFavouriteList(userBookFavourite);
		
		Base base = new Base();
		base.setCode(0);
		base.setMessage("成功");
		base.setState("成功");
		return base;
	}
	//书和用户的关系表 (0 还书 1 借书 2 想读)
	@RequestMapping(value = "/userBook")
	@ResponseBody
	public String changeUserBook(BaseUserBook baseUserBook){
		return "ddddafd"+bookBaseService.changeUserBook(baseUserBook);
	}
	//在读数量
	@RequestMapping(value = "/userBook/count")
	@ResponseBody
	public Object selectUseBookCount(BaseUserBook baseUserBook){
		return "列表"+bookBaseService.selectUseBookCount(baseUserBook);
	}	
	//在读/想读/已读/列表
	@RequestMapping(value = "/userBook/list")
	@ResponseBody
	public Object selectUserBookList(BaseUserBook baseUserBook){
		return "333"+bookBaseService.selectUserBookList(baseUserBook);
	}
	
	//写书评
	
	@RequestMapping(value = "/userComment/write")
	@ResponseBody
	public Object writeUserComment(UserComment userComment){
		
		return "write"+bookBaseService.insertUserComment(userComment);
	}
	
	//评论列表数量
	@RequestMapping(value = "/userComment/count")
	@ResponseBody
	public Object selectUserCommentCount(String uid){
		
		return "书评数量"+bookBaseService.selectUserCommentCount(uid);
	}
	
	@RequestMapping(value = "/userComment/list")
	@ResponseBody
	public Object selectUserCommentList(String uid){
		return "书评列表"+bookBaseService.serlectUserCommentList(uid);
	}
	*/
	
//	----------------------------重新整理数据库-------------------------------------------------
	
	//录入接口
	
	@RequestMapping(value = "/book/input", method = RequestMethod.POST )
	@ResponseBody
	public Base inputBookDetails( String book ){

		BookDetails bookDetails = JSON.parseObject(book,BookDetails.class);

		return bookBaseService.inputBookDetails (bookDetails);
	}
	
	//图书列表接口
	
	@RequestMapping(value = "/book/select")
	@ResponseBody
	public BasePage selectBook(QueryVo vo){
		BasePage basepage = new BasePage();
		try {

			Page<BookDetails> page =  bookBaseService.queryBookBasePage(vo);
		
			//List<BookDetails> baseBookPage = bookBaseService.queryBookBasePage(vo).getRows();
			basepage.setCode(0);
			basepage.setState("成功");
			basepage.setPageNum(vo.getPage());
			basepage.setPageSize(vo.getSize());
			basepage.setData(page.getRows());
			basepage.setTotal(page.getTotal());
			return basepage;
		} catch (Exception e) {
			// TODO: handle exception
			basepage.setCode(2);
			basepage.setState(e.getMessage());
			basepage.setData(null);
			return basepage;
		}
		
	}
	
	
	//图书详情页
	@RequestMapping (value = "/book/bookDetails")
	@ResponseBody
	public Base selectBookDetials(String id){
		Base base = new Base();
		try {
			BookDetails bookDetails =  bookBaseService.selectBookDetails(id);
			base.setCode(0);
			base.setState("成功");
			base.setData(bookDetails);
			return base;
		} catch (Exception e) {

			base.setCode(2);
			base.setState(e.getMessage());
			base.setData(null);
			return base;
		}
		
	} 
	
	//用户图书关系表(0 还书 （已读）1 借书 （在读） 2 想读 （想读）)
	
	@RequestMapping(value = "/userBook/nexus")
	@ResponseBody
	public Base updateUserBook(BaseUserBook baseUserBook){
		baseUserBook.setNexusTime(System.currentTimeMillis());	
		return bookBaseService.updateUserBook(baseUserBook);
	}
	
	// 状态关系数量
	
	@RequestMapping(value = "/userBook/count")
	@ResponseBody
	public Base selectUserBookCount(BaseUserBook baseUserBook){
		Base base = new Base();
		
		try {
			if(	baseUserBook.getUid() == null || 
				baseUserBook .getNexusState() < 0 || 
				baseUserBook.getNexusState() >2){
				throw new Exception("参数异常");
			}
			
			int count = bookBaseService.selectBookCount(baseUserBook);
			base.setCode(0);
			base.setState("成功");
			base.setData(count);
			return base;
		} catch (Exception e) {

			base.setCode(2);
			base.setState(e.getMessage());
			base.setData(null);
			return base;
		}
		
	} 
	
	//关系列表（在读 ，想读， 已读） 
	
	public Base selectUserBookList(BaseUserBook baseUserBook){
		Base base = new Base();
		try {
			if(	baseUserBook.getId() == null||
				baseUserBook.getNexusState()<0||
				baseUserBook.getNexusState()>2){
				throw new Exception("参数异常");
			}
			
		List<BookDetails>	list = bookBaseService.selectUserBookList(baseUserBook);
			base.setCode(0);
			base.setState("成功");
			base.setData(list);
			return base;
		} catch (Exception e) {
			base.setCode(2);
			base.setState(e.getMessage());
			base.setData(null);
			return base;
		}
	}
	
	//写书评
	@RequestMapping(value = "/userComment/insert")
	@ResponseBody
	public Base insertUserComment(UserComment userComment){
		System.out.println("-----"+userComment.toString());
		
		Base base = new Base();
		try {
			if( userComment.getId() == null||
				userComment.getUid() == null||
				userComment.getContent() == null){
				throw new Exception("参数异常");
			}
			long now = System.currentTimeMillis();
			userComment.setCommentTime(now);
			int i = bookBaseService.insertUserComment(userComment);
			base.setCode(0);
			base.setState("成功");
			base.setData(i);
			return base;
		} catch (Exception e) {
			base.setCode(2);
			base.setState(e.getMessage());
			base.setData(null);
			return base;
		}
		
	}
	
	//书评数量
	
	@RequestMapping(value = "/userComment/count")
	@ResponseBody
	public Base selectUserCommentCount(String uid){
		Base base = new Base();
		try {
			if( uid == null	){
				throw new Exception("参数异常");
			}
			
		int i = bookBaseService.selectUserCommentCount(uid);
		base.setCode(0);
		base.setState("成功");
		base.setData(i);
		return base;
		} catch (Exception e) {
			base.setCode(2);
			base.setState(e.getMessage());
			base.setData(null);
			return base;
		}
		
	}
	
	//用户书评列表
	@RequestMapping(value = "/userComment/list")
	@ResponseBody
	public Base selectUserCommentList(String uid){
		Base base = new Base();
		try {
			if(uid == null){
				throw new Exception("参数异常");
			}
		List<CommentItem> list = bookBaseService.selectUserCommentList(uid);
			base.setCode(0);
			base.setState("成功");
			base.setData(list);
			return base;
		} catch (Exception e) {
			base.setCode(2);
			base.setState(e.getMessage());
			base.setData(null);
			return base;
		}
	}
	
	//图书评论 列表
	
	@RequestMapping(value = "/userComment/booklist")
	@ResponseBody
	
	public Base selectUserCommentBookList(String id){
		Base base = new Base();
		try {			
		List<BookCommentItem> list = bookBaseService.setlectUserCommentBookList(id);
		base.setCode(0);
		base.setState("成功");
		base.setData(list);
		return base;
		} catch (Exception e) {
			base.setCode(0);
			base.setState("失败");
			base.setData(null);
			return base;
		}
	}
	
}
