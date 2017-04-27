package rml.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import rml.controller.BookBaseController;
import rml.model.BaseBook;
import rml.model.BookAuthor;
import rml.model.BookCommentItem;
import rml.model.BookCommentUserItem;
import rml.model.BookDetails;
import rml.model.BookImages;
import rml.model.BookRating;
import rml.model.BookTags;
import rml.model.FavouriteList;
import rml.model.QueryVo;
import rml.model.UserBookFavourite;
import rml.model.UserBookState;
import rml.model.UserWeapp;
import rml.request.BaseUserBook;
import rml.request.UserComment;

public interface BookBaseDao {
	
	public List<BaseBook> queryBookBase();
	
	public int insertBookBase(BaseBook baseBook);
	
	//总条数
	public Integer queryBookBasePage(QueryVo vo);
	//结果集
	public List<BookDetails> queryBookBaseListByQueryVo(QueryVo vo);
	//通过Id找到书目
	public List<BaseBook> queryBookById(Integer id);
	//修改书目通过Id
	public void updateBookById(BaseBook basebook);
	//通过Id 删除客户
	public void deleteBookById(Integer id);
	
	//通过扫面isbn 添加图书信息
	public int insertBookDetails(BookDetails bookdetails);
	//插入作者表数据
	public int insertBookAuthor(BookAuthor bookAuthor);
	//插入图书封面表
	public int insertBookImages(BookImages bookImages);
	//插入图书标签
	public int insertBookTags(BookTags bookTags);
	//添豆瓣评分数据
	public int insertBookRating(BookRating bookRating);
	// 查询查入的数据
	public BookDetails selectBookDetails(String id);
	public String[] selectBookAuthor(String id);
	public BookImages  selectBookImages(String id);
	public List<BookTags>   selectBookTag(String id);
	public BookRating selectBookRating(String id);
	
	//改变书的借约状态
	public int changeBookState(UserBookState userBookState);
	public int changeBookStateToBase(UserBookState userBookState);
	//收藏，取消收藏
	public int changeBookFavourite(UserBookFavourite userBookFavourite);
	public int changeBookFavouriteToBase(UserBookFavourite userBookFavourite);
	//收藏(添加收藏)
	public int insertFavouriteList(UserBookFavourite userBookFavourite);
	//查询收藏列表
	public int selectFavouriteList(UserBookFavourite userBookFavourite);
	//删除收藏列表(取消收藏)
	public int deleteFavouriteList(UserBookFavourite userBookFavourite);
	
	//修改用户和书的关系表
	public int updateUserBook(BaseUserBook baseUserBook);
	//添加一条关系数据
	public int insertUserBook(BaseUserBook baseUserBook); 
	
	//根据用户id nexusState 查询表
	public int selectUserBookCount(BaseUserBook baseUserBook);
	
	//根据关系状态查询 书的id
	public List<String> selectUserBookList(BaseUserBook baseUserBook);
	
	//写书评
	public int insertUserComment(UserComment userComment);
	//用户书评数量
	public int selectUserCommentCount(String uid);
	//用户书评列表
	public List<UserComment> selectUserCommentList(String uid);
	//书的评论列表
	public List<UserComment> selectUserCommentBookList(String id);
	
	//查询评论用户
	BookCommentUserItem selectUserWeappByBookId(String  uid);
}
